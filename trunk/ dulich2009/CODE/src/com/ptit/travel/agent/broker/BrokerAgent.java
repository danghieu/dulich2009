package com.ptit.travel.agent.broker;


/**
 *
 * @author D05CNPM
 */
import com.ptit.travel.DAO.AgentDAO;
import com.ptit.travel.agent.user.communication.*;
import com.ptit.travel.agent.user.memory.*;
import com.ptit.travel.agent.user.onto.*;

import java.io.StringWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.WebServer;

/**
 * User Agent runs also XML-RPC server
 * on port 8000 and public methods can be called by outside 
 * Behaviors:
 * 	search for services, supplier, place, event
 * 	request booking
 * 	prepare to modify/cancel
 * 	modify
 * 	cancel	
 * 
 * Each behavior executed by two methods and one inner class. For example
 *  
 * @version 0.5
 */
public class BrokerAgent extends Agent {

	private static Logger log = Logger.getLogger(BrokerAgent.class.getName());
	WebServer xmlrpcServer;
	private static final int port = 8000;
	private Memory mem;
	private Resource agentIndividual;
	// message queue of agent contains every satisfactory replied messages 
	private Hashtable<String,ArrayList<String>> msgQueue 
		= new Hashtable<String, ArrayList<String>>();
	
	private AgentDAO agentDAO = new AgentDAO(); // manipulate with agent db

	protected void setup() {
		Message.register(this, this.getLocalName());

		try {
			xmlrpcServer = new WebServer(port);
			log.debug("XMLRPC Running ....");
		} catch (IOException e) {
			log.error("PANIC: maybe the port " + port + " is in use");
			System.out.println("NGOAI LE: " + e);
		}
		xmlrpcServer.addHandler(this.getLocalName(), this);

		mem = new Memory(
				"E:/Develop/Netbean/Travel/config/UserAgent.properties",
				this.getLocalName());// E:/Develop/Netbean/Travel/
		agentIndividual = mem.getModel().getResource(
				Memory.getBase() + this.getLocalName());
		HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages(
				this);
		addBehaviour(hrmBehaviour);
	}

	/**
	 * This method is called by whatever GUI implementation In current
	 * implementation it is called by XML-RPC through XMLRPC webserver Method
	 * send Resource which should be search by SupplierAgent <br>
	 * you need to call "Agent.search(Resource resource)" function from
	 * your external system
	 * 
	 * @param resource
	 *            represents type of resource in Memory Model (OWL model)
	 * 
	 */
	public String search(String resource) {
		final String msgId = "102";//getLocalName() + System.currentTimeMillis();
		ArrayList<String> msgs = new ArrayList<String>();
		addBehaviour(new RequestAvailability(this, resource, msgId, msgs));		

		System.out.println(System.currentTimeMillis());
		return "ok";
	}	
	public String getSearchResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			msgQueue.remove(msgId);
			for (int i = 0; i < msgs.size(); i++) {
				results += msgs.get(i).trim();			
				if(i <msgs.size()-1)
					results += Message.SEPARATE;
			}
		} catch (Exception e) {
			results = "";
			e.printStackTrace();
		}			

		return results;
	}
	/**
	 * This method is called when we want this agent executes booking behavior
	 * @param uh
	 * 
	 * 			resource: information about services wanting to book
	 */
	public String book(String resource) {
		String msgId = getLocalName() + System.currentTimeMillis(); //unique
		addBehaviour(new RequestBook(this, resource, msgId));
		ArrayList<String> msgs = msgQueue.get(msgId);
		msgQueue.remove(msgId);
		if(msgs != null && msgs.size() != 0)
		return msgs.toString();
		else
			return "_aaaNONEbbb_";
	}	
	public String getBookResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			msgQueue.remove(msgId);
			for (int i = 0; i < msgs.size(); i++) {
				results += msgs.get(i).trim();			
				if(i <msgs.size()-1)
					results += Message.SEPARATE;
			}
		} catch (Exception e) {
			results = "";
			e.printStackTrace();
		}			

		return results;
	}
	/**
	 * This method is called when we want this agent prepares to execute 
	 * modifying or canceling behavior
	 * @param 
	 * 			resource: information about services
	 */
	public String prepareModify(String resource) {
		String msgId = getLocalName() + System.currentTimeMillis(); //unique
		addBehaviour(new PrepareModify(this, resource, msgId));
		
		return "ok";
	}	
	public String getPrepareResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			msgQueue.remove(msgId);
			for (int i = 0; i < msgs.size(); i++) {
				results += msgs.get(i).trim();			
				if(i <msgs.size()-1)
					results += Message.SEPARATE;
			}
		} catch (Exception e) {
			results = "";
			e.printStackTrace();
		}			

		return results;
	}
	/**
	 * This method is called when we want this agent executes modifying behavior
	 * @param 
	 * 			resource: information about services wanting to modify
	 */
	public String modify(String resource) {
		String msgId = getLocalName() + System.currentTimeMillis(); //unique
		addBehaviour(new RequestModify(this, resource, msgId));
		
		return "ok";
	}	
	public String getModifyResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			msgQueue.remove(msgId);
			for (int i = 0; i < msgs.size(); i++) {
				results += msgs.get(i).trim();			
				if(i <msgs.size()-1)
					results += Message.SEPARATE;
			}
		} catch (Exception e) {
			results = "";
			e.printStackTrace();
		}			

		return results;
	}
	/**
	 * This method is called when we want this agent executes canceling behavior
	 * @param 
	 * 			resource: information about services wanting to cancel
	 */
	public String cancel(String resource) {
		String msgId = getLocalName() + System.currentTimeMillis(); //unique
		addBehaviour(new RequestCancel(this, resource, msgId));
		
		return "ok";
	}	
	public String getCancelResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			msgQueue.remove(msgId);
			for (int i = 0; i < msgs.size(); i++) {
				results += msgs.get(i).trim();			
				if(i <msgs.size()-1)
					results += Message.SEPARATE;
			}
		} catch (Exception e) {
			results = "";
			e.printStackTrace();
		}			

		return results;
	}
	/**
	 * checking availability behavior 
	 * @author D05CNPM
	 * @param resource: information about services need to book
	 * @param a: agent execute this behavior
	 */

	private class RequestAvailability extends Behaviour {
		
		private int repliesCnt = 0; // The counter of replies from agents
		private MessageTemplate mt; // The template to receive replies
		private int step = 0;

		private String resource = null;
		private ArrayList<String> receivers;
		private String msgId;
		private Agent a;
		private boolean avail = false;
		private ArrayList<String> msgs = new ArrayList<String>();
		public RequestAvailability(Agent _a, String _resource, String _msgId, ArrayList<String> _msgs) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
			msgs = _msgs;
		}

		public void action() {	
			msgs.add("Nothing is more important than peace");
			switch (step) {
			case 0:
				// collect agents who satisfy action
				//receivers = agentDAO.getAgents("", "hotel");
				// FOR TEST ONLY
				receivers = new ArrayList<String>();
				receivers.add("HotelAgent");
				// Send the cfp to all agents
				ACLMessage msg = Message.createInformMessage(a, receivers,
						resource);

				msg.setConversationId(myAgent.getLocalName());
				msg.setReplyWith(msgId); // Unique
				// value
				myAgent.send(msg);
				// Prepare the template to get proposals
				mt = MessageTemplate.and(MessageTemplate
						.MatchConversationId(myAgent.getLocalName()),
						MessageTemplate.MatchInReplyTo(msg.getReplyWith()));

				step = 1;
				break;
			case 1:
				// Receive all proposals/refusals from agents
				ACLMessage replyMsg = myAgent.receive(mt);
				if (replyMsg != null) {
					if (replyMsg.getPerformative() == ACLMessage.PROPOSE) {
						/**
						 * / check if there is any available hotel/ if yes set
						 * avail = true
						 * put msg into msgQueue of agent: 
						 */
						//TODO
					}
					repliesCnt++;
					if (repliesCnt >= receivers.size()) {
						// We received all replies
						step = 2;
					}
				} else {
					block();

				}

				break;

			}
		}

		public boolean done() {
				
			if(step == 2) {// && avail); if finishing only exist available
				// put messages into queue of agent
				msgQueue.put(msgId,msgs );		
				return true;
			}
			return false;
									// hotel
		}
	} // End of inner class RequestAvailability

	/**
	 * booking services behavior
	 * @author D05CNPM
	 * @param resource: information about services need to book
	 * @param a: agent execute this behavior
	 */
	class RequestBook extends Behaviour {

		private String resource = null;
		private Agent a;
		private String msgId;

		public RequestBook(Agent _a, String _resource, String _msgId) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
		}

		public void action() {
			send(Message.createInformMessage(a, "UserAgent", resource));
		}
		public boolean done(){
			
			return false;
		}
	} // End class RequestBook
	

	/**
	 * preparing for Modifying or canceling booking services behavior
	 * @author D05CNPM
	 * @param resource: information about services need to modify 
	 * @param a: agent execute this behavior
	 */
	class PrepareModify extends Behaviour {

		private String resource = null;
		private Agent a;
		private String msgId;

		public PrepareModify(Agent _a, String _resource, String _msgId) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
		}
		public void action() {
			send(Message.createInformMessage(a, "UserAgent", resource));
		}
		public boolean done(){
			
			return false;
		}
	} // End class RequestCancel
	/**
	 * Modifying booking services behavior
	 * @author D05CNPM
	 * @param resource: information about services need to modify 
	 * @param a: agent execute this behavior
	 */
	class RequestCancel extends Behaviour {

		private String resource = null;
		private Agent a;
		private String msgId;

		public RequestCancel(Agent _a, String _resource, String _msgId) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
		}

		public void action() {
			send(Message.createInformMessage(a, "UserAgent", resource));
		}
		public boolean done(){
			
			return false;
		}
	} // End class RequestCancel
	/**
	 * canceling services behavior
	 * @author D05CNPM
	 * @param resource: information about services need to book
	 * @param a: agent execute this behavior
	 */
	class RequestModify extends Behaviour {

		private String resource = null;
		private Agent a;
		private String msgId;

		public RequestModify(Agent _a, String _resource, String _msgId) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
		}

		public void action() {
			send(Message.createInformMessage(a, "UserAgent", resource));
		}
		public boolean done(){
			
			return false;
		}
	} // End class RequestCancel
	
	/**
	 * 
	 *This behaviour is used by HotelAgent to handle received messages return
	 * the results to Ask Agent using BehaviourSentResult
	 * 
	 * 
	 */
	class HandleRecivedMessages extends SimpleBehaviour {

		private boolean finished = false;
		private Agent a;

		public HandleRecivedMessages(Agent _a) {
			super(_a);
			a = _a;
		}

		public void action() {

			synchronized (this) {
					ACLMessage msg = receive();
				if (msg != null) {
					switch (msg.getPerformative()) {
					case ACLMessage.QUERY_REF:
						break;
					
					case ACLMessage.INFORM:
						// read language to know
						String language = msg.getLanguage(); 
						//if it is hotel to process
						if (language.equals(Language.HOTEL)) {
							// read content message into a model
							OntModel m = ModelFactory.createOntologyModel();
							try {
								mem.getModel().read(
										new StringReader(msg.getContent()),
										Ontology.BASE);
								// OM.getModel().commit();
								m.read(new StringReader(msg.getContent()),
										Ontology.BASE);
							} catch (Exception e) {
								log.debug("Something failed: " + e.toString());
							}

							// action of message <-> protocol of ACL
							String protocol = msg.getProtocol();
							if (protocol.equals(Protocol.AVAIL_GET)) {
								// TODO
							}
							if(protocol.equals(Protocol.HOTEL_RES)){
								// TODO
							}
							if(protocol.equals(Protocol.CANCEL)){
								// TODO
							}
						}
						if(language.equals(Language.CAR)){
							// TODO
						}
						if(language.equals(Language.FLIGHT)){
							// TODO
						}
						if(language.equals(Language.TRAIN)){
							// TODO
						}
						if(language.equals(Language.EVENT)){
							// TODO
						}
						if(language.equals(Language.DESTINATION)){
							// TODO
						}

						break;
					default:
						// TODO
						break;
					}
				}
				block(); // this is to allow other behaviours

			}
			;

		}

		public boolean done() {
			return finished;
		}
	} // end of HandleRecivedMessages

}
