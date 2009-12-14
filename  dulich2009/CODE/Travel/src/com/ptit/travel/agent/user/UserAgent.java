/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent.user;

/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.agent.onto.*;
import com.ptit.travel.agent.memory.*;
import com.ptit.travel.agent.communication.*;
import java.io.StringWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

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
public class UserAgent extends Agent {

    private static Logger log = Logger.getLogger(UserAgent.class.getName());
    WebServer xmlrpcServer;
    private static final int port = 8006;//ConfigXMLConnect.PORT_USER;
    private Memory mem;
    private Resource agentIndividual;
    // message queue of agent contains every satisfactory replied messages 
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    protected void setup() {
        Message.register(this, this.getLocalName());

        try {
            xmlrpcServer = new WebServer(port);
            log.debug("XMLRPC Running ....");
        } catch (IOException e) {
            log.error("PANIC: maybe the port " + port + " is in use");
            System.out.println(e);
        }
        xmlrpcServer.addHandler(this.getLocalName(), this);

        mem = new Memory(
                "E:/Develop/Netbean/Travel/config/UserAgent.properties",
                this.getLocalName());// E:/Develop/Netbean/Travel/

//        agentIndividual = mem.getModel().getResource(
//                Memory.getBase() + this.getLocalName());
    }

    /**
     * This method is called by whatever GUI implementation In current
     * implementation it is called by XML-RPC through XMLRPC webserver Method
     * send Resource which should be search by SupplierAgent <br>
     * you need to call "Agent.search(Resource resource)" function from
     * your external system
     * 
     * @param resource
     *            represents type of resource in Memory Model (OWL model)
     * 
     */
    public String search(String msg, String msgId, String protocol) {
        log.info("=== ADD RequestInfo behavior to " + this.getLocalName());
        addBehaviour(new RequestInfo(this, msg, msgId, protocol));// duoc trieu goi tu UserServlet

        return "ok";
    }

    public String searchResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            log.info("|| message Results:" + msgs);
            msgQueue.remove(msgId);
            for (int i = 0; i < msgs.size(); i++) {
                results += msgs.get(i).trim();
                if (i < msgs.size() - 1) {
                    results += Message.OBJECT_SEPARATE;
                }
            }
        } catch (Exception e) {
            results = "null";
            log.info(e.toString());
            e.printStackTrace();
        }

        return results;
    }

    /**
     * This method is called when we want this agent executes booking behavior
     * @param uh
     * 
     * 			resource: information about services wanting to book
     */
    public String book(String resource) {
        String msgId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Book(this, resource, msgId));
        ArrayList<String> msgs = msgQueue.get(msgId);
        msgQueue.remove(msgId);
        if (msgs != null && msgs.size() != 0) {
            return msgs.toString();
        } else {
            return "_aaaNONEbbb_";
        }
    }

    public String bookResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            msgQueue.remove(msgId);
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
     * This method is called when we want this agent prepares to execute 
     * modifying or canceling behavior
     * @param 
     * 			resource: information about services
     */
    public String prepareModify(String resource) {
        String msgId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Prepare(this, resource, msgId));

        return "ok";
    }

    public String prepareModifyResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            msgQueue.remove(msgId);
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
     * This method is called when we want this agent executes modifying behavior
     * @param 
     * 			resource: information about services wanting to modify
     */
    public String modify(String resource) {
        String msgId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Modify(this, resource, msgId));

        return "ok";
    }

    public String modifyResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            msgQueue.remove(msgId);
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
     * This method is called when we want this agent executes canceling behavior
     * @param 
     * 			resource: information about services wanting to cancel
     */
    public String cancel(String resource) {
        String msgId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Cancel(this, resource, msgId));

        return "ok";
    }

    /**
     * get results affter finishing cancel behavior
     * @param msgId
     * @return
     */
    public String cancelResults(String msgId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(msgId);
            msgQueue.remove(msgId);
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
     * checking availability behavior 
     * @author D05CNPM
     * @param resource: information about services need to book
     * @param a: agent execute this behavior
     */
    private class RequestInfo extends Behaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private String content = null;
        private String protocol = null;
        private ArrayList<String> receivers;
        private String msgId;
        private Agent a;
        private boolean avail = false;
        private ArrayList<String> msgs;

        public RequestInfo(Agent _a, String _content, String _msgId, String _p) {
            content = _content;
            msgId = _msgId;
            protocol = _p;
            msgs = new ArrayList<String>();
            a = _a;
        }

        public void action() {

            switch (step) {
                case 0:
                    try {
                        // collect agents who satisfy action
                        //receivers = agentDAO.getAgents("", "hotel");
                        // FOR TEST ONLY
                        log.info("=== Preparing msg to send HotelAgent: " + protocol);
                        receivers = new ArrayList<String>();
                        receivers.add("HotelAgent");
                        // Send the cfp to all agents
                        ACLMessage msg = Message.createInformMessage(a, receivers, content);
                        msg.setProtocol(protocol);
                        msg.setConversationId(myAgent.getLocalName());
                        msg.setReplyWith(msgId); // Unique
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
                            msgs.add(replyMsg.getContent());
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

                log.info("=== FIHISHED RequestInfo behavior");
                msgQueue.put(msgId, msgs);
                return true;
            }
            return false;
        // hotel
        }
    } // End of inner class RequestAvailability


    /**
     * booking services behavior
     * @author D05CNPM
     * @param resource: information about services need to book
     * @param a: agent execute this behavior
     */
    class Book extends Behaviour {

        private String resource = null;
        private Agent a;
        private String msgId;

        public Book(Agent _a, String _resource, String _msgId) {
            super(_a);
            a = _a;
            resource = _resource;
            msgId = _msgId;
        }

        public void action() {
            send(Message.createInformMessage(a, "UserAgent", resource));
        }

        public boolean done() {

            return false;
        }
    } // End class RequestBook


    /**
     * preparing for Modifying or canceling booking services behavior
     * @author D05CNPM
     * @param resource: information about services need to modify 
     * @param a: agent execute this behavior
     */
    class Prepare extends Behaviour {

        private String resource = null;
        private Agent a;
        private String msgId;

        public Prepare(Agent _a, String _resource, String _msgId) {
            super(_a);
            a = _a;
            resource = _resource;
            msgId = _msgId;
        }

        public void action() {
            send(Message.createInformMessage(a, "UserAgent", resource));
        }

        public boolean done() {

            return false;
        }
    } // End class RequestCancel


    /**
     * Modifying booking services behavior
     * @author D05CNPM
     * @param resource: information about services need to modify 
     * @param a: agent execute this behavior
     */
    class Cancel extends Behaviour {

        private String resource = null;
        private Agent a;
        private String msgId;

        public Cancel(Agent _a, String _resource, String _msgId) {
            super(_a);
            a = _a;
            resource = _resource;
            msgId = _msgId;
        }

        public void action() {
            send(Message.createInformMessage(a, "UserAgent", resource));
        }

        public boolean done() {

            return false;
        }
    } // End class RequestCancel


    /**
     * canceling services behavior
     * @author D05CNPM
     * @param resource: information about services need to book
     * @param a: agent execute this behavior
     */
    class Modify extends Behaviour {

        private String resource = null;
        private Agent a;
        private String msgId;

        public Modify(Agent _a, String _resource, String _msgId) {
            super(_a);
            a = _a;
            resource = _resource;
            msgId = _msgId;
        }

        public void action() {
            send(Message.createInformMessage(a, "UserAgent", resource));
        }

        public boolean done() {

            return false;
        }
    } // End class RequestCancel


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
}
