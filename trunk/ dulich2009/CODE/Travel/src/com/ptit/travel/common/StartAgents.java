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
import com.ptit.travel.moduleJDBC.Model.AgentDB;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String function = "UserAgent.search";
        ;
        String param[] = {"102", "abc", "available"};
        CallAgent ca = new CallAgent();
        String result = ca.callTheAgentViaXmlRpc(function, param);
        System.out.println("|| CALL " + function + ": " + result);
    }

    public void testSplitMessage() {
        String input = "param1" + Message.FIELD_SEPARATE +
                "param2@" + Message.FIELD_SEPARATE +
                "param3$" + Message.OBJECT_SEPARATE +
                "paramA" + Message.FIELD_SEPARATE +
                "paramB" + Message.OBJECT_SEPARATE +
                "paramX";
        input = "HotelAgent@_&405 _ Thanh Xuan Bac _ Nam Dinh _ Vietnam@_&warm@_&10. AM@_&10 AM@_&yennh235@gmail.com@_&HaiYen@_&LakeSide@_&4@_&MeetingRoom@_&100.0@_&USD@_&LivingRoom@_&50.0@_&USD@_&PartyRoom@_&250.0@_&USD@_&ConferenceRoom@_&300.0@_&USD@_&Km5 _ Ton That Tung _ Nam Dinh _ Vietnam@_&warm@_&10AM@_&10AM@_&Thien Truong@_&Thien Truong@_&Center@_&4@_&15 _ Tran Phu _ Ha Noi _ Vietnam@_&warm@_&11AM@_&11AM@_&ThienHuong@gmail.com@_&Thien Huong@_&Center@_&4@_&@_&Parking,BabySittingService,LaundryService,nightClub,24HoursReception,CurrencyExchangeCounter,DryCleaningService,MoutainBikeDentalService,Spa,SwimmingPool,#_&#_&TrainAgent@_&SE1@_&HN_PL_SE1_2@_&Ha Noi@_&Phu Ly@_&2009-12-29,19:00:00@_&2009-12-29,20:02:00@_&Ghe cung@_&22000.0 VND@_&@_&#_&#_&SE1@_&HN_PL_SE1_1@_&Ha Noi@_&Phu Ly@_&2009-12-29,19:00:00@_&2009-12-29,20:02:00@_&Ghe mem@_&24000.0 VND@_&@_&#_&#_&";
        System.out.println("|| INPUT: " + input);
        ArrayList<String> list = Message.split(input, Message.OBJECT_SEPARATE);
        System.out.println("|| SEPARATE OBJECT: " + list.toString());
        System.out.println("|| SEPARATE FIELD of 1st Ojbect: " + Message.split(list.get(0), Message.FIELD_SEPARATE));
    }

    public void testConfigXMLConnect() {
        System.out.println(com.ptit.travel.agent.onto.Config.BASE);
    }

    public void testCreateAgentCH() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testCreateAgentUCH() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testCreateAgentUC() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, false,"").get(0);
             nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void testCreateAgentUCF() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "FlightAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.FlightAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testCreateAgentUCHFT() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "FlightAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.FlightAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "TrainAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.TrainAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void testCreateAgentCF(boolean main) {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, main,"").get(0);
            nickName = "FlightAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.FlightAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void testCreateAgentC(boolean main) {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, main,"").get(0);
    

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void testCreateAgentCHF() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "FlightAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.FlightAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testCreateAgentCHFT() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "FlightAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.FlightAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "TrainAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.TrainAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testCreateAgentCUT() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host, port,
                    nickName, className, true,"").get(0);
            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "TrainAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.TrainAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void testCreateAgentUC2H() {
        AgentController agentController;
        String host = "localhost";
        String port = "1099";
        String nickName = "ControllerAgent";//"Guest" + System.currentTimeMillis();HotelAgent1

        String className = "com.ptit.travel.agent.ControllerAgent";
        try {
            ContainerController containerController = (ContainerController) AgentManager.startAgent(host,
                    port, nickName, className, false,"").get(0);
            nickName = "HotelAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
            nickName = "HotelAgent1";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.HotelAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");

            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            className = "com.ptit.travel.agent.UserAgent";
            agentController = AgentManager.addAgent(host, port, nickName, className, containerController,"");
//                nickName = "UserAgent2";//"Guest" + System.currentTimeMillis();
//                className = "com.ptit.travel.agent.user.UserAgent";
//                agentController = AgentManager.addAgent(host, port, nickName, className,containerController);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 
     * @param fromDate: start date
     * @param toDate: end date
     * @param pattern: format of date, example: "yyyy/MM/dd" or "yyyy-MM-dd" or "dd-MM-yyyy" so on...
     * @return float value = toDate - fromDate
     * -1 if wrong pattern and throw an exception 
     */
    public float subDay(String fromDate, String toDate, String pattern) throws Exception {
        float days = -1;
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date date1 = format.parse(fromDate);
            long time1 = date1.getTime();
            Date date2 = format.parse(toDate);
            long time2 = date2.getTime();
            days = (float) ((time2 - time1) / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;//ParseException();
        }
        return days;
    }

    /**
     * chi can chay file nay thoi, no khoi tao 3 angen User, Controller, HOtel luon.
     * ko can hay servlet
     * 
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception{
        AgentDB db=new AgentDB();
                
	boolean result = db.loadData(true);
        StartAgents test = new StartAgents();
//        test.testSplitMessage();
        //test.testConfigXMLConnect();
        //test.callTheAgentViaXmlRpc();
//        test.testCreateAgentCF(false);   // chi chay contrller & Hotel     
        test.testCreateAgentCHFT();
//        System.out.println(test.subDay("2009-12-30", "2010-01-01", "yyyy-MM-dd"));
        

    }
}
