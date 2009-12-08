package com.ptit.travel.common.servlet;

import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
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
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchHotel
 */
public class UserServlet extends HttpServlet {

    private Logger log = Logger.getLogger(UserServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private CallAgent callAgent;// ConfigXMLConnect.HOST_USER
    private AgentController agentController = null;
    private String nickName;
    /*

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


        String protocol = request.getParameter("protocol");
        //*

        String msgId = "guest" + System.currentTimeMillis();
        String msg = extract(request);
        String function = "";

        // Foward to JSP to view

        String page = "/SearchServlet";
        if (protocol != null) {

            if (protocol.endsWith(Protocol.SUFFIX_SEARCH)) {
                function = "UserAgent.search";
                page = "/SearchServlet";
            } else if (protocol.endsWith(Protocol.SUFFIX_BOOK)) {
                function = "UserAgent.book";
                page = "/BookServlet";
            } // more... //TODO
            else {
                log.error("Don't understand protocol: " + protocol);
            }
            // create parameters to call agent
            String params[] = {msg, msgId, protocol};

//            callAgent = new CallAgent();
//            String result = callAgentBehavior(msgId, function, params);
//            request.setAttribute("result", result);
            try {
                log.info("Forward to: " + page);
                getServletConfig().getServletContext().getRequestDispatcher(
                        page).forward(request, response);

            } catch (ServletException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            log.error("Not identified protocol yet");
        }
    }

    /**
     * call to add a behavior to agent and get result from agent affter agent finishing this behavior
     * @param msgId unique id of msg
     * @param function invoked function of agent 
     * @param params String[] parameters of function
     * @return result affter agent has done the behavior
     */
    public String callAgentBehavior(String msgId, String function, String params[]) {

        String result = "null";
        try {
            log.info("Call " + function + "()");
            callAgent.callTheAgentViaXmlRpc(function, params);
            int times = 5; // max times 

            while ("null".equals(result) && times > 0) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                times--;
                result = callAgent.callTheAgentViaXmlRpc(function + "Results", msgId);

            }
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * combine all parameters values into a string.
     * Firstly, parameters ordered according to alphabet, then executes combining 
     * @param request
     * @return ([) + SEPARATE + getParameter(param1) + ...]
     */
    public String extract(HttpServletRequest request) {
        String param = null;
        String msg = "";
        Enumeration paramList = request.getParameterNames();
        TreeSet<String> paramSet = new TreeSet<String>();

        while (paramList.hasMoreElements()) {            
            param = (String) paramList.nextElement();            
            if (!"protocol".equals(param) && !param.contains("submit") && !"button".equals(param)) {
                paramSet.add(param);

            }
        }
        log.info("|| Parameter: " + paramSet);
        Object[] params = paramSet.toArray();
        int length = params.length;
        for (int i = 0; i < length; i++) {
            param = (String) params[i];
            msg += param + ": " +request.getParameter(param);
            if (i < length - 1) {
                msg += Message.FIELD_SEPARATE;
                

            }
        }
        log.info("|| " + msg);
        return msg;
    }
}