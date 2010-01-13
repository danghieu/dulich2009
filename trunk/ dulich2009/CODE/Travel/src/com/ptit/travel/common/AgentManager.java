package com.ptit.travel.common;

import com.ptit.travel.agent.communication.Language;
import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.jane.agent.AgentProcess;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.logging.Level;
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
            String nickName, String className, ContainerController containerController, String address) throws Exception {

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
            //*
            String type = getType(className);
            try {
                if (type != null) {
                    if (address == null || "".equals(address)) {
                        address = "localhost:1099";
                    }
                    String agentInfo = address + Message.FIELD_SEPARATE +
                            "temp agent" + Message.FIELD_SEPARATE +
                            nickName + Message.FIELD_SEPARATE +
                            "" + Message.FIELD_SEPARATE + // no owner
                            "active" + Message.FIELD_SEPARATE +
                            type;
                    AgentProcess.insertAgent(agentInfo);
                } else {
                    log.error("Cannot obtain the type of the agent: " + type);
                    log.error("Cannot insert into DB the agent: " + nickName);
                }
            } catch (Exception e) {
                log.error("Cannot update DB agent: " + e);
            }//*/
            return ac;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }
        return null;
    }

    public static ArrayList startAgent(String host, String port,
            String nickName, String className, boolean main, String address) throws Exception {

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
        //log.info("Created a container: " + cc.getContainerName());
        arr.add(cc);
        AgentController ac;
        try {
            // ac = cc.acceptNewAgent(nickName, agent);
            ac = cc.createNewAgent(nickName, className, null);
            ac.start();
            ac.activate();
            arr.add(ac);
            //*
            String type = getType(className);
            try {
                if (type != null) {
                    if (address == null || "".equals(address)) {
                        address = "localhost:1099";
                    }

                    String agentInfo = address + Message.FIELD_SEPARATE +
                            "temp agent" + Message.FIELD_SEPARATE +
                            nickName + Message.FIELD_SEPARATE +
                            "" + Message.FIELD_SEPARATE + // no owner
                            "active" + Message.FIELD_SEPARATE +
                            type;
                    AgentProcess.insertAgent(agentInfo);
                } else {
                    log.error("Cannot obtain the type of the agent: " + type);
                    log.error("Cannot insert into DB the agent: " + nickName);
                }
            } catch (Exception e) {
                log.error("Cannot update DB agent: " + e);
            }//*/
            return arr;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            arr.add(null);
            e1.printStackTrace();

        }
        return arr;
    }

    public static boolean kill(AgentController agentController, String nickName) {

        try {
            // kill from jade
            agentController.kill();
            // delete from DB
            AgentProcess.deleteAgent(nickName);
        } catch (StaleProxyException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getType(String className) {
        className = className.toLowerCase();
        if (className.contains(Language.CAR)) {
            return Language.CAR;
        }
        if (className.contains(Language.CONTROLLER)) {
            return Language.CONTROLLER;
        }
        if (className.contains(Language.CUSTOMER)) {
            return Language.CUSTOMER;
        }
        if (className.contains(Language.FLIGHT)) {
            return Language.FLIGHT;
        }
        if (className.contains(Language.HOTEL)) {
            return Language.HOTEL;
        }
        if (className.contains(Language.BROKER)) {
            return Language.BROKER;
        }
        if (className.contains(Language.TRAIN)) {
            return Language.TRAIN;
        }
        return null;
    }
}
