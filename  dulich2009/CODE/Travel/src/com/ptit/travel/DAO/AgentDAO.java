/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.DAO;

/**
 *
 * @author D05CNPM
 */
import jena.query;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;

import com.ptit.travel.beans.AgentBean;
import java.io.*;
import java.util.ArrayList;

public class AgentDAO extends Object {

    static final String inputFileName = "data/AgentRDF.owl";
    private String SPARQL_GET_AGENT_CONNECTION = "";

    public ArrayList<String> getAgents(String prefix, String agentType) {

        ArrayList<String> agents = new ArrayList<String>();
        
        Model model = ModelFactory.createDefaultModel();

        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException("File: " + inputFileName + " not found");
        }

        Query query = QueryFactory.create("PREFIX tiento:" +
                "<http://www.owl-ontologies.com/Ontology1254907557.owl#> " + // prefix
                "SELECT ?name WHERE { tiento:type " +
                agentType );

        QueryExecution qexec = QueryExecutionFactory.create(query, model);
        try {
            ResultSet rs = qexec.execSelect();

            for (; rs.hasNext();) {
                QuerySolution rb = rs.nextSolution();
                agents.add(rb.getLiteral("y").getString());
            }

        } finally {
            qexec.close();
        }

        return agents;
    }

    public String readMessage(String filePath) {
        String meg = "";
        String line = "";
        try {
            FileInputStream inFile = new FileInputStream(new File(filePath));
            BufferedReader buf = new BufferedReader(new InputStreamReader(inFile));
            line = buf.readLine();
            while (line != null) {
                meg += line;
                line = buf.readLine();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return meg;
    }
}