/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent.hotel;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;


import com.ptit.travel.agent.communication.*;
import com.ptit.travel.agent.memory.*;
import com.ptit.travel.agent.onto.*;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;

import com.hp.hpl.jena.rdf.model.Resource;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Hotel Agent runs also XML-RPC server on port 8000 and public methods can be
 * called by outside Behaviors:
 * 
 * 
 * 
 * Each behavior executed by two methods and one inner class. For example
 * 
 * @version 0.5
 */
public class HotelAgent extends Agent {

	private static Logger log = Logger.getLogger(HotelAgent.class.getName());
	private Memory mem;

	// message queue of agent contains every satisfactory replied messages
	private Hashtable<String, ArrayList<String>> msgQueue = new Hashtable<String, ArrayList<String>>();

	protected void setup() {

		Message.register(this, "hotel");

		mem = new Memory(
				"E:/Develop/Netbean/Travel/config/HotelAgent.properties", this
						.getLocalName());

		HandleRecivedMessages hrmBehaviour = new HandleRecivedMessages(
				this);
		addBehaviour(hrmBehaviour);
	}

	// this method sends result to AskAgent
	private void informAskAgent(Resource r) {
		this.addBehaviour(new BehaviourSendResult(this, r));
	}

	/**
	 * 
	 *Behaviour is used with AnswerAgent and it sends RDF Resources to other
	 * Agents
	 * 
	 * @author D05CNPM
	 */
	class BehaviourSendResult extends OneShotBehaviour {

		private Resource r = null;
		private Agent a;

		public BehaviourSendResult(Agent _a, Resource _r) {
			super(_a);
			a = _a;
			r = _r;
		}

		public void action() {
			System.out.println("sends RDF Resources to UserAgent");
			send(Message.createInformMessage(a, "UserAgent", r));
		}
	} // End class BehaviourSendResult

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
						// read language to know if it is hotel to process
						if (msg.getLanguage().equals("hotel")) {
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

						}
						;

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

	/**
	 * This method is called by whatever GUI implementation In current
	 * implementation it is called by XML-RPC through XMLRPC webserver Method
	 * send Resource which should be search by SupplierAgent <br>
	 * you need to call
	 * external system
	 * 
	 * @param resource
	 *            represents type of resource in Memory Model (OWL model)
	 * 
	 */
	public String search(String resource) {
		final String msgId = "102";// getLocalName() +
									// System.currentTimeMillis();
		ArrayList<String> msgs = new ArrayList<String>();
		addBehaviour(new CommunicationHotel(this, resource, msgId, msgs));

		System.out.println(System.currentTimeMillis());
		return "ok";
	}

	public String getSearchResults(String msgId){
		String results = "";
		try {
			ArrayList<String> msgs = msgQueue.get(msgId);
			//msgQueue.remove(msgId);
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
	 * This behavior to communicate other hotels
	 */

	class CommunicationHotel extends Behaviour {
		private int repliesCnt = 0; // The counter of replies from agents
		private MessageTemplate mt; // The template to receive replies
		private int step = 0;

		private String resource = null;
		private ArrayList<String> receivers;
		private String msgId;
		private Agent a;
		private boolean avail = false;
		private ArrayList<String> msgs = new ArrayList<String>();

		public CommunicationHotel(Agent _a, String _resource, String _msgId,
				ArrayList<String> _msgs) {
			super(_a);
			a = _a;
			resource = _resource;
			msgId = _msgId;
			msgs = _msgs;
		}

		public void action() {
			switch (step) {
			case 0:
				// collect agents who satisfy action
				// receivers = agentDAO.getAgents("", "hotel");
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
						 * avail = true put msg into msgQueue of agent:
						 */
						msgs.add("Nothing is more important than peace");
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
			msgQueue.put(msgId, msgs);
			if (step == 2) {// && avail); if finishing only exist available
				// put messages into queue of agent

				return true;
			}
			return false;
			// hotel
		}
	}
}
