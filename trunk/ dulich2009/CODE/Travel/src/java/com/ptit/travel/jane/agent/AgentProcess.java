/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.jane.agent;

/**
 *
 * @author Ariang
 */
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
import org.apache.log4j.Logger;

public class AgentProcess {

    private static Logger log = Logger.getLogger(AgentProcess.class.getName());

    public static ArrayList<String> searchAgent(String s) {
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        ArrayList<String> result = new ArrayList<String>();

        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" + "SELECT DISTINCT * \n" + "WHERE \n" + "{\n" + "?x agent:id ?ID. \n" + "?x agent:type ?Type. \n" + "?x agent:state ?State. \n" + "FILTER regex(?Type,\"" + s + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = queryexec.execSelect();

            while (rs.hasNext()) {

                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                String ma = binding.getLiteral("ID").getValue().toString();
                String loai = binding.getLiteral("Type").getValue().toString();
                String trangthai = binding.getLiteral("State").getValue().toString();
                if (trangthai.equalsIgnoreCase("active")) {
                    result.add(ma);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }

    public static int searchState(String id) {
        int giatri = 0;
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" + "SELECT DISTINCT * \n" + "WHERE \n" + "{\n" + "?x agent:id ?ID. \n" + "?x agent:type ?Type. \n" + "?x agent:state ?State. \n" + "FILTER regex(?ID, \"" + id + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = queryexec.execSelect();
            while (rs.hasNext()) {

                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                String ma = binding.getLiteral("ID").getValue().toString();
                String trangthai = binding.getLiteral("State").getValue().toString();
                if (trangthai.equalsIgnoreCase("active")) {
                    giatri = 1;
                }
                if (trangthai.equalsIgnoreCase("passive")) {
                    giatri = 2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return giatri;
    }
    public static void insertAgent(String info){
         ArrayList<String> arr = new ArrayList<String>();
         arr = Message.split(info, Message.FIELD_SEPARATE);
            log.info ("Return Split: "+ arr.toString());
            //Tao OntoModel trong de dua thong tin vao model
         AgentDB.LoadOnt2Database();
         OntModel model = AgentDB.getOntologyModel();
            Individual ind=null;
            try{
            // tao ra 1 lop request de lay cac thong tin duoc yeu cau
                OntClass oc= model.createClass(Agent.getURI()+ "Agent");
                ind= model.createIndividual(Agent.getURI()+ "Agent_"+ System.currentTimeMillis(),oc);
                //them cac thuoc tinh vao ca the can tao
                if(arr.get(0)!=null){
                    ind.addLiteral(Agent.address, arr.get(0));
                }
                if (arr.get(1)!=null){
                    ind.addLiteral(Agent.discription, arr.get(1));
                }
                if(arr.get(2)!=null){
                    ind.addLiteral(Agent.id, arr.get(2));
                }
                if(arr.get(3)!=null){
                    ind.addLiteral(Agent.owner, arr.get(3));
                }
                if(arr.get(4)!=null){
                    ind.addLiteral(Agent.state, arr.get(4));
                }
                if(arr.get(5)!=null){
                    ind.addLiteral(Agent.type, arr.get(5));
                }
            }catch (Exception e) {
            System.out.println(e.toString());

        }           
    }
    public void searchAgent2(String s){
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        String ma="";
        String trangthai="";
        String diachi="";
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" 
                + "SELECT DISTINCT * \n" + "WHERE \n" 
                + "{\n" + "?x agent:id ?ID. \n" 
                + "?x agent:type ?Type. \n" 
                + "?x agent:state ?State. \n" 
                + "?x agent:address ?Address. \n"
                + "FILTER regex(?Type,\"" + s + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = queryexec.execSelect();

            while (rs.hasNext()) {

                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                ma = binding.getLiteral("ID").getValue().toString();
                //loai = binding.getLiteral("Type").getValue().toString();
                trangthai = binding.getLiteral("State").getValue().toString();
                diachi= binding.getLiteral("Address").getValue().toString();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println(ma+diachi+trangthai);
    }

    public static void main(String arg[]) throws Exception {
        AgentProcess agent = new AgentProcess();
//        String s = "hotel";
//        ArrayList<String> ss = agent.searchAgent(s);
////        int ss=agent.searchState("ControllerAgent");
//        System.out.println("RESULT: " + ss);
        String info="dia chi"+Message.FIELD_SEPARATE+"mo ta"+Message.FIELD_SEPARATE
                +"id"+ Message.FIELD_SEPARATE+"owner"+Message.FIELD_SEPARATE
                +"active"+Message.FIELD_SEPARATE+"hotel";
        agent.insertAgent(info);
        String s = "hotel";
        ArrayList<String> ss = agent.searchAgent(s);    
        System.out.println("RESULT: " +ss);
        agent.searchAgent2(s);

    }
}
