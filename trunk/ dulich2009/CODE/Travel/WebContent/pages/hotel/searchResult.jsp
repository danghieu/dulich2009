<%-- 
    Document   : SearchResult
    Created on : Dec 29, 2009, 3:03:10 PM
    Author     : D05CNPM
--%>
<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Message" %>
<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Tourism</title>
        <link rel="stylesheet" type="text/css"
              href="css/hotel.css" />
    </head>
    <body>
        <%
            Logger log = Logger.getLogger("searchResult.jsp");
            String result = (String) request.getAttribute("result");

            String services, supplier, hotelName, address;
            ArrayList<String> supplierList = Message.split(result, Message.OBJECT_SEPARATE);
            ArrayList<String> serviceList;



            int i, j;
            if (supplierList != null) {

                for (i = 0; i < supplierList.size(); i++) {
                    try {
                        services = supplierList.get(i);
                        serviceList = Message.split(services, Message.FIELD_SEPARATE);
                        if (serviceList != null) {
                            supplier = serviceList.get(0);
                            hotelName = serviceList.get(6);
                            address = serviceList.get(1); 
                            log.info("[HOTEL INFO] supplier: " + supplier + " hotelName: " 
                                    +hotelName+ " address: " + address);
                            out.println("<form action=\"bookRoom.jsp\" method=\"get\" id=\"form" + i + "\"> ");
                            out.println("<div style=\"padding-left: 18px;\">" +
                                    "<div align=\"left\" style=\"border-bottom: 2px solid rgb(204, 204, 204); padding-bottom: 5px; padding-top: 5px; width: 99%; " +
                                    "margin-right: 5px; clear: both;\">" +
                                    "<div align=\"left\" style=\"overflow: hidden; padding-left: 0px;\">" +
                                    "<div style=\"padding: 5px 0px; text-transform: uppercase;\">");
                            out.println("<a href=\"pages/hotel/datphong.jsp\" onClick=\"\"><h1>" +
                                    supplier + "</h1></a>");
                            out.println("</div><div align=\"center\" style=\"border: 1px solid rgb(204, 204, 204); padding: 3px; width: 73px; float: left;\">" +
                                    "<img height=\"67\" border=\"0\" width=\"73\" src=\"http://congdulich.com//./uploads/hinhcongty/972972_160908085010.JPG\"/>" +
                                    "</div><div style=\"float: left; width: 445px; padding-left: 10px;\">");
                            out.println("<ul>");
                            services = services.replaceFirst(supplier, "");
                            log.info("Services: " + services);
                            int size = serviceList.size();
                            /*
                            for (j = 1; j < serviceList.size(); j++) {
                            out.println("<li>" + serviceList.get(j) + "</li>");                                
                            }/*/
                            out.println("<li>Khách sạn: " + hotelName + "</li>");
                            out.println("<li>Địa chỉ: " + address + "</li>");
                            out.println("<li>" + serviceList.get(8) + " sao</li>");
                            out.println("<li>Vị trí: " + serviceList.get(7) + "</li>");
                            //*/
                            out.println("</ul>");
        %>
        
        <table width="250" border="1">
            <tr>
                <td>Loại phòng</td>
                <td>Giá</td>
                <td>Đơn vị</td>
            </tr>
            <%
                        for (int k = 9; k < size;) {
            %>
            <tr>
                <td><%=serviceList.get(k)%></td>
                <td><%=serviceList.get(k + 1)%></td>
                <td><%=serviceList.get(k + 2)%></td>
            </tr>
            <%
                            k += 3;
                        }
            %>
        </table>
        
        
        <%
                            out.println("<input type=\"hidden\" name=\"supplier\" value=\"" + supplier + "\"/>");
                            out.println("<input type=\"hidden\" name=\"hotelName\" value=\"" + hotelName + "\"/>");
                            out.println("<input type=\"hidden\" name=\"address\" value=\"" + address + "\"/>");
                            out.println("<input type=\"hidden\" name=\"protocol\" value=\"" + Protocol.HOTEL_RES + "\"/>");
                            out.println("<input type=\"hidden\" name=\"services\" value=\"" + services + "\"/>");
                            //out.println("<a href=\"abc\" onClick=\"\">more</a>");
                            out.println("<input type=\"submit\" name=\"submit\" value=\"Đặt\"/>");
                            out.println("</div><div id=\"B2FVNw_3D_3D\" style=\"overflow: hidden; padding-top: 3px; padding-bottom: 10px; display: none; clear: both;\">" +
                                    "</div></div></div>");
                            out.println("</form>");
                        }


                    } catch (Exception e) {
                        log.error(e);
                    }
                }


            }



        %>
    </body>
</html>
