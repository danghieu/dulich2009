/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.agent;

import com.ptit.travel.agent.communication.Language;
import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import com.ptit.travel.beans.SerializableBean;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author D05CNPM
 */
public class ControllerAgent extends Agent{

    private static Logger log = Logger.getLogger(ControllerAgent.class.getName());
    protected void setup() {

        Message.register(this, this.getName());


//        addBehaviour(new TickerBehaviour(this, 30000) {
//            protected void onTick() {
                HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages();
                addBehaviour(hrmBehaviour);
//            }
//        });
    }

    /**
     * 
     *This behaviour is used by HotelAgent to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     * 
     */
    class HandleRecivedMessages extends Behaviour{

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
                        log.info("=== [ControllerAgent] received reply message " + content);
                    switch (msg.getPerformative()) {
                        case ACLMessage.QUERY_REF:
                            break;

                        case ACLMessage.INFORM:
                            // 
                            log.info("LANGUAGE: " + msg.getLanguage());
                            // Language used to get all appropriate supplier agents
                            
                            // Call DB and gain result into list
                            ArrayList<String> recieverNames = new ArrayList<String>();
                            
                            ACLMessage forwardMsg = Message.createInformMessage(myAgent, recieverNames, content);
                            log.info("=== [ControllerAgent] sent reply message " + content);
                            send(forwardMsg);
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


}
