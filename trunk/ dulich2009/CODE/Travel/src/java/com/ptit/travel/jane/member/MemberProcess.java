/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.member;

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
public class MemberProcess {
private static Logger log = Logger.getLogger(MemberProcess.class.getName());
    public static String searchPassword(String id){
        MemberDatabase.LoadOnt2Database();
        OntModel model=MemberDatabase.getOntologyModel();
        String result =null;
        
        String queryString = "PREFIX member: <http://www.owl-ontologies.com/partner-info.owl#> \n"
                +"SELECT DISTINCT * \n" + "WHERE \n" +"{\n"
                +"?x member:id ?ID. \n"
                +"?x member:password ?Pass. \n"
                +"FILTER regex(?ID,\""+ id + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try{
            ResultSet rs = queryexec.execSelect();
            
            while(rs.hasNext()){
              //  System.out.println("dfjksdf");
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                String ma=binding.getLiteral("Pass").getValue().toString();
         //       String loai=binding.getLiteral("Type").getValue().toString();
           //     String trangthai=binding.getLiteral("State").getValue().toString();
           //     if(trangthai.equalsIgnoreCase("active"))
                result= ma;
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return result;
    }
    public static void main(String arg[]) throws Exception{
        MemberProcess mem=new MemberProcess();
    //    String s="controller";
    //    String ss=agent.searchAgent(s);
        String ss=mem.searchPassword("amin");
        System.out.println(ss);
        
    }
}
