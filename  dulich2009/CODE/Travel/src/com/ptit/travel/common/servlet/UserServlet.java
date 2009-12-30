package com.ptit.travel.common.servlet;

import com.ptit.travel.agent.communication.ConfigXMLConnect;
import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptit.travel.agent.UserAgent;
import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchHotel
 */
public class UserServlet extends HttpServlet {

    private Logger log = Logger.getLogger(UserServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private String agentHost = ConfigXMLConnect.HOST_USER;
    /**
     * Generate a new port for user agent
     */
    private int agentPort = ConfigXMLConnect.PORT_USER;
    private CallAgent callAgent = new CallAgent(agentHost, agentPort);
    private AgentController agentController = null;
    private ContainerController containerController = null;
    private String nickName;
    private String host = "localhost";
    private String port = "1099";
    private String className = "com.ptit.travel.agent.UserAgent";

    /**
     * Huy 1 agent khi het phien lam viec, timeout hoac tat agent
     */
    @Override
    public void destroy() {
        super.destroy();
        if (agentController != null) {
            try {
                log.info("|| Killing agent: " + nickName);
                agentController.kill();
                if (containerController != null) {
                    log.info("|| Killing container: " + containerController.getContainerName());
                    containerController.kill();

                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("UserServlet.destroy(): " + e);
            }
        }

    }

    /**
     * chi chay 1 lan, duoc goi khi nguoi dung request den server
     * @throws javax.servlet.ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        if (agentController == null) {
            //log.info("Generate a NEW PORT for user agent: " + agentHost);
            nickName = "Guest" + System.currentTimeMillis();

            log.info("Call agent on port " + agentPort);
            // Generate Next port for next UserAgent
            ConfigXMLConnect.nextPort("PORT_USER");

            try {
                log.info("|| Starting agent: " + nickName);
                ArrayList arr = AgentManager.startAgent(host, port, nickName, className, false);
                if (arr != null && arr.size() == 2) {
                    containerController = (ContainerController) arr.get(0);
                    agentController = (AgentController) arr.get(1);
                }

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
        process(request, response);
    }

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String protocol = request.getParameter("protocol");
        //*

        String msgId = "guest" + System.currentTimeMillis();
        String msg = extractMsg(request);
        String function = "";

        // Foward to JSP to view


        String page = "/SearchServlet";
        if (protocol != null) {

            if (protocol.endsWith(Protocol.SUFFIX_SEARCH)) {
                function = nickName + ".search";
                page = "/pages/hotel/searchResult.jsp";
            } else if (protocol.endsWith(Protocol.SUFFIX_BOOK)) {
                function = nickName + ".book";
                page = "/BookServlet";
            } // more... //TODO
            else {
                log.error("Don't understand protocol: " + protocol);
            }
            // create parameters to call agent
            String params[] = {msg, msgId, protocol};

            //callAgent = new CallAgent();
            String result = callAgentBehavior(msgId, function, params);
            request.setAttribute("result", result);
//            request.setAttribute("callAgent", callAgent);
//            request.setAttribute("msgId", msgId);
//            request.setAttribute("function", function + "Results");
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
            log.info("Call getResults()");
            result = callAgent.callTheAgentViaXmlRpc(nickName + ".getResults", msgId);


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
    public String extractMsg(HttpServletRequest request) {
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
        String value = "";
        for (int i = 0; i < length; i++) {
            param = (String) params[i];
            
            value = request.getParameter(param);//param + ": " +
            if(value == null || "".equals(value))
                value = "null";
            msg += value;
            if (i < length - 1) {
                msg += Message.FIELD_SEPARATE;


            }
        }
        log.info("|| " + msg);
        return msg;
    }

    /**
     * khi co yeu cau dang nhap
     * @param request
     * @return
     */
    public String login(HttpServletRequest request) {
        String id = request.getParameter("userName");
        String password = request.getParameter("password");
        // check information account

        /**
         * Truy van CSDL Agent.owl lay ra address[http://agentHost:agentPort], type, 
         * state cua id agent
         * Gan lai 
         *  className = "com.ptit.travel.agent." + type ;
         *  address 
         *  
         *  callAgent=new CallAgent(address);
         * 
         * Neu state = active: khong tao them agent
         */
        HttpSession session = request.getSession();
        // if satified
        if (containerController != null) {
            try {
                agentController.kill();
                containerController.kill();
                containerController = null;
                nickName = id;
                agentController = AgentManager.addAgent(host, port, nickName, className, containerController);
            } catch (Exception e) {
                id = null;
            }

        }
        /**
         * Neu type = UserAgent: truy van CSDL booking de doc thong tin offline
         * // TODO
         */
        return id;
    }

    public void logout(HttpServletRequest request) {
        log.info("User logout action...");
        log.debug("# Begin method user logout");

        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception ex) {
            log.info("Error while perform user logout action..");
            log.error(ex.getMessage());
        }
        log.debug("# End method user logout action");
        log.info("User logout has been done!");

    }
}
