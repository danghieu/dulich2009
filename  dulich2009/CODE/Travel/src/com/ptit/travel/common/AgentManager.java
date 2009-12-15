package com.ptit.travel.common;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class AgentManager {

    private static Logger log = Logger.getLogger(AgentManager.class.getName());

    /**
     * StartAgent create a new agent and start one on jadeServer
     * @param: jadeServer is jade server on which new agent will run
     * @param: nickName is nick name of agent
     * return 1 if start successfully new agent
     * else return -1;
     */
    public static AgentController addAgent(String host, String port,
            String nickName, String className, ContainerController containerController) throws Exception {

        Runtime rt = Runtime.instance();
        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.MAIN_PORT, port);

        AgentController ac;
        try {
            // ac = cc.acceptNewAgent(nickName, agent);
            log.info("Create a new agent: " + nickName);
            ac = containerController.createNewAgent(nickName, className, null);
            ac.start();
            ac.activate();
            return ac;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }
        return null;
    }

    public static ArrayList startAgent(String host, String port,
            String nickName, String className, boolean main) throws Exception {

        ArrayList arr = new ArrayList();
        ContainerController cc = null;
        Runtime rt = Runtime.instance();
        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.MAIN_PORT, port);
        // Create a new main container, connecting to the default

        if (main) {
            try {
                cc = rt.createMainContainer(p);
            } catch (Exception e) {
                log.error("Cannot create main container: " + e.toString());
                log.info("Trying to create normal container");
                cc = rt.createAgentContainer(p);// 

            }
        } else {
            cc = rt.createAgentContainer(p);// 
        }
        log.info("Created a container: " + cc.getContainerName());
        arr.add(cc);
        AgentController ac;
        try {
            // ac = cc.acceptNewAgent(nickName, agent);
            ac = cc.createNewAgent(nickName, className, null);
            ac.start();
            ac.activate();
            arr.add(ac);
            return arr;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            arr.add(null);
            e1.printStackTrace();

        }
        return arr;
    }
}
