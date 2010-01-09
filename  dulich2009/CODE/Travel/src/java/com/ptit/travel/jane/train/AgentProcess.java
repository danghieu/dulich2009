/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.train;

import com.ptit.travel.agent.communication.Message;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.ptit.travel.moduleJDBC.Model.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Ariang
 */
public class AgentProcess {
private static Logger log = Logger.getLogger(AgentProcess.class.getName());
    public static String searchAgent(String s){
        AgentDB.LoadOnt2Database();
        OntModel model=AgentDB.getOntologyModel();
        String result ="";
        
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n"
                +"SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x agent:id ?ID. \n"
                +"?x agent:type ?Type. \n"
                +"?x agent:state ?State. \n"
                +"FILTER regex(?Type,\""+ s + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet rs = queryexec.execSelect();
            System.out.print("dsfsdf");
            while(rs.hasNext()){
                System.out.println("duoc roi nay");
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                String ma=binding.getLiteral("ID").getValue().toString();
                String loai=binding.getLiteral("Type").getValue().toString();
                String trangthai=binding.getLiteral("State").getValue().toString();
                result= ma + Message.FIELD_SEPARATE+ loai+Message.FIELD_SEPARATE+ trangthai;
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return result;
    }
            
 

/*    public static String searchAgent(String s){
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
//                if(binding.getLiteral("State").getValue().toString()!=null){
//                    String name = binding.getLiteral("State").getValue().toString();
//                    result = result + name +Message.FIELD_SEPARATE;
//                }
//                if(binding.getLiteral("Type").getValue().toString()!=null){
//                    String name = binding.getLiteral("Type").getValue().toString();
//                    result = result + name +Message.FIELD_SEPARATE;
//                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//         output = output + result + Message.OBJECT_SEPARATE;
         System.out.print(result);
       return result;
    }*/

    public static void main(String arg[]) throws Exception{
        AgentProcess agent=new AgentProcess();
        String s="controller";
        String ss=agent.searchAgent(s);
        System.out.println(ss);
    }
}
