/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.agent.onto.*;
import com.ptit.travel.agent.memory.*;
import com.ptit.travel.agent.communication.*;
import java.io.StringWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.xmlrpc.WebServer;

/**
 * Ask Agent asks AnswerAgent for recources of certain type by generating SPARQL
 * query and then save results into its memory. Agent runs also XML-RPC server
 * on port 8000 and public methods can be called by GUI implementation see:
 * {@link agent.demo.askAgent.FrameGUI FrameGUI}
 * 
 * @version 0.5
 */
public class UserAgent extends Agent {

    private static Logger log = Logger.getLogger(UserAgent.class.getName());
    WebServer xmlrpcServer;
    private static final int port = 8000;
    private Memory mem;
    private Resource agentIndividual;

    protected void setup() {
        Message.register(this, "AskAgent");

        try {
            xmlrpcServer = new WebServer(port);
            log.debug("XMLRPC Running ....");
        } catch (IOException e) {
            log.error("PANIC: maybe the port " + port + " is in use");
            System.out.println("NGOAI LE: " + e);
        }
        xmlrpcServer.addHandler("UserAgent", this);

        mem = new Memory("config/UserAgent.properties", "UserAgent");
        agentIndividual = mem.getModel().getResource(
                Memory.getBase() + "UserAgent");

        search("CD");// TrungPQ

        BehaviourHandleRecivedMessages hrmBehaviour = new BehaviourHandleRecivedMessages(
                this);
        addBehaviour(hrmBehaviour);

    }

    /**
     * This method is called by whatever GUI implementation In current
     * implementation it is called by XML-RPC through XMLRPC webserver Method
     * send type of Resource which should be search by AskAgent <br>
     * you need to call "AskAgent.search(String resourceType)" function from
     * your external system
     * 
     * @param resourceType
     *            represents type of resource in Memory Model (OWL model)
     * 
     */
    public String search(String resourceType) {
        addBehaviour(new BehaviourQueryAnswerAgent(this, resourceType));
        return "ok";
    }

    /**
     * This method is called by whatever GUI implementation In current
     * implementation it is called by XML-RPC through XMLRPC webserver Method
     * returns XML data of the actor - context,resources and other or of any
     * other resource <br>
     * you need to call "AskAgent.getXML(String id)" function from your external
     * system in order to get xml of that RDF resource if you want to get RDF
     * results instead of XML id will leead with "rdf:" for example
     * "rdf:concrete_id"
     * 
     * @param id
     *            represents id of resource in Agent Memory if we want to get
     *            actor/Employee data we need to enter actor ID which is in this
     *            case "AskAgent"
     * 
     */
    public String getXML(String id) {
        String xml = "";
        mem.testConnection();

        boolean wantsRDF = false;
        if (id.startsWith("rdf:")) {
            Pattern pp = Pattern.compile("rdf:");
            Matcher mm = pp.matcher(id);
            id = mm.replaceAll("");
            wantsRDF = true;
        }

        Resource r = mem.getModel().getResource(Memory.getBase() + id);

        if (wantsRDF) {
            // This is rdf implementation
            OntModel m = ModelFactory.createOntologyModel();
            Message.addProperties(m, r);

            StringWriter writer = new StringWriter();
            m.write(writer, "RDF/XML", Ontology.BASE); // "RDF/XML-ABBREV");

            xml = writer.getBuffer().toString();
        } else {
            xml = Message.getXML(r, "", "");
        }
        return xml;
    }

    /**
     *One shot behaviour used by AskAgent to send SPARQL query to AnswerAgent
     * 
     * @author Michal Laclavik
     * 
     */
    class BehaviourQueryAnswerAgent extends OneShotBehaviour {

        private String resourceType = null;
        private Agent a;

        public BehaviourQueryAnswerAgent(Agent _a, String _resourceType) {
            super(_a);
            a = _a;
            resourceType = _resourceType;
        }

        public void action() {
            agentIndividual.removeAll(mem.createProperty("resource"));
            log.debug("Sending message to HotelAgent ...");
            send(Message.createQueryMessage(a, "HotelAgent",
                    "SELECT ?x WHERE {?x rdf:type ont:" + resourceType + "}"));
        }
    } // End class bQueryAnswerAgent


    /**
     * 
     * Behaviour used by AskAgent to handle recieved inform messages - results
     * of querying and storing them into memory model
     * 
     * @author Michal Laclavik
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
                        case ACLMessage.QUERY_REF:
                            ;
                            break;
                        // case ACLMessage.INFORM:
                        default:
                            log.info("######Message#######\n" + msg.toString() + "\n############");

                            log.info(getName() + ":message recived:" + msg.getContent());
                            if (msg.getLanguage().equals(Ontology.RDF)) {
                                // this model we need to know exactly which resoures
                                // were recieved and to handle them later
                                OntModel m = ModelFactory.createOntologyModel();
                                try {
                                    mem.getModel().read(
                                            new StringReader(msg.getContent()),
                                            Ontology.BASE);
                                    // OM.getModel().commit();
                                    m.read(new StringReader(msg.getContent()),
                                            Ontology.BASE);
                                } catch (Exception e) {
                                    log.debug("Something failed: " + e.toString());
                                }
                                Iterator iter = m.listSubjectsWithProperty(mem.createProperty("title"));
                                while (iter.hasNext()) {
                                    Resource r = (Resource) iter.next();
                                    agentIndividual.addProperty(mem.createProperty("resource"), mem.getModel().getResource(r.getURI()));
                                    log.debug("res:" + r.getLocalName());
                                }
                            }
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

}
