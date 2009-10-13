/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.DAO.AgentDAO;
import com.ptit.travel.beans.AgentBean;
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;

public class UserAgent extends Agent {
    // The title of the book to buy

    private AgentDAO agentDAO = new AgentDAO();
    ArrayList<AgentBean> recievers;
    private String transport;
    private String hotel;
    // The list of known seller agents
    private AID[] transportAgents,  hotelAgents;

    // Put agent initializations here
    protected void setup() {
        // Printout a welcome message
        System.out.println("Traveller-agent " + getAID().getLocalName() + " want to hire:");

        // Get the title of the book to buy as a start-up argument
        Object[] args = getArguments();
        if (args != null && args.length > 1) {
            transport = (String) args[0];
            hotel = (String) args[1];
            System.out.println("Transport: " + transport + "\nRank of hotel: " + hotel);

            this.setEnabledO2ACommunication(true, 0);
            // Add a TickerBehaviour that schedules a request to seller agents every minute
            addBehaviour(new TickerBehaviour(this, 60000) {
                
                protected void onTick() {  
                    AgentBean agentBean = (AgentBean) myAgent.getO2AObject();
                    agentBean.setHost("192.168.1.111");
                    String prefix = "";
                    String agentType = "Hotel";
                    recievers = agentDAO.getAgents(prefix, agentType); 
                    myAgent.addBehaviour(new RequestPerformer());
                }
            });
        } else {
            // Make the agent terminate
            System.out.println("No target service title specified");
            doDelete();
        }
    }

    // Put agent clean-up operations here
    protected void takeDown() {
        // Printout a dismissal message
        System.out.println("Traveller-agent " + getAID().getName() + " terminating.");
    }

    /**
    Inner class RequestPerformer.
    This is the behaviour used by Book-buyer agents to request seller 
    agents the target book.
     */
    private class RequestPerformer extends Behaviour {

        private AID bestTransport; // The agent who provides the best offer 

        private int bestTransportPrice;  // The best offered price

        private int repliesTransportCnt = 0; // The counter of replies from seller agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;

        public void action() {
            switch (step) {
                case 0:
                    // Send the cfp to all recievers
                    ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                    AgentBean agent;
                    for (int i = 0; i < recievers.size(); ++i) {
                        agent = recievers.get(i);
                        cfp.addReceiver(new AID(agent.getName(), false));
                    }
                    // get content and set content of message
                    String content = agentDAO.readMessage("mesg/askAvailable.rdf");
                    cfp.setContent(content);                    

                    cfp.setReplyWith("cfpTransport" + System.currentTimeMillis()); // Unique value

                    myAgent.send(cfp);
                    // Prepare the template to get proposals
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("transport-trade"),
                            MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
                    step = 1;
                    break;
                case 1:
                    // Receive all proposals/refusals from seller agents
                    ACLMessage reply = myAgent.receive(mt);
                    if (reply != null) {
                        // Reply received
                        if (reply.getPerformative() == ACLMessage.PROPOSE) {
                            // This is an offer 
                            int priceTransport = Integer.parseInt(reply.getContent());
                            if (bestTransport == null || priceTransport < bestTransportPrice) {
                                // This is the best offer at present
                                bestTransportPrice = priceTransport;
                                bestTransport = reply.getSender();
                            }
                        }
                        repliesTransportCnt++;

                        if (repliesTransportCnt >= transportAgents.length) {
                            // We received all replies
                            step = 2;
                        }
                    } else {
                        block();

                    }

                    break;
                case 2:
                    // Send the purchase order to the seller that provided the best offer
                    ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
                    order.addReceiver(bestTransport);
                    order.setContent(transport);
                    order.setConversationId("transport-trade");
                    order.setReplyWith("orderTransport" + System.currentTimeMillis());
                    myAgent.send(order);
                    // Prepare the template to get the purchase order reply
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("transport-trade"),
                            MessageTemplate.MatchInReplyTo(order.getReplyWith()));

                    step = 3;
                    break;
                case 3:
                    // Receive the purchase order reply
                    reply = myAgent.receive(mt);
                    if (reply != null) {
                        // Purchase order reply received
                        if (reply.getPerformative() == ACLMessage.INFORM) {
                            // Purchase successful. We can terminate
                            System.out.println("Transpord: " + transport + " provided by agent " +
                                    reply.getSender().getLocalName());
                            System.out.println("Price = " + bestTransportPrice);
                        //myAgent.doDelete();
                        } else {
                            System.out.println("Attempt failed: requested transport already hired.");
                        }

                        step = 4;
                    } else {
                        block();
                    }
                    break;
            }
        }

        public boolean done() {
            if (step == 2 && bestTransport == null) {
                System.out.println(step + "Attempt failed: " + transport + " not available for hire");
            }
            return ((step == 2 && bestTransport == null) || step == 4);
        }
    }  // End of inner class RequestPerformer

}
