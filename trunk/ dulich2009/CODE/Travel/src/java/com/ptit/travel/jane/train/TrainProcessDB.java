/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.train;
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
import java.util.ArrayList;
import com.hp.hpl.jena.rdf.model.*;
import java.io.FileOutputStream;



import com.hp.hpl.jena.util.iterator.ExtendedIterator;
//import com.ptit.travel.jane.Train.Train;
import com.ptit.travel.jane.train.Train;
import org.apache.log4j.Logger;
//import com.ptit.travel.jane.train.Train;
/**
 *
 * @author Ariang
 */
public class TrainProcessDB {
private static Logger log = Logger.getLogger(TrainProcessDB.class.getName());
        public static OntModel insertMsg_TrainAvailRQ(String input, long total){
            ArrayList<String> arr = new ArrayList<String>();
           //Tach thong tin dua vao duoi dang String dua tren ky hieu phan tach da quy dinh
            arr = Message.split(input, Message.FIELD_SEPARATE);
            log.info ("Return Split: "+ arr.toString());
            //Tao OntoModel trong de dua thong tin vao model
            OntModel model= ModelFactory.createOntologyModel();
            Individual ind=null;
            try{
            // tao ra 1 lop request de lay cac thong tin duoc yeu cau
                OntClass oc= model.createClass(Train.getURI()+ "Msg_TrainAvailRQ");
                ind= model.createIndividual(Train.getURI()+ "Msg_TrainAvailRQ"+ total,oc);
                //them cac thuoc tinh vao ca the can tao
                if(arr.get(0)!=null){
                    ind.addLiteral(Train.DepartureRailway, arr.get(0));
                }
                if (arr.get(1)!=null){
                    ind.addLiteral(Train.ArrivalRailway, arr.get(1));
                }
                if(arr.get(2)!=null){
                    ind.addLiteral(Train.DepartureDate, arr.get(2));
                }
            }catch (Exception e) {
            System.out.println(e.toString());

        }           
            return model;
        }
        
        public static String search(String input){
        log.info("Starting search with: " + input);
        //  Database.LoadOnt2Database();
        String ont = "http://www.owl-ontologies.com/Train.owl#";
        // lay khung du lieu tu owl
  //      String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
        String file = "C:/apache-tomcat-6.0.16/webapps/MyOntology/Train2.owl";
        //dua ontology vao 1 model
              TrainDatabase.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, TrainDatabase.getOntologyModel());
         // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        System.out.print("Khai bao");
        ObjectProperty train = model.getObjectProperty(ont + "hasTrainTicket");
        DatatypeProperty ticketid=model.getDatatypeProperty(ont+ "ticketID");
        
        OntClass cl = model.getOntClass(ont + "Msg_TrainAvailRS");
       
        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_TrainAvailRQ(input, System.currentTimeMillis());
        model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day
        String s = "";

        
        while (extendedIterator.hasNext()) {
          
            
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());            
            String result = TrainProcess.printPropertyValues(individual,ticketid);
            System.out.println("\n result="+result);
            s = printValues(result);
            
        }
        
     //    xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
          Individual individual = model.getIndividual(ont + "Msg_TrainAvailRQ"+System.currentTimeMillis());
          if(individual!=null)
           individual.remove();

        return s;



    }
   /*     public static String searchID(String input){
        log.info("Starting search with: " + input);
        //  Database.LoadOnt2Database();
        String ont = "http://www.owl-ontologies.com/Train.owl#";
        // lay khung du lieu tu owl
  //      String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
        String file = "C:/apache-tomcat-6.0.16/webapps/MyOntology/Train2.owl";
        //dua ontology vao 1 model
              TrainDatabase.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, TrainDatabase.getOntologyModel());
         // phuc vu cho viec hien thi du lieu, cho nguoi lap trinh test
        System.out.print("Khai bao");
        ObjectProperty train = model.getObjectProperty(ont + "hasTrainTicket");
        DatatypeProperty ticketid=model.getDatatypeProperty(ont+ "ticketID");
        
        OntClass cl = model.getOntClass(ont + "Msg_TrainAvailRS");
       
        log.info("Insert msg to infer");
        // add model yeu cau vao ontology de tao ra 1 model moi chua tat ca cac rang buoc ke ca luat
        Model ontmodel = insertMsg_TrainAvailRQ(input, System.currentTimeMillis());
        model.add(ontmodel);
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day
        String s = "";

        
        while (extendedIterator.hasNext()) {
          
            
            OntResource resource = (OntResource) extendedIterator.next();
            System.out.println("Dich vu ket hop: " + cl);
            System.out.println("The hien: " + resource.getLocalName());

            System.out.println("Tai Nguyen");
            Individual individual = model.getIndividual(ont + resource.getLocalName());            
            String result = TrainProcess.printPropertyValues(individual,ticketid);
            System.out.println("\n result="+result);
            s = printValuesID(result);
            
        }
        
     //    xoa mesage search request voi respone khoi model (neu ko xoa 2 msg nay se tong tai trong csdl)
           Individual individual = model.getIndividual(ont + "Msg_TrainAvailRQ"+System.currentTimeMillis());
          if(individual!=null)
           individual.remove();

        return s;



    }*/
   /*     public static String printValuesID(String s){
            System.out.println("goi den ham hien thi ket qua");
   //     String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
          String file ="C:/apache-tomcat-6.0.16/webapps/MyOntology/Train2.owl";
    //        ArrayList<String> arr = new ArrayList<String>();
    //     arr = Message.split(s,Message.FIELD_SEPARATE );
         String result="";
         String output="";
          TrainDatabase.LoadOnt2Database();
        OntModel model = TrainDatabase.getOntologyModel();
        TrainDatabase.LoadOnt2Database();   
   
       String queryString = null;

    //   for(int i = 0; i<arr.size(); i++){ 
         result="";
         queryString="PREFIX train: <http://www.owl-ontologies.com/Train.owl#> \n"
                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x train:ticketID ?ticketid. \n"

                +"?x train:numberTickets ?totalNum. \n"
                +"?x train:numberbookedTicket ?bookedNum. \n"
                
    

                
                  + " FILTER regex(?ticketid,\"" +s + "\", \"i\")}";   
         
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
        Model model2 = ModelFactory.createDefaultModel();

        try{
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi");
            while(rs.hasNext()){
                System.out.print("ket qua");
                model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString());

                if(binding.getLiteral("ticketid").getValue().toString()!=null){
                    String name = binding.getLiteral("ticketid").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("totalNum").getValue().toString()!=null){
                    String name = binding.getLiteral("totalNum").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("bookedNum").getValue().toString()!=null){
                    String name = binding.getLiteral("bookedNum").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }

//                
                 result = result +Message.OBJECT_SEPARATE;

            }
        }         catch (Exception e1) {
            e1.printStackTrace();
        }
        output = output + result + Message.OBJECT_SEPARATE;
   //    }  
//        System.out.println("ket qua:"+output );
        return output;
    }*/
        public static String printValues(String s){
            System.out.println("goi den ham hien thi ket qua");
   //     String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
          String file ="C:/apache-tomcat-6.0.16/webapps/MyOntology/Train2.owl";
            ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(s,Message.FIELD_SEPARATE );
         String result="";
         String output="";
          TrainDatabase.LoadOnt2Database();
        OntModel model = TrainDatabase.getOntologyModel();
        TrainDatabase.LoadOnt2Database();   
   
       String queryString = null;

       for(int i = 0; i<arr.size(); i++){ 
         result="";
         queryString="PREFIX train: <http://www.owl-ontologies.com/Train.owl#> \n"
                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x train:ticketID ?ticketid. \n"

                +"?x train:hasChanges ?change. \n"
                +"?x train:hasPrice ?price. \n"
                +"?x train:trainJourneyClass ?class. \n"
                +"?x train:trainJourneyCode ?code. \n"
       //         +"?x train:numberTickets ?totalNum. \n"
          //      +"?x train:numberbookedTicket ?bookedNum. \n"
                
                +"?change train:hasBeginPoint ?Departure. \n"
                +"?Departure train:name ?dRailway. \n"
                +"?change train:hasEndPoint ?Arrival. \n"
                +"?Arrival train:name ?aRailway. \n"
                +"?change train:hasBeginTime ?beginTime. \n"
                +"?beginTime train:date ?date1. \n"
                +"?beginTime train:time ?time1. \n"
                +"?change train:hasEndTime ?endTime. \n"
                +"?endTime train:date ?date2. \n"
                +"?endTime train:time ?time2. \n"
   
                
                +"?price train:Amount ?amount. \n"
                +"?price train:CurrencyCode ?currencyCode. \n"

                
                  + " FILTER regex(?ticketid,\"" +arr.get(i) + "\", \"i\")}";   
         
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
       // System.out.print("thuc thi");
        Model model2 = ModelFactory.createDefaultModel();

        try{
            ResultSet rs = queryexec.execSelect();
            System.out.print("thuc thi");
            while(rs.hasNext()){
                System.out.print("ket qua");
                model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                System.out.println("truy2:" + binding.toString());
                String datetime1="";
                String datetime2="";
                String price1="";
                   if(binding.getLiteral("code").getValue().toString()!=null){
                    String name = binding.getLiteral("code").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("ticketid").getValue().toString()!=null){
                    String name = binding.getLiteral("ticketid").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("dRailway").getValue().toString()!=null){
                    String name = binding.getLiteral("dRailway").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("aRailway").getValue().toString()!=null){
                    String name = binding.getLiteral("aRailway").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("date1").getValue().toString()!=null){
                    String name = binding.getLiteral("date1").getValue().toString();
                    datetime1 = datetime1 + name +",";
                }
                if(binding.getLiteral("time1").getValue().toString()!=null){
                    String name = binding.getLiteral("time1").getValue().toString();
                    datetime1 = datetime1 + name;
                }
                result=result+datetime1+Message.FIELD_SEPARATE;
                if(binding.getLiteral("date2").getValue().toString()!=null){
                    String name = binding.getLiteral("date2").getValue().toString();
                    datetime2 = datetime2 + name +",";
                }
                if(binding.getLiteral("time2").getValue().toString()!=null){
                    String name = binding.getLiteral("time2").getValue().toString();
                    datetime2 = datetime2 + name;
                }
                result=result+datetime2+Message.FIELD_SEPARATE;
                if(binding.getLiteral("class").getValue().toString()!=null){
                    String name = binding.getLiteral("class").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
            
                if(binding.getLiteral("amount").getValue().toString()!=null){
                    String name = binding.getLiteral("amount").getValue().toString();
                    price1 = price1 + name +" ";
                }
                if(binding.getLiteral("currencyCode").getValue().toString()!=null){
                    String name = binding.getLiteral("currencyCode").getValue().toString();
                    price1 = price1 + name +Message.FIELD_SEPARATE;
                }
                result = result + price1 +Message.FIELD_SEPARATE;
                
                 result = result +Message.OBJECT_SEPARATE;

            }
        }         catch (Exception e1) {
            e1.printStackTrace();
        }
        output = output + result + Message.OBJECT_SEPARATE;
       }  
//        System.out.println("ket qua:"+output );
        return output;
    }
public static String printPropertyValues(Individual ind, Property prop) {
        System.out.print(ind.getLocalName() + " has " + prop.getLocalName() + "(s): ");
 //       System.out.println("Chay den ham prinP");
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
 public static boolean processBooking(String input){
     TrainDatabase.LoadOnt2Database();
     OntModel model = TrainDatabase.getOntologyModel();
//        OntModel model = ModelFactory.createOntologyModel(
//                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
      // chuyen nguoc lai tu csdl -> OntModel
      
      String ont = "http://www.owl-ontologies.com/Train.owl#";  
      System.out.println("booking");
      ArrayList <String> arrLT = new ArrayList<String>();
      arrLT = Message.split(input, Message.OBJECT_SEPARATE);
      System.out.println("arr: "+arrLT.toString());
      
      ArrayList<String> arr_customer = new ArrayList<String>(); 
      //ho ten, tuoi, email, dien thoai, so nha, duong, thanh pho, doi tuong
      arr_customer = Message.split(arrLT.get(1),Message.FIELD_SEPARATE);
      System.out.println("CustomerInfo: "+arr_customer);    
      System.out.println("arr_customer="+arr_customer.toString());
      String c_fullname=arr_customer.get(0);
 //     String c_sex=arr_customer.get(1);
      String c_age=arr_customer.get(1);
      String c_email=arr_customer.get(2);
      String c_phone=arr_customer.get(3);
      String c_houseNumber=arr_customer.get(4);
      String c_Street=arr_customer.get(5);
      String c_city=arr_customer.get(6);
      String c_personType=arr_customer.get(7);
      
      ArrayList<String> arr = new ArrayList<String>();
      //ticketID, trainJourneyCode,number
      arr = Message.split(arrLT.get(0),Message.FIELD_SEPARATE);  
      System.out.println("arr: "+arr.toString());
      
      String input_ticketid=arr.get(0);
      String input_trainCode=arr.get(1);
      float input_BookNumber=Float.parseFloat(arr.get(2));
    //  String queryString="";
//      
      String queryString="PREFIX train: <http://www.owl-ontologies.com/Train.owl#> \n"
                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x train:ticketID ?ticketid. \n"
                +"?x train:hasChanges ?change. \n"
                +"?x train:hasPrice ?price. \n"
                +"?x train:trainJourneyClass ?class. \n"
                +"?x train:trainJourneyCode ?code. \n"
                +"?x train:numberTickets ?totalNum. \n"
                +"?x train:numberbookedTicket ?bookedNum. \n"
                
                +"?change train:hasBeginPoint ?Departure. \n"
                +"?Departure train:name ?dRailway. \n"
                +"?change train:hasEndPoint ?Arrival. \n"
                +"?Arrival train:name ?aRailway. \n"
                +"?change train:hasBeginTime ?beginTime. \n"
                +"?beginTime train:date ?date1. \n"
                +"?beginTime train:time ?time1. \n"
                +"?change train:hasEndTime ?endTime. \n"
                +"?endTime train:date ?date2. \n"
                +"?endTime train:time ?time2. \n"
                
                +"?price train:Amount ?amount. \n"
                +"?price train:CurrencyCode ?currencyCode. \n"
   
                + " FILTER regex(?ticketid,\"" + input_ticketid + "\", \"i\")}";  
     Query query = QueryFactory.create(queryString);
     QueryExecution queryexec = QueryExecutionFactory.create(query, model);
      
      boolean isOk = false;
      
      try{
          ResultSet rs = queryexec.execSelect();
          boolean a=rs.hasNext();
         System.out.print(a);
     //     System.out.print(rs.toString());
         while(rs.hasNext()){
                      // boolean a=rs.hasNext();
        // System.out.print(a);
          String result=null;
          Object obj = rs.next();
          ResultBinding binding = (ResultBinding) obj;
          float totalnumber = Float.parseFloat(binding.getLiteral("totalNum").getValue().toString());   
                System.out.print("Total Number=" +totalnumber); 

          float totalbooknumber = Float.parseFloat(binding.getLiteral("bookedNum").getValue().toString());   
                System.out.print("Total Book Number=" +totalbooknumber);
           float tong=input_BookNumber + totalbooknumber;
          if(tong<=totalnumber){
              try{
                   String new_ticket=binding.getResource("x").toString();
                   if(new_ticket!=null){
                       Individual ind1=model.getIndividual(new_ticket);
                       if(ind1!=null){
                           ind1.removeAll(Train.numberbookedTicket);
                           ind1.addLiteral(Train.numberbookedTicket, totalbooknumber+input_BookNumber);
                        System.out.println("Them gia tri"); 
                        OntClass oc_address=model.createClass("http://www.owl-ontologies.com/Train.owl#Address");
                        Individual ind_address=model.createIndividual(ont + "Address_" + System.currentTimeMillis(),oc_address);
                        ind_address.addLiteral(Train.houseNumber, c_houseNumber);
                        ind_address.addLiteral(Train.street,c_Street);
                        ind_address.addLiteral(Train.city, c_city);

                        
                        OntClass oc_customer=model.createClass("http://www.owl-ontologies.com/Train.owl#CustomerData");
                        Individual ind_customer=model.createIndividual(ont + "CustomerData_" + System.currentTimeMillis(),oc_customer);
                        ind_customer.addLiteral(Train.GivenName, c_fullname);
                        ind_customer.addLiteral(Train.age,c_age);
                        ind_customer.addLiteral(Train.Email, c_email);
                        ind_customer.addLiteral(Train.PhoneNumer, c_phone);
                        ind_customer.addLiteral(Train.personType, c_personType);
                        ind_customer.addProperty(Train.hasAddress, ind_address);
                        
//                        OntClass oc_contract=model.createClass("http://www.owl-ontologies.com/Flight.owl#FlightBookContract");
//                        Individual ind_contract=model.createIndividual(ont + "FlightBookContract_" + System.currentTimeMillis(),oc_contract);
//                        ind_contract.addLiteral(Flight.bookNumber, input_BookNumber);
//                        ind_contract.addLiteral(Flight.id, id);
//                        ind_contract.addProperty(Flight.hasCustomer, ind_customer);
                        isOk=true;
                       }
                       else 
                    System.out.println("Khong du dau");
                   }
              }catch(Exception e){
                e.printStackTrace();
            }
          }
         }

                
      }catch(Exception e){
                e.printStackTrace();
            }
      return isOk;
 }
    public static void main(String arg[]) throws Exception{
        TrainProcessDB trainprocess=new TrainProcessDB();
        OntModel ontmodel = ModelFactory.createOntologyModel();
        String input="Ha Noi"+Message.FIELD_SEPARATE+"Phu Ly"+Message.FIELD_SEPARATE+"2009-12-29";
        String ss=trainprocess.search(input);
        System.out.print("ss="+ss);
//        String input1="HN_PL_SE1_1";
//        String s=trainprocess.searchID(input1);
//        System.out.print(s);
//        String input="HN_PL_SE1_1"+Message.FIELD_SEPARATE+"SE1"+Message.FIELD_SEPARATE+"5"
//                +Message.OBJECT_SEPARATE+"Hanh"+Message.FIELD_SEPARATE+"22"+Message.FIELD_SEPARATE
//                +"amin200587@yahoo.com"+Message.FIELD_SEPARATE+"38546204"+Message.FIELD_SEPARATE
//                +"19"+Message.FIELD_SEPARATE+"Khuat Duy Tien"+Message.FIELD_SEPARATE
//                +"Ha Noi"+Message.FIELD_SEPARATE+"student";
//        boolean ss=trainprocess.processBooking(input);
//        System.out.println("ss=" +ss);
    } 

}