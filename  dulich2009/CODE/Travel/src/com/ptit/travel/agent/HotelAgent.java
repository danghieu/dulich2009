/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent;
import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.ptit.travel.agent.communication.Message;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.ptit.travel.jane.hotel.Hotel;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.ptit.travel.moduleJDBC.Model.*;
import java.util.*;
import com.hp.hpl.jena.rdf.model.*;

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

        public HandleRecivedMessages() {
        }

        public void action() {            
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
                                    content = processBooking(msg.getContent());
                                    
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

    /**
     *  Message yeu cau tim kiem tat ca cac khach san tai 1 thanh pho nao day
     * @param input : city
     * @param total: Bien xac dinh tinh duy nhat cua Msg
     * @return: Tap hop cac khach san thoa man
     */
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
            ind.addProperty(Hotel.city, arr.get(0));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        model.write(System.out);
        return model;
    }

    /**
     *  Message yeu cau tim kiem tat ca cac khach san tai 1 thanh pho, co vi tri cu the (vi du: gan bien, gan trung tam), thoi tiet cu the (vi du: khi hau mat me).
     * @param input: City - location  - Weather 
     * @param total
     * @return: tra lai tat ca cac khach san thoa man
     */
    public static OntModel insertMsg_HotelSearchRQFull(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        log.info("Resulted SPLIT: " + arr.toString());

        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;
        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQFull");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQFull" + total, oc);
            ind.addLiteral(Hotel.city, arr.get(0));
            ind.addLiteral(Hotel.AreaWeather, arr.get(2));
            ind.addLiteral(Hotel.HotelLocation, arr.get(1));

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }

    /**
     * Mesage yeu cau tim kiem theo thoi tiet
     * @param input: weather
     * @param total
     * @return: tra ve khach san thoa man
     */
    public static OntModel insertMsg_HotelSearchRQ2(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        log.info("Resulted SPLIT: " + arr.toString());
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQ2");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQ2" + total, oc);

            if (arr.get(2) != null) {
                ind.addLiteral(Hotel.AreaWeather, arr.get(2));
            }

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }

    /**
     * Message yeu cau tim kiem theo vi tri cua khach san
     * @param input: Location
     * @param total
     * @return: tra ve khach san thoa man
     */
    public static OntModel insertMsg_HotelSearchRQ3(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        log.info("Resulted SPLIT: " + arr.toString());
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQ3");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQ3" + total, oc);

            if (arr.get(1) != null) {
                ind.addLiteral(Hotel.HotelLocation, arr.get(1));
            }
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }

    /**
     * Dua vao luat duoc xay dung trong Ontology de lay ra cac khach san thoa man
     * @param input: la cac thong tin yeu cau
     * @return
     */
    public static String search(String input) {
        log.info("Starting search with: " + input);
        String ont = "http://www.owl-ontologies.com/Travel.owl#";
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        Database.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());

        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        ObjectProperty beginPoint = model.getObjectProperty(ont + "hasHotel");
        DatatypeProperty HotelName = model.getDatatypeProperty(ont + "HotelName");
        DatatypeProperty HotelName1 = model.getDatatypeProperty(ont + "city");
        OntClass cl = model.getOntClass(ont + "Msg_HotelSearchRS");
        long total = System.currentTimeMillis();

        log.info("Insert msg to infer");
        String nameMsg = "";

        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        OntModel ontmodel = ModelFactory.createOntologyModel();
        if (arr.get(0).equals(" ") && !arr.get(1).equalsIgnoreCase(" ") && arr.get(2).equals(" ")) {
            ontmodel = insertMsg_HotelSearchRQ3(input, total);
            nameMsg = "Msg_HotelSearchRQ3";

        }

        if (!arr.get(0).equalsIgnoreCase(" ") && !arr.get(1).equalsIgnoreCase(" ") && !arr.get(2).equals("")) {
            ontmodel = insertMsg_HotelSearchRQFull(input, total);
            nameMsg = "Msg_HotelSearchRQFull";
        }

        if (arr.get(0).equals(" ") && arr.get(1).equals(" ") && !arr.get(2).equalsIgnoreCase(" ")) {
            ontmodel = insertMsg_HotelSearchRQ2(input, total);
            nameMsg = "Msg_HotelSearchRQ2";
        }

        if (!arr.get(0).equalsIgnoreCase(" ") && arr.get(1).equals(" ") && arr.get(2).equals(" ")) {
            ontmodel = insertMsg_HotelSearchRQ(input, total);
            nameMsg = "Msg_HotelSearchRQ";
        }
        model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String output = "";
        String result="";
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
            OntResource resource = (OntResource) extendedIterator.next();
            Individual individual = model.getIndividual(ont + resource.getLocalName());
            result = result  + printPropertyValues(individual, HotelName)+Message.FIELD_SEPARATE;
            System.out.println("result=" + result);          

            // xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
            individual = model.getIndividual(Hotel.getURI() + nameMsg + total);
            if (individual != null) {
                individual.remove();
            }
        }
        output = printValues(result);
        return output;
    }

    /**
     * Hien thi thong tin chi tiet ve cac k hach san theo yeu cau
     * @param input: Ten cac khach san muon hien thi thong tin chi tiet     
     * output: address ( number -street - city) - areaweather -checin - checkout - email -  location - star - (roomType - amount - total): có nhieu + facilities
     */
    public static String printValues(String input) {
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen10.owl";
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        String result; // Thong tin cua 1 khach san thoa man
        String facilities;
        String output = ""; // Thong tin tat ca cac khach san   

        Database.LoadOnt2Database();
        OntModel model = Database.getOntologyModel();
        String queryString = null;
        for (int i = 0; i < arr.size(); i++) {
            result = "";
            facilities = "";
            queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
                    + "SELECT DISTINCT * \n " 
                    + "WHERE \n"
                    + "{ \n" 
                    + "?x hotel:HotelName ?hotelname. \n" 
                    + "?x hotel:HotelLocation ?location. \n"
                    + "?x hotel:hasHotelInfo ?info. \n" 
                    + "?info hotel:CheckIn ?checkIn. \n"
                    + "?info hotel:CheckOut ?checkOut. \n" 
                    + "?info hotel:StarNumber ?star. \n"
                    + "?info hotel:AreaWeather ?areaWeather. \n"
                    + "?info hotel:hasRoomDetail ?roomdetail. \n" 
                    + "?roomdetail hotel:roomType ?roomtype. \n"
                    + "?roomdetail hotel:hasPrice ?price. \n" 
                    + "?price hotel:Amount ?amount. \n" 
                    + "?price hotel:CurrencyCode ?currency. \n" 
                    + "?x hotel:hasContact ?contact. \n"
                    + "?contact hotel:Email ?email. \n" 
                    + "?contact hotel:hasAddress ?Address. \n"
                    + "?Address hotel:number ?number. \n"
                    + "?Address hotel:street ?Street. \n"
                    + "?Address hotel:city ?city. \n" 
                    + "?Address hotel:country ?Country. \n";
            queryString = queryString + "?hotel hotel:hotelFacilities ?facilities" + ".\n";
            queryString = queryString + " FILTER regex(?hotelname,\"" + arr.get(i) + "\", \"i\")}";
            Query query = QueryFactory.create(queryString);
            QueryExecution queryexec = QueryExecutionFactory.create(query, model);

            try {
                ResultSet rs = queryexec.execSelect();              

                while (rs.hasNext()) {                  
                    Object obj = rs.next();
                    ResultBinding binding = (ResultBinding) obj;
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
                        result = result + roomtype + Message.FIELD_SEPARATE + binding.getLiteral("amount").getValue().toString() + Message.FIELD_SEPARATE + binding.getLiteral("currency").getValue().toString() + Message.FIELD_SEPARATE;
                    }
                    String name = binding.getLiteral("facilities").getValue().toString();
                    int index1 = facilities.indexOf(name);

                    if (index1 < 0 || index1 > facilities.length()) 
                        facilities = facilities + name + ",";        

                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
            output = output + result + Message.FIELD_SEPARATE + facilities + Message.OBJECT_SEPARATE;
        }
        System.out.println("ket qua:" + output);
        return output;
    }

    /**
     * In chi tiet cac thuoc tinh cua 1 the hien nao day.
     * @param ind: 
     * @param prop: thuoc tinh can hien thi
     * @return
     */
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

    /**
     * Message yeu cau dat 1 dich vu khach san
     * @param input: HotelName - City - number-street - roomType -Number -  fromdate - todate - fullName - Profession - IdentityCard 
     * @param total: bien xac dinh tinh duy nhat cua Msg
     * @return: 1 model chua thong tin dung trong viec xu ly dat dich vu khach san.
     */
public static OntModel insertMsg_HotelBookRQ(String input) {
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;
       
        try {
             float numberOfDay = subDay(arr.get(6), arr.get(7), "yyyy-mm-dd");
            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRQ");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelBookRQ" + System.currentTimeMillis(), oc);
            ind.addLiteral(Hotel.HotelName, arr.get(0));
            ind.addLiteral(Hotel.city, arr.get(1));
            ind.addLiteral(Hotel.number, arr.get(2));
            ind.addLiteral(Hotel.street, arr.get(3));
            ind.addLiteral(Hotel.roomType, arr.get(4));
            ind.addLiteral(Hotel.Number, Float.parseFloat(arr.get(5)));
            ind.addLiteral(Hotel.fullName, arr.get(8));
            ind.addLiteral(Hotel.profession, arr.get(9));
            ind.addLiteral(Hotel.IdentityCard, arr.get(10));
            ind.addLiteral(Hotel.numberOfDay, numberOfDay);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        model.write(System.out);
        return model;
    }


    /**
     *  Xu ly dat dich vu theo cac thong tin yeu cau 
     * @param input thong tin yeu cau
     HotelName - City - number-street - roomType -Number -  fromdate - todate - fullName - Profession - IdentityCard 
     * @return Dat duoc hoac khong dat duoc
     */
      public static String processBooking(String input) {
        Database.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
        String ont = "http://www.owl-ontologies.com/Travel.owl#";
        System.out.println("booking");
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);
        System.out.println("arr: " + arr.toString());
        String queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" 
                + "SELECT DISTINCT * " 
                + "WHERE { "
                + "?x hotel:HotelName ?hotelname. " 
                + "?x hotel:hasContact ?contact. "
                + "?contact hotel:hasAddress ?Address. "
                + "?Address hotel:number ?add_number. " 
                + "?Address hotel:street ?add_street. "
                + "?Address hotel:city ?add_city. "
                + "?x hotel:hasHotelRoom ?hotel_Room." 
                + "?hotel_Room hotel:roomType ?room_Type."
                + "?hotel_Room hotel:Number ?total. "
                + "?hotel_Room hotel:hasNotAvailabilityPeriod ?notAvail. "
                + "?notAvail hotel:roomType ?room_Type1. "
                + "?notAvail hotel:ToDate ?to_Date. "
                + "?notAvail hotel:FromDate ?from_Date."
                + "?notAvail hotel:Number ?number1. "
                + " FILTER ( regex(?hotelname,\"" + arr.get(0) + "\"))" 
                + " FILTER ( regex(?add_city,\"" + arr.get(1) + "\"))" 
                + " FILTER ( regex(?add_number,\"" + arr.get(2) + "\"))"
                + " FILTER ( regex(?add_street,\"" + arr.get(3) + "\"))" 
                + " FILTER ( regex(?room_Type,\"" + arr.get(4) + "\"))"
                + " FILTER ( regex(?room_Type1,\"" + arr.get(4) + "\"))}";

        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        
        // NumberNotAvail: So phong da bi truoc trong khoang thoi gian yeu cau.
        // Number: Tong so phong loai yeu cau ma khach san do co
        float numberNotAvail = 0, Number = 0;
        boolean isOk = false;
        String notavail = null; //  the hien co khoang thoi gian dat truoc trung voi khoang thoi gian muon dat
        float number = 0; //  Gia tri da duoc dat truoc trong khoang thoi gian arr.get(6) - arr.get(7)
        String hotelroom = null;
        
        try {
            ResultSet rs = queryexec.execSelect();
            float total = 0;
            float maxTotal = 0;  

            while (rs.hasNext()) {           
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;         
                total = Float.parseFloat(binding.getLiteral("total").getValue().toString());
                String todate = binding.getLiteral("to_Date").getValue().toString();
                String fromdate = binding.getLiteral("from_Date").getValue().toString();       
               
                // So luong da bi dat trong khoang thoi gian tu  fromDat - ToDate
                Number = Float.parseFloat(binding.getLiteral("number1").getValue().toString());            
                hotelroom = binding.getResource("hotel_Room").toString();
                
                //TH 0: ca 2 cung trung voi 1 ngay da duoc dat truoc   
                if (fromdate.equalsIgnoreCase(arr.get(6)) && todate.equalsIgnoreCase(arr.get(7))) {
                    number = Number;
                    System.out.println("truong hop 0");
                    notavail = binding.getResource("notAvail").toString();
                    numberNotAvail = numberNotAvail + Number;
                }

                // TH1: fromdate < arr.get(6) < todate
                if (fromdate.compareTo(arr.get(6)) < 0 && todate.compareTo(arr.get(6)) > 0 && arr.get(7).compareTo(todate) > 0) 
                    numberNotAvail = numberNotAvail + Number;           
                
                
                // TH2: fromdate < arr.get(7) < todate
                if (fromdate.compareTo(arr.get(7)) < 0 && todate.compareTo(arr.get(7)) > 0 && fromdate.compareTo(arr.get(6)) > 0) 
                    numberNotAvail = numberNotAvail + Number;
              
                // TH3: fromdate < arr.get(6) < arr.get(7) < todate                
                if (fromdate.compareTo(arr.get(6)) < 0 && todate.compareTo(arr.get(7)) > 0) 
                    numberNotAvail = numberNotAvail + Number;
                
                // TH4: arr.get(6) < fromdate <  todate <arr.get(7);                
                if (fromdate.compareTo(arr.get(6)) > 0 && todate.compareTo(arr.get(7)) < 0) 
                    numberNotAvail = numberNotAvail + Number;                
               
                // TH5:fromdate hoac todate bi trung
                if ((fromdate.equals(arr.get(6)) && todate.compareTo(arr.get(7)) < 0) || (fromdate.compareTo(arr.get(6)) > 0 && todate.equals(arr.get(7)))) 
                    numberNotAvail = numberNotAvail + Number;    
            }  
            
            float newNumber = numberNotAvail + Float.parseFloat(arr.get(5));      
            System.out.println("Tong so phong da bi dat truoc: " + numberNotAvail + "total = " + total);
            
            if (newNumber <= total) {
           
            // Truong hop trung mot khoang thoi gian da duoc dat    => chi cap nhat so luong
                if (notavail != null) {
                    Individual ind = model.getIndividual(notavail);
                    if (ind != null) {
                        System.out.println(" Cap nhat them so luong dat");
                        ind.removeAll(Hotel.Number);
                        ind.addLiteral(Hotel.Number, number + Float.parseFloat(arr.get(5)));
                        isOk = true;
                    }
                }
                
             // khong bi trung => tao ra 1 the hien cua lop NotAvailability
                else {
                    OntModel model1 = ModelFactory.createOntologyModel();
                    try {
                        System.out.println("tao 1 the hien moi");
                        OntClass oc = model1.createClass("http://www.owl-ontologies.com/Travel.owl#NotAvailabilityPeriod");
                        Individual ind = model1.createIndividual(ont + "NotAvailabilityPeriod_" + System.currentTimeMillis(), oc);
                        ind.addLiteral(Hotel.roomType, arr.get(4));
                        ind.addLiteral(Hotel.ToDate, arr.get(7));
                        ind.addLiteral(Hotel.FromDate, arr.get(6));
                        ind.addLiteral(Hotel.Number, Float.parseFloat(arr.get(5)));
                      
                        // the hien nay la thuoc tinh cua hotelroom
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

        if (isOk == false) {
            System.out.println("khong dat duoc");
            return "Dat Khong Thanh Cong";
        }

        String totalprice = processCustomer(isOk, input);
        String result = "Dat Thanh Cong" +Message.FIELD_SEPARATE + totalprice;
        return result;
    }

    /**
     *Xu ly thong tin khach hang khi khach hang muon dat 1 dich vu
     * input:HotelName - City -Address Number  _Street - Roomtype - Quantity - BeginTime - ToTime -   email  - fullName - Profession - IdentityCard  - faxOfCustomer -  phoneNumber + specificAddress 
     * @param input
     * @return
     */
    public static String processCustomer(boolean b, String input) {
      String totalprice="";
        if (b == false) {
            System.out.println("khong dat duoc");
            return totalprice;
        }
        String ont = "http://www.owl-ontologies.com/Travel.owl#";
        Database.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
        OntModel ontmodel = insertMsg_HotelBookRQ(input);
        model.add(ontmodel);
        DatatypeProperty price = model.getDatatypeProperty(ont + "price");
        OntClass cl = model.getOntClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRS");       
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day
        String s = null;
     
        // Gia sau khi tru discout theo tung loai khach hang
        while (extendedIterator.hasNext()) {          
            OntResource resource = (OntResource) extendedIterator.next();
            Individual individual = model.getIndividual(ont + resource.getLocalName());
            String price2 = (individual.listPropertyValues(price).next()).toString();
            int index = price2.indexOf("^^");
            String price1 = price2.substring(0, index);
            System.out.println("Gia khach hang phai tra: " + price1);
            totalprice = price1;
            // xoa the hien cua MsgBookRQ va RS trong model
            individual.remove();
        }
        
      // Quan ly thong tin khach hang
        try {
            ArrayList<String> arr = new ArrayList<String>();
            arr = Message.split(input, Message.FIELD_SEPARATE);
            System.out.println("them khach hang");
            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass("http://www.owl-ontologies.com/Travel.owl#Customer");
            Individual ind = model.getIndividual("http://www.owl-ontologies.com/Travel.owl#Customer_" + arr.get(10));
            if (ind != null) {
                System.out.println("Co khach hang nay trong sdl");
                return totalprice;
            }

            ind = model.createIndividual("http://www.owl-ontologies.com/Travel.owl#Customer_" + arr.get(10), oc);            
            ind.addLiteral(Hotel.Email, arr.get(8));
            ind.addLiteral(Hotel.fullName, arr.get(9));
            ind.addLiteral(Hotel.profession, arr.get(10));
            ind.addLiteral(Hotel.IdentityCard, arr.get(11));
            ind.addLiteral(Hotel.PhoneNumer, arr.get(12));
            ind.addLiteral(Hotel.fax, arr.get(13));
            ind.addLiteral(Hotel.specificAddress, arr.get(14));
            ind.addLiteral(Hotel.specificAddress, arr.get(14));
        } catch (Exception e) {
        }
        return totalprice;
    }

/**
 * Lay tat ca cac the hien cua lop NotAvailabilityPeriod - Dung de test xem gia tri dat co duoc dua vao csdl khong
 */    
    public static void searchNotAvailability() {
        Database.LoadOnt2Database();
        OntModel model = Database.getOntologyModel();
        String ont = "http://www.owl-ontologies.com/Travel.owl#";      
        DatatypeProperty roomtype = model.getDatatypeProperty(ont + "roomType");
        DatatypeProperty number = model.getDatatypeProperty(ont + "Number");
        DatatypeProperty todate = model.getDatatypeProperty(ont + "ToDate");
        DatatypeProperty fromdate = model.getDatatypeProperty(ont + "FromDate");
        OntClass cl = model.getOntClass(ont + "NotAvailabilityPeriod");
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
            OntResource resource = (OntResource) extendedIterator.next();    
            System.out.println("The hien: " + resource.getLocalName());  
            Individual individual = model.getIndividual(ont + resource.getLocalName());
            String number1 = (individual.listPropertyValues(number).next()).toString();
            System.out.println("So luong da dat: " + number1);
            String fromdate1 = (individual.listPropertyValues(fromdate).next()).toString();       
            System.out.println("FromDate: " + fromdate1);
            String todate1 = (individual.listPropertyValues(todate).next()).toString();
            System.out.println("ToDate: " + todate1);            
            String room = (individual.listPropertyValues(roomtype).next()).toString();        
            System.out.println("Loai phog dat: " + room);
        }
    }
     public static float subDay(String fromDate, String toDate, String pattern) throws Exception {
        float days = -1;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date1 = format.parse(fromDate);
            long time1 = date1.getTime();
            Date date2 = format.parse(toDate);
            long time2 = date2.getTime();
            days = (float) ((time2 - time1) / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;//ParseException();
        }
        return days;
    }

}
