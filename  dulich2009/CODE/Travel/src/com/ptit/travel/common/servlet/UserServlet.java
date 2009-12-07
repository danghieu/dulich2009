package com.ptit.travel.common.servlet;

import com.ptit.travel.agent.communication.Message;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptit.travel.agent.user.UserAgent;
import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;
import jade.wrapper.AgentController;
import java.util.ArrayList;
import java.util.Enumeration;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchHotel
 */
public class UserServlet extends HttpServlet {

    Logger log = Logger.getLogger(UserServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private CallAgent callAgent;// ConfigXMLConnect.HOST_USER
    private AgentController agentController = null;
    private String nickName;
    //*

    @Override
    public void destroy() {
        super.destroy();
        if (agentController != null) {
            try {
                log.info("|| Killing agent: " + nickName);
                agentController.kill();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("UserServlet.destroy(): " + e);
            }
        }

    }

    @Override
    public void init() throws ServletException {
        super.init();
        if (agentController == null) {
            String host = "localhost";
            String port = "1099";
            nickName = "UserAgent";//"Guest" + System.currentTimeMillis();

            String className = "com.ptit.travel.agent.user.UserAgent";
            try {
                log.info("|| Starting agent: " + nickName);
                agentController = AgentManager.startAgent(host, port, nickName, className);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("UserServlet.init(): " + e);
            }

        }
    }//*/

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //process(request,response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String protocol = null;
        String param = "";
        String msgId = "guest" + System.currentTimeMillis();
        String msg = "";
        Enumeration list = request.getParameterNames();
        protocol = request.getParameter("protocol");

        while (list.hasMoreElements()) {
            param = (String) list.nextElement();
            if (!"protocol".equals(param) && !"submit".equals(param)) {
                msg += param + ": " + request.getParameter(param);
                if (list.hasMoreElements()) {
                    msg += Message.FIELD_SEPARATE;

                }
            }
        }
        log.info("|| " + msg);
        callAgent = new CallAgent();//("localhost", 8006);



        //*
        String result = "done";
        System.out.println("------------ Before call ---------------------");
        try {
            String input[] = { msg, msgId, protocol};
            callAgent.callTheAgentViaXmlRpc("UserAgent.search",input);

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            result = callAgent.callTheAgentViaXmlRpc("UserAgent.getSearchResults", msgId);// trieu goi de lay ket qua search

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("------------ affter call ---------------------" + result);
        //*/
        PrintWriter out = response.getWriter();
        out.print(result);// hien thi ket qua tren trang servlet

    }
}
