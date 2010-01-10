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
import  com.ptit.travel.jane.train.Train;
import com.hp.hpl.jena.rdf.model.*;
import java.io.FileOutputStream;



import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class HotelProcess {

    private static Logger log = Logger.getLogger(HotelProcess.class.getName());
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

        queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" 
                + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" 
                + "?x hotel:hasHotelRoom ?meetingRoom. \n" 
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
                String todate = binding.getResource("x").toString();
                System.out.print("x=:" + todate);
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
              String todate = binding.getResource("x").toString();
                System.out.print("x=:" + todate);
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
                + "?notAvail hotel:ToDate ?ToDate. \n" + "?notAvail hotel:LivingRoomType ?LivingRomType1. \n"
                + " FILTER ( regex(?hotelname,\"" + hotelName + "\", \"i\"))" 
                + " FILTER ( regex(?roomType1,\"" + roomType + "\", \"i\"))";
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
    public static OntModel insertMsg_HotelSearchRQ(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();
System.out.println("goi den search1");
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
    
    
     public static OntModel insertMsg_HotelSearchRQFull(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQFull");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQFull" + total, oc);


            if (arr.get(0) != null) {
                ind.addLiteral(Hotel.city, arr.get(0));
            }
           
            if(arr.get(2)!=null){
                ind.addLiteral(Hotel.AreaWeather, arr.get(2));
            }
            
            if(arr.get(1)!=null)
                ind.addLiteral(Hotel.HotelLocation, arr.get(2));

        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }
    
 
    //Truong hop chi co thoi tiet
         public static OntModel insertMsg_HotelSearchRQ2(String input, long total) {
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("goi den search2");
        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQ2");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQ2" + total, oc);        
            
            if(arr.get(2)!=null){
                ind.addLiteral(Hotel.AreaWeather, arr.get(2));
            }
           
        } catch (Exception e) {
            System.out.println(e.toString());

        }
        model.write(System.out);
        return model;
    }
    
     // truong hop chi co vi tri    
           public static OntModel insertMsg_HotelSearchRQ3(String input, long total) {
      
               System.out.println("goi den search3");
               ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + "Msg_HotelSearchRQ3");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelSearchRQ3" + total, oc);
        
            if(arr.get(1)!=null)
                ind.addLiteral(Hotel.HotelLocation, arr.get(1));

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
        
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(input, Message.FIELD_SEPARATE);

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
        DatatypeProperty HotelName =model.getDatatypeProperty(ont + "HotelName");
        DatatypeProperty HotelName1 =model.getDatatypeProperty(ont + "city");
        OntClass cl = model.getOntClass(ont + "Msg_HotelSearchRS");
        long total = System.currentTimeMillis();

        log.info("Insert msg to infer");
        String nameMsg ="";
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        OntModel ontmodel = ModelFactory.createOntologyModel();
       if(arr.get(0).equals(" ") && !arr.get(1).equalsIgnoreCase(" ") && arr.get(2).equals(" ") )
       {  
        ontmodel = insertMsg_HotelSearchRQ3(input, total);
        nameMsg = "Msg_HotelSearchRQ3";
        
       }  
        
       if(!arr.get(0).equalsIgnoreCase(" ") && !arr.get(1).equalsIgnoreCase(" ")&& !arr.get(2).equals(""))
       {
           ontmodel = insertMsg_HotelSearchRQFull(input, total);
           nameMsg = "Msg_HotelSearchRQFull";
          
       }
        if(arr.get(0).equals(" ") && arr.get(1).equals(" ") && !arr.get(2).equalsIgnoreCase(" ") )
        {
            ontmodel = insertMsg_HotelSearchRQ2(input, total);
             nameMsg = "Msg_HotelSearchRQ2";
          
          
        }
        if(!arr.get(0).equalsIgnoreCase(" ") && arr.get(1).equals(" ") && arr.get(2).equals(" "))
        {
            ontmodel = HotelProcess.insertMsg_HotelSearchRQ(input, total);
            nameMsg = "Msg_HotelSearchRQ";
          
        } 
        
           
        
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
            String result = HotelProcess.printPropertyValues(individual,HotelName);
           System.out.println("result="+result);
            s = printValues(result);
         individual = model.getIndividual(Hotel.getURI() + nameMsg +total);           
          if(individual!=null)
           individual.remove();
          
            
        }
        
        // xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
           
                   

        return s;



    }

    /**
     * Hien thi thong tin
     * @param ind
     * @param prop
     * output: address ( number -street - city) - areaweather -checin - checkout - email -  location - star - (roomType - amount - total): có nhieu
     */
    public static String printValues(String input) {
        System.out.println("goi den ham hien thi ket qua");
        String file = "C://apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen6.owl";

         ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(input,Message.FIELD_SEPARATE );
         String result="";
         String output="";
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

       for(int i = 0; i<arr.size(); i++){ 
         result="";
         queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" 
                + "SELECT DISTINCT * \n " + "WHERE \n" + "{ \n" 
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
         
     
                queryString = queryString + "?hotel hotel:hotelFacilities ?facilities"  + ".\n";          
                queryString = queryString + " FILTER regex(?hotelname,\"" +arr.get(i) + "\", \"i\")}";
         
                Query query = QueryFactory.create(queryString);
                QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
     //   Model model2 = ModelFactory.createDefaultModel();
      
        try {
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi");
            String facilities ="facilities";
           
        
            while (rs.hasNext()) {
                System.out.print("ket qua");
              //  model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
             //   System.out.println("truy2:" + binding.toString());
               
               
                String Address = "";
                
                if(binding.getLiteral("number").getValue().toString()!=null){
                    
                    String name = binding.getLiteral("number").getValue().toString();
                    int index = result.indexOf(name);
                    if(index < 0 || index > result.length())
                    {
                       
                        Address = Address + name +" _ ";
                
                         if(binding.getLiteral("Street").getValue().toString()!=null){
                              String street = binding.getLiteral("Street").getValue().toString();
                              Address = Address + street +" _ ";
                         }
                        if(binding.getLiteral("city").getValue().toString()!=null){
                             String city = binding.getLiteral("city").getValue().toString();
                              Address = Address + city +" _ ";
                          }
                
                        if(binding.getLiteral("Country").getValue().toString()!=null){
                         String country = binding.getLiteral("Country").getValue().toString();
                         Address = Address + country ;
                         }
                
                       result = result + Address +Message.FIELD_SEPARATE;
                
                         
                           String  area = binding.getLiteral("areaWeather").getValue().toString();
                          result = result + area +Message.FIELD_SEPARATE;
                      
                
                     
                           String checkIn = binding.getLiteral("checkIn").getValue().toString();
                           result = result + checkIn +Message.FIELD_SEPARATE;
                           
                
                    
                        String checkOut = binding.getLiteral("checkOut").getValue().toString();
                        result = result + checkOut +Message.FIELD_SEPARATE;
              
                

                       String email = binding.getLiteral("email").getValue().toString();
                       result = result + email +Message.FIELD_SEPARATE;
             
                
                
                    String hotelname = binding.getLiteral("hotelname").getValue().toString();
                    result = result + hotelname +Message.FIELD_SEPARATE;
             
                
         
                    String location = binding.getLiteral("location").getValue().toString();
                    result = result + location +Message.FIELD_SEPARATE;
              
                
                
                    String star = binding.getLiteral("star").getValue().toString();
                    result = result + star +Message.FIELD_SEPARATE;
              
           
                    }
                }
                            
                
                
             
                    
                    String roomtype = binding.getLiteral("roomtype").getValue().toString();
                    int index = result.indexOf(roomtype);
                    
                    if(index < 0 || index > result.length())
                    {
                      result = result + roomtype + Message.FIELD_SEPARATE+ binding.getLiteral("amount").getValue().toString()+Message.FIELD_SEPARATE+ binding.getLiteral("currency").getValue().toString()+Message.FIELD_SEPARATE;
                      
                   
                }        
                    String name = binding.getLiteral("facilities").getValue().toString();    
                     int index1 = result.indexOf(roomtype);
                    
                    if(index1 < 0 || index1 > facilities.length())
                    {
                     
                      facilities = facilities + name +",";
                                    
                   }     
                    
	} 
          
            }
         catch (Exception e1) {
            e1.printStackTrace();
        }
        output = output + result + Message.OBJECT_SEPARATE;
       }  
        System.out.println("ket qua:"+output );
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
        String result ="";
        if (!i.hasNext()) {
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


  /**
   * 
   * @param input: HotelName - City - number-street - roomType -Number -  fromdate - todate - fullName - Profession - IdentityCard 
   * @param total: bien xac dinh tinh duy nhat cua Msg
   * @return: 1 model chua thong tin dung trong viec xu ly dat dich vu khach san.
   */
     
     public static OntModel insertMsg_HotelBookRQ(String input) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

        log.info("Resulted SPLIT: " + arr.toString());
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass(Hotel.getURI() + " Msg_HotelBookRQ");
            ind = model.createIndividual(Hotel.getURI() + "Msg_HotelBookRQ" + System.currentTimeMillis(), oc);
        
                ind.addLiteral(Hotel.HotelName, arr.get(0));         
                ind.addLiteral(Hotel.city, arr.get(1));         
                ind.addLiteral(Hotel.number, arr.get(2));            
                ind.addLiteral(Hotel.street, arr.get(3));           
                ind.addLiteral(Hotel.roomType, arr.get(4));   
                ind.addLiteral(Hotel.Number,Float.parseFloat(arr.get(5)));
                ind.addLiteral(Hotel.fullName, arr.get(8));          
                ind.addLiteral(Hotel.profession, arr.get(9));         
                ind.addLiteral(Hotel.IdentityCard, arr.get(10));           
             
        } catch (Exception e) {
            System.out.println(e.toString());

        }
       model.write(System.out);
        return model;
    }
     
     
     
     
   

     
     
     
     // cach tiep - co len nao
     /**
      * 
      * @param input: HotelName - City - number-street - roomType -Number -  fromdate - todate - fullName - Profession - IdentityCard 
      * @return
      */
     
  public static boolean processBooking(String input){
        Database.LoadOnt2Database();
        
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
      // chuyen nguoc lai tu csdl -> OntModel
      
      String ont = "http://www.owl-ontologies.com/Travel.owl#";  
      System.out.println("booking");
      ArrayList <String> arr = new ArrayList<String>();
      arr = Message.split(input, Message.FIELD_SEPARATE);
      System.out.println("arr: "+arr.toString());
      String queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n" + "SELECT * \n " 
                + "WHERE \n" + "{ \n" + "?x hotel:HotelName ?hotel_Name. \n"
             
                + "?x hotel:hasContact ?contact. \n" 
                + "?contact hotel:hasAddress ?add. \n" 
                + "?add hotel:city ?add_city. \n" 
                + "?add hotel:street ?add_street. \n" 
                + "?add hotel:number ?add_number. \n" 
               
                + "?x hotel:hasHotelRoom ?hotel_Room. \n" 
                + "?hotel_Room hotel:roomType ?room_Type. \n"       
                + "?hotel_Room hotel:Number ?total. \n"  
                + "?hotel_Room hotel:hasNotAvailabilityPeriod ?notAvail. \n"
        
                
                + "?notAvail hotel:roomType ?room_Type1. \n" 
                + "?notAvail hotel:ToDate ?to_Date. \n" 
                + "?notAvail hotel:FromDate ?from_Date. \n" 
                + "?notAvail hotel:Number ?number1. \n"
       
                
                + " FILTER ( regex(?hotel_Name,\"" + arr.get(0) + "\", \"i\"))" 
                + " FILTER ( regex(?add_city,\"" + arr.get(1) + "\", \"i\"))"
                + " FILTER ( regex(?add_number,\"" + arr.get(2) + "\", \"i\"))"
                + " FILTER ( regex(?add_street,\"" + arr.get(3) + "\", \"i\"))"
                + " FILTER ( regex(?room_Type,\"" + arr.get(4) + "\", \"i\"))"
          
                + " FILTER ( regex(?room_Type1,\"" + arr.get(4) + "\", \"i\"))}";
     
      Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
    


        float numberNotAvail = 0, Number=0;
        boolean isOk = false;
        String notavail=null; //  the hien co khoang thoi gian dat truoc trung voi khoang thoi gian muon dat
        float number=0; //  luu gia tri cu
        String hotelroom = null;
        
        try {
            ResultSet rs = queryexec.execSelect();
          
            float total = 0;
            float maxTotal =0;
           
            
            while (rs.hasNext()) {
                 System.out.print("co gia tri thoa man");
              
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("ket qua truy van :" + binding.toString());
            
                total = Float.parseFloat(binding.getLiteral("total").getValue().toString());                           
              
                String todate = binding.getLiteral("to_Date").getValue().toString();
                System.out.println("ToDate truy van :"+todate);
                String fromdate = binding.getLiteral("from_Date").getValue().toString();
                System.out.println("FromDate truy van :"+fromdate);
                Number = Float.parseFloat(binding.getLiteral("number1").getValue().toString());
              
                 // Tim ra khoang dat gan nhat phia tren ngay muon dat
                  hotelroom = binding.getResource("hotel_Room").toString();
                    System.out.println("hotel:"+hotelroom);
              
                    
                  //TH 0: ca 2 cung trung voi 1 ngay da duoc dat truoc   
                 if(fromdate.equalsIgnoreCase(arr.get(6))&& todate.equalsIgnoreCase(arr.get(7))){
                    number = Number;
                    System.out.println("truong hop 0");
                    notavail = binding.getResource("notAvail").toString();
                   
                    numberNotAvail = numberNotAvail+ Number;
                }
                  
                 // TH1: fromdate < arr.get(6) < todate
                 if(fromdate.compareTo(arr.get(6))<0&& todate.compareTo(arr.get(6))>0 && arr.get(7).compareTo(todate)>0)
                     numberNotAvail = numberNotAvail+ Number;
                 
                
                // TH2: fromdate < arr.get(7) < todate
                 if(fromdate.compareTo(arr.get(7))<0&& todate.compareTo(arr.get(7))>0 && fromdate.compareTo(arr.get(6))>0)
                    numberNotAvail = numberNotAvail+ Number;
                 
                // TH3: fromdate < arr.get(6) < arr.get(7) < todate
                 if(fromdate.compareTo(arr.get(6))<0&& todate.compareTo(arr.get(7))>0)
                     numberNotAvail = numberNotAvail+ Number;
                 
                // TH4: arr.get(6) < fromdate <  todate <arr.get(7);
                 if(fromdate.compareTo(arr.get(6))>0&& todate.compareTo(arr.get(7))<0)
                      numberNotAvail = numberNotAvail+ Number;
                    
                // TH5:fromdate hoac todate bi trung
                 if((fromdate.equals(arr.get(6))&& todate.compareTo(arr.get(7))<0)||(fromdate.compareTo(arr.get(6))>0&& todate.equals(arr.get(7))))
                 {
                     System.out.println("truong hop 5");
                     numberNotAvail = numberNotAvail+ Number; 
                 }
                
            // sau khi xac dinh duoc cac khoang can ke
            
            }
            
            float newNumber = numberNotAvail+ Float.parseFloat(arr.get(5));   
             System.out.println(" ");
              System.out.println(" ");
                     
            System.out.println("Tong so phong da bi dat truoc: "+numberNotAvail +"total = "+total);
            if(newNumber<=total)
                 if(notavail != null ){
                     Individual ind = model.getIndividual(notavail);
                     if(ind!=null){                    
                        
                        System.out.println("co khoang trung");
                        ind.removeAll(Hotel.Number);
			ind.addLiteral(Hotel.Number, number+Float.parseFloat(arr.get(5)));
                        isOk=true;
                        	
                         }
                  }
                 else{
                      
                OntModel model1 = ModelFactory.createOntologyModel();
                        try {
                            System.out.println("them gia tri");
                      OntClass oc = model1.createClass("http://www.owl-ontologies.com/Travel.owl#NotAvailabilityPeriod");
                      Individual ind = model1.createIndividual(ont + "NotAvailabilityPeriod_" + System.currentTimeMillis(),oc);
                      ind.addLiteral(Hotel.roomType, arr.get(4) );                        		
		      ind.addLiteral(Hotel.ToDate,arr.get(7));			
		       ind.addLiteral(Hotel.FromDate,arr.get(6));			
                      
                      ind.addLiteral(Hotel.Number, Float.parseFloat(arr.get(5)));
                      
                      System.out.println("hotelRoom: "+hotelroom);
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
                        isOk=true;
                  }
            }catch(Exception e){
                e.printStackTrace();
                isOk = false;
            }
  
            if(isOk==false){
        System.out.println("khong dat duoc");
        return false;
    } 
            
    System.out.println("xu ly lay gia cuoi cung");
            OntModel ontmodel = insertMsg_HotelBookRQ(input);
            model.add(ontmodel);
            
            
            DatatypeProperty price =model.getDatatypeProperty(ont + "Result");
       OntClass cl = model.getOntClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRS");   
      
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
            System.out.println("" +hotelname );         
                      
        }  
        
        testCustomer(isOk,input);
        return isOk;
  }
     
     
     
     
     
     /**
      * input: roomtype - Number - toDate - fromDate 
      * @param input
      * @return
      */ 
     
     
     
     
     
 public static void testCustomer(boolean b, String input){
    if(b==false){
        System.out.println("khong dat duoc");
        return;
    } 
      String ont = "http://www.owl-ontologies.com/Travel.owl#";  
      Database.LoadOnt2Database()              ;        
      OntModel model = ModelFactory.createOntologyModel(
               PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());   
    /*String file = "C:/apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen10.owl";
      
       try {
            model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    */
    System.out.println("xu ly lay gia cuoi cung");
            OntModel ontmodel = insertMsg_HotelBookRQ(input);
            model.add(ontmodel);
            
            
           DatatypeProperty price =model.getDatatypeProperty(ont + "price");
            OntClass cl = model.getOntClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRS");   
      
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
            System.out.println("" +hotelname );                      
        }
  try {

        ArrayList<String> arr = new ArrayList<String>();
        
        arr = Message.split(input, Message.FIELD_SEPARATE);
            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRQ");
           Individual ind = model.getIndividual("http://www.owl-ontologies.com/Travel.owl#Customer_"+arr.get(10));
           if(ind!=null){
               System.out.println("Co khach hang nay trong sdl");
           return;
           }         
           
           ind = model.createIndividual("http://www.owl-ontologies.com/Travel.owl#Customer_" + arr.get(10), oc);                
           ind.addLiteral(Hotel.fullName, arr.get(8));          
           ind.addLiteral(Hotel.profession, arr.get(9));         
           ind.addLiteral(Hotel.IdentityCard, arr.get(10));  
           
                     
        } catch (Exception e) {}
 
 
 }   
     
     
 
 
 public static void searchNotAvailability(){
       Database.LoadOnt2Database();
       OntModel model = Database.getOntologyModel();
        String ont = "http://www.owl-ontologies.com/Travel.owl#";  
    
      
      
        // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
          DatatypeProperty roomtype =model.getDatatypeProperty(ont + "roomType");
         DatatypeProperty number =model.getDatatypeProperty(ont + "Number");
          DatatypeProperty todate =model.getDatatypeProperty(ont + "ToDate");
           DatatypeProperty fromdate =model.getDatatypeProperty(ont + "FromDate");
        OntClass cl = model.getOntClass(ont + "NotAvailabilityPeriod");
      

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
           String hotelName =  (individual.listPropertyValues(number).next()).toString();
          //  int index = hotelName.indexOf("^^");
         //   String hotelname = hotelName.substring(0, index);
               System.out.println("" +hotelName );
            
               String todate1 =  (individual.listPropertyValues(todate).next()).toString();
           
          //  int indexToDate = todate1.indexOf("^^");
          //  String todate2 = todate1.substring(0, indexToDate);
             System.out.println("" +todate1 );
            
            String fromdate1 =  (individual.listPropertyValues(fromdate).next()).toString();
         //   int indexFromDate = fromdate1.indexOf("^^");
          //  String fromdate2 = fromdate1.substring(0, indexFromDate);
             System.out.println("" +fromdate1 );
             String room =  (individual.listPropertyValues(roomtype).next()).toString();
         //   int indexFromDate = fromdate1.indexOf("^^");
          //  String fromdate2 = fromdate1.substring(0, indexFromDate);
             System.out.println("" +room );
            
        }    
            
             
 }
 
 // Train
 
 
 

 
 
 
 
 
 
 
 
 
    public static void main(String s[]) throws Exception {
        HotelProcess hotelprocess = new HotelProcess();
//        hotelprocess.searchHotel(false, true, false, false, false, "inside", false, true, "Nam Dinh");
      //  hotelprocess.search2("HaiYen", "HotelSofitel");
        String s_begin = "2010-01-6";
        String s_end = "2010-01-7";
        //     hotelprocess.checkAvailability( "HaiYen", "LivingRoom", "SingleRoom");
      
  String input = " "+Message.FIELD_SEPARATE+"inside"+Message.FIELD_SEPARATE+" ";
       
        
   String input1 = "HaiYen" + Message.FIELD_SEPARATE + "Nam Dinh" + Message.FIELD_SEPARATE
               + "405" + Message.FIELD_SEPARATE + "Thanh Xuan Bac"+ Message.FIELD_SEPARATE 
               + "MeetingRoom" + Message.FIELD_SEPARATE + "2" + Message.FIELD_SEPARATE + s_begin + Message.FIELD_SEPARATE + s_end
               +Message.FIELD_SEPARATE+"Hanh"+Message.FIELD_SEPARATE+"Sinh Vien" + Message.FIELD_SEPARATE+"162882805";
   
   boolean ss1 = HotelProcess.processBooking(input1);
               
//   HotelProcess.testCustomer(true, input1);
   
   
               
//   String input2 = "Nam Dinh"+Message.FIELD_SEPARATE+null+Message.FIELD_SEPARATE+null;
  //  String ss = HotelProcess.search(input);
               
               
  
    }
}
