/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.jane.hotel;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.ptit.travel.agent.communication.Message;
import java.io.*;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
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

/**
 *
 * @author Administrator
 */
public class HotelProcess {

    /**
     * Tim kiem theo mot so dieu kien, gia tri tra ve la Model chua cac khach san va thog tin ve cac khach san nay
     * @param bar
     * @param fitnescenter
     * @param gardenCafe
     * @param karaoke
     * @param nightClub
     * @param location
     * @param meetingRoom
     * @param restaurant
     * @return
     */
    public Model searchHotel(boolean bar, boolean fitnescenter, boolean gardenCafe, boolean karaoke, boolean nightClub, String location, boolean meetingRoom, boolean restaurant, String city) {

        Database.LoadOnt2Database();
        OntModel om = Database.getOntologyModel();

        try {
            FileOutputStream ontoModel = new FileOutputStream("D:\\ontoModel");
            om.write(ontoModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String queryString = null;

        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:hasHotelRoom ?meetingRoom. \n" 
                + "?x hotel:hasRestaurant ?restaurant. \n" 
                + "?restaurant hotel:RestaurantName ?ten.\n" 
                + "?x hotel:hasContact ?contact. \n" 
                + "?contact hotel:hasAddress ?Address. \n" 
                + "?Address hotel:city ?city. \n";

        if (bar == true) {
            queryString = queryString + "?x hotel:hotelActivities ?bar. \n" + " FILTER regex(?bar,\"" + "Bar" + "\", \"i\")\n";
        }

        if (fitnescenter == true) {
            queryString = queryString + "?x hotel:hotelFacilities ?hotelFacilities1. \n" + " FILTER regex(?hotelFacilities1,\"" + "FitnessCenter" + "\", \"i\")\n";
        }

        if (gardenCafe == true) {
            queryString = queryString + "?x hotel:hotelFacilities ?garden. \n" + " FILTER regex(?garden,\"" + "GardenCafe" + "\", \"i\")\n";
        }
        if (gardenCafe == true) {
            queryString = queryString + "?x hotel:hotelActivities ?karaoke. \n" + " FILTER regex(?karaoke,\"" + "Karaoke" + "\", \"i\")\n";
        }

        if (location != null) {
            queryString = queryString + "?x hotel:HotelLocation ?location. \n" + " FILTER regex(?location,\"" + location + "\", \"i\")\n";
        }
        if (city != null) {
            queryString = queryString + " FILTER regex(?city,\"" + city + "\", \"i\")\n";
        }

        if (nightClub == true) {
            queryString = queryString + "?x hotel:hotelActivities ?sauna. \n" + " FILTER regex(?sauna,\"" + "Sauna" + "\", \"i\")\n";
        }

        //    queryString = queryString + "?x hotel:hasMeetingRoom, ?meetingRoom. \n" ;                   

        //    queryString = queryString + "?x hotel:hasRestaurant ?restaurant. \n";                    

        queryString = queryString + "}";
        //     System.out.println("truy van:"+queryString);
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, om);
        Model model = ModelFactory.createDefaultModel();
        try {
            ResultSet rs = queryexec.execSelect();
            while (rs.hasNext()) {
                model = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy 1:" + binding.toString());

                String meeting = binding.getResource("restaurant").getLocalName().toString();
                if (meeting != null) {
                    System.out.print("dung" + meeting);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public Model search2(String HotelName, String HotelName2) {
        Database.LoadOnt2Database();
        Model om = Database.getOntologyModel();


        String queryString = null;

        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" +
                "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" +
                " FILTER regex(?hotelname,\"" + HotelName + "\", \"i\")}";

        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, om);
        Model model1 = ModelFactory.createDefaultModel();
        try {
            ResultSet rs = queryexec.execSelect();
            while (rs.hasNext()) {
                model1 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        HotelProcess hotelprocess = new HotelProcess();

        Model model = ModelFactory.createDefaultModel();
        model = hotelprocess.searchHotel(false, true, false, false, false, "inside", false, true, "Nam Dinh");

        model1.add(model);



        String queryString1 = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" + "?x hotel:hasHotelRoom ?hotelRoom. \n" + "?hotelRoom hotel:roomType ?roomType. \n" + "?hotelRoom hotel:hasNotAvailabilityPeriod ?notAvail. \n" + "?notAvail hotel:roomType ?roomType1. \n" + "?notAvail hotel:ToDate ?ToDate. \n" + " FILTER ( regex(?hotelname,\"" + HotelName + "\", \"i\")||regex(?hotelname,\"" + HotelName2 + "\", \"i\")) }";

        Query query2 = QueryFactory.create(queryString1);
        QueryExecution queryexec2 = QueryExecutionFactory.create(query2, model1);

        try {
            ResultSet rs2 = queryexec2.execSelect();
            while (rs2.hasNext()) {

                Object obj = rs2.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy 3" + binding.toString());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        return model1;
    }

    public Model checkAvailability(String hotelName, String roomType, String livingRoomType) {
        Database.LoadOnt2Database();
        Model om = Database.getOntologyModel();
        Database.LoadOnt2Database();
        String queryString = null;
        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n" + "?x hotel:hasHotelRoom ?hotelRoom. \n" + "?hotelRoom hotel:roomType ?roomType. \n" + "?hotelRoom hotel:LivingRomType ?LivingRomType. \n" + "?hotelRoom hotel:hasNotAvailabilityPeriod ?notAvail. \n" + "?notAvail hotel:roomType ?roomType1. \n" + "?notAvail hotel:ToDate ?ToDate. \n" + "?notAvail hotel:LivingRomType ?LivingRomType1. \n" + " FILTER ( regex(?hotelname,\"" + hotelName + "\", \"i\"))" + " FILTER ( regex(?roomType,\"" + roomType + "\", \"i\"))";
        if (livingRoomType != null) {
            queryString = queryString + " FILTER ( regex(?LivingRomType,\"" + livingRoomType + "\", \"i\"))";
        /*
        +" FILTER ( regex(?roomType,\""
        + roomType + "\", \"i\")||regex(?roomType1,\""
        + roomType + "\", \"i\")) ";
        
        if(livingRoomType != null)
        queryString = queryString    +" FILTER ( regex(?LivingRomType,\""
        + livingRoomType + "\", \"i\")||regex(?LivingRomType1,\""
        + livingRoomType + "\", \"i\")) ";
         */
        }
        queryString = queryString + "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, om);
        Model model = ModelFactory.createDefaultModel();
        //   ArrayList <ResultSet> arr =new ArrayList<ResultSet>();
        try {
            ResultSet rs = queryexec.execSelect();
            while (rs.hasNext()) {
                model = rs.getResourceModel();




                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("hotel:" + binding.toString());
            /*   if (binding.get("notAvail").isLiteral()) {
            
            long todate = binding.getLiteral("ToDate").getLong();
            long beginDate = begin.getTime();
            if(todate < beginDate)
            arr.add(rs);
            }
            else arr.add(rs);                                               
             */
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //    long time = begin.getTime();
        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT DISTINCT * \n " 
                + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotelname. \n"
                + "?x hotel:hasHotelRoom ?hotelRoom. \n" 
                + "?hotelRoom hotel:roomType ?roomType. \n"
                + "?hotelRoom hotel:hasNotAvailabilityPeriod ?notAvail. \n"
                + "?notAvail hotel:roomType ?roomType1. \n" 
                + "?notAvail hotel:ToDate ?ToDate. \n" + "?notAvail hotel:LivingRoomType ?LivingRomType1. \n" + " FILTER ( regex(?hotelname,\"" + hotelName + "\", \"i\"))" + " FILTER ( regex(?roomType1,\"" + roomType + "\", \"i\"))";
        // + " FILTER (xsd:date(?date) >= xsd:date(\"2008-01-02\"))" ;



        /*              
        String query2 = "PREFIX ex: <http://temp.example.org/terms/>" 
        + "PREFIX loc: <http://simile.mit.edu/2005/05/ontologies/location#>" 
        + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" 
        + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" 
        + "SELECT *"
        + "WHERE {{?event ex:date ?date ." 
        + " FILTER (xsd:date(?date) >= xsd:date("+begin+") && xsd:date(?date) <= xsd:date("+2008-01-11+"))}" 
        + " UNION {?event ex:starts ?start; ex:finishes ?end." 
        + " FILTER (xsd:date(?start) >= xsd:date("2008-01-02") && xsd:date(?end) <= xsd:date("2008-01-10"))}" 
        + "}" 
        + "ORDER BY ?event" +  """;
        
         */

        if (livingRoomType != null) {
            queryString = queryString + " FILTER ( regex(?LivingRomType1,\"" + livingRoomType + "\", \"i\"))";
        }
        queryString = queryString + "}";

        Query query1 = QueryFactory.create(queryString);
        QueryExecution queryexec1 = QueryExecutionFactory.create(query1, om);
        Model model1 = ModelFactory.createDefaultModel();

        try {
            ResultSet rs = queryexec1.execSelect();
            Iterator<QuerySolution> results = queryexec1.execSelect();

            while (rs.hasNext()) {
                ResultSet rs1 = null;
                model1 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("notavail:" + binding.toString());


                String todate = binding.getLiteral("ToDate").getValue().toString();
                System.out.print("todate:" + todate);
            //   int toDate= todate.hashCode();
            //     int Begin = begin.hashCode();
            //    System.out.println("todate:"+todate);
            //    System.out.println("begin:"+begin);
                  /*      if(toDate < Begin) {
            System.out.println("availability");
            System.out.print( rs.getRowNumber());
            //       rs.next().getLiteral(begin);
            //         model.addLiteral(arg0, arg1, Begin);
            //         ResultSet.class
            
            
            }  */
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        return om;
    }

    /**
     * Dua cac thog tin trong chuoi input vao msg yeu cau tim kiem khach san
     * @param input
     * @param total
     * @return
     */
    public static OntModel insertMsg_HotelSearchRQ(String input, int total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

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
    public static String search(OntModel ontmodel) {
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
       

        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
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
            String queryString = null;

           s=printValues(hotelname);
            
        }

        return s;



    }

    /**
     * Hien thi thong tin
     * @param ind
     * @param prop
     */
    public static String printValues(String s) {
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
        HotelProcess hotelprocess = new HotelProcess();
//        hotelprocess.searchHotel(false, true, false, false, false, "inside", false, true, "Nam Dinh");
        hotelprocess.search2("HaiYen", "HotelSofitel");

        String s_begin = "2009-12-19";
        String s_end = "2009-12-20";
        //     hotelprocess.checkAvailability( "HaiYen", "LivingRoom", "SingleRoom");
        OntModel ontmodel = ModelFactory.createOntologyModel();



        String input = "Nam Dinh" + Message.FIELD_SEPARATE + s_begin + Message.FIELD_SEPARATE + s_end + Message.FIELD_SEPARATE + "MeetingRoom" + Message.FIELD_SEPARATE + "Spa" + Message.FIELD_SEPARATE + "Playroom";
        ontmodel = hotelprocess.insertMsg_HotelSearchRQ(input, 3);
       String ss = HotelProcess.search(ontmodel);
       // printValues("<http://www.owl-ontologies.com/Travel.owl#Hotel_1>");

    }
}
