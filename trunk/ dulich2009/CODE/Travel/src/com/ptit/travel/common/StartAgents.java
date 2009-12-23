package com.ptit.travel.common;

import com.ptit.travel.agent.ControllerAgent;
import com.ptit.travel.agent.communication.ConfigXMLConnect;
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
import com.ptit.travel.agent.HotelAgent;
import com.ptit.travel.beans.Address;
import com.ptit.travel.beans.AgentBean;
import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;

public class StartAgents {

    public static final String SERVER = "http://localhost:8006";

    //
    // String id = "AskAgent";
    // String resourceType = "CD";
    XmlRpcClientLite myClient;

    public StartAgents() {
        try {
            myClient = new XmlRpcClientLite(SERVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param args
     * 
     */
    private void callTheAgentViaXmlRpc() {
        String function = "UserAgent.search";;
        String param[] = {"102", "abc", "available"};
        CallAgent ca = new CallAgent();
        String result = ca.callTheAgentViaXmlRpc(function, param);
        System.out.println("|| CALL "+function+": " + result);
    }
 
    public void testSplitMessage() {
        String input = "param1" + Message.FIELD_SEPARATE +
                "param2@" + Message.FIELD_SEPARATE +
                "param3$" + Message.OBJECT_SEPARATE +
                "paramA" + Message.FIELD_SEPARATE +
                "paramB" + Message.OBJECT_SEPARATE +
                "paramX";
        input = "@_&HaiPhong@_&cool@_&@_&inside@_&Parking@_&2@_&Swimm";
        System.out.println("|| INPUT: " + input);
        ArrayList<String> list = Message.split(input, Message.FIELD_SEPARATE);
        System.out.println("|| SEPARATE OBJECT: " + list.toString());
        System.out.println("|| SEPARATE FIELD of 1st Ojbect: " + Message.split(list.get(0), Message.FIELD_SEPARATE));
    }

    public void testConfigXMLConnect(){
        System.out.println(com.ptit.travel.agent.onto.Config.BASE);
    }
     public void testCreateAgentCH(){
        AgentController agentController;
            String host = "localhost";
            String port = "1099";
            String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();
            String className = "com.ptit.travel.agent.ControllerAgent";
            try {                
                ContainerController containerController = (ContainerController)AgentManager.startAgent(host, port, 
                        nickName, className,true).get(0);
                nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.HotelAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        
    }
    public void testCreateAgentUCH(){
        AgentController agentController;
            String host = "localhost";
            String port = "1099";
            String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();
            String className = "com.ptit.travel.agent.ControllerAgent";
            try {                
                ContainerController containerController = (ContainerController)AgentManager.startAgent(host, port, 
                        nickName, className,true).get(0);
                nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.HotelAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
                nickName = "UserAgent";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.UserAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        
    }
    public void testCreateAgentUC2H(){
        AgentController agentController;
            String host = "localhost";
            String port = "1099";
            String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();HotelAgent1
            String className = "com.ptit.travel.agent.ControllerAgent";
            try {                
                ContainerController containerController = (ContainerController)AgentManager.startAgent(host, 
                        port, nickName, className,false).get(0);
                nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.HotelAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
                 nickName = "HotelAgent1";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.HotelAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);

                nickName = "UserAgent";//"Guest" + System.currentTimeMillis();
                className = "com.ptit.travel.agent.UserAgent";
                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
//                nickName = "UserAgent2";//"Guest" + System.currentTimeMillis();
//                className = "com.ptit.travel.agent.user.UserAgent";
//                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StartAgents test = new StartAgents();
//        test.testSplitMessage();
        //test.testConfigXMLConnect();
        //test.callTheAgentViaXmlRpc();
        test.testCreateAgentUCH();        
    
    }

}
