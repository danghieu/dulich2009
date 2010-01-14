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
import com.ptit.travel.agent.communication.Language;
import org.apache.log4j.Logger;

public class AgentProcess {

    private static Logger log = Logger.getLogger(AgentProcess.class.getName());


    /**
     * 
     * @param type: type of agent, example: hotel, flight, customer, ...
     * @return
     */
    public static ArrayList<String> getActiveAgents(String type) {
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        ArrayList<String> result = new ArrayList<String>();

        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" +
                "SELECT DISTINCT * \n" + "WHERE \n" + "{\n" + "?x agent:id ?ID. \n" + "?x agent:type ?Type. \n" +
                "?x agent:state ?State. \n" + "FILTER regex(?Type,\"" + type + "\",\"i\")}";
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
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" +
                "SELECT DISTINCT * \n" + "WHERE \n" + "{\n" + "?x agent:id ?ID. \n" + "?x agent:type ?Type. \n" +
                "?x agent:state ?State. \n" + "FILTER regex(?ID, \"" + id + "\",\"i\")}";
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

    public static boolean insertAgent(String info) {
        ArrayList<String> arr = new ArrayList<String>();
        arr = Message.split(info, Message.FIELD_SEPARATE);
        log.info("Return Split: " + arr.toString());
        //Tao OntoModel trong de dua thong tin vao model
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        Individual ind = null;
        try {
            // tao ra 1 lop request de lay cac thong tin duoc yeu cau
            OntClass oc = model.createClass(Agent.getURI() + "Agent");
            ind = model.createIndividual(Agent.getURI() + "Agent_" + System.currentTimeMillis(), oc);
            //them cac thuoc tinh vao ca the can tao
            if (arr.get(0) != null) {
                ind.addLiteral(Agent.address, arr.get(0));
            }
            if (arr.get(1) != null) {
                ind.addLiteral(Agent.discription, arr.get(1));
            }
            if (arr.get(2) != null) {
                ind.addLiteral(Agent.id, arr.get(2));
            }
            if (arr.get(3) != null) {
                ind.addLiteral(Agent.owner, arr.get(3));
            }
            if (arr.get(4) != null) {
                ind.addLiteral(Agent.state, arr.get(4));
            }
            if (arr.get(5) != null) {
                ind.addLiteral(Agent.type, arr.get(5));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;

        }
        return true;
    }

    /**
     * 
     * @param type
     * @return
     */
    public static Hashtable<String, ArrayList<String>> getAgentByType(String type) {
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        Hashtable<String, ArrayList<String>> hashtable = new Hashtable<String, ArrayList<String>>();
        String ma = "";
        String trangthai = "";
        String diachi = "";
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" + "SELECT DISTINCT * \n" + "WHERE \n" + "{\n" + "?x agent:id ?ID. \n" + "?x agent:type ?Type. \n" + "?x agent:state ?State. \n" + "?x agent:address ?Address. \n" + "FILTER regex(?Type,\"" + type + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = queryexec.execSelect();
            ArrayList<String> arr = new ArrayList<String>();
            while (rs.hasNext()) {

                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                ma = binding.getLiteral("ID").getValue().toString();
                //loai = binding.getLiteral("Type").getValue().toString();
                trangthai = binding.getLiteral("State").getValue().toString();
                diachi = binding.getLiteral("Address").getValue().toString();
                arr.add(trangthai);
                arr.add(diachi);
                hashtable.put(ma, arr);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return hashtable;

    }

    public static ArrayList<String> getAgentById(String id) {
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        String trangthai = "";
        String diachi = "";
        String queryString = "PREFIX agent: <http://www.owl-ontologies.com/Ontology1254907557.owl#> \n" 
                +"SELECT DISTINCT * \n" + "WHERE \n" + "{\n" 
                + "?x agent:id ?ID. \n" 
                + "?x agent:type ?Type. \n" 
                + "?x agent:state ?State. \n" 
                + "?x agent:address ?Address. \n" 
                + "FILTER regex(?ID,\"" + id + "\",\"i\")}";
        Query query = QueryFactory.create(queryString);
        QueryExecution queryexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = queryexec.execSelect();
            ArrayList<String> arr = new ArrayList<String>();
            while (rs.hasNext()) {
                Object obj = rs.next();
                ResultBinding binding = (ResultBinding) obj;
                //loai = binding.getLiteral("Type").getValue().toString();
                trangthai = binding.getLiteral("State").getValue().toString();
                diachi = binding.getLiteral("Address").getValue().toString();
                arr.add(trangthai);
                arr.add(diachi);
                return arr;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;

    }

    public static void deleteAgent(String s) {
        AgentDB.LoadOnt2Database();
        OntModel model = AgentDB.getOntologyModel();
        String ont = "http://www.owl-ontologies.com/Ontology1254907557.owl#";
        DatatypeProperty ma = model.getDatatypeProperty(ont + "id");
        OntClass cl = model.getOntClass(ont + "Agent");
        log.info("Insert msg to infer");
        ExtendedIterator<?> extendedIterator = cl.listInstances(); // lay tat ca cac the hien cua cai lop day

        //    s=s+"^^http://www.w3.org/2001/XMLSchema#string";
        while (extendedIterator.hasNext()) {
            OntResource resource = (OntResource) extendedIterator.next();

            Individual individual = model.getIndividual(ont + resource.getLocalName());
            String tenAgent = (individual.listPropertyValues(ma).next()).toString();
            int index = tenAgent.indexOf("^^");
            tenAgent = tenAgent.substring(0, index);
            System.out.println("Ten agent: " + tenAgent);
            if (tenAgent.matches(s)) {
                individual.remove();
                log.info(tenAgent + " Deleted from DB");
                System.out.println("xoa roi");
            }

        }
    }

    public static void main(String arg[]) throws Exception {
        AgentProcess agent = new AgentProcess();
//        String s = "hotel";
//        ArrayList<String> ss = agent.searchAgent(s);
////        int ss=agent.searchState("ControllerAgent");
//        System.out.println("RESULT: " + ss);
        String info = "dia chi" + Message.FIELD_SEPARATE + "mo ta" + Message.FIELD_SEPARATE + "id" + Message.FIELD_SEPARATE + "owner" + Message.FIELD_SEPARATE + "active" + Message.FIELD_SEPARATE + "hotel";
        //agent.insertAgent(info);
        String s = "hotel";
        ArrayList<String> ss = agent.getActiveAgents(Language.HOTEL);
        System.out.println("RESULT: " + ss);
        s = "Sofitel";
        //System.out.println("Result: " + agent.getAgentById(s));

    }
}
