package com.ptit.travel.common.servlet;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptit.travel.common.AgentManager;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
    // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        //Agent user = new UserAgent();
        String host = "localhost";
        String nickName = "user" + System.currentTimeMillis();
        String port = "1099";
        String className = " com.ptit.travel.agent.user.UserAgent";
        try {
            //AgentController acUser = AgentManager.startAgent(host, port, nickName, className);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }



        out.println("aloha Get");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("aloha Post");
    }
}
