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
                                if (Protocol.HOTEL_AVAIL.equals(protocol)) {

                                    log.info("Call module DB with String input: " + content);

                                    content = search(content);// chi goi DB o day

                                    log.info("RETURN RESULT: " + content);
                                    ACLMessage reply = Message.createReplyMessage(msg, content);

                                    log.info("=== [HotelAgent] sent reply message " + reply);
                                    send(reply);

                                    finished = true;
                                } else if (Protocol.HOTEL_RES.equals(protocol)) {
                                    boolean booking = processBooking(msg.getContent());
                                    if (booking) {
                                        content = Message.SUCCESS;
                                    } else {
                                        content = Message.FAIL;
                                    }
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



            if (arr.get(0) != null) {
                ind.addProperty(Hotel.city, arr.get(0));

            }


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
        Database.LoadOnt2Database();


        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());

        /*   
        try {
        model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
        
         */


        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        ObjectProperty beginPoint = model.getObjectProperty(ont + "hasHotel");
        DatatypeProperty HotelName = model.getDatatypeProperty(ont + "HotelName");
        DatatypeProperty HotelName1 = model.getDatatypeProperty(ont + "city");
        OntClass cl = model.getOntClass(ont + "Msg_HotelSearchRS");
        long total = System.currentTimeMillis();

        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_HotelSearchRQ(input, total);
        model.add(ontmodel);

        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String s = "";


        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
            System.out.println("goi ham next");
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);

            System.out.println("The hien: " + resource.getLocalName());// respone

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());
            String result = printPropertyValues(individual, HotelName);
            System.out.println("result=" + result);
            s = printValues(result);

        }

        // xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
        Individual individual = model.getIndividual(ont + "Msg_HotelSearchRQ" + total);
        if (individual != null) {
            individual.remove();
        }
        Individual individual1 = model.getIndividual(ont + "Msg_HotelSearchRQ5");
        if (individual != null) {
            individual.remove();
        }
        return s;



    }

    /**
     * Hien thi thong tin
     * @param ind
     * @param prop
     */
    public static String printValues(String input) {
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen6.owl";

        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        String result = "";
        String output = "";
        //dua ontology vao 1 model
   /*     OntModel model = ModelFactory.createOntologyModel(
        //OntModelSpec.OWL_MEM_RULE_INF, null);
        PelletReasonerFactory.THE_SPEC, null);
        try {
        model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }      
         */

        Database.LoadOnt2Database();
        OntModel model = Database.getOntologyModel();
        Database.LoadOnt2Database();


        String queryString = null;

        for (int i = 0; i < arr.size(); i++) {
            result = "";
            queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" + "?x hotel:HotelLocation ?location. \n" + "?x hotel:hasHotelInfo ?info. \n" + "?info hotel:CheckIn ?checkIn. \n" + "?info hotel:CheckOut ?checkOut. \n" + "?info hotel:StarNumber ?star. \n" + "?info hotel:AreaWeather ?areaWeather. \n" + "?info hotel:hasRoomDetail ?roomdetail. \n" + "?roomdetail hotel:roomType ?roomtype. \n" + "?roomdetail hotel:hasPrice ?price. \n" + "?price hotel:Amount ?amount. \n" + "?price hotel:CurrencyCode ?currency. \n" + "?x hotel:hasContact ?contact. \n" + "?contact hotel:Email ?email. \n" + "?contact hotel:hasAddress ?Address. \n" + "?Address hotel:number ?number. \n" + "?Address hotel:street ?Street. \n" + "?Address hotel:city ?city. \n" + "?Address hotel:country ?Country. \n";


            queryString = queryString + "?hotel hotel:hotelFacilities ?facilities" + ".\n";
            queryString = queryString + " FILTER regex(?hotelname,\"" + arr.get(i) + "\", \"i\")}";

            Query query = QueryFactory.create(queryString);
            QueryExecution queryexec = QueryExecutionFactory.create(query, model);
            // System.out.print("thuc thi");
            //   Model model2 = ModelFactory.createDefaultModel();

            try {
                ResultSet rs = queryexec.execSelect();
                System.out.print("thuc thi");
                String facilities = "facilities";


                while (rs.hasNext()) {
                    System.out.print("ket qua");
                    //  model2 = rs.getResourceModel();
                    Object obj = rs.next();
                    ResultBinding binding = (ResultBinding) obj;
                    System.out.println("truy2:" + binding.toString());


                    String Address = "";

                    if (binding.getLiteral("number").getValue().toString() != null) {

                        String name = binding.getLiteral("number").getValue().toString();
                        int index = result.indexOf(name);
                        if (index < 0 || index > result.length()) {

                            Address = Address + name + " _ ";

                            if (binding.getLiteral("Street").getValue().toString() != null) {
                                String street = binding.getLiteral("Street").getValue().toString();
                                Address = Address + street + " _ ";
                            }
                            if (binding.getLiteral("city").getValue().toString() != null) {
                                String city = binding.getLiteral("city").getValue().toString();
                                Address = Address + city + " _ ";
                            }

                            if (binding.getLiteral("Country").getValue().toString() != null) {
                                String country = binding.getLiteral("Country").getValue().toString();
                                Address = Address + country;
                            }

                            result = result + Address + Message.FIELD_SEPARATE;


                            String area = binding.getLiteral("areaWeather").getValue().toString();
                            result = result + area + Message.FIELD_SEPARATE;



                            String checkIn = binding.getLiteral("checkIn").getValue().toString();
                            result = result + checkIn + Message.FIELD_SEPARATE;



                            String checkOut = binding.getLiteral("checkOut").getValue().toString();
                            result = result + checkOut + Message.FIELD_SEPARATE;



                            String email = binding.getLiteral("email").getValue().toString();
                            result = result + email + Message.FIELD_SEPARATE;



                            String hotelname = binding.getLiteral("hotelname").getValue().toString();
                            result = result + hotelname + Message.FIELD_SEPARATE;



                            String location = binding.getLiteral("location").getValue().toString();
                            result = result + location + Message.FIELD_SEPARATE;



                            String star = binding.getLiteral("star").getValue().toString();
                            result = result + star + Message.FIELD_SEPARATE;


                        }
                    }





                    String roomtype = binding.getLiteral("roomtype").getValue().toString();
                    int index = result.indexOf(roomtype);

                    if (index < 0 || index > result.length()) {
                        result = result + roomtype + Message.FIELD_SEPARATE +
                                binding.getLiteral("amount").getValue().toString() + Message.FIELD_SEPARATE +
                                binding.getLiteral("currency").getValue().toString() + Message.FIELD_SEPARATE;


                    }
                    String name = binding.getLiteral("facilities").getValue().toString();
                    int index1 = result.indexOf(roomtype);

                    if (index1 < 0 || index1 > facilities.length()) {

                        facilities = facilities + name + ",";


                    }

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            output = output + result + Message.OBJECT_SEPARATE;
        }
        System.out.println("ket qua:" + output);
        return result;
    }

    public static String printPropertyValues(Individual ind, Property prop) {
        System.out.print(ind.getLocalName() + " has " + prop.getLocalName() + "(s): ");
        String result = printIterator(ind.listPropertyValues(prop));
        return result;
    }

    public static String printInstances(OntClass cls) {
        System.out.print(cls.getLocalName() + " instances: ");
        String result = printIterator(cls.listInstances());
        return result;
    }

    public static String printIterator(ExtendedIterator i) {
        String result = "";
        if (!i.hasNext()) {
            System.out.print("none");
        } else {
            while (i.hasNext()) {
                Literal val = (Literal) i.next();
                System.out.print(val.getString());
                result = result + val.getString();
                if (i.hasNext()) {
                    result = result + Message.FIELD_SEPARATE;
                }
            }
        }

        return result;
    }

    // Thao tac voi yeu cau dat dich vu khach san
    public static OntModel insertMsg_HotelBookRQ(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelBookRQ");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelBookRQ" + total, oc);
            if (arr.get(0) != null) {
                ind.addProperty(Hotel.HotelName, arr.get(0));

            }
            if (arr.get(1) != null) {
                ind.addProperty(Hotel.city, arr.get(1));

            }
            if (arr.get(2) != null) {
                ind.addProperty(Hotel.number, arr.get(2));

            }
            if (arr.get(3) != null) {
                ind.addProperty(Hotel.street, arr.get(3));

            }
            if (arr.get(4) != null) {
                ind.addProperty(Hotel.roomType, arr.get(4));
            }
            if (arr.get(5) != null) {
                ind.addLiteral(Hotel.Number, Float.parseFloat(arr.get(5)));
            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }
    /*
    public static String processBooking(String input) {
    log.info("Starting Booking: " + input);
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
    
    DatatypeProperty result = model.getDatatypeProperty(ont + "Result");
    OntClass cl = model.getOntClass(ont + "Msg_HotelBookRS");
    
    
    log.info("Result Process Booking");
    // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
    Model ontmodel = insertMsg_HotelBookRQ(input, System.currentTimeMillis());
    Model model1 = model.add(ontmodel);
    ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day
    
    String s = null;
    
    
    // lay tat cac cac ket qua thoa man
    while (extendedIterator.hasNext()) {
    OntResource resource = (OntResource) extendedIterator.next();                   
    System.out.println("Tai Nguyen");
    Individual individual = model.getIndividual(ont + resource.getLocalName());
    //     s = ((Resource) individual.listPropertyValues(beginPoint).next()).toString();
    
    String Result = (individual.listPropertyValues(result).next()).toString();
    
    System.out.println("---------------------------------------------------------------" + s);
    //Model hotelName = individual.getOntClass().getModel();
    
    
    //hotelName.write(System.out);
    
    
    int index = Result.indexOf("^^");
    String hotelname = Result.substring(0, index);
    System.out.println("" + hotelname);
    // co nghia la no ko in ra hotelname a?
    // ko goi duoc ket qua ay, ko goi duoc cac ham trong lop nay. vo ly nhi ^^|
    
    s = printValues(hotelname);
    
    }
    
    
    return s;
    
    
    
    }
     */

    public static boolean processBooking(String input) {
        Database.LoadOnt2Database();

        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
        // chuyen nguoc lai tu csdl -> OntModel

        String ont = "http://www.owl-ontologies.com/Travel.owl#";
        System.out.println("booking");
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        System.out.println("arr: " + arr.toString());
        String queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" +
                "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" +
                "?x hotel:hasHotelRoom ?hotelRoom. \n" + "?x hotel:hasContact ?contact. \n" +
                "?contact hotel:hasAddress ?add. \n" + "?add hotel:city ?city. \n" +
                "?add hotel:street ?street. \n" + "?add hotel:number ?number. \n" +
                "?hotelRoom hotel:roomType ?roomType. \n" + "?hotelRoom hotel:Number ?total. \n" +
                "?hotelRoom hotel:hasNotAvailabilityPeriod ?notAvail. \n" +
                "?notAvail hotel:roomType ?roomType1. \n" + "?notAvail hotel:ToDate ?toDate. \n" +
                "?notAvail hotel:FromDate ?fromDate. \n" + "?notAvail hotel:Number ?number1. \n" +
                " FILTER ( regex(?hotelname,\"" + arr.get(0) + "\", \"i\"))" + " FILTER ( regex(?city,\"" +
                arr.get(1) + "\", \"i\"))" + " FILTER ( regex(?number,\"" + arr.get(2) + "\", \"i\"))" +
                " FILTER ( regex(?street,\"" + arr.get(3) + "\", \"i\"))" + " FILTER ( regex(?roomType,\"" +
                arr.get(4) + "\", \"i\"))" + " FILTER ( regex(?roomType1,\"" + arr.get(4) + "\", \"i\"))}";

        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);



        float numberNotAvail = 0, Number = 0;
        boolean isOk = false;
        String notavail = null; //  the hien co khoang thoi gian dat truoc trung voi khoang thoi gian muon dat

        float number = 0; //  luu gia tri cu

        String hotelroom = null;

        try {
            ResultSet rs = queryexec.execSelect();

            float total = 0;
            float maxTotal = 0;


            while (rs.hasNext()) {
                System.out.print("co gia tri thoa man");

                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("ket qua truy van :" + binding.toString());

                total = Float.parseFloat(binding.getLiteral("total").getValue().toString());

                String todate = binding.getLiteral("toDate").getValue().toString();
                System.out.println("ToDate truy van :" + todate);
                String fromdate = binding.getLiteral("fromDate").getValue().toString();
                System.out.println("FromDate truy van :" + fromdate);
                Number = Float.parseFloat(binding.getLiteral("number1").getValue().toString());

                // Tim ra khoang dat gan nhat phia tren ngay muon dat
                hotelroom = binding.getResource("hotelRoom").toString();
                System.out.println("hotel:" + hotelroom);


                //TH 0: ca 2 cung trung voi 1 ngay da duoc dat truoc   
                if (fromdate.equalsIgnoreCase(arr.get(6)) && todate.equalsIgnoreCase(arr.get(7))) {
                    number = Number;
                    System.out.println("truong hop 0");
                    notavail = binding.getResource("notAvail").toString();

                    numberNotAvail = numberNotAvail + Number;
                }

                // TH1: fromdate < arr.get(6) < todate
                if (fromdate.compareTo(arr.get(6)) < 0 && todate.compareTo(arr.get(6)) > 0 && arr.get(7).compareTo(todate) > 0) {
                    numberNotAvail = numberNotAvail + Number;
                // TH2: fromdate < arr.get(7) < todate
                }
                if (fromdate.compareTo(arr.get(7)) < 0 && todate.compareTo(arr.get(7)) > 0 && fromdate.compareTo(arr.get(6)) > 0) {
                    numberNotAvail = numberNotAvail + Number;
                // TH3: fromdate < arr.get(6) < arr.get(7) < todate
                }
                if (fromdate.compareTo(arr.get(6)) < 0 && todate.compareTo(arr.get(7)) > 0) {
                    numberNotAvail = numberNotAvail + Number;
                // TH4: arr.get(6) < fromdate <  todate <arr.get(7);
                }
                if (fromdate.compareTo(arr.get(6)) > 0 && todate.compareTo(arr.get(7)) < 0) {
                    numberNotAvail = numberNotAvail + Number;
                // TH5:fromdate hoac todate bi trung
                }
                if ((fromdate.equals(arr.get(6)) && todate.compareTo(arr.get(7)) < 0) || (fromdate.compareTo(arr.get(6)) > 0 && todate.equals(arr.get(7)))) {
                    System.out.println("truong hop 5");
                    numberNotAvail = numberNotAvail + Number;
                }

            // sau khi xac dinh duoc cac khoang can ke

            }

            float newNumber = numberNotAvail + Float.parseFloat(arr.get(5));
            System.out.println(" ");
            System.out.println(" ");

            System.out.println("Tong so phong da bi dat truoc: " + numberNotAvail + "total = " + total);
            if (newNumber <= total) {
                if (notavail != null) {
                    Individual ind = model.getIndividual(notavail);
                    if (ind != null) {

                        System.out.println("co khoang trung");
                        ind.removeAll(Hotel.Number);
                        ind.addLiteral(Hotel.Number, number + Float.parseFloat(arr.get(5)));
                        isOk = true;

                    }
                } else {

                    OntModel model1 = ModelFactory.createOntologyModel();
                    try {
                        System.out.println("them gia tri");
                        OntClass oc = model1.createClass("http://www.owl-ontologies.com/Travel.owl#NotAvailabilityPeriod");
                        Individual ind = model1.createIndividual(ont + "NotAvailabilityPeriod_" + System.currentTimeMillis(), oc);
                        ind.addLiteral(Hotel.roomType, arr.get(4));
                        ind.addLiteral(Hotel.ToDate, arr.get(7));
                        ind.addLiteral(Hotel.FromDate, arr.get(6));

                        ind.addLiteral(Hotel.Number, Float.parseFloat(arr.get(5)));

                        System.out.println("hotelRoom: " + hotelroom);
                        Individual hotelRoomInd = model.getIndividual(hotelroom);
                        hotelRoomInd.addProperty(Hotel.hasNotAvailabilityPeriod, ind);

                        isOk = true;
                        System.out.println("Them the hien NotAvailability thanh cong");

                        model1.write(System.out);
                        model.add(model1);

                    } catch (Exception e) {
                        System.out.println(e.toString());
                        isOk = false;
                    }
                    isOk = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isOk = false;
        }

        /*   if(isOk == true){
        System.out.println("xu ly lay gia cuoi cung");
        OntModel ontmodel = HotelProcess.insertMsg_HotelBookRQ(input, System.currentTimeMillis());
        model.add(ontmodel);
        
        DatatypeProperty price =model.getDatatypeProperty(ont + "Result");
        OntClass cl = model.getOntClass(ont + "Msg_HotelBookRS");   
        
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day
        
        String s = null;
        System.out.println("ngoai next");
        
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
        System.out.println("trong next");
        OntResource resource = (OntResource) extendedIterator.next();
        
        Individual individual = model.getIndividual(ont + resource.getLocalName());                   
        String hotelName =  (individual.listPropertyValues(price).next()).toString();
        int index = hotelName.indexOf("^^");
        String hotelname = hotelName.substring(0, index);
        System.out.println("" +hotelName );
        
        
        }
         */

        // }
        return isOk;
    }
}
