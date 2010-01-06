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
               //     System.out.print("ga di="+arr.get(0));
                }
                if (arr.get(1)!=null){
                    ind.addLiteral(Train.ArrivalRailway, arr.get(1));
                }
                if(arr.get(2)!=null){
                    ind.addLiteral(Train.DepartureDate, arr.get(2));
                }
           //     if(arr.get(3)!=null){
          //          ind.addProperty(Train.date, arr.get(3));
          //      }
            }catch (Exception e) {
            System.out.println(e.toString());

        }
//            System.out.print("insert/n");
//            model.write(System.out);
            
            return model;
        }
        
        public static String search(String input){
        log.info("Starting search with: " + input);
        //  Database.LoadOnt2Database();
        String ont = "http://www.owl-ontologies.com/Train.owl#";
        // lay khung du lieu tu owl
  //      String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
        String file = "C:/apache-tomcat-6.0.16/webapps/MyOntology/Train1.owl";
        //dua ontology vao 1 model
              TrainDatabase.LoadOnt2Database();
        OntModel model = ModelFactory.createOntologyModel(
                PelletReasonerFactory.THE_SPEC, TrainDatabase.getOntologyModel());
//       OntModel model = ModelFactory.createOntologyModel(
//                PelletReasonerFactory.THE_SPEC, null);
//        try {
//            model.read(new FileInputStream(new File(file)), "");
//             System.out.print("doc file /n");
//    //         model.write(System.out);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


       

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
        public static String printValues(String s){
            System.out.println("goi den ham hien thi ket qua");
   //     String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
          String file ="C:/apache-tomcat-6.0.16/webapps/MyOntology/Train1.owl";
            ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(s,Message.FIELD_SEPARATE );
         String result="";
         String output="";
          TrainDatabase.LoadOnt2Database();
        OntModel model = TrainDatabase.getOntologyModel();
        TrainDatabase.LoadOnt2Database();   
        //dua ontology vao 1 model
//        OntModel model = ModelFactory.createOntologyModel(
//                //OntModelSpec.OWL_MEM_RULE_INF, null);
//                PelletReasonerFactory.THE_SPEC, null);
//        try {
//            model.read(new FileInputStream(new File(file)), "");
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }      
       String queryString = null;

       for(int i = 0; i<arr.size(); i++){ 
         result="";
         queryString="PREFIX train: <http://www.owl-ontologies.com/Train.owl#> \n"
                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x train:ticketID ?ticketid. \n"
       //         +"?x train:hasTrainTicket ?TrainTicket. \n"
       //         +"?TrainTicket train:ticketID ?ticketid. \n"
      //          +"?TrainTicket train:hasChanges ?change. \n"
                +"?x train:hasChanges ?change. \n"
                +"?x train:hasPrice ?price. \n"
                +"?x train:trainJourneyClass ?class. \n"
                +"?x train:trainJourneyCode ?code. \n"
                
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
        //        +"?TrainTicket train:hasPrice ?price. \n"
                
                +"?price train:Amount ?amount. \n"
                +"?price train:CurrencyCode ?currencyCode. \n"
      //          +"?TrainTicket train:trainJourneyClass ?class. \n"
        //        +"?TrainTicket train:trainJourneyCode ?code. \n"
                
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
                    datetime1 = datetime1 + name +Message.FIELD_SEPARATE;
                }
                result=result+datetime1+Message.FIELD_SEPARATE;
                if(binding.getLiteral("date2").getValue().toString()!=null){
                    String name = binding.getLiteral("date2").getValue().toString();
                    datetime2 = datetime2 + name +",";
                }
                if(binding.getLiteral("time2").getValue().toString()!=null){
                    String name = binding.getLiteral("time2").getValue().toString();
                    datetime2 = datetime2 + name +Message.FIELD_SEPARATE;
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
// public static boolean processBooking(String input){
//     Database.LoadOnt2Database();
//        
//        OntModel model = ModelFactory.createOntologyModel(
//                PelletReasonerFactory.THE_SPEC, Database.getOntologyModel());
//      // chuyen nguoc lai tu csdl -> OntModel
//      
//      String ont = "http://www.owl-ontologies.com/Train.owl#";  
//      System.out.println("booking");
//      ArrayList <String> arr = new ArrayList<String>();
//      arr = Message.split(input, Message.FIELD_SEPARATE);
//      System.out.println("arr: "+arr.toString());
//      
// }
    public static void main(String arg[]) throws Exception{
        TrainProcessDB trainprocess=new TrainProcessDB();
        OntModel ontmodel = ModelFactory.createOntologyModel();
        String input="Ha Noi"+Message.FIELD_SEPARATE+"Phu Ly"+Message.FIELD_SEPARATE+"2009-12-29";
        String ss=trainprocess.search(input);
        System.out.print("ss="+ss);
    } 

}