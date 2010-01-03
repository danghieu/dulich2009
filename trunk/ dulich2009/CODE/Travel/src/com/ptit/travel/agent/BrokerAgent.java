package com.ptit.travel.agent;

/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;


import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.WebServer;

/**
 * User Agent runs also XML-RPC server
 * on port 8000 and public methods can be called by outside 
 * Behaviors:
 * 	search for services, supplier, place, event
 * 	request booking
 * 	prepare to modify/cancel
 * 	modify
 * 	cancel	
 * 
 * Each behavior executed by two methods and one inner class. For example
 *  
 * @version 0.5
 */
public class BrokerAgent extends Agent {

    private static Logger log = Logger.getLogger(BrokerAgent.class.getName());
    WebServer xmlrpcServer;
    private static final int port = 8000;
    private Memory mem;
    // message queue of agent contains every satisfactory replied messages 
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();


    @Override
    protected void setup() {
        //Message.register(this, this.getLocalName());

        try {
            xmlrpcServer = new WebServer(port);
            log.debug("XMLRPC Running ....");
        } catch (IOException e) {
            log.error("PANIC: maybe the port " + port + " is in use");
            System.out.println("NGOAI LE: " + e);
        }
        xmlrpcServer.addHandler(this.getLocalName(), this);

        HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages(
                this);
        addBehaviour(hrmBehaviour);
    }

    /**
     * 
     *This behaviour is used by BrokerAgent to handle received messages return
     * the results to Ask Agent using BehaviourSentResult
     * 
     * 
     */
    class HandleRecivedMessages extends SimpleBehaviour {

        private boolean finished = false;
        private Agent a;

        public HandleRecivedMessages(Agent _a) {
            super(_a);
            a = _a;
        }

        public void action() {

            synchronized (this) {
                ACLMessage msg = receive();
                if (msg != null) {
                    switch (msg.getPerformative()) {
                        case ACLMessage.QUERY_REF:
                            break;

                        case ACLMessage.INFORM:
                            // read language to know
                            String protocol = msg.getProtocol();
                            //if it is hotel to process
                            if (protocol.contains(Protocol.PREFIX_TOURSERVICE)) {
                                
                                //if(protocol.equals("AVAIL"))// hoi xem dv cio kha dung ko?
                                /**
                                 * Doc CSDL lay ve thong tin chi tiet ve tour, sau do hien thi len giao dien
                                 * dua tren ma a tour
                                 */
                            }
                            break;
                        default:
                            // TODO
                            break;
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
