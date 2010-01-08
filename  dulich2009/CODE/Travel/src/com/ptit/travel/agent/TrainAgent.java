package com.ptit.travel.agent;

import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;

//import com.ptit.travel.jane.hotel.HotelProcess;
import com.ptit.travel.jane.train.TrainProcessDB;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;

import com.ptit.travel.agent.communication.Message;
//import com.ptit.travel.jane.hotel.Hotel;
import java.util.*;
import com.hp.hpl.jena.rdf.model.*;

public class TrainAgent extends Agent {

    private static Logger log = Logger.getLogger(TrainAgent.class.getName());
    private Memory mem;

    // message queue of agent contains every satisfactory replied messages
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    protected void setup() {

        //Message.register(this, this.getName());

//        mem = new Memory(
//                "E:/Develop/Netbean/Travel/config/HotelAgent.properties", this.getLocalName());

        addBehaviour(new TickerBehaviour(this, 60000) {

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
        TrainProcessDB train = new TrainProcessDB();

        public HandleRecivedMessages() {
        }

        public void action() {
            log.info("=== TRAINAGENT is Ready now");
            synchronized (this) {
                ACLMessage msg = receive();
                if (msg != null) {

                    try {
                        String content = msg.getContent();
                        log.info("=== [TrainAgent] received from " + msg.getSender().getLocalName());
                        switch (msg.getPerformative()) {
                            case ACLMessage.QUERY_REF:
                                break;

                            case ACLMessage.INFORM:
                                // action of message <-> protocol of ACL
                                String protocol = msg.getProtocol();
                                if (Protocol.TRAIN_AVAIL.equals(protocol)) {

                                    log.info("Call module DB with String input: " + content);

                                    content = train.search(content);// chi goi DB o day

                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [TrainAgent] sent reply message " + reply);
                                    send(reply);

                                    finished = true;
                                } else if (Protocol.TRAIN_RES.equals(protocol)) {
                                    boolean booking = train.processBooking(msg.getContent());
                                    if (booking) {
                                        content = Message.SUCCESS;
                                    } else {
                                        content = Message.FAIL;
                                    }
                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [FLIGHTAGENT] sent reply message " + reply);
                                    send(reply);
                                    finished = true;
                                     //*/
                                }else{
                                    content = "Not understand protocol";
                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [TRAINAGENT] sent reply message " + reply);
                                    send(reply);

                                    finished = true;
                                }
                                break;
                            default:
                                //TODO.. 
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.toString());
                    }
                }
                block(); // this is to allow other behaviours

            }
        }

        public boolean done() {
            return finished;
        }
    } // end of HandleRecivedMessages


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
    class CommunicationTrain extends Behaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private String resource = null;
        private ArrayList<String> receivers;
        private String msgId;
        private Agent a;
        private boolean avail = false;
        private ArrayList<String> msgs = new ArrayList<String>();

        public CommunicationTrain(Agent _a, String _resource, String _msgId,
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
                    receivers.add("TrainAgent");
                    // Send the cfp to all agents

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