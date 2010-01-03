/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

import com.ptit.travel.agent.communication.*;
import com.ptit.travel.jane.hotel.HotelProcess;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.ptit.travel.agent.communication.Message;
import java.io.*;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.ptit.travel.jane.hotel.Hotel;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.ptit.travel.moduleJDBC.Model.*;
import java.util.*;
import com.hp.hpl.jena.rdf.model.*;
import java.io.FileOutputStream;

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
        HotelProcess hotel = new HotelProcess();

        public HandleRecivedMessages() {
        }

        public void action() {
            log.info("=== FLIGHTAGENT is Ready now");
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

                                    //content = search(content);// chi goi DB o day

                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [FLIGHTAGENT] sent reply message " + reply);
                                    send(reply);

                                    finished = true;
                                } else if (Protocol.FLIGHT_RES.equals(protocol)) {
                                    /*
                                    boolean booking = processBooking(msg.getContent());
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
                                     * */
                                }else{
                                    content = "Not understand protocol";
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
