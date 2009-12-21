/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

import com.ptit.travel.agent.communication.Language;
import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import com.ptit.travel.beans.SerializableBean;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.log4j.Logger;

/**
 *
 * @author D05CNPM
 */
public class ControllerAgent extends Agent {

    private static Logger log = Logger.getLogger(ControllerAgent.class.getName());
    // message queue of agent contains every satisfactory replied messages 
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    protected void setup() {

        log.info("CONTROLLER AGENT now is ready ");
        //Message.register(this, this.getName());


        addBehaviour(new TickerBehaviour(this, 60000) {

            protected void onTick() {
                HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages();
                addBehaviour(hrmBehaviour);
            }
        });
    }

    /**
     * 
     *This behaviour is used by ControllerAgent to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     */
    class HandleRecivedMessages extends Behaviour {

        private String protocol;
        private String conversationId;
        private String language;
        private ArrayList<String> msgsContent = new ArrayList<String>();
        private MessageTemplate mt; // The template to receive replies

        private String userTracker;
        private String replyWith;
        private int step = 0;
        private int repliesCnt;
        private final int finalStep = 100;
        private ArrayList<String> receivers;

        public HandleRecivedMessages() {
        }

        public void action() {

            synchronized (this) {
                try {
                    switch (step) {


                        case 0:
                            ACLMessage msg = receive();
                            if (msg != null) {
                                // keep track of user
                                userTracker = msg.getSender().getLocalName();
                                replyWith = msg.getReplyWith();
                                protocol = msg.getProtocol();
                                conversationId = msg.getConversationId();
                                language = msg.getLanguage();
                                // 
                                log.info("LANGUAGE: " + language);
                                // Language used to get all appropriate supplier agents

                                // Call DB and gain result into list
                                receivers = new ArrayList<String>();
                                receivers.add("HotelAgent");
                                receivers.add("HotelAgent1");
                                ACLMessage forwardMsg = Message.createForwardMessage(myAgent, receivers, msg,
                                        replyWith);
                                //myAgent.addBehaviour(new Negotiate(myAgent, forwardMsg));
                                //log.info("HandleRecivedMessages behavior is blocked " + this.STATE_BLOCKED);

                                send(forwardMsg);
                                mt = MessageTemplate.and(MessageTemplate.MatchConversationId(forwardMsg.getConversationId()),
                                        MessageTemplate.MatchInReplyTo(forwardMsg.getReplyWith()));
                                log.info("Message Template: " + mt.toString());
                                step = 1;
                            }else {
                                //block();
                            }
                            
                            break;
                        case 1:
                            log.info("[ControllerAgent] Negotiating... ");
                            ACLMessage replyMsg = receive(mt);
                            // Negotiating here

                            if (replyMsg != null) {

                                //if (replyMsg.getPerformative() == ACLMessage.PROPOSE) 
                                {
                                    /**
                                     * / check if there is any available hotel/ if yes set
                                     * avail = true
                                     * put msg into msgQueue of agent: 
                                     */
                                    //FOR TEST
                                    log.info("=== One more received message from " + replyMsg.getSender().getLocalName());
                                    msgsContent.add(replyMsg.getContent());
                                }
                                repliesCnt++;
                                log.info("|| RECEIVERS: " + receivers.size() + " || repliesCnt: " + repliesCnt);
                                if (repliesCnt >= receivers.size()) {
                                    //We received all replies, can combine services here

                                    msgQueue.put(conversationId, msgsContent);
                                    step = finalStep; // finish negotiating

                                    log.info("[ControllerAgent] Refine the results ");
                                    //TODO.. 
                                    ArrayList<String> contents = msgQueue.get(conversationId);
                                    msgQueue.remove(conversationId);
                                    ACLMessage reply = Message.createReplyMessage(myAgent, userTracker,
                                            contents, language, protocol, conversationId, replyWith);

                                    log.info(reply);
                                    send(reply);
                                }
                            } else {
                                block();

                            }


                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e.toString());
                }
            }
            block(); // this is to allow other behaviours

        }
        ;

        public boolean done() {
            if (step == finalStep) {
                return true;
            }
            return false;
        }
    } // end of HandleRecivedMessages


    /**
     * 
     *This behaviour is used by HotelAgent to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     */
    class Negotiate extends Behaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private ArrayList<String> receivers;
        private ArrayList<String> msgsContent;
        private String conversationId;
        private Agent a;
        private ACLMessage msg;

        public Negotiate(Agent _a, ACLMessage _msg) {
            conversationId = _msg.getConversationId();
            msg = _msg;
            a = _a;
            msgsContent = new ArrayList<String>();
        }

        public void action() {
            log.info("===== Starting Negotiation ===== ");
            switch (step) {
                case 0:
                    try {
                        // collect agents who satisfy action
                        //receivers = agentDAO.getAgents("", "hotel");
                        // FOR TEST ONLY
                        log.info("=== Preparing msg to send HotelAgent: ");
                        receivers = new ArrayList<String>();
                        receivers.add("HotelAgent");
                        // Send the cfp to all agents
                        msg.setConversationId(conversationId);
                        // value

                        myAgent.send(msg);
                        // Prepare the template to get proposals
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId(myAgent.getLocalName()),
                                MessageTemplate.MatchInReplyTo(msg.getReplyWith()));

                        step = 1;
                        break;
                    } catch (Exception e) {
                        // TODO: handle exception
                        log.error(e.toString());
                        e.printStackTrace();
                    }

                case 1:
                    // Receive all proposals/refusals from agents
                    ACLMessage replyMsg = myAgent.receive(mt);
                    //ACLMessage replyMsg = myAgent.receive();
                    if (replyMsg != null) {
                        //if (replyMsg.getPerformative() == ACLMessage.PROPOSE) 
                        {
                            /**
                             * / check if there is any available hotel/ if yes set
                             * avail = true
                             * put msg into msgQueue of agent: 
                             */
                            //FOR TEST
                            log.info("=== One more received message from " + replyMsg.getSender());
                            msgsContent.add(replyMsg.getContent());
                        }
                        repliesCnt++;
                        log.info("|| RECEIVERS: " + receivers.size() + " || repliesCnt: " + repliesCnt);
                        if (repliesCnt >= receivers.size()) {
                            // We received all replies
                            step = 2;
                        }
                    } else {
                        block();

                    }
                    break;
                case 2:

                    break;

            }
        }

        public boolean done() {


            if (step == 2) {// && avail); if finishing only exist available
                // put messages into queue of agent

                msgQueue.put(conversationId, msgsContent);
                log.info("=== FIHISHED Negotiation");
                return true;
            }
            return false;
        // hotel
        }
    } // end of HandleRecivedMessages

}
