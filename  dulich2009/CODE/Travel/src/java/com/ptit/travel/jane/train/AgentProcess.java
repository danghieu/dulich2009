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
//import com.ptit.travel.jane.train.Train;
import org.apache.log4j.Logger;
import com.ptit.travel.moduleJDBC.Model.AgentDB;
/**
 *
 * @author Ariang
 */
public class AgentProcess {
private static Logger log = Logger.getLogger(AgentProcess.class.getName());
    
 

    public static String searchAgent(String s){
   //     System.out.println("goi den ham hien thi ket qua");
   //     String file = "C://Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/Train.owl";
   //       String file ="C:/apache-tomcat-6.0.16/webapps/MyOntology/Agent.owl";
  //          ArrayList<String> arr = new ArrayList<String>();
  //       arr = Message.split(s,Message.FIELD_SEPARATE );
         String result="";
 //        String output="";
          AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
  //     TrainDatabase.LoadOnt2Database();   
   
       String queryString = null;
   //     for(int i = 0; i<arr.size(); i++){
   //         result="";
//            queryString ="PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n"
//                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
//                +"?x agent:id ?ID. \n"
//                +"?x agent:state ?State. \n"
//                +"?x agent:type ?Type. \n"
//                   
//                + " FILTER regex(?Type,\"" + s+ "\", \"i\")}";  
       queryString="PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n"
                + "SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x agent:id ?ID. \n"
                +"?x agent:state ?State. \n"
                +"?x agent:type ?Type. \n"

   
                + " FILTER regex(?Type,\"" + s + "\", \"i\")}";  
    //    }
       Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
//        Model model2 = ModelFactory.createDefaultModel();
        try{
            ResultSet rs = queryexec.execSelect();System.out.println("cos j hok");
               boolean a=rs.hasNext();
         System.out.print(a);
            while(rs.hasNext()){
                System.out.println("cos j hok");
 //               model2 = rs.getResourceModel();
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                if(binding.getLiteral("ID").getValue().toString()!=null){
                    String name = binding.getLiteral("ID").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("State").getValue().toString()!=null){
                    String name = binding.getLiteral("State").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
                if(binding.getLiteral("Type").getValue().toString()!=null){
                    String name = binding.getLiteral("Type").getValue().toString();
                    result = result + name +Message.FIELD_SEPARATE;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//         output = output + result + Message.OBJECT_SEPARATE;
         System.out.print(result);
       return result;
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
    public static void main(String arg[]) throws Exception{
        AgentProcess agent=new AgentProcess();
        String s="controller";
        String ss=agent.searchAgent(s);
        System.out.println(ss);
    }
}
