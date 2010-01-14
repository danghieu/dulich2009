package com.ptit.travel.common.servlet;

import com.ptit.travel.agent.communication.ConfigXMLConnect;
import com.ptit.travel.agent.communication.Language;
import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;
import com.ptit.travel.jane.Flight.FlightProcess;
import com.ptit.travel.jane.agent.AgentProcess;
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
                AgentManager.kill(agentController, nickName);
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
    //*
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
                String address = agentHost + ":" + agentHost;
                ArrayList arr = AgentManager.startAgent(host, port, nickName, className, false,address);
//                String agentInfo = agentHost + ":" + agentPort +
//                        "temp agent" +
//                        nickName +
//                        "" + // no owner
//                        "active" +
//                        Language.CUSTOMER;
//                AgentProcess.insertAgent(agentInfo);
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

    protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String protocol = request.getParameter("protocol");
        log.info("POTOCOL: " + protocol);
        //*

        String msgId = "guest" + System.currentTimeMillis();

        String msg = "";// = extractMsg(request);

        String function = "";

        // Foward to JSP to view


        String page = "index.jsp";
        if (protocol != null) {

            if (protocol.equals(Protocol.HOTEL_AVAIL)) {
                msg = extractSearchMsg(request);
                function = nickName + ".search";
                page = "/searchResult.jsp";
            } else if (protocol.equals(Protocol.FLIGHT_AVAIL)) {
                msg = extractSearchFlightMsg(request);
                function = nickName + ".search";
                page = "/searchFlightResult.jsp";
            } else if (protocol.equals(Protocol.TRAIN_AVAIL)) {
                msg = extractSearchTrainMsg(request);
                function = nickName + ".search";
                page = "/searchTrainResult.jsp";
            } else if (protocol.equals(Protocol.TOURSERVICE_AVAIL)) {
                msg = extractSearchTourMsg(request);
                function = nickName + ".search";
                page = "/searchTourResult.jsp";
            } else if (protocol.equals(Protocol.HOTEL_RES)) {
                msg = extractBookRoomMsg(request);
                function = nickName + ".book";
                page = "/bookResult.jsp";
            } else if (protocol.equals(Protocol.FLIGHT_RES)) {
                msg = extractBookFlightMsg(request);
                function = nickName + ".book";
                page = "/bookResult.jsp";
            } else if (protocol.equals(Protocol.TRAIN_RES)) {
                msg = extractBookTrainMsg(request);
                function = nickName + ".book";
                page = "/bookResult.jsp";
            }else if (protocol.equals("LOGIN")) {
                login(request, response);
                return;
            }// more... //TODO
            else {
                log.error("Don't understand protocol: " + protocol);
                return;
            }
            // create parameters to call agent
            String params[] = {msg, msgId, protocol};

            //callAgent = new CallAgent();
            String result = //"FlightAgent  VN213_20100201_E  Information  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN217_20100201_E  Information  Vietnam Airlines  VN217  Noi Bai-Ha Noi  11:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  13:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN219_20100201_E  Information  Vietnam Airlines  VN219  Noi Bai-Ha Noi  13:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  15:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&";
                    callAgentBehavior(msgId, function, params);
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
     * khi co yeu cau dang nhap
     * @param request
     * @return
     */
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String type = request.getParameter("type");
        String input = userName + Message.FIELD_SEPARATE + password;
        String result = "";
        String page = "#";
        // check information account
        HttpSession session = request.getSession();

        log.debug("TYPE : " + type);
        if (type.equals(Language.FLIGHT)) {
            page = "/flightAgentUpdate.jsp";

            log.debug("CALL FlightServiceManager() with: " + input);
            result = FlightProcess.FlightServiceManager(input);
            log.debug("RETURN FROM FlightServiceManager(): " + result);
            if (!"".equals(result)) {
                session.setAttribute("isValidate", Language.FLIGHT);
            } else {
                page = "/login.jsp";
                log.debug("LOGIN FAIL");
            }
            request.setAttribute("result", result);
            try {
                getServletConfig().getServletContext().getRequestDispatcher(page).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else{
            
        }
        // if satified
        if (containerController != null) {
            try {
                page = "/index.jsp";

                AgentManager.kill(agentController, nickName);
                nickName = userName;
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
                ArrayList<String> agent = AgentProcess.getAgentById(nickName);
                //if (agent == null) {
                agentController = AgentManager.addAgent(host, port, nickName, className, containerController, "");
                //}
                session.setAttribute("userContext", nickName);
            } catch (Exception e) {
                userName = null;
            }

        }
        /**
         * Neu type = UserAgent: truy van CSDL booking de doc thong tin offline
         * // TODO
         */
        return userName;
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
    public String extractSearchMsg(HttpServletRequest request) {
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

            if (value == null || "".equals(value)) {
                value = "null";
            }
            msg += value;
            if (i < length - 1) {
                msg += Message.FIELD_SEPARATE;


            }
        }
        log.info("SEARCH HOTEL Msg: " + msg);
        return msg;
    }

    public String extractSearchFlightMsg(HttpServletRequest request) {
        String msg = "";
        String depart,destination ,startDate ,ticket ,quatity ;
        depart = request.getParameter("city");
        destination = request.getParameter("climate");
        startDate = request.getParameter("startDate");
        ticket = request.getParameter("ticket");
        quatity = request.getParameter("returnDate");// trung voi index.jsp


        msg = "" + depart + Message.FIELD_SEPARATE +
                destination + Message.FIELD_SEPARATE +
                startDate + Message.FIELD_SEPARATE +
                ticket + Message.FIELD_SEPARATE +
                quatity;
        log.info("SEARCH FLIGHT Msg: " + msg);
        return msg;
    }

    public String extractSearchTrainMsg(HttpServletRequest request) {
        String msg = "";
        String depart,destination ,startDate ,ticket ,quatity ;
        depart = request.getParameter("city");
        destination = request.getParameter("climate");
        startDate = request.getParameter("startDate");
        ticket = request.getParameter("ticket");
        quatity = request.getParameter("quatity");


        msg = "" + depart + Message.FIELD_SEPARATE +
                destination + Message.FIELD_SEPARATE +
                startDate + Message.FIELD_SEPARATE +
                ticket + Message.FIELD_SEPARATE +
                quatity;
        log.info("SEARCH FLIGHT Msg: " + msg);
        return msg;
    }
    public String extractSearchTourMsg(HttpServletRequest request) {
        String msg = "";
        String beginPoit,endPoit ,beginTime ,endTime;
        beginPoit = request.getParameter("beginPoit");
        endPoit = request.getParameter("endPoit");
        beginTime = request.getParameter("beginTime");
        endTime = request.getParameter("endTime");


        msg = "" + beginPoit + Message.FIELD_SEPARATE +
                endPoit + Message.FIELD_SEPARATE +
                beginTime + Message.FIELD_SEPARATE +
                endTime;
        log.info("SEARCH TOUR Msg: " + msg);
        return msg;
    }

    public String extractBookRoomMsg(HttpServletRequest request) {
        String msg = "";
        String agentName,hotelName ,city ,number ,street ,quantity ,roomType ,fromdate ,todate ,fullName ,profession ,identityCard ;
        agentName = request.getParameter("agentName");
        hotelName = request.getParameter("hotelName");
        String address = request.getParameter("address");
        ArrayList<String> arr = Message.split(address, " _ ");
        log.debug("SPLITED ADDRESS: " + arr);
        city = arr.get(2);
        number = arr.get(0);
        street = arr.get(1);
        quantity = request.getParameter("quantity");
        roomType = request.getParameter("roomType");
        fromdate = request.getParameter("fromdate");
        todate = request.getParameter("todate");
        fullName = request.getParameter("fullName");
        profession = request.getParameter("profession");
        identityCard = request.getParameter("identityCard");

        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String fax = request.getParameter("fax");
        String specificAddress = request.getParameter("specificAddress");
        String userCity = request.getParameter("userCity");
        msg = "" + agentName + Message.FIELD_SEPARATE +
                hotelName + Message.FIELD_SEPARATE +
                city + Message.FIELD_SEPARATE +
                number + Message.FIELD_SEPARATE +
                street + Message.FIELD_SEPARATE +
                roomType + Message.FIELD_SEPARATE +
                quantity + Message.FIELD_SEPARATE +
                fromdate + Message.FIELD_SEPARATE +
                todate + Message.FIELD_SEPARATE +
                fullName + Message.FIELD_SEPARATE +
                profession + Message.FIELD_SEPARATE +
                identityCard + Message.FIELD_SEPARATE +
                email + Message.FIELD_SEPARATE +
                fax + Message.FIELD_SEPARATE +
                phoneNumber + Message.FIELD_SEPARATE +
                specificAddress + Message.FIELD_SEPARATE +
                userCity;
        log.debug("BOOK ROOM Msg: " + msg);
        return msg;
    }

    public String extractBookFlightMsg(HttpServletRequest request) {
        String msg = "";
        String agentName,id ,agent ,booknumber ,fullName ,sex ,email ,phoneNumber ,specificAddress ,city ,country ;
        agentName = request.getParameter("agentName");
        id = request.getParameter("id");
        agent = request.getParameter("agent");
        booknumber = request.getParameter("booknumber");
        fullName = request.getParameter("fullName");
        sex = request.getParameter("sex");
        email = request.getParameter("email");
        phoneNumber = request.getParameter("phoneNumber");
        specificAddress = request.getParameter("specificAddress");
        city = request.getParameter("city");
        country = request.getParameter("country");

        msg = "" + agentName + Message.FIELD_SEPARATE +
                id + Message.FIELD_SEPARATE +
                agent + Message.FIELD_SEPARATE +
                booknumber +
                Message.OBJECT_SEPARATE +
                fullName + Message.FIELD_SEPARATE +
                sex + Message.FIELD_SEPARATE +
                email + Message.FIELD_SEPARATE +
                phoneNumber + Message.FIELD_SEPARATE +
                specificAddress + Message.FIELD_SEPARATE +
                city + Message.FIELD_SEPARATE +
                country;
        log.debug("BOOK FLIGHT Msg: " + msg);
        return msg;

    }

    public String extractBookTrainMsg(HttpServletRequest request) {
        String msg = "";
        String agentName,id ,agent ,booknumber ,fullName ,age ,email ,phoneNumber ,specificAddress ,city ,job ;
        agentName = request.getParameter("agentName");
        id = request.getParameter("id");
        agent = request.getParameter("agent");
        booknumber = request.getParameter("booknumber");
        fullName = request.getParameter("fullName");
        age = request.getParameter("age");
        email = request.getParameter("email");
        phoneNumber = request.getParameter("phoneNumber");
        specificAddress = request.getParameter("specificAddress");
        city = request.getParameter("city");
        job = request.getParameter("job");

        msg = "" + agentName + Message.FIELD_SEPARATE +
                id + Message.FIELD_SEPARATE +
                agent + Message.FIELD_SEPARATE +
                booknumber +
                Message.OBJECT_SEPARATE +
                fullName + Message.FIELD_SEPARATE +
                age + Message.FIELD_SEPARATE +
                email + Message.FIELD_SEPARATE +
                phoneNumber + Message.FIELD_SEPARATE +
                specificAddress + Message.FIELD_SEPARATE +
                city + Message.FIELD_SEPARATE +
                job;
        log.debug("BOOK TRAIN Msg: " + msg);
        return msg;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }
}
