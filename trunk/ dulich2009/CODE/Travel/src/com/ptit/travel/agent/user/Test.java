package com.ptit.travel.agent.user;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClientLite;
import org.apache.xmlrpc.XmlRpcException;

import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.hotel.HotelAgent;
import com.ptit.travel.beans.Address;
import com.ptit.travel.beans.AgentBean;
import com.ptit.travel.common.AgentManager;

public class Test {

	public static final String SERVER = "http://localhost:8000";

	//
	// String id = "AskAgent";
	// String resourceType = "CD";
	XmlRpcClientLite myClient;

	public Test() {
		try {
			myClient = new XmlRpcClientLite(SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	private String callTheAgentViaXmlRpc(String function, Address param) {
		String result = null;
		// the parameters are inserted in a vector
		Vector v = new Vector();
		v.addElement(param);
		System.out.println("==============: " + param.getCity());

		System.out.println("method is going to be called");

		try {

			result = (String) myClient.execute("UserAgent." + function, v);
		} catch (XmlRpcException e) {
			System.out.println("exception while transmitting message " + e);
			e.printStackTrace();
		} catch (java.io.IOException e) {
			System.out.println("exception while transmitting message " + e);
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test = new Test();
		try {

			String host = "localhost";
			String nickName = "user" + System.currentTimeMillis();
			String port = "1099";
			String className = "com.ptit.travel.agent.user.UserAgent";

			AgentController acUser = AgentManager.startAgent(host, port, nickName, className);
			System.out.println(acUser.getName());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * StartAgent create a new agent and start one on jadeServer
	 * 
	 * @param: jadeServer is jade server on which new agent will run
	 * @param: nickName is nick name of agent return 1 if start successfully new
	 *         agent else return -1;
	 */
	public static AgentController startAgent(String host, String port,
			String nickName, String className) {
		ContainerController cc;
		Runtime rt = Runtime.instance();
		// Create a default profile
		Profile p = new ProfileImpl();
		p.setParameter(Profile.MAIN_HOST, host);
		p.setParameter(Profile.MAIN_PORT, port);
		// Create a new main container, connecting to the default
		cc = rt.createAgentContainer(p);// createMainContainer(p);

		AgentController ac;
		try {
			// ac = cc.acceptNewAgent(nickName, agent);
			ac = cc.createNewAgent(nickName, className, null);
			ac.start();
			ac.activate();
			return ac;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		return null;
	}

}
