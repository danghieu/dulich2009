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
import com.ptit.travel.jane.flight.Flight;
import org.apache.log4j.Logger;

/**
 *
 * @author Thao Hoang
 */
public class FlightProcess_2 {
    
    private static Logger log = Logger.getLogger(FlightProcess_2.class.getName());
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
            ind.addProperty(Flight.date, arr.get(2));

            }   
            if (arr.get(3) != null) {
            ind.addProperty(Flight.flightClass, arr.get(3));

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
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/Flight.owl";

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
        ObjectProperty flight = model.getObjectProperty(ont + "hasFlight");
        DatatypeProperty flightNumber =model.getDatatypeProperty(ont + "flightNumber");
        DatatypeProperty date=model.getDatatypeProperty(ont+ "date");
        
        OntClass cl = model.getOntClass(ont + "Msg_FlightAvailRS");
       
        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_FlightAvailRQ(input, System.currentTimeMillis());
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
            String FlightNumber =  (individual.listPropertyValues(flightNumber).next()).toString();
            int index = FlightNumber .indexOf("^^");
            String flightnumber = FlightNumber .substring(0, index);
            System.out.println("flightNumber=" +flightnumber );
            String Date =  (individual.listPropertyValues(date).next()).toString();
            int index2 = Date .indexOf("^^");
            String date2 = Date.substring(0, index2);
            System.out.println("date=" + date2 );

            s = printValues(flightnumber,date2);
            
        }

        return s;



    }

    /**
     * Hien thi thong tin
     * @param ind
     * @param prop
     */
    public static String printValues(String s, String s2) {
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/Flight.owl";

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
        
        String queryString = null;


         queryString = "PREFIX flight: <http://www.owl-ontologies.com/Flight.owl#> \n" 
                + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" 
                + "?x flight:airline ?Airline. \n"     
                + "?x flight:flightClass ?FlightClass. \n"  

                + "?x flight:flightNumber ?FlightNumber. \n" 
                + "?x flight:hasAirplane ?Airplane. \n"   
                + "?x flight:hasArrivalAirport ?arrivalAirport. \n" 
                + "?x flight:hasDepartureAirport ?departureAirport. \n"   
                + "?x flight:hasArrivalDateTime ?arrivalDateTime. \n" 
                + "?x flight:hasDepartureDateTime ?departureDateTime. \n" 
                + "?x flight:hasPrice ?price. \n" 
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
                + " FILTER regex(?FlightNumber,\"" +s + "\", \"i\")"
                + " FILTER regex(?departureDate,\"" +s2 + "\", \"i\")}";       
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
        Model model2 = ModelFactory.createDefaultModel();
        String result="";
        try {
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi");
            while (rs.hasNext()) {
                System.out.print("ket qua");
                model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString()); 
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
               // Ma so chuyen bay  
                String flightnumber = binding.getLiteral("FlightNumber").getValue().toString();
               //Hang ve may bay
                String flightclass = binding.getLiteral("FlightClass").getValue().toString();
                // Loai may bay
                String airplanetype=binding.getLiteral("AirplaneType").getValue().toString();
                
                result=result+ airline + Message.FIELD_SEPARATE + flightnumber + Message.FIELD_SEPARATE
                        + departure + Message.FIELD_SEPARATE + departuredatetime + Message.FIELD_SEPARATE
                        + arrival + Message.FIELD_SEPARATE + arrivaldatetime + Message.FIELD_SEPARATE
                        + airplanetype + Message.FIELD_SEPARATE + flightclass + Message.FIELD_SEPARATE
                        + price + Message.FIELD_SEPARATE;
                
                result = result +Message.OBJECT_SEPARATE;
                
                
	} 
            }
         catch (Exception e1) {
            e1.printStackTrace();
        }
        return result;
    }

    public static void printPropertyValues(Individual ind, Property prop) {
        System.out.print(ind.getLocalName() + " has " + prop.getLocalName() + "(s): ");
        printIterator(ind.listPropertyValues(prop));
    }

    public static void printInstances(OntClass cls) {
        System.out.print(cls.getLocalName() + " instances: ");
        printIterator(cls.listInstances());
    }

    public static void printIterator(ExtendedIterator i) {
        if (!i.hasNext()) {
            System.out.print("none");
        } else {
            while (i.hasNext()) {
                Resource val = (Resource) i.next();
                System.out.print(val.getLocalName());
                if (i.hasNext()) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println();
    }

 
    public static void main(String s[]) throws Exception {
        FlightProcess_2 flightprocess = new FlightProcess_2();
        OntModel ontmodel = ModelFactory.createOntologyModel();
        String input = "Ha Noi" + Message.FIELD_SEPARATE +"Ho Chi Minh" + Message.FIELD_SEPARATE + "2010-02-01"+ Message.FIELD_SEPARATE + "economy" + Message.FIELD_SEPARATE + "1";
        String ss = flightprocess.search(input);
        System.out.print("ss="+ss);
       // printValues("<http://www.owl-ontologies.com/Travel.owl#Hotel_1>");

    }
}