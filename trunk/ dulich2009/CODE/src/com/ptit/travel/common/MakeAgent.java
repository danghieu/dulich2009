/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.common;

import com.ptit.travel.agent.HotelAgent;
import com.ptit.travel.agent.UserAgent;

import com.ptit.travel.beans.AgentBean;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.io.*;
import jade.core.Runtime;
import java.net.*;

import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author D05CNPM
 */
public class MakeAgent extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MakeAgent</title>");
        out.println("</head>");
        out.println("<body>");        
        try {            
            Agent user = new UserAgent();
            Agent hoteler = new HotelAgent();
            String host = "localhost";
            String nickName = "user" + System.currentTimeMillis();
            String port = "1099";
            AgentBean agentBean = new AgentBean();
            
            //AgentController acUser = startAgent(host, port, nickName, user);
            //acUser.putO2AObject(agentBean, true);
            nickName = "hotel" + System.currentTimeMillis();
            out.print("HOST: " + agentBean.getHost());
            AgentController acHoteler = startAgent(host, port, nickName, hoteler);
            
            /**
             * Kill agent contains in agent controller 
             */
//            try {
//
//                ac.kill();
//
//                out.println("ok");
//            } catch (ControllerException ex) {
//                out.println(ex);
//                Logger.getLogger(MakeAgent.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //* TODO output your page here

            out.println("<h1>Servlet MakeAgent at " + System.getProperty("user.dir") + "</h1>");
            //out.println("<h1>Servlet MakeAgent at " + acHoteler + "</h1>");
            out.println("</body>");
            out.println("</html>");
        //*/
        } catch (Exception ex) {
            out.println(ex);
            Logger.getLogger(MakeAgent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    /**
     * StartAgent create a new agent and start one on jadeServer
     * @para: jadeServer is jade server on which new agent will run
     * @para: nickName is nick name of agent
     * return 1 if start successfully new agent
     * else return -1;
     */
    public AgentController startAgent(String host, String port, String nickName, Agent agent) {
        ContainerController cc;
        Runtime rt = Runtime.instance();
        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, host);
        p.setParameter(Profile.MAIN_PORT, port);
        // Create a new main container, connecting to the default
        cc = rt.createAgentContainer(p);//createMainContainer(p);        

        AgentController ac;
        try {
            //ac = cc.acceptNewAgent(nickName, agent);
            ac = cc.createNewAgent(nickName, agent.getClass().getName(), null);
            ac.start();
            ac.activate();
            return ac;
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();

        }
        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}
