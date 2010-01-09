/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.agent;

/**
 *
 * @author Ariang
 */

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
            
            while(rs.hasNext()){
                
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                String ma=binding.getLiteral("ID").getValue().toString();
                String loai=binding.getLiteral("Type").getValue().toString();
                String trangthai=binding.getLiteral("State").getValue().toString();
                if(trangthai.equalsIgnoreCase("active"))
                result= ma;
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return result;
    }
    public static int searchState(String id){
        int giatri=0;
        AgentDB.LoadOnt2Database();
        OntModel model=AgentDB.getOntologyModel();
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n"
                +"SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x agent:id ?ID. \n"
                +"?x agent:type ?Type. \n"
                +"?x agent:state ?State. \n"
                +"FILTER regex(?ID, \""+ id + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet rs = queryexec.execSelect();
            while(rs.hasNext()){
                
                Object obj = rs.next();
                ResultBinding binding=(ResultBinding) obj;
                String ma=binding.getLiteral("ID").getValue().toString();
                String trangthai=binding.getLiteral("State").getValue().toString();
                if(trangthai.equalsIgnoreCase("active")) giatri=1;
                if(trangthai.equalsIgnoreCase("passive")) giatri=2;
            }
        }catch(Exception e){
                e.printStackTrace();
            }
        return giatri;
    }
    public static void main(String arg[]) throws Exception{
        AgentProcess agent=new AgentProcess();
        String s="controller";
    //    String ss=agent.searchAgent(s);
        int ss=agent.searchState("ControllerAgent");
        System.out.println(ss);
        
    }
}
