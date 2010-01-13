/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

import com.ptit.travel.agent.communication.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import org.apache.log4j.Logger;
import com.ptit.travel.agent.communication.Message;

import java.util.*;
import com.ptit.travel.jane.Flight.FlightProcess;
/**
 *
 * @author D05CNPM
 */
public class FlightAgent extends Agent{

    private static Logger log = Logger.getLogger(FlightAgent.class.getName());

    // message queue of agent contains every satisfactory replied messages
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    protected void setup() {

        //Message.register(this, this.getName());

//        mem = new Memory(
//                "E:/Develop/Netbean/Travel/config/FLIGHTAGENT.properties", this.getLocalName());

        addBehaviour(new TickerBehaviour(this, 60000) {

            protected void onTick() {
                HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages();
                addBehaviour(hrmBehaviour);
            }
        });
    }

    /**
     * 
     *This behaviour is used by FLIGHTAGENT to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     * 
     */
    class HandleRecivedMessages extends SimpleBehaviour {

        private boolean finished = false;

        public HandleRecivedMessages() {
        }

        public void action() {
            synchronized (this) {
                ACLMessage msg = receive();
                if (msg != null) {

                    try {
                        String content = msg.getContent();
                        log.info("=== [FLIGHTAGENT] received from " + msg.getSender().getLocalName());
                        switch (msg.getPerformative()) {
                            case ACLMessage.QUERY_REF:
                                break;

                            case ACLMessage.INFORM:
                                // action of message <-> protocol of ACL
                                String protocol = msg.getProtocol();
                                if (Protocol.FLIGHT_AVAIL.equals(protocol)) {

                                    log.info("Call module DB with String input: " + content);

                                    //content = "ha noi" + Message.FIELD_SEPARATE +"Ho Chi Minh" + Message.FIELD_SEPARATE + "2010-02-01"+ Message.FIELD_SEPARATE + "economy" + Message.FIELD_SEPARATE + "1";
                                    content = FlightProcess.search(content);// chi goi DB o day

                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [FLIGHTAGENT] sent reply message " + reply);
                                    send(reply);

                                    finished = true;
                                } else if (Protocol.FLIGHT_RES.equals(protocol)) {
                                    //*
                                    boolean booking = FlightProcess.processingBooking(msg.getContent());
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
                                    content = Message.PROTOCOL_NOT_UNDERSTAND;
                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [FLIGHTAGENT] sent reply message " + reply);
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

}
