package com.ptit.travel.agent.user;

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
import com.ptit.travel.agent.hotel.HotelAgent;
import com.ptit.travel.beans.Address;
import com.ptit.travel.beans.AgentBean;
import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;

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
    private void callTheAgentViaXmlRpc() {
        String function = "UserAgent.getSearchResults";
        String param = "102";
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
        System.out.println("|| INPUT: " + input);
        ArrayList<String> list = Message.split(input, Message.OBJECT_SEPARATE);
        System.out.println("|| SEPARATE OBJECT: " + list.toString());
        System.out.println("|| SEPARATE FIELD of 1st Ojbect: " + Message.split(list.get(0), Message.FIELD_SEPARATE));
    }

    public void testConfigXMLConnect(){
        System.out.println(com.ptit.travel.agent.onto.Config.BASE);
    }
    public void testCreateAgent(){
        AgentController agentController;
            String host = "localhost";
            String port = "1099";
            String nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();
            String className = "com.ptit.travel.agent.hotel.HotelAgent";
            try {                
                agentController = AgentManager.startAgent(host, port, nickName, className);
                System.out.println("|| Started agent: " + agentController.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Test test = new Test();
        //test.testSplitMessage();
        //test.testConfigXMLConnect();
        //test.callTheAgentViaXmlRpc();
        test.testCreateAgent();
    
    }

}
