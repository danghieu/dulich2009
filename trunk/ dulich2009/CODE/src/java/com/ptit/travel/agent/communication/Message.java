package com.ptit.travel.agent.communication;

import com.hp.hpl.jena.rdf.model.*;

import java.io.StringWriter;

import com.ptit.travel.agent.onto.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * Contain of relevant methods for communication using OWL or RDF models
 * 
 */
public class Message {

    private static Logger log = Logger.getLogger(Message.class.getName());

    /**
     * This method returns XML/RDF text representation of RDF Resource
     * 
     * @param resource
     *            Jena Resource
     */
    public static String resource2RDF(Resource resource) {
        return resource2RDF(resource, ModelFactory.createOntologyModel());
    }

    /**
     * This method returns XML/RDF text representation of RDF Model
     * 
     * @param model
     *            Jena Model
     */
    public static String model2RDF(Model model) {
        String rdf = "";
        try {
            StringWriter writer = new StringWriter();

            model.write(writer, "RDF/XML-ABBREV");
            rdf = writer.getBuffer().toString();
        } catch (Exception e) {
            log.error("error adding instance to model");
        }
        return rdf;
    }

    public static String resource2RDF(Resource r, Model model) {
        return model2RDF(resource2Model(r, model));
    }

    public static Model resource2Model(Resource r, Model model) {
        try {

            model.add(r.listProperties());

            StmtIterator i = r.listProperties();
            while (i.hasNext()) { // this is here to put simple resource values
                // (String, int) to message as well

                Statement s = i.nextStatement();
                if (s.getObject() instanceof Resource) {
                    // log.debug("ddi:" + s.getObject().toString());
                    model.add(((Resource) r.getProperty(s.getPredicate()).getObject()).listProperties());
                }
            // log.debug("prop:"+s.toString());

            }

        } catch (Exception e) {
            log.error("error adding instance to model");
        }
        return model;
    }

    /**
     * Recursive method which writes resource with all properties recursively
     * into string and returns it
     * 
     * @param space
     *            is just to track levels of XML
     *@param r
     *            resource which should be converted to XML text
     * 
     */
    public static String getXML(Resource r, String space, String strRes) {
        String xml = "";
        String typeAndId = "";
        String typeEnd = "";
        StmtIterator si = r.listProperties();
        while (si.hasNext()) {
            Statement s = si.nextStatement();
            Resource subject = s.getSubject(); // get the subject

            Property predicate = s.getPredicate(); // get the predicate

            RDFNode object = s.getObject(); // get the object
            // System.out.println(space + s.toString() );


            if (predicate.getNameSpace().equals(Ontology.RDF) && predicate.getLocalName().equals("type")) {
                typeAndId = space + "<" + s.getResource().getLocalName() + " ID=\"" + subject.getLocalName() + "\">\n";
                // System.out.print(typeAndId);
                typeEnd = space + "</" + s.getResource().getLocalName() + ">\n";
            // xml = typeAndId + xml + typeEnd;
            } else {
                StmtIterator rP = r.listProperties(r.getModel().createProperty(
                        predicate.getURI()));

                while (rP.hasNext()) {
                    String xmlRes = "";

                    try {
                        Statement ss = rP.nextStatement();
                        Resource rr = ss.getResource();
                        if (rr.toString().equals(s.getObject().toString()) && strRes.indexOf(r.getLocalName()) == -1) {
                            // we have to compare strRes in this if because
                            // otherwise we willhave infinite recursive loop
                            xmlRes += getXML(rr, space + "    ", strRes + ";" + r.getLocalName());
                        }
                    } catch (Exception e) {
                        String valS = "";
                        if (object instanceof Literal) {
                            valS = ((Literal) object).getString();
                        } else {
                            valS = object.toString();
                        }

                        Pattern pp = Pattern.compile("<");
                        Matcher mm = pp.matcher(valS);
                        valS = mm.replaceAll("&lt;");
                        pp = Pattern.compile(">");
                        mm = pp.matcher(valS);
                        valS = mm.replaceAll("&gt;");

                        String simpleProp = space + "  <" + predicate.getLocalName() + ">" + valS + "</" + predicate.getLocalName() + ">\n";
                        xml = xml + simpleProp;
                    // System.out.print(simpleProp);
                    // System.out.println(space+"error1:"+e.toString());
                    }

                    String beginRes = space + "  <" + predicate.getLocalName() + ">\n";
                    String endRes = space + "  </" + predicate.getLocalName() + ">\n";
                    if (!xmlRes.equals("")) {
                        xml = xml + beginRes + xmlRes + endRes;
                    }

                // System.out.print(endRes);
                }
            }

        }
        xml = typeAndId + xml + typeEnd;
        return xml;
    }

    /**
     * Recursive method which move resource with all properties recursively into
     * new model
     * 
     * @param m
     *            represents model where properties should be put in
     *@param r
     *            resource which properties should be added
     * 
     */
    public static void addProperties(Model m, Resource r) {
        m.add(r.listProperties());
        StmtIterator si = r.listProperties();
        while (si.hasNext()) {
            Statement s = si.nextStatement();
            try {
                m.add(s.getResource().listProperties());
                addProperties(m, s.getResource());
            } catch (Exception e) {
                ;
            }
        }
    }

    /**
     * This method creates ACL Query message
     * 
     * @param sender
     *            Agent which sends message
     *@param recieverName
     *            string name of Agent which recieve the message
     *@param query
     *            SPARQL query
     * 
     */
    public static ACLMessage createQueryMessage(Agent sender,
            String recieverName, String query) {
        ACLMessage m = new ACLMessage(ACLMessage.QUERY_REF);
        AID receiver = new AID(recieverName, false);
        m.setSender(sender.getAID());
        m.addReceiver(receiver);
        m.setLanguage(Ontology.SPARQL);
        m.setOntology(Ontology.BASE);

        String content = Ontology.SPARQL_PREFIX + query;
        log.debug("Message:\n" + content);

        m.setContent(content);
        log.debug("message prepared for " + recieverName + ": " + m);

        return m;

    }

    /**
     * This method creates ACL Inform message containing of RDF of some Jena RDF
     * Resource
     * 
     * @param sender
     *            Agent which sends message
     *@param recieverName
     *            string name of Agent which recieve the message
     *@param r
     *            RDF Jena Resouce
     * 
     */
    public static ACLMessage createInformMessage(Agent sender,
            String recieverName, Resource r) {

        log.info("Preparing inform message...");
        String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        m.addReceiver(new AID(recieverName, false));
        m.setLanguage(Ontology.RDF);
        m.setOntology(Ontology.BASE);
        m.setContent(content);
        log.debug("message prepared for " + recieverName + ": " + m);
        return m;
    }

    /**
     * This method register agent with directory facilitator
     * 
     * @param a
     *            Agent which registers
     *@param agentType
     *            string description of agent type
     * 
     */
    public static void register(Agent a, String agentType) {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(agentType);
        sd.setName(a.getName());
        sd.setOwnership(Ontology.AGENT_OWNERSHIP);
        sd.addOntologies(Ontology.BASE);
        dfd.setName(a.getAID());
        dfd.addServices(sd);
        try {
            DFService.register(a, dfd);
        } catch (FIPAException e) {
            System.err.println(a.getLocalName() + " registration with DF unsucceeded. Reason: " + e.getMessage()); //$NON-NLS-1$

            a.doDelete();
        }

    }
}