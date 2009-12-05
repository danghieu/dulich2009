package com.ptit.travel.common;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class AgentManager {

	  /**
     * StartAgent create a new agent and start one on jadeServer
     * @param: jadeServer is jade server on which new agent will run
     * @param: nickName is nick name of agent
     * return 1 if start successfully new agent
     * else return -1;
     */
	public static AgentController startAgent(String host, String port,
			String nickName, String className) throws Exception{
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
