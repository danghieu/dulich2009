/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.Flight;
import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.ptit.travel.agent.communication.Message;
import java.io.*;

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



import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.ptit.travel.jane.Flight.Flight;
import com.ptit.travel.moduleJDBC.Model.FlightDatabase;
import org.apache.log4j.Logger;

/**
 *
 * @author Thao Hoang
 */
public class FlightProcess{
    
    private static Logger log = Logger.getLogger(FlightProcess.class.getName());
    public static OntModel insertMsg_FlightAvailRQ(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Flight.getURI() + "Msg_FlightAvailRQ");
            
            ind = model.createIndividual(Flight.getURI() + "Msg_FlightAvailRQ" + total, oc);
             // them cac thuoc tinh vao ca the can tao           
            if (arr.get(0) != null) {
            ind.addProperty(Flight.departureAirportCity, arr.get(0));

            }
            
            if (arr.get(1) != null) {
            ind.addProperty(Flight.arrivalAirportCity, arr.get(1));

            }
     
             if (arr.get(2) != null) {
            ind.addLiteral(Flight.date, arr.get(2));

            }   
            if (arr.get(3) != null) {
            ind.addLiteral(Flight.flightClass, arr.get(3));

            }  
            if (arr.get(4) != null) {
     
            ind.addLiteral(Flight.bookNumber,Float.parseFloat(arr.get(4)));
            System.out.print("gia tri="+Float.parseFloat(arr.get(4)));

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
        String ont = "http://www.owl-ontologies.com/Flight.owl#";
        // lay khung du lieu tu owl
        String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Flight.owl";

        //dua ontology vao 1 model
        FlightDatabase.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, FlightDatabase.getOntologyModel());
       

        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        
        ObjectProperty flightManager = model.getObjectProperty(ont + "hasFlightManager");
       // ObjectProperty flightAgentInfo = model.getObjectProperty(ont + "hasAgentFlightInfo");
        DatatypeProperty id=model.getDatatypeProperty(ont+"id");
        
        OntClass cl = model.getOntClass(ont + "Msg_FlightAvailRS");
       
        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_FlightAvailRQ(input, System.currentTimeMillis());
        model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String s = "";

        
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
          
            
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());            
            String result = FlightProcess.printPropertyValues(individual,id);
            System.out.println("result="+result);
            s = printValues(result);
            
        }
        
        // xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
          Individual individual = model.getIndividual(ont + "Msg_FlightAvailRQ"+System.currentTimeMillis());
          if(individual!=null)
           individual.remove();

        return s;



    }

    /**
     * Hien thi thong tin
     * @param ind
     * @param prop
     */
    public static String printValues(String input) {
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Flight.owl";

        //dua ontology vao 1 model
         ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(input,Message.FIELD_SEPARATE);
 
         
         
         String result="";
         String output="";
         FlightDatabase.LoadOnt2Database();
        OntModel model = FlightDatabase.getOntologyModel();
        FlightDatabase.LoadOnt2Database();   
        
        
       String queryString = null;

       for(int i = 0; i<arr.size(); i++){ 
         result="";


         queryString = "PREFIX flight: <http://www.owl-ontologies.com/Flight.owl#> \n" 
                + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" 
                +"?FlightAgent flight:hasFlightManager ?FlightManager. \n"
                +"?FlightAgent flight:hasAgentFlightInfo ?AgentFlightInfo. \n"
                +"?FlightManager  flight:hasFlight ?x. \n"
                +"?FlightManager flight:hasPrice ?Price. \n" 
                +"?FlightManager flight:id ?ID. \n" 
                +"?AgentFlightInfo  flight:flightAgentName ?FlightAgentName. \n" 
                +"?AgentFlightInfo  flight:email ?Email. \n" 
                +"?AgentFlightInfo flight:fax ?Fax. \n" 
                +"?AgentFlightInfo  flight:phoneNumber ?PhoneNumber. \n" 
                +"?AgentFlightInfo  flight:hasAddress ?Address. \n"
                +"?Address  flight:specificAddress ?SpecificAddress. \n"
                +"?Address  flight:city ?City. \n"                
                + "?x flight:airline ?Airline. \n"  
                + "?x flight:flightClass ?FlightClass. \n"  
                + "?x flight:flightNumber ?FlightNumber. \n" 
                + "?x flight:hasAirplane ?Airplane. \n"   
                + "?x flight:hasArrivalAirport ?arrivalAirport. \n" 
                + "?x flight:hasDepartureAirport ?departureAirport. \n"   
                + "?x flight:hasArrivalDateTime ?arrivalDateTime. \n" 
                + "?x flight:hasDepartureDateTime ?departureDateTime. \n" 
                + "?arrivalAirport flight:city ?arrivalCity. \n" 
                + "?arrivalAirport flight:airportName ?arrivalAirportName. \n"
                + "?departureAirport flight:city ?departureCity. \n"  
                + "?departureAirport flight:airportName ?departureAirportName. \n"
                + "?departureDateTime flight:date ?departureDate. \n" 
                + "?departureDateTime flight:time ?departureTime. \n" 
                + "?arrivalDateTime flight:date ?arrivalDate. \n" 
                + "?arrivalDateTime flight:time ?arrivalTime. \n"                 
                + "?Airplane flight:airplaneType ?AirplaneType. \n" 
                + "?Price flight:price ?realPrice. \n" 
                + "?Price flight:priceUnit ?PriceUnit. \n"
                + " FILTER regex(?ID,\"" +arr.get(i) + "\", \"i\")}";       
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
         Model model2 = ModelFactory.createDefaultModel();
       
        try {
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi" +i+":");
            
            while (rs.hasNext()) {
                System.out.print("\n ket qua :");
                 model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString()); 
                
                //Dai ly ban ve may bay
                
                String flightAgent="";
                    String agentname=binding.getLiteral("FlightAgentName").getValue().toString();
                         flightAgent=flightAgent+agentname + "-";

                    String specificaddress=binding.getLiteral("SpecificAddress").getValue().toString();
                         flightAgent=flightAgent+specificaddress + "-";
                         

                    String city=binding.getLiteral("City").getValue().toString();
                         flightAgent=flightAgent+city + "-";     
                         

                    String email=binding.getLiteral("Email").getValue().toString();
                         flightAgent=flightAgent+email + "-"; 
                         
 
                    String phoneNumber=binding.getLiteral("PhoneNumber").getValue().toString();
                         flightAgent=flightAgent+phoneNumber + "-";    
                         
  
                    String fax=binding.getLiteral("Fax").getValue().toString();
                         flightAgent=flightAgent+fax;                          
                // Dia diem xuat phat
                String departure="";
                    String departureairport=binding.getLiteral("departureAirportName").getValue().toString();
                        departure=departure+departureairport+"-";
                    String departureCity = binding.getLiteral("departureCity").getValue().toString();
                        departure= departure + departureCity;                   
               // Dia diem den
                String arrival="";
                    String arrivalairport=binding.getLiteral("arrivalAirportName").getValue().toString();
                        arrival=arrival+arrivalairport+"-";
                    String arrivalCity = binding.getLiteral("arrivalCity").getValue().toString();
                        arrival= arrival + arrivalCity;                           
              //Thoi gian di 
                String departuredatetime="";
                 
                    String departuretime = binding.getLiteral("departureTime").getValue().toString();
                        departuredatetime= departuredatetime + departuretime+ ",";
                    String departuredate = binding.getLiteral("departureDate").getValue().toString();
                        departuredatetime= departuredatetime + departuredate;
 
                //Thoi gian den                        
                String arrivaldatetime="";
                 
                    String arrivaltime = binding.getLiteral("arrivalTime").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaltime+ ",";
                    String arrivaldate = binding.getLiteral("arrivalDate").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaldate;
               // Gia ca
                String price="";
                    String realprice=binding.getLiteral("realPrice").getValue().toString();
                        price=price+realprice;
                    String priceunit=binding.getLiteral("PriceUnit").getValue().toString();
                        price=price+priceunit;
               //  Hang hang khong
                String airline = binding.getLiteral("Airline").getValue().toString();
                String id=binding.getLiteral("ID").getValue().toString();
               // Ma so chuyen bay  
                String flightnumber = binding.getLiteral("FlightNumber").getValue().toString();
               //Hang ve may bay
                String flightclass = binding.getLiteral("FlightClass").getValue().toString();
                // Loai may bay
                String airplanetype=binding.getLiteral("AirplaneType").getValue().toString();
                
                result=result+ id + Message.FIELD_SEPARATE+ flightAgent + Message.FIELD_SEPARATE+airline + Message.FIELD_SEPARATE + flightnumber + Message.FIELD_SEPARATE
                        + departure + Message.FIELD_SEPARATE + departuredatetime + Message.FIELD_SEPARATE
                        + arrival + Message.FIELD_SEPARATE + arrivaldatetime + Message.FIELD_SEPARATE
                        //+ flightnumber + Message.FIELD_SEPARATE 
                        + airplanetype + Message.FIELD_SEPARATE + flightclass + Message.FIELD_SEPARATE
                        + price + Message.FIELD_SEPARATE;
                
                result = result +Message.OBJECT_SEPARATE;
                
                
	} 
            }
         catch (Exception e1) {
            e1.printStackTrace();
        }
        output = output + result + Message.OBJECT_SEPARATE;
       }  
       // System.out.println("ket qua cuoi:"+output );
        return output;
    }


  public static String printPropertyValues(Individual ind, Property prop) {
        System.out.print(ind.getLocalName() + " has " + prop.getLocalName() + "(s): ");
        String result = printIterator(ind.listPropertyValues(prop));
       // Property p1,p2,p3;
      
        return result;
    }

    public static String printInstances(OntClass cls) {
        System.out.print(cls.getLocalName() + " instances: ");
        String result = printIterator(cls.listInstances());
        return result;
    }

    public static String printIterator(ExtendedIterator i ) {
        String result ="";
        if (!i.hasNext() ) {
            System.out.print("none");
        } else {
            while (i.hasNext()) {
               Literal val = (Literal) i.next();
                System.out.print(val.getString());
                result= result+val.getString();
                if (i.hasNext()) {
                    result = result+Message.FIELD_SEPARATE;
                }
            }
        }
        
        return result;
    }
  public static boolean processingBooking(String input){
     FlightDatabase.LoadOnt2Database();
     OntModel model = FlightDatabase.getOntologyModel();
      String ont = "http://www.owl-ontologies.com/Flight.owl#";  
      System.out.println("booking");
      ArrayList<String> arrLT = new ArrayList<String>();
      arrLT = Message.split(input,Message.OBJECT_SEPARATE);
      System.out.println("arrLT: "+arrLT.toString());
 
      ArrayList<String> arr = new ArrayList<String>();
      arr = Message.split(arrLT.get(0),Message.FIELD_SEPARATE);  // id, agent, booknumber
      System.out.println("arr: "+arr.toString());
      
      String input_id=arr.get(0);
      String input_agentInfo=arr.get(1);
      float input_BookNumber=Float.parseFloat(arr.get(2));
      
     
      ArrayList<String> arr_customer = new ArrayList<String>(); // hoten, gioi tinh,email, dien thoai, dia chi cu the, thanh pho, nuoc
      arr_customer = Message.split(arrLT.get(1),Message.FIELD_SEPARATE);
      System.out.println("CustomerInfo: "+arr_customer);    
      System.out.println("arr_customer="+arr_customer.toString());
      
      String c_fullname=arr_customer.get(0);
      String c_sex=arr_customer.get(1);
      String c_email=arr_customer.get(2);
      String c_phone=arr_customer.get(3);
      String c_specificAddress=arr_customer.get(4);
      String c_city=arr_customer.get(5);
      String c_country=arr_customer.get(6);
              
              

      String queryString = "PREFIX flight: <http://www.owl-ontologies.com/Flight.owl#> \n" + "SELECT DISTINCT * \n " 
                + "WHERE \n" + "{ \n" 
                +"?FlightAgent flight:hasFlightManager ?FlightManager. \n"
                +"?FlightAgent flight:hasAgentFlightInfo ?AgentFlightInfo. \n"
                +"?FlightManager  flight:hasFlight ?x. \n"
                +"?FlightManager flight:hasPrice ?Price. \n" 
                +"?FlightManager flight:id ?ID. \n" 
                +"?FlightManager flight:totalBookNumber ?TotalBookNumber. \n" 
                +"?FlightManager flight:totalNumber ?TotalNumber. \n" 
                +"?AgentFlightInfo  flight:flightAgentName ?FlightAgentName. \n" 
                +"?AgentFlightInfo  flight:email ?Email. \n" 
                +"?AgentFlightInfo flight:fax ?Fax. \n" 
                +"?AgentFlightInfo  flight:phoneNumber ?PhoneNumber. \n" 
                +"?AgentFlightInfo  flight:hasAddress ?Address. \n"
                +"?Address  flight:specificAddress ?SpecificAddress. \n"
                +"?Address  flight:city ?City. \n"                
                + "?x flight:airline ?Airline. \n"  
                + "?x flight:flightClass ?FlightClass. \n"  
                + "?x flight:flightNumber ?FlightNumber. \n" 
                + "?x flight:hasAirplane ?Airplane. \n"   
                + "?x flight:hasArrivalAirport ?arrivalAirport. \n" 
                + "?x flight:hasDepartureAirport ?departureAirport. \n"   
                + "?x flight:hasArrivalDateTime ?arrivalDateTime. \n" 
                + "?x flight:hasDepartureDateTime ?departureDateTime. \n" 
                + "?arrivalAirport flight:city ?arrivalCity. \n" 
                + "?arrivalAirport flight:airportName ?arrivalAirportName. \n"
                + "?departureAirport flight:city ?departureCity. \n"  
                + "?departureAirport flight:airportName ?departureAirportName. \n"
                + "?departureDateTime flight:date ?departureDate. \n" 
                + "?departureDateTime flight:time ?departureTime. \n" 
                + "?arrivalDateTime flight:date ?arrivalDate. \n" 
                + "?arrivalDateTime flight:time ?arrivalTime. \n"                 
                + "?Airplane flight:airplaneType ?AirplaneType. \n" 
                + "?Price flight:price ?realPrice. \n" 
                + "?Price flight:priceUnit ?PriceUnit. \n"     
                + " FILTER ( regex(?ID,\"" + input_id + "\", \"i\"))}";
     
      Query query = QueryFactory.create(queryString);
      QueryExecution queryexec = QueryExecutionFactory.create(query, model);
     // Float booknumber=Float.parseFloat(arr.get(3));

        boolean isOk = false;


         try {
            ResultSet rs = queryexec.execSelect();
         
               String result=null;
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                 System.out.print("co gia tri thoa man:"+ binding.getLiteral("ID").getValue().toString());
                String flightAgent="";
                    String agentname=binding.getLiteral("FlightAgentName").getValue().toString();
                         flightAgent=flightAgent+agentname + "-";

                    String specificaddress=binding.getLiteral("SpecificAddress").getValue().toString();
                         flightAgent=flightAgent+specificaddress + "-";
                         

                    String city=binding.getLiteral("City").getValue().toString();
                         flightAgent=flightAgent+city + "-";     
                         

                    String email=binding.getLiteral("Email").getValue().toString();
                         flightAgent=flightAgent+email + "-"; 
                         
 
                    String phoneNumber=binding.getLiteral("PhoneNumber").getValue().toString();
                         flightAgent=flightAgent+phoneNumber + "-";    
                         
  
                    String fax=binding.getLiteral("Fax").getValue().toString();
                         flightAgent=flightAgent+fax;                          
                // Dia diem xuat phat
                String departure="";
                    String departureairport=binding.getLiteral("departureAirportName").getValue().toString();
                        departure=departure+departureairport+"-";
                    String departureCity = binding.getLiteral("departureCity").getValue().toString();
                        departure= departure + departureCity;                   
               // Dia diem den
                String arrival="";
                    String arrivalairport=binding.getLiteral("arrivalAirportName").getValue().toString();
                        arrival=arrival+arrivalairport+"-";
                    String arrivalCity = binding.getLiteral("arrivalCity").getValue().toString();
                        arrival= arrival + arrivalCity;                           
              //Thoi gian di 
                String departuredatetime="";
                 
                    String departuretime = binding.getLiteral("departureTime").getValue().toString();
                        departuredatetime= departuredatetime + departuretime+ ",";
                    String departuredate = binding.getLiteral("departureDate").getValue().toString();
                        departuredatetime= departuredatetime + departuredate;
 
                //Thoi gian den                        
                String arrivaldatetime="";
                 
                    String arrivaltime = binding.getLiteral("arrivalTime").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaltime+ ",";
                    String arrivaldate = binding.getLiteral("arrivalDate").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaldate;
               // Gia ca
                String price="";
                    String realprice=binding.getLiteral("realPrice").getValue().toString();
                        price=price+realprice;
                    String priceunit=binding.getLiteral("PriceUnit").getValue().toString();
                        price=price+priceunit;
               //  Hang hang khong
                String airline = binding.getLiteral("Airline").getValue().toString();
                String id=binding.getLiteral("ID").getValue().toString();
               // Ma so chuyen bay  
                String flightnumber = binding.getLiteral("FlightNumber").getValue().toString();
               //Hang ve may bay
                String flightclass = binding.getLiteral("FlightClass").getValue().toString();
                // Loai may bay
                String airplanetype=binding.getLiteral("AirplaneType").getValue().toString();

                float totalnumber = Float.parseFloat(binding.getLiteral("TotalNumber").getValue().toString());   
                System.out.print("Total Number=" +totalnumber); 

                float totalbooknumber = Float.parseFloat(binding.getLiteral("TotalBookNumber").getValue().toString());   
                System.out.print("Total Book Number=" +totalbooknumber); 
// 
//                  result=result+ id + Message.FIELD_SEPARATE+ flightAgent + Message.FIELD_SEPARATE+airline + Message.FIELD_SEPARATE + flightnumber + Message.FIELD_SEPARATE
//                        + departure + Message.FIELD_SEPARATE + departuredatetime + Message.FIELD_SEPARATE
//                        + arrival + Message.FIELD_SEPARATE + arrivaldatetime + Message.FIELD_SEPARATE
//                        + flightnumber + Message.FIELD_SEPARATE 
//                        + airplanetype + Message.FIELD_SEPARATE + flightclass + Message.FIELD_SEPARATE
//                        + price + Message.FIELD_SEPARATE;
//                System.out.println("The hien:"+result);
                float tong=input_BookNumber + totalbooknumber;
                if(tong<=totalnumber){
                    
                    
                    System.out.println("Bat dau ghi nay");
                    try{
                        // Tang tong so luong dat
                        String new_flightmanager=binding.getResource("FlightManager").toString();
                        if(new_flightmanager!=null){
                            Individual ind1=model.getIndividual(new_flightmanager);
                            if(ind1!=null){
                                ind1.removeAll(Flight.totalBookNumber);
                                ind1.addLiteral(Flight.totalBookNumber, totalbooknumber+input_BookNumber);
                        System.out.println("Them gia tri"); 
                        OntClass oc_address=model.createClass("http://www.owl-ontologies.com/Flight.owl#Address");
                        Individual ind_address=model.createIndividual(ont + "Address_" + System.currentTimeMillis(),oc_address);
                        ind_address.addLiteral(Flight.specificAddress, c_specificAddress);
                        ind_address.addLiteral(Flight.city,c_city);
                        ind_address.addLiteral(Flight.country, c_country);

                        
                        OntClass oc_customer=model.createClass("http://www.owl-ontologies.com/Flight.owl#Customer");
                        Individual ind_customer=model.createIndividual(ont + "Customer_" + System.currentTimeMillis(),oc_customer);
                        ind_customer.addLiteral(Flight.fullName, c_fullname);
                        ind_customer.addLiteral(Flight.gender,c_sex);
                        ind_customer.addLiteral(Flight.email, c_email);
                        ind_customer.addLiteral(Flight.phoneNumber, c_phone);
                        ind_customer.addProperty(Flight.hasAddress, ind_address);
                        
                        OntClass oc_contract=model.createClass("http://www.owl-ontologies.com/Flight.owl#FlightBookContract");
                        Individual ind_contract=model.createIndividual(ont + "FlightBookContract_" + System.currentTimeMillis(),oc_contract);
                        ind_contract.addLiteral(Flight.bookNumber, input_BookNumber);
                        ind_contract.addLiteral(Flight.id, id);
                        ind_contract.addProperty(Flight.hasCustomer, ind_customer);
                        isOk=true;
                                
                            }
                        }
  
                        
                    }
                    catch(Exception e1){
                    e1.printStackTrace();}
                }
                else 
                    System.out.println("Khong du dau");
//                
    
              
            }catch(Exception e){
                e.printStackTrace();
            }
     
        return isOk;
  }
  public static void searchFlightContract(){
       FlightDatabase.LoadOnt2Database();
       OntModel model = FlightDatabase.getOntologyModel();
        String ont = "http://www.owl-ontologies.com/Flight.owl#";  
    
      
      
        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
          DatatypeProperty booknumber =model.getDatatypeProperty(ont + "bookNumber");
         DatatypeProperty id =model.getDatatypeProperty(ont + "id");
         ObjectProperty customer=model.getObjectProperty(ont+"hasCustomer");
         
        OntClass cl = model.getOntClass(ont + "FlightBookContract");
      

        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
    
   
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String s = null;

        
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());                   
            String bookNumber =  (individual.listPropertyValues(booknumber).next()).toString();
            System.out.println("booknumber=" + bookNumber );
            
            String ID =  (individual.listPropertyValues(id).next()).toString();

             System.out.println("ID=" +ID );
 
          
             String Customer =  (individual.listPropertyValues(customer).next()).toString();
         //   int indexFromDate = fromdate1.indexOf("^^");
          //  String fromdate2 = fromdate1.substring(0, indexFromDate);
             System.out.println("Customer" +Customer );
            
            
        }
  }
       public static OntModel insertMsg_FlightServiceManagerRQ(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Flight.getURI() + "Msg_FlightServiceManagerRQ");
            
            ind = model.createIndividual(Flight.getURI() + "Msg_FlightServiceManagerRQ" + total, oc);
             // them cac thuoc tinh vao ca the can tao           
            if (arr.get(0) != null) {
            ind.addProperty(Flight.username, arr.get(0));

            }
            
            if (arr.get(1) != null) {
            ind.addProperty(Flight.password, arr.get(1));

            }

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }
 
  public static String FlightServiceManager(String input){
        System.out.println("abc");
         log.info("Starting search with: " + input);
        //  Database.LoadOnt2Database();
        String ont = "http://www.owl-ontologies.com/Flight.owl#";
        // lay khung du lieu tu owl
        
        String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Flight.owl";

        //dua ontology vao 1 model
        FlightDatabase.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, FlightDatabase.getOntologyModel());
       

        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        
        ObjectProperty flightManager = model.getObjectProperty(ont + "hasFlightManager");
        ObjectProperty flightAgentInfo = model.getObjectProperty(ont + "hasAgentFlightInfo");
        DatatypeProperty id=model.getDatatypeProperty(ont+"id");
        
        OntClass cl = model.getOntClass(ont + "Msg_FlightServiceManagerRS");
       
        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_FlightServiceManagerRQ(input, System.currentTimeMillis());
        model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        String s = "";

        
        // lay tat cac cac ket qua thoa man
        while (extendedIterator.hasNext()) {
          
            
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());            
            String result = FlightProcess.printPropertyValues(individual,id);
            System.out.println("result="+result);
            s = Print_FlightServiceManager(result);
            
        }
        
        // xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
          Individual individual = model.getIndividual(ont + "Msg_FlightAvailRQ"+System.currentTimeMillis());
          if(individual!=null)
           individual.remove();

        return s;


  }
 
  public static String Print_FlightServiceManager(String input){
  
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Flight.owl";
        String ont = "http://www.owl-ontologies.com/Travel.owl#"; 
        //dua ontology vao 1 model
         ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(input,Message.FIELD_SEPARATE );
         String result="";
         String output="";
         FlightDatabase.LoadOnt2Database();
        OntModel model = FlightDatabase.getOntologyModel();
        FlightDatabase.LoadOnt2Database();   
        
        
       String queryString = null;

       for(int i = 0; i<arr.size(); i++){ 
         result="";


         queryString = "PREFIX flight: <http://www.owl-ontologies.com/Flight.owl#> \n" 
                + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" 
                +"?FlightAgent flight:hasFlightManager ?FlightManager. \n"
                +"?FlightAgent flight:hasAgentFlightInfo ?AgentFlightInfo. \n"
                +"?FlightManager  flight:hasFlight ?x. \n"
                +"?FlightManager flight:hasPrice ?Price. \n" 
                +"?FlightManager flight:id ?ID. \n" 
                +"?FlightManager flight:totalBookNumber ?TotalBookNumber. \n" 
                +"?FlightManager flight:totalNumber ?TotalNumber. \n" 
                +"?AgentFlightInfo  flight:flightAgentName ?FlightAgentName. \n" 
                +"?AgentFlightInfo  flight:email ?Email. \n" 
                +"?AgentFlightInfo flight:fax ?Fax. \n" 
                +"?AgentFlightInfo  flight:phoneNumber ?PhoneNumber. \n" 
                +"?AgentFlightInfo  flight:hasAddress ?Address. \n"
                +"?Address  flight:specificAddress ?SpecificAddress. \n"
                +"?Address  flight:city ?City. \n"                
                + "?x flight:airline ?Airline. \n"  
                + "?x flight:flightClass ?FlightClass. \n"  
                + "?x flight:flightNumber ?FlightNumber. \n" 
                + "?x flight:hasAirplane ?Airplane. \n"   
                + "?x flight:hasArrivalAirport ?arrivalAirport. \n" 
                + "?x flight:hasDepartureAirport ?departureAirport. \n"   
                + "?x flight:hasArrivalDateTime ?arrivalDateTime. \n" 
                + "?x flight:hasDepartureDateTime ?departureDateTime. \n" 
                + "?arrivalAirport flight:city ?arrivalCity. \n" 
                + "?arrivalAirport flight:airportName ?arrivalAirportName. \n"
                + "?departureAirport flight:city ?departureCity. \n"  
                + "?departureAirport flight:airportName ?departureAirportName. \n"
                + "?departureDateTime flight:date ?departureDate. \n" 
                + "?departureDateTime flight:time ?departureTime. \n" 
                + "?arrivalDateTime flight:date ?arrivalDate. \n" 
                + "?arrivalDateTime flight:time ?arrivalTime. \n"                 
                + "?Airplane flight:airplaneType ?AirplaneType. \n" 
                + "?Price flight:price ?realPrice. \n" 
                + "?Price flight:priceUnit ?PriceUnit. \n"
                + " FILTER regex(?ID,\"" +arr.get(i) + "\", \"i\")}";       
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
         Model model2 = ModelFactory.createDefaultModel();
       
        try {
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi" +i+":");
            
            while (rs.hasNext()) {
                System.out.print("\n ket qua :");
                 model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString()); 
                
                //Dai ly ban ve may bay
                
                String flightAgent="";
                    String agentname=binding.getLiteral("FlightAgentName").getValue().toString();
                         flightAgent=flightAgent+agentname + "-";

                    String specificaddress=binding.getLiteral("SpecificAddress").getValue().toString();
                         flightAgent=flightAgent+specificaddress + ",";
                         

                    String city=binding.getLiteral("City").getValue().toString();
                         flightAgent=flightAgent+city + "-";     
                         

                    String email=binding.getLiteral("Email").getValue().toString();
                         flightAgent=flightAgent+email + "-"; 

                    String fax=binding.getLiteral("Fax").getValue().toString();
                         flightAgent=flightAgent+fax + "-";  
 
                    String phoneNumber=binding.getLiteral("PhoneNumber").getValue().toString();
                         flightAgent=flightAgent+phoneNumber;    
                         
  
                        
                // Dia diem xuat phat
                String departure="";
                    String departureairport=binding.getLiteral("departureAirportName").getValue().toString();
                        departure=departure+departureairport+"-";
                    String departureCity = binding.getLiteral("departureCity").getValue().toString();
                        departure= departure + departureCity;                   
               // Dia diem den
                String arrival="";
                    String arrivalairport=binding.getLiteral("arrivalAirportName").getValue().toString();
                        arrival=arrival+arrivalairport+"-";
                    String arrivalCity = binding.getLiteral("arrivalCity").getValue().toString();
                        arrival= arrival + arrivalCity;                           
              //Thoi gian di 
                String departuredatetime="";
                 
                    String departuretime = binding.getLiteral("departureTime").getValue().toString();
                        departuredatetime= departuredatetime + departuretime+ ",";
                    String departuredate = binding.getLiteral("departureDate").getValue().toString();
                        departuredatetime= departuredatetime + departuredate;
 
                //Thoi gian den                        
                String arrivaldatetime="";
                 
                    String arrivaltime = binding.getLiteral("arrivalTime").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaltime+ ",";
                    String arrivaldate = binding.getLiteral("arrivalDate").getValue().toString();
                        arrivaldatetime= arrivaldatetime + arrivaldate;
               // Gia ca
                String price="";
                    String realprice=binding.getLiteral("realPrice").getValue().toString();
                        price=price+realprice;
                    String priceunit=binding.getLiteral("PriceUnit").getValue().toString();
                        price=price+priceunit;
               //  Hang hang khong
                String airline = binding.getLiteral("Airline").getValue().toString();
                String id=binding.getLiteral("ID").getValue().toString();
               // Ma so chuyen bay  
                String flightnumber = binding.getLiteral("FlightNumber").getValue().toString();
               //Hang ve may bay
                String flightclass = binding.getLiteral("FlightClass").getValue().toString();
                // Loai may bay
                String airplanetype=binding.getLiteral("AirplaneType").getValue().toString();
                String totalnumber=binding.getLiteral("TotalNumber").getValue().toString();
                String totalbooknumber=binding.getLiteral("TotalBookNumber").getValue().toString();
                
                result=result+ id + Message.FIELD_SEPARATE+ flightAgent + Message.FIELD_SEPARATE+airline + Message.FIELD_SEPARATE + flightnumber + Message.FIELD_SEPARATE
                        + departure + Message.FIELD_SEPARATE + departuredatetime + Message.FIELD_SEPARATE
                        + arrival + Message.FIELD_SEPARATE + arrivaldatetime + Message.FIELD_SEPARATE
                        + flightnumber + Message.FIELD_SEPARATE 
                        + airplanetype + Message.FIELD_SEPARATE + flightclass + Message.FIELD_SEPARATE
                        + price + Message.FIELD_SEPARATE + totalnumber +Message.FIELD_SEPARATE +totalbooknumber;
                
                result = result +Message.OBJECT_SEPARATE;
                
                
	} 
            }
         catch (Exception e1) {
            e1.printStackTrace();
        }
        output = output + result + Message.OBJECT_SEPARATE;
       }  
       // System.out.println("ket qua cuoi:"+output );
        return output;  
  }
    public static void main(String s[]) throws Exception {
    
        FlightProcess flightprocess = new FlightProcess();
        OntModel ontmodel = ModelFactory.createOntologyModel();
//        String input = "Ha noi" + Message.FIELD_SEPARATE +"Ho Chi Minh" + Message.FIELD_SEPARATE + "2010-02-01"+ Message.FIELD_SEPARATE + "economy" + Message.FIELD_SEPARATE + "1";
//        String ss = flightprocess.search(input);
//        System.out.print("ss="+ss);
 /*        String input = "M_BL791_E1_1" + Message.FIELD_SEPARATE +"Ho Chi Minh" + Message.FIELD_SEPARATE + "2"+ 
                Message.OBJECT_SEPARATE + "Hoang Thao" + Message.FIELD_SEPARATE + "Nu"+ 
                Message.FIELD_SEPARATE+"thaohoang87@gmail.com" + Message.FIELD_SEPARATE +"0918523376" +
                Message.FIELD_SEPARATE+"Thanh xuan" + Message.FIELD_SEPARATE + "Ha Noi" + 
                Message.FIELD_SEPARATE+"Viet Nam";
        boolean ss = flightprocess.processingBooking(input);*/
        String ss=null;
        String input= "anz"+Message.FIELD_SEPARATE+"anz123";
        ss = flightprocess.FlightServiceManager(input);       
        System.out.print("ss="+ss);
        flightprocess.searchFlightContract();

    }
}