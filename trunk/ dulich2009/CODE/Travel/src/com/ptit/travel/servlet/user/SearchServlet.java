/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.servlet.user;

import com.ptit.travel.agent.communication.Message;
import com.ptit.travel.agent.communication.Protocol;
import java.io.*;

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
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/hotel.css\" />");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"pages/hotel/datphong.jsp\" method=\"get\"> ");
        int i, j;
        if (supplierList != null) {
            log.info(supplierList.toString());
            for (i = 0; i < supplierList.size(); i++) {
                try {
                    services = supplierList.get(i);
                    serviceList = Message.split(services, Message.FIELD_SEPARATE);
                    if (serviceList != null) {
                        out.println("<div style=\"padding-left: 18px;\">" +
                                "<div align=\"left\" style=\"border-bottom: 2px solid rgb(204, 204, 204); padding-bottom: 5px; padding-top: 5px; width: 99%; " +
                                "margin-right: 5px; clear: both;\">" +
                                "<div align=\"left\" style=\"overflow: hidden; padding-left: 0px;\">" +
                                "<div style=\"padding: 5px 0px; text-transform: uppercase;\">");
                        out.println("<a href=\"pages/hotel/datphong.jsp\" onClick=\"\"><h1>" +
                                serviceList.get(0) + "</h1></a>");
                        out.println("</div><div align=\"center\" style=\"border: 1px solid rgb(204, 204, 204); padding: 3px; width: 73px; float: left;\">" +
                                
                                "<img height=\"67\" border=\"0\" width=\"73\" src=\"http://congdulich.com//./uploads/hinhcongty/972972_160908085010.JPG\"/>" +
                                "</div><div style=\"float: left; width: 445px; padding-left: 10px;\">");
                        out.println("<ul>");
                        for (j = 1; j < serviceList.size(); j++) {
                            out.println("<li> " + serviceList.get(j) + "</li>");
                        }
                        out.println("</ul>");
                        //out.println("<a href=\"abc\" onClick=\"\">more</a>");
                        out.println("<input class=\"hidden\" type=\"submit\" name=\"submit\" value=\"Book\"/>");
                        out.println("</div><div id=\"B2FVNw_3D_3D\" style=\"overflow: hidden; padding-top: 3px; padding-bottom: 10px; display: none; clear: both;\">" +
                                "</div></div></div>");
                    }


                } catch (Exception e) {
                }
            }

            out.println("<input type=\"hidden\" name=\"protocol\" value=\"" + Protocol.HOTEL_RES + "\"/>");
            out.println("<input type=\"hidden\" name=\"input\" value=\"" + result + "\"/>");
        }


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
