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
public class FlightProcess2{
    
    private static Logger log = Logger.getLogger(FlightProcess2.class.getName());
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
        ObjectProperty flightAgentInfo = model.getObjectProperty(ont + "hasAgentFlightInfo");
        ObjectProperty flight = model.getObjectProperty(ont + "hasFlight");
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
            String result = FlightProcess2.printPropertyValues(individual,id);
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
                +"?FlightManager  flight:hasFlight ?x. \n"
                +"?FlightManager flight:hasPrice ?Price. \n" 
                +"?FlightManager flight:id ?ID. \n" 
                +"?FlightManager flight:hasFlightAgent ?FlightAgent. \n" 
                +"?FlightAgent hasAgentFlightInfo ?AgentFlightInfo. \n" 
                +"?AgentFlightInfo  flight:flightAgentName ?FlightAgentName. \n" 
                +"?AgentFlightInfo  flight:email ?Email. \n" 
                +"?AgentFlightInfo  flight:fax ?Fax. \n" 
                +"?AgentFlightInfo  flight:phoneNumber ?PhoneNumber. \n" 
                +"?AgentFlightInfo  flight:hasAddress ?Address. \n"
                +"?Address  flight:specificAdress ?SpecificAddress. \n"
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
      //  Model model2 = ModelFactory.createDefaultModel();
       
        try {
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi" +i+":");
            System.out.println("RS="+rs.getRowNumber());
            while (rs.hasNext()) {
                System.out.print("\n ket qua :");
              //  model2 = rs.getResourceModel();
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
                
                result=result+ id + Message.FIELD_SEPARATE +  flightAgent+ Message.FIELD_SEPARATE+airline + Message.FIELD_SEPARATE + flightnumber + Message.FIELD_SEPARATE
                        + departure + Message.FIELD_SEPARATE + departuredatetime + Message.FIELD_SEPARATE
                        + arrival + Message.FIELD_SEPARATE + arrivaldatetime + Message.FIELD_SEPARATE
                        + flightnumber + Message.FIELD_SEPARATE 
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
/*
 public static boolean processingBooking(String input){
     FlightDB.LoadOnt2Database();
     OntModel model = FlightDB.getOntologyModel();
      String ont = "http://www.owl-ontologies.com/Flight.owl#";  
      System.out.println("booking");
      ArrayList <String> arr = new ArrayList<String>();
      arr = Message.split(input, Message.FIELD_SEPARATE);
      System.out.println("arr: "+arr.toString());
      String queryString = "PREFIX flight: <http://www.owl-ontologies.com/Flight.owl#> \n" + "SELECT DISTINCT * \n " 
                + "WHERE \n" + "{ \n" 
                + "?flightManager flight:hasFlight ?Flight \n"  
                + "?flightManager flight:totalBookNumber ?TotalBookNumber. \n" 
                + "?flightManager flight:totalNumber ?TotalNumber. \n"  
                + "?Flight flight:airline ?Airline. \n"  
                + "?Flight flight:id ?ID. \n" 
                + "?Flight flight:flightClass ?FlightClass. \n"  
                + "?Flight flight:flightNumber ?FlightNumber. \n" 
                + "?Flight flight:hasAirplane ?Airplane. \n"   
                + "?Flight flight:hasArrivalAirport ?arrivalAirport. \n" 
                + "?Flight flight:hasDepartureAirport ?departureAirport. \n"   
                + "?Flight flight:hasArrivalDateTime ?arrivalDateTime. \n" 
                + "?Flight flight:hasDepartureDateTime ?departureDateTime. \n" 
                + "?Flight flight:hasPrice ?price. \n" 
                + "?arrivalAirport flight:city ?arrivalCity. \n" 
                + "?arrivalAirport flight:airportName ?arrivalAirportName. \n"
                + "?departureAirport flight:city ?departureCity. \n"  
                + "?departureAirport flight:airportName ?departureAirportName. \n"
                + "?departureDateTime flight:date ?departureDate. \n" 
                + "?departureDateTime flight:time ?departureTime. \n" 
                + "?arrivalDateTime flight:date ?arrivalDate. \n" 
                + "?arrivalDateTime flight:time ?arrivalTime. \n"                 
                + "?Airplane flight:airplaneType ?AirplaneType. \n" 
                + "?price flight:price ?realPrice. \n" 
                + "?price flight:priceUnit ?PriceUnit. \n"
                + " FILTER ( regex(?FlightNumber,\"" + arr.get(0) + "\", \"i\"))" 
                + " FILTER ( regex(?FightClass,\"" + arr.get(1) + "\", \"i\"))"      
                + " FILTER ( regex(?departureDate,\"" + arr.get(2) + "\", \"i\"))}";
     
      Query query = QueryFactory.create(queryString);
      QueryExecution queryexec = QueryExecutionFactory.create(query, model);
      Float booknumber=Float.parseFloat(arr.get(3));


        boolean isOk = false;


         try {
            ResultSet rs = queryexec.execSelect();
           if(rs!=null){
                System.out.print("co gia tri thoa man");
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("ket qua truy van :" + binding.toString());
            
   
                String flightnumber=binding.getLiteral("FlightNumber").getValue().toString();
                System.out.println("Flight Number="+flightnumber);
                String flightclass=binding.getLiteral("FlightClass").getValue().toString();
                System.out.println("Flight Class="+flightclass);              
                String date = binding.getLiteral("departureDate").getValue().toString();
                System.out.println("ToDate truy van :"+date);

                float totalnumber = Float.parseFloat(binding.getLiteral("TotalNumber").getValue().toString());   
                System.out.print("Total Number=" +totalnumber); 

                float totalboonumber = Float.parseFloat(binding.getLiteral("TotalBookNumber").getValue().toString());   
                System.out.print("Total Book Number=" +totalboonumber); 
 
               
                System.out.print("Book Number=" +booknumber);     
                
                
                float tong=booknumber + totalboonumber;
                if(tong<=totalnumber){
                    isOk = true;
                    System.out.println("Bat dau ghi nay");
                   try {
                      System.out.println("them gia tri");
                      OntClass oc = model.createClass("http://www.owl-ontologies.com/Flight.owl#FlightBookContract");
                      Individual ind = model.createIndividual(ont + "FlightBookContract_" + System.currentTimeMillis(),oc);
                      ind.addLiteral(Flight.bookNumber, arr.get(3) );   
                      ind.addLiteral(Flight.bookdate, System.currentTimeMillis());
                      ind.addLiteral(Flight.flightNumber, arr.get(0)); 
                      ind.addLiteral(Flight.flightClass,arr.get(1));
                      ind.addLiteral(Flight.date,arr.get(2));
                      ind.addLiteral(Flight.fullName, arr.get(4));
                      ind.addLiteral(Flight.gender, arr.get(5));
                      ind.addLiteral(Flight.email, arr.get(6));
                      ind.addLiteral(Flight.phoneNumber,arr.get(7));
                                          
                      System.out.println("hotelRoom: "+hotelroom);
                       Individual hotelRoomInd = model.getIndividual(hotelroom);
                       hotelRoomInd.addProperty(Hotel.hasNotAvailabilityPeriod, ind);
                       
			isOk = true;
                        System.out.println("Them the hien NotAvailability thanh cong");
                       } catch (Exception e) {
			System.out.println(e.toString());
			isOk = false;
		}
                
                }
                else {
                    isOk=false;
                    System.out.println("Khong du dau");
                }
         }
         else{
             System.out.println("Khong thay");
         }
              
            }catch(Exception e){
                e.printStackTrace();
            }
     
        return isOk;
  }
   */  
    public static void main(String s[]) throws Exception {
        System.out.println("Dien a?");
        FlightProcess2 flightprocess = new FlightProcess2();
        OntModel ontmodel = ModelFactory.createOntologyModel();
        String input = "Ha noi" + Message.FIELD_SEPARATE +"Ho Chi Minh" + Message.FIELD_SEPARATE + "2010-02-01"+ Message.FIELD_SEPARATE + "economy" + Message.FIELD_SEPARATE + "1";
        String ss = flightprocess.search(input);
        System.out.print("ss="+ss);
       // printValues("<http://www.owl-ontologies.com/Travel.owl#Hotel_1>");

    }
}