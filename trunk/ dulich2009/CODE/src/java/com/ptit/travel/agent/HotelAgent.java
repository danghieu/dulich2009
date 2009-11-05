/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

import java.net.URL;

import com.ptit.travel.agent.onto.*;
import com.ptit.travel.agent.memory.*;
import com.ptit.travel.agent.communication.*;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;

import com.hp.hpl.jena.rdf.model.Resource;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * AnswerAgent answers to SPARQL queries sent by AskAgent
 * 
 * @author Michal Laclavik
 * @version 0.5
 */
public class HotelAgent extends Agent {

    private static Logger log = Logger.getLogger(HotelAgent.class.getName());
    private Memory mem;

    // this method sends result to AskAgent
    private void informAskAgent(Resource r) {
        this.addBehaviour(new BehaviourSendResult(this, r));
    }

    /**
     * 
     * 
     *Behaviour is useed with AnswerAgent and it sends RDF Resources to
     * AskAgents
     * 
     * @author Michal Laclavik
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
     *This behaviour is used by AnswerAgent to handle recieved SPARQL messages
     * sent by AskAgent then it performs SPARQL query on AnswerAgent model and
     * return the results to AskAgent using BehaviourSentResult
     *      
     * 
     */
    class BehaviourHandleRecivedMessages extends SimpleBehaviour {

        private boolean finished = false;
        private Agent a;

        public BehaviourHandleRecivedMessages(Agent _a) {
            super(_a);
            a = _a;
        }

        public void action() {

            synchronized (this) {
                /*
                 * try {
                 * 
                 * Thread.sleep(100); } catch (InterruptedException e) { ; }
                 */
                ACLMessage msg = receive();
                if (msg != null) {
                    switch (msg.getPerformative()) {
                        // case ACLMessage.QUERY_REF:
                        default: // TrungPQ
                            //System.out.println("ANSWER....");

                            log.info("######Message#######\n" + msg.toString() + "\n############");

                            log.info(getName() + ":message recived:" + msg.getContent());

                            if (msg.getLanguage().equals(Ontology.SPARQL)) {
                                Model m = mem.getModel();

                                Query query = QueryFactory.create(msg.getContent());
                                QueryExecution qexec = QueryExecutionFactory.create(query, m);
                                try {
                                    ResultSet results = qexec.execSelect();
                                    for (; results.hasNext();) {
                                        QuerySolution soln = results.nextSolution();
                                        Resource x = soln.getResource("x"); 

                                        informAskAgent(x);
                                        log.info(x.toString());

                                    }
                                } finally {
                                    qexec.close();
                                }

                            }
                            break;
                        case ACLMessage.INFORM:
                            ;

                            break;
                    }
                }
                block(); // this is to allow other behaviours

            }
            ;

        }

        public boolean done() {
            return finished;
        }
    } // end of BehaviourHandleRecivedMessages


    protected void setup() {

        Message.register(this, "HotelAgent");

        mem = new Memory("config/HotelAgent.properties", "HotelAgent");

        BehaviourHandleRecivedMessages hrmBehaviour = new BehaviourHandleRecivedMessages(
                this);
        addBehaviour(hrmBehaviour);
    }
}
