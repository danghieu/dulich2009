/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.agent.communication.*;
import com.ptit.travel.jane.agent.AgentProcess;
import java.util.ArrayList;
import java.util.Hashtable;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Set;
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
    private static final int port = ConfigXMLConnect.PORT_USER;
    // message queue of agent contains every satisfactory replied messages 
    private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

    @Override
    protected void setup() {
        //Message.register(this, this.getLocalName());

        try {
            xmlrpcServer = new WebServer(port);
            log.debug("XMLRPC Running on port: " + port);
        } catch (Exception e) {
            log.error("PANIC: maybe the port " + port + " is in use");
            System.out.println(e);
        }
        xmlrpcServer.addHandler(this.getLocalName(), this);
//
//        mem = new Memory(
//                "E:/Develop/Netbean/Travel/config/UserAgent.properties",
//                this.getLocalName());// E:/Develop/Netbean/Travel/

    /* Tu gan hanh okvi search sau 0.5 s
    addBehaviour(new TickerBehaviour(this, 60000) {
    
    protected void onTick() {
    String content = "ha noi" + Message.FIELD_SEPARATE + "Ho Chi Minh" + Message.FIELD_SEPARATE +
    "2010-02-01" + Message.FIELD_SEPARATE + "economy" + Message.FIELD_SEPARATE + "1";
    String s_begin = "2010-01-6";
    String s_end = "2010-01-7";
    //     hotelprocess.checkAvailability( "HaiYen", "LivingRoom", "SingleRoom");
    
    content = "HotelAgent" + Message.FIELD_SEPARATE + "HaiYen" + Message.FIELD_SEPARATE + "Nam Dinh" + 
    Message.FIELD_SEPARATE + "405" + Message.FIELD_SEPARATE + "Thanh Xuan Bac" + 
    Message.FIELD_SEPARATE + "MeetingRoom" + Message.FIELD_SEPARATE + "2" + 
    Message.FIELD_SEPARATE + s_begin + Message.FIELD_SEPARATE + s_end + Message.FIELD_SEPARATE +
    "Hanh" + Message.FIELD_SEPARATE + "Sinh Vien" + Message.FIELD_SEPARATE + "162882805";
    
    String input = "Nam Dinh"+Message.FIELD_SEPARATE+null+Message.FIELD_SEPARATE+null;
    input="Ha Noi"+Message.FIELD_SEPARATE+"Phu Ly"+Message.FIELD_SEPARATE+"2009-12-29" +Message.FIELD_SEPARATE+"2009-12-31";
    search(input, "conversationId", Protocol.TOURSERVICE_AVAIL);
    }
    });//*/

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
    public String search(String msg, String conversationId, String protocol) {
        log.info("=== ADDed RequestInfo behavior to " + this.getLocalName());
        addBehaviour(new RequestInfo(this, msg, conversationId, protocol));// duoc trieu goi tu UserServlet

        return "ok";
    }

    public String getResults(String conversationId) {

        ArrayList<String> msgs = null;
        while (msgs == null) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //log.info(e.toString());
            }
            msgs = msgQueue.get(conversationId);
        }
        log.info("|| message Results:" + msgs);
        msgQueue.remove(conversationId);
        String results = "";
        try {

            for (int i = 0; i < msgs.size(); i++) {
                results += msgs.get(i).trim();
                if (i < msgs.size() - 1) {
                    results += Message.OBJECT_SEPARATE;
                }
            }
        } catch (Exception e) {
            results = "Khong co ket qua de hien thi";
            log.info(e.toString());
        }

        return results;
    }

    /**
     * This method is called when we want this agent executes booking behavior
     * @param uh
     * 
     * 			resource: information about services wanting to book
     */
    public String book(String msg, String conversationId, String protocol) {
        log.info("=== ADDed book behavior to " + this.getLocalName());
        addBehaviour(new Book(this, msg, conversationId, protocol));
        return "ok";
    }

    /**
     * This method is called when we want this agent prepares to execute 
     * modifying or canceling behavior
     * @param 
     * 			resource: information about services
     */
    public String prepareModify(String resource) {
        String conversationId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Prepare(this, resource, conversationId));

        return "ok";
    }

    public String prepareModifyResults(String conversationId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(conversationId);
            msgQueue.remove(conversationId);
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
        String conversationId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Modify(this, resource, conversationId));

        return "ok";
    }

    public String modifyResults(String conversationId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(conversationId);
            msgQueue.remove(conversationId);
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
        String conversationId = getLocalName() + System.currentTimeMillis(); //unique

        addBehaviour(new Cancel(this, resource, conversationId));

        return "ok";
    }

    /**
     * get results affter finishing cancel behavior
     * @param conversationId
     * @return
     */
    public String cancelResults(String conversationId) {
        String results = "";
        try {
            ArrayList<String> msgs = msgQueue.get(conversationId);
            msgQueue.remove(conversationId);
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
    private class RequestInfo extends SimpleBehaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private String content = null;
        private String protocol = null;
        private ArrayList<String> receivers;
        private String conversationId;
        private Agent a;
        private ArrayList<String> msgs;

        public RequestInfo(Agent _a, String _content, String _conversationId, String _p) {
            content = _content;
            conversationId = _conversationId;
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

                        receivers = new ArrayList<String>();
                        receivers.add("ControllerAgent");
                        String replyWith = "[" + myAgent.getLocalName() + "]" + System.currentTimeMillis();
                        // Send the cfp to all agents
                        ACLMessage msg = Message.createInformMessage(a, receivers, content, null,
                                protocol, conversationId, replyWith);

                        // value
                        log.info("-------- Gui Thong Diep toi " + receivers.toString()+ "\n" + msg);
                        myAgent.send(msg);
                        // Prepare the template to get proposals
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
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
                            log.info("-------- Nhan Thong Diep tu " + 
                                    replyMsg.getSender().getLocalName() + "\n" + replyMsg);
                            msgs.add(//replyMsg.getSender().getLocalName() + Message.FIELD_SEPARATE + 
                                    replyMsg.getContent());
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

                log.info("========== KET THUC QUA TIM KIEM ============");
                msgQueue.put(conversationId, msgs);
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

        private String content = null;
        private Agent a;
        private String conversationId;
        private String protocol;
        private int step = 0;
        private MessageTemplate mt;
        private ArrayList<String> msgs;
        Set<String> receivers;
        private int repliesCnt;

        public Book(Agent _a, String _msg, String _conversationId, String _p) {
            super(_a);
            a = _a;
            String s_begin = "2010-01-6";
        String s_end = "2010-01-7";
            String input1 = "HaiYen" + Message.FIELD_SEPARATE + "Nam Dinh" + Message.FIELD_SEPARATE + "405" + Message.FIELD_SEPARATE + "Thanh Xuan Bac" + Message.FIELD_SEPARATE + "MeetingRoom" + Message.FIELD_SEPARATE + "1" + Message.FIELD_SEPARATE + 
                    s_begin + Message.FIELD_SEPARATE + s_end + Message.FIELD_SEPARATE + "Hanh" + Message.FIELD_SEPARATE + "Broker" + Message.FIELD_SEPARATE + "162882805" + Message.FIELD_SEPARATE + "4" + Message.FIELD_SEPARATE + "0948226160" + Message.FIELD_SEPARATE + "0948226160" + Message.FIELD_SEPARATE + "Y yen - Nam Dinh";
            content = _msg;
            conversationId = _conversationId;
            protocol = _p;
            msgs = new ArrayList<String>();
        //receivers = new Set<String>();
        }

        public void action() {
            switch (step) {
                case 0:
                    try {
                        // collect agents who satisfy action
                        //receivers = agentDAO.getAgents("", "hotel");
                        log.info("Start Booking ... ");
                        Hashtable<String, String> agentMsg = Message.extractAgentMsg(content);

                        if (agentMsg == null) {
                            log.error("Invalid format input from servlet: " + content);
                            return;
                        }

                        receivers = agentMsg.keySet();

                        String replyWith = "[" + myAgent.getLocalName() + "]" + System.currentTimeMillis();
                        // Send the cfp to all agents
                        log.info("-------- Gui Thong Diep toi " + receivers.toString());
                        
                        ACLMessage msg;
                        String receiver;
                        for (int i = 0; i < receivers.size(); i++) {
                            receiver = (String) receivers.toArray()[i];
                            msg = Message.createInformMessage(a, receiver, agentMsg.get(receiver),
                                    Language.HOTEL, protocol, conversationId, replyWith);
                            log.info(msg);
                            myAgent.send(msg);
                        }
                        
                        // Prepare the template to get proposals
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
                                MessageTemplate.MatchInReplyTo(replyWith));

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
                            log.info("-------- Nhan Thong Diep tu " + 
                                    replyMsg.getSender().getLocalName()+ "\n" + replyMsg);
                            msgs.add(replyMsg.getContent());
                        }
                        repliesCnt++;
                        //log.info("|| RECEIVERS: " + receivers.size() + " || repliesCnt: " + repliesCnt);
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

                log.info("========== KET THUC QUA TRINH DAT DICH VU ============");
                msgQueue.put(conversationId, msgs);
                return true;
            }
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
        private String conversationId;

        public Prepare(Agent _a, String _resource, String _conversationId) {
            super(_a);
            a = _a;
            resource = _resource;
            conversationId = _conversationId;
        }

        public void action() {
        }

        public boolean done() {

            return false;
        }
    } // End class Prepare


    /**
     * Modifying booking services behavior
     * @author D05CNPM
     * @param resource: information about services need to modify 
     * @param a: agent execute this behavior
     */
    class Cancel extends Behaviour {

        private String resource = null;
        private Agent a;
        private String conversationId;

        public Cancel(Agent _a, String _resource, String _conversationId) {
            super(_a);
            a = _a;
            resource = _resource;
            conversationId = _conversationId;
        }

        public void action() {
        }

        public boolean done() {

            return false;
        }
    } // End class Cancel


    /**
     * canceling services behavior
     * @author D05CNPM
     * @param resource: information about services need to book
     * @param a: agent execute this behavior
     */
    class Modify extends Behaviour {

        private String resource = null;
        private Agent a;
        private String conversationId;

        public Modify(Agent _a, String _resource, String _conversationId) {
            super(_a);
            a = _a;
            resource = _resource;
            conversationId = _conversationId;
        }

        public void action() {
        }

        public boolean done() {

            return false;
        }
    } // End class RequestCancel


    @Override
    public void takeDown() {
        if (xmlrpcServer != null) {
            log.debug("XMLRPC shut down  on " + port);
            xmlrpcServer.shutdown();
            xmlrpcServer = null;

        }
    }
}
