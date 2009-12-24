/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;

import com.hp.hpl.jena.rdf.model.Resource;

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
 * Hotel Agent runs also XML-RPC server on port 8000 and public methods can be
 * called by outside Behaviors:
 * 
 * 
 * 
 * Each behavior executed by two methods and one inner class. For example
 * 
 * @version 0.5
 */
public class HotelAgent extends Agent {

    private static Logger log = Logger.getLogger(HotelAgent.class.getName());
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
        HotelProcess hotel = new HotelProcess(); 

        public HandleRecivedMessages() {
        }

        public void action() {
            log.info("=== HOTELAGENT is Ready now");
            synchronized (this) {
                ACLMessage msg = receive();
                if (msg != null) {     
                    
                    try {                        
                        String content = msg.getContent();
                        log.info("=== [HotelAgent] received from " + msg.getSender().getLocalName());
                    switch (msg.getPerformative()) {
                        case ACLMessage.QUERY_REF:
                            break;

                        case ACLMessage.INFORM:

                               
                            // action of message <-> protocol of ACL
                            String protocol = msg.getProtocol();
                            //if (Protocol.HOTEL_AVAIL.equals(protocol)) 
                            {
                                // FOR TEST
                                log.info("Call module DB with String input: " + content);// chu nay hien thi ra roi con ju
                               
//                                OntModel ont = ModelFactory.createOntologyModel();
//                                System.out.println("hotel agent ontmodel");// nhung dong nay co thay no chay dau
//                                ont = HotelProcess.insertMsg_HotelSearchRQ(content, 3);
//                                
//                                ont.write(System.out);// may in ra o day con j, thi dag thu goi den co chay ko ma
//                     
                                
                                content = search(content);// chi goi DB o day
                                log.info("RETURN RESULT: " + content);
                                ACLMessage reply = Message.createReplyMessage(msg, content);
                                
                                log.info("=== [HotelAgent] sent reply message " + reply);
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
            ;
            

        }
        

        public boolean done() {
            return finished;
        }
    } // end of HandleRecivedMessages


    /**
     * This method is called by whatever GUI implementation In current
     * implementation it is called by XML-RPC through XMLRPC webserver Method
     * send Resource which should be search by SupplierAgent <br>
     * you need to call
     * external system
     * 
     * @param resource
     *            represents type of resource in Memory Model (OWL model)
     * 
     
    public String search(String resource) {
        final String msgId = "102";// getLocalName() +
        // System.currentTimeMillis();

        ArrayList<String> msgs = new ArrayList<String>();
        addBehaviour(new CommunicationHotel(this, resource, msgId, msgs));

        System.out.println(System.currentTimeMillis());
        return "ok";
    }
*/
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
    class CommunicationHotel extends Behaviour {

        private int repliesCnt = 0; // The counter of replies from agents

        private MessageTemplate mt; // The template to receive replies

        private int step = 0;
        private String resource = null;
        private ArrayList<String> receivers;
        private String msgId;
        private Agent a;
        private boolean avail = false;
        private ArrayList<String> msgs = new ArrayList<String>();

        public CommunicationHotel(Agent _a, String _resource, String _msgId,
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
                    receivers.add("HotelAgent");
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
    
            public static OntModel insertMsg_HotelSearchRQ(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQ");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQ" + total, oc);

            // them cac thuoc tinh vao ca the can tao
//            if (arr.get(3) != null) {
//                ind.addProperty(Hotel.roomType, arr.get(3));
//
//            }
//
//
//            if (arr.get(1) != null) {
//                ind.addProperty(Hotel.beginTime, arr.get(1));
//
//            }
//
//            if (arr.get(2) != null) {
//                ind.addProperty(Hotel.endTime, arr.get(2));
//
//            }

            if (arr.get(0) != null) {
                ind.addProperty(Hotel.city, arr.get(0));

            }


//            if (arr.get(4) != null) {
//                ind.addProperty(Hotel.hasActivity, arr.get(4));
//
//            }
//            if (arr.get(5) != null) {
//                ind.addProperty(Hotel.hotelActivities, arr.get(5));
//
//            }

        /* -- trong truong hop du lieu la so  
        if (arr.get(2) != null) {
        //	ind.addProperty(Hotel.LargestSeatingCapacity,arr.get(4));
        //ind.addLiteral(Hotel.LargestSeatingCapacity, Integer.parseInt(arr.get(2)));
        ind.addLiteral(Hotel.LargestSeatingCapacity, Float.parseFloat(arr.get(2)));
        
        
        System.out.print("giatri: " + Float.parseFloat(arr.get(4)));
        }
         */



        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }

    /**
     * Tra ve danh sach ten cac khach san thoa man
     * @param ontmodel: model chua thong tin yeu cau tim kiem
     * @return
     */
    public static String search(String input) {
        log.info("Starting search with: " + input);
        //  Database.LoadOnt2Database();
        String ont = "http://www.owl-ontologies.com/Travel.owl#";
        // lay khung du lieu tu owl
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen6.owl";

        //dua ontology vao 1 model
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, null);
        try {
            model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        ObjectProperty beginPoint = model.getObjectProperty(ont + "hasHotel");
        DatatypeProperty HotelName =model.getDatatypeProperty(ont + "HotelName");
        OntClass cl = model.getOntClass(ont + "Msg_HotelSearchRS");
       

        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_HotelSearchRQ(input, 3);
        Model model1 = model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String s = null;

        
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());            
       //     s = ((Resource) individual.listPropertyValues(beginPoint).next()).toString();
          
           String hotelName =  (individual.listPropertyValues(HotelName).next()).toString();

             System.out.println("---------------------------------------------------------------" +s );
           //Model hotelName = individual.getOntClass().getModel();

           
           //hotelName.write(System.out);
         

            int index = hotelName.indexOf("^^");
            String hotelname = hotelName.substring(0, index);
               System.out.println("" +hotelname );
            // co nghia la no ko in ra hotelname a?
               // ko goi duoc ket qua ay, ko goi duoc cac ham trong lop nay. vo ly nhi ^^|

           s = printValues(hotelname);
            
        }

        
        return s;



    }
        
 public static String printValues(String s) {
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen6.owl";

        //dua ontology vao 1 model
        OntModel model = ModelFactory.createOntologyModel(
                //OntModelSpec.OWL_MEM_RULE_INF, null);
                PelletReasonerFactory.THE_SPEC, null);
        try {
            model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         Database.LoadOnt2Database();
        Model om = Database.getOntologyModel();
        
        String queryString = null;


        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" +
                "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" +
                " FILTER regex(?hotelname,\"" +s + "\", \"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, om);
       // System.out.print("thuc thi");
        Model model2 = ModelFactory.createDefaultModel();
        String name="";
        try {
            ResultSet rs = queryexec.execSelect();
         //   System.out.print("thuc thi" + rs.toString());
            while (rs.hasNext()) {
           //     System.out.print("thuc thi");
                model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString());
                name = binding.getLiteral("hotelname").getValue().toString();
		System.out.println("name:"+name);	
	} 
            }
         catch (Exception e1) {
            e1.printStackTrace();
        }
        return name;
    }

    
}
