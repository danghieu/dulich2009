/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.hotel;


import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import com.ptit.travel.agent.communication.Message;
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
import java.io.FileInputStream;
import java.io.*;
import com.ptit.travel.agent.communication.Message;
/**
 *
 * @author Administrator
 */
public class Test {
 public static OntModel insertMsg_HotelBookRQ(String input) {
        ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);

     
        // Tao mot OntModel trong, de dua cac thong tin vao 1 model
        OntModel model = ModelFactory.createOntologyModel();
        Individual ind = null;

        try {

            //tao ra 1 lop request de lay cac thong tin yeu cau
            OntClass oc = model.createClass("http://www.owl-ontologies.com/Travel.owl#Msg_HotelBookRQ");
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
                ind.addLiteral(Hotel.numberOfDay, Float.parseFloat(arr.get(11)));
             
        } catch (Exception e) {
            System.out.println(e.toString());

        }
       model.write(System.out);
        return model;
    }
   public static void testCustomer(boolean b, String input){
    if(b==false){
        System.out.println("khong dat duoc");
        return;
    } 
      String ont = "http://www.owl-ontologies.com/Travel.owl#";  
      Database.LoadOnt2Database()              ;        
      OntModel model = ModelFactory.createOntologyModel(
               PelletReasonerFactory.THE_SPEC, null);   
    String file = "C:/apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen10.owl";
      
       try {
            model.read(new FileInputStream(new File(file)), "");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
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
public static void main(String s[]){
           String s_begin = "2010-01-6";
        String s_end = "2010-01-7";
   String input1 = "HaiYen" + Message.FIELD_SEPARATE + "Nam Dinh" + Message.FIELD_SEPARATE
               + "405" + Message.FIELD_SEPARATE + "Thanh Xuan Bac"+ Message.FIELD_SEPARATE 
               + "MeetingRoom" + Message.FIELD_SEPARATE + "11" + Message.FIELD_SEPARATE + s_begin + Message.FIELD_SEPARATE + s_end
               
               +Message.FIELD_SEPARATE+"Hanh"+Message.FIELD_SEPARATE+"Broker" + Message.FIELD_SEPARATE+"162882805"+Message.FIELD_SEPARATE+"4";
   
//   boolean ss1 = HotelProcess.processBooking(input1);
               
   testCustomer(true, input1);
}   
}
