package com.ptit.travel.agent.communication;

import com.hp.hpl.jena.rdf.model.*;

import java.io.Serializable;
import java.io.StringWriter;

import com.ptit.travel.agent.onto.*;
import com.ptit.travel.beans.Address;

import com.ptit.travel.beans.SerializableBean;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.util.Hashtable;

/**
 * Contain of relevant methods for communication using OWL or RDF models
 * 
 */
public class Message {

    private static Logger log = Logger.getLogger(Message.class.getName());
    public static String SUCCESS = "Đặt thành công";
    public static String FAIL = "Đặt không thành công";
    /**
     * special string uesed to separate objects
     *  #_$
     */
    public final static String OBJECT_SEPARATE = "#_&";
    /**
     * special string uesed to separate fields of the object
     * @_&
     */
    public final static String FIELD_SEPARATE = "@_&";

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
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
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
        //m.setLanguage(Ontology.RDF);
        //m.setOntology(Ontology.BASE);
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
     *@param content
     *            String
     * 
     */
    public static ACLMessage createInformMessage(Agent sender, String recieverName,
            String content, String language, String protocol, String conversationId, String replyWith) {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        m.addReceiver(new AID(recieverName, false));
        m.setLanguage(language);
        m.setOntology(Ontology.BASE);
        m.setProtocol(protocol);
        m.setConversationId(conversationId);
        m.setContent(content);
        m.setReplyWith(replyWith);
        log.debug("message prepared for " + recieverName + ": " + m);
        return m;
    }

    public static ACLMessage createInformMessage(Agent sender, ArrayList<String> recieverNames,
            String content, String language, String protocol, String conversationId, String replyWith) {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        for (int i = 0; i < recieverNames.size(); i++) {
            m.addReceiver(new AID((String) recieverNames.get(i), false));
        }

        m.setLanguage(language);
        m.setOntology(Ontology.BASE);
        m.setProtocol(protocol);
        m.setConversationId(conversationId);
        m.setReplyWith(replyWith);
        m.setContent(content);
        log.debug("message prepared for " + recieverNames.toString() + ": " + m);
        return m;
    }

    /**
     * create message with contain object 
     */
    public static ACLMessage createInformMessage(Agent sender,
            ArrayList<String> recieverNames, Serializable content) throws Exception {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        for (int i = 0; i < recieverNames.size(); i++) {
            m.addReceiver(new AID((String) recieverNames.get(i), false));
        }

        m.setLanguage(Ontology.RDF);
        m.setOntology(Ontology.BASE);
        m.setContentObject(content);
        log.debug("message prepared for " + recieverNames.toString() + ": " + m);
        return m;
    }
    /*
    public static ACLMessage createReplyMessage(ACLMessage msg, ArrayList<SerializableBean> arrContent) {
    ACLMessage reply = msg.createReply();
    reply.setPerformative(ACLMessage.PROPOSE);
    SerializableBean bean;
    String content = "";
    int size = arrContent.size();
    for (int i = 0; i < size; i++) {
    bean = arrContent.get(i);
    content += bean.toMsg();
    if (i < size - 1) {
    content += Message.OBJECT_SEPARATE;
    }
    }
    reply.setContent(content);
    return reply;
    }*/

    public static ACLMessage createReplyMessage(ACLMessage msg, ArrayList<String> contents) {
        ACLMessage m = msg.createReply();
        m.setPerformative(ACLMessage.PROPOSE);
        String content = "";
        int size = contents.size();
        for (int i = 0; i < size; i++) {
            content += contents.get(i);
            if (i < size - 1) {
                content += Message.OBJECT_SEPARATE;
            }
        }
        m.setContent(content);
        log.debug("reply message prepared for " + msg.getSender().getLocalName() + ": " + m);
        return m;
    }

    public static ACLMessage createReplyMessage(ACLMessage msg, String content) {
        ACLMessage m = msg.createReply();
        m.setPerformative(ACLMessage.PROPOSE);
        m.setContent(content);
        log.debug("reply message prepared for " + msg.getSender().getLocalName() + ": " + m);
        return m;
    }

    public static ACLMessage createReplyMessage(Agent sender, String reciever, ArrayList<String> contents,
            String language, String protocol, String conversationId, String replyWith) {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());

        m.addReceiver(new AID((String) reciever, false));

        m.setLanguage(language);
        m.setOntology(Ontology.BASE);
        m.setProtocol(protocol);
        m.setConversationId(conversationId);

        m.setInReplyTo(replyWith);
        String content = "";
        int size = contents.size();
        for (int i = 0; i < size; i++) {
            content += contents.get(i);
            if (i < size - 1) {
                content += Message.OBJECT_SEPARATE;
            }
        }
        m.setContent(content);
        log.debug("message prepared for " + reciever.toString() + ": " + m);
        return m;
    }

    /**
     * This method used to forward msg from USER agent to OTHER agents
     * @param sender
     * @param recieverNames
     * @param msg
     * @param replyWith
     * @return
     * @throws java.lang.Exception
     */
    public static ACLMessage createForwardMessage(Agent sender,
            ArrayList<String> recieverNames, ACLMessage msg, String replyWith) throws Exception {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        for (int i = 0; i < recieverNames.size(); i++) {
            m.addReceiver(new AID((String) recieverNames.get(i), false));
        }

        m.setContent(msg.getContent());
        m.setLanguage(msg.getLanguage());
        m.setOntology(msg.getOntology());
        m.setProtocol(msg.getProtocol());
        m.setConversationId(msg.getConversationId());

        m.setReplyWith(replyWith);
        log.debug("message prepared for " + recieverNames.toString() + ": " + m);
        return m;
    }

    /**
     * this method used to forward msgs to USER agent from OTHER agents
     * @param sender
     * @param reciever
     * @param msg
     * @param inReplyTo: the same as replyWith of received msg from USER agent
     * @return
     * @throws java.lang.Exception
     */
    public static ACLMessage createForwardMessage(Agent sender,
            String reciever, ACLMessage msg, String inReplyTo) throws Exception {

        log.info("Preparing inform message...");
        //String content = resource2RDF(r);
        ACLMessage m = new ACLMessage(ACLMessage.INFORM);
        m.setSender(sender.getAID());
        m.addReceiver(new AID(reciever, false));

        m.setContent(msg.getContent());
        m.setLanguage(msg.getLanguage());
        m.setOntology(msg.getOntology());
        m.setProtocol(msg.getProtocol());
        m.setConversationId(msg.getConversationId());

        m.setInReplyTo(inReplyTo);
        log.debug("message prepared for " + reciever + ": " + m);
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
//    public static void register(Agent a, String agentType) {
//        DFAgentDescription dfd = new DFAgentDescription();
//        ServiceDescription sd = new ServiceDescription();
//        sd.setType(agentType);
//        sd.setName(a.getName());
//        sd.setOwnership(Ontology.AGENT_OWNERSHIP);
//        sd.addOntologies(Ontology.BASE);
//        dfd.setName(a.getAID());
//        dfd.addServices(sd);
//        try {
//            DFService.register(a, dfd);
//        } catch (FIPAException e) {
//            System.err.println(a.getLocalName() + " registration with DF unsucceeded. Reason: " + e.getMessage()); //$NON-NLS-1$
//
//            a.doDelete();
//        }

//    }
    /**
     * method split a string with Message.SAPARATE.
     * @param input
     * @return Array of pieces of input separated by Message.SAPARATE
     * For example:
     * 	input = "we@@@love@@@you"
     *  return {we,love,you}
     */
    public static ArrayList<String> split(String input, String separate) {
        ArrayList<String> arr = new ArrayList<String>();
        //String separate = Message.OBJECT_SEPARATE;
        if (input == null) {
            log.error("input: " + input);
            return null;
        }
        int beginIndex = 0;
        int endIndex = input.indexOf(separate);
        int maxIndex = input.lastIndexOf(separate);
        String s = "";
        if (endIndex == -1) {
            arr.add(input);
            return arr;
        }
        while (beginIndex <= maxIndex) {
            endIndex = input.indexOf(separate, beginIndex + 1);
            if (endIndex != -1) {
                s = input.substring(beginIndex, endIndex);

                s = s.replaceAll(separate, "");
                arr.add(s);
            } else {
                s = input.substring(beginIndex, input.length());
                s = s.replaceAll(separate, "");
                if (!s.equals("")) {
                    arr.add(s);
                }
                break;
            }

            beginIndex = endIndex;
        }
        return arr;
    }

    /**
     * This method extract string input into agent and agent's msg
     * @param input: agentName-MF-msg-MO-agentName-MF-msg...
     * @return Hasttable<agentName, msg>
     */
    public static Hashtable<String, String> extractAgentMsg(String input) {
        ArrayList<String> splitedContent = Message.split(input, Message.OBJECT_SEPARATE);
        if (splitedContent == null) {
            //log.error("Invalid format input from servlet: " + input);
            return null;
        }
        Hashtable<String, String> agentMsg = new Hashtable<String, String>();
        String receiver, content;
        int index;
        log.debug("|| Receiver and Msg extracted: ");
        for (int i = 0; i < splitedContent.size(); i++) {
            content = splitedContent.get(i);
            index = content.indexOf(Message.FIELD_SEPARATE);
            if (index > 0) {
                receiver = content.substring(0, index);
                //receivers.add(receiver);
                content = content.replaceFirst(receiver + Message.FIELD_SEPARATE, "");
                // if there is more message for the agent 
                if(agentMsg.containsKey(receiver)){
                    content = agentMsg.get(receiver) + Message.OBJECT_SEPARATE + content;                    
                }
                agentMsg.put(receiver, content);
                log.debug("|| AGENT  : " + receiver);
                log.debug("|| Message: " + content);
            }

        }
        return agentMsg;
    }

    public static void main(String[] args) {
        String input = "param1" + Message.FIELD_SEPARATE +
                "param2@" + Message.FIELD_SEPARATE +
                "param3$" + Message.OBJECT_SEPARATE +
                "paramA" + Message.FIELD_SEPARATE +
                "paramB" + Message.OBJECT_SEPARATE +
                "paramX";
        input = "HotelAgent@_&405 _ Thanh Xuan Bac _ Nam Dinh _ Vietnam@_&warm@_&12:00.AM@_&12.00 PM@_&yennh235@gmail.com@_&HaiYen@_&inside@_&4@_&#_$";
        System.out.println("|| INPUT: " + input);
        ArrayList<String> list = Message.split(input, Message.OBJECT_SEPARATE);
        System.out.println("|| SEPARATE OBJECT: " + list.toString());
        System.out.println("|| SEPARATE FIELD of 1st Ojbect: " + Message.split(list.get(0), Message.FIELD_SEPARATE));
    }
}