/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.servlet.user;

import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import com.ptit.travel.common.CallAgent;
import com.ptit.travel.servlet.HandleRequest;
import java.io.*;
import java.net.*;

import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

/**
 *
 * @author D05CNPM
 */
public class SearchServlet extends HttpServlet {

    private Logger log = Logger.getLogger(SearchServlet.class.getName());

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String result = (String) request.getAttribute("result");
        String services;
        ArrayList<String> supplierList = Message.split(result, Message.OBJECT_SEPARATE);
        ArrayList<String> serviceList;
        // Display
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet SearchServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"UserServlet\" method=\"get\"> ");
        int i, j;
        if (supplierList != null) {
            for (i = 0; i < supplierList.size(); i++) {
                try {
                    services = supplierList.get(i);
                    serviceList = Message.split(services, Message.FIELD_SEPARATE);
                    if (serviceList != null) {
                        out.println("<h1>Supplier: " + serviceList.get(0) + "</h1>");
                        out.println("<ul>");
                        for (j = 1; j < serviceList.size(); j++) {
                            out.println("<li> " + serviceList.get(j) + "</li>");
                        }
                        out.println("</ul>");
                    }


                } finally {
                    out.close();
                }
            }
        }
        out.println("<input type=\"submit\" name=\"submit\" value=\"Book\"/>");
        out.println("<input type=\"hidden\" name=\"protocol\" value=\"" + Protocol.HOTEL_RES + "\"/>");
        out.println("<input type=\"hidden\" name=\"input\" value=\"" + result + "\"/>");

        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
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
