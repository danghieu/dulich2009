/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent.hotel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;
import com.ptit.travel.agent.onto.*;

import com.hp.hpl.jena.rdf.model.Resource;

import com.ptit.travel.beans.SerializableBean;
import com.ptit.travel.beans.hotel.HotelAvailability;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hotel Agent runs also XML-RPC server on port 8000 and public methods can be
 * called by outside Behaviors:
 * 
 * 
 * 
 * Each behavior executed by two methods and one inner class. For example
 * 
 * @version 0.5
 */
public class HotelAgent extends Agent {

    private static Logger log = Logger.getLogger(HotelAgent.class.getName());
    private Memory mem;

    // message queue of agent contains every satisfactory replied messages
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    protected void setup() {

        Message.register(this, this.getName());

        mem = new Memory(
                "E:/Develop/Netbean/Travel/config/HotelAgent.properties", this.getLocalName());

        addBehaviour(new TickerBehaviour(this, 30000) {
            protected void onTick() {
                HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages();
                addBehaviour(hrmBehaviour);
            }
        });
    }

    // this method sends result to AskAgent
    private void informAskAgent(Resource r) {
        this.addBehaviour(new BehaviourSendResult(this, r));
    }

    /**
     * 
     *Behaviour is used with AnswerAgent and it sends RDF Resources to other
     * Agents
     * 
     * @author D05CNPM
     */
    class BehaviourSendResult extends OneShotBehaviour {

        private Resource r = null;
        private Agent a;

        public BehaviourSendResult(Agent _a, Resource _r) {
            super(_a);
            a = _a;
            r = _r;
        }

        public void action() {
            System.out.println("sends RDF Resources to UserAgent");
            send(Message.createInformMessage(a, "UserAgent", r));
        }
    } // End class BehaviourSendResult


    /**
     * 
     *This behaviour is used by HotelAgent to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     * 
     */
    class HandleRecivedMessages extends SimpleBehaviour {

        private boolean finished = false;

        public HandleRecivedMessages() {
        }

        public void action() {
            log.info("=== HOTELAGENT Ready to handle received message ");
            synchronized (this) {
                ACLMessage msg = receive();
                if (msg != null) {     
                    
                    try {                        
                        String content = msg.getContent();
                        log.info("=== [HotelAgent] received reply message " + content);
                    switch (msg.getPerformative()) {
                        case ACLMessage.QUERY_REF:
                            break;

                        case ACLMessage.INFORM:

                            // action of message <-> protocol of ACL
                            String protocol = msg.getProtocol();
                            if (Protocol.HOTEL_AVAIL.equals(protocol)) {
                                // convert content into object
                                HotelAvailability ha = new HotelAvailability(content);
                                // Call DB and gain result into list
                                ArrayList<SerializableBean> list = new ArrayList<SerializableBean>();
                                
                                ACLMessage reply = Message.createReplyMessage(msg, list);
                                // FOR TEST
                                reply.setContent(content);
                                log.info("=== [HotelAgent] sent reply message " + content);
                                send(reply);
                                
                            }
                            break;
                        default:
                            //TODO.. 
                            break;
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("Exception in Hotel.HandleReceivedMessages: " +e);
                    }
                }
                block(); // this is to allow other behaviours

            }
            ;

        }

        public boolean done() {
            return finished;
        }
    } // end of HandleRecivedMessages


    /**
     * This method is called by whatever GUI implementation In current
     * implementation it is called by XML-RPC through XMLRPC webserver Method
     * send Resource which should be search by SupplierAgent <br>
     * you need to call
     * external system
     * 
     * @param resource
     *            represents type of resource in Memory Model (OWL model)
     * 
     */
    public String search(String resource) {
        final String msgId = "102";// getLocalName() +
        // System.currentTimeMillis();

        ArrayList<String> msgs = new ArrayList<String>();
        addBehaviour(new CommunicationHotel(this, resource, msgId, msgs));

        System.out.println(System.currentTimeMillis());
        return "ok";
    }

    public String getSearchResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            //msgQueue.remove(msgId);
            for (int i = 0; i < msgs.size(); i++) {
                results += msgs.get(i).trim();
                if (i < msgs.size() - 1) {
                    results += Message.OBJECT_SEPARATE;
                }
            }
        } catch (Exception e) {
            results = "";
            e.printStackTrace();
        }

        return results;
    }

    /**
     * This behavior to communicate other hotels
     */
    class CommunicationHotel extends Behaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private String resource = null;
        private ArrayList<String> receivers;
        private String msgId;
        private Agent a;
        private boolean avail = false;
        private ArrayList<String> msgs = new ArrayList<String>();

        public CommunicationHotel(Agent _a, String _resource, String _msgId,
                ArrayList<String> _msgs) {
            super(_a);
            a = _a;
            resource = _resource;
            msgId = _msgId;
            msgs = _msgs;
        }

        public void action() {
            switch (step) {
                case 0:
                    // collect agents who satisfy action
                    // receivers = agentDAO.getAgents("", "hotel");
                    // FOR TEST ONLY
                    receivers = new ArrayList<String>();
                    receivers.add("HotelAgent");
                    // Send the cfp to all agents
                    ACLMessage msg = Message.createInformMessage(a, receivers,
                            resource);

                    msg.setConversationId(myAgent.getLocalName());
                    msg.setReplyWith(msgId); // Unique
                    // value

                    myAgent.send(msg);
                    // Prepare the template to get proposals
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId(myAgent.getLocalName()),
                            MessageTemplate.MatchInReplyTo(msg.getReplyWith()));

                    step = 1;
                    break;
                case 1:
                    // Receive all proposals/refusals from agents
                    ACLMessage replyMsg = myAgent.receive(mt);
                    if (replyMsg != null) {
                        if (replyMsg.getPerformative() == ACLMessage.PROPOSE) {
                            /**
                             * / check if there is any available hotel/ if yes set
                             * avail = true put msg into msgQueue of agent:
                             */
                            msgs.add("Nothing is more important than peace");
                        }
                        repliesCnt++;
                        if (repliesCnt >= receivers.size()) {
                            // We received all replies
                            step = 2;
                        }
                    } else {
                        block();

                    }

                    break;

            }
        }

        public boolean done() {
            msgQueue.put(msgId, msgs);
            if (step == 2) {// && avail); if finishing only exist available
                // put messages into queue of agent

                return true;
            }
            return false;
        // hotel
        }
    }
}
