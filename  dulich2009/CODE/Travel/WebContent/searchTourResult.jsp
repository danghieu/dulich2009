<%@ page language="java" import="java.util.ArrayList"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Message" %>
<%@ page language="java" import="org.apache.log4j.Logger"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="templatemo_maincontainer">
            <div id="templatemo_topsection">
                <jsp:include page="/pages/layout/header.jsp" flush="false"></jsp:include>
            </div>
            <div id="templatemo_left_column">
                <div id="templatemo_menu_top"></div>
                <div class="templatemo_menu"> 
                    <jsp:include page="/pages/layout/menu.jsp" flush="false"></jsp:include>
                </div>
                <div id="templatemo_contact">
                    <jsp:include page="/pages/layout/contact.jsp" flush="false"></jsp:include>  
                </div>
            </div>
            <div id="templatemo_right_column">
                <%
            Logger log = Logger.getLogger("searchTourResult.jsp");
            String result = (String) request.getAttribute("result");

            if (result == null || "".equals(result)) {
                log.error("String received from UserServlet " + result);
                out.println("There is no result to deplay");
                return;
            }
            //result = "FlightAgent  VN213_20100201_E  Information  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN217_20100201_E  Vietnam Airlines  VN217  Noi Bai-Ha Noi  11:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  13:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN219_20100201_E  Vietnam Airlines  VN219  Noi Bai-Ha Noi  13:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  15:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&";
            String agentName = result.substring(0, result.indexOf(Message.FIELD_SEPARATE));
            //result = result.replace(agentName, "");
            //      log.info(result);
            String services;
            ArrayList<String> supplierList = Message.split(result, Message.OBJECT_SEPARATE);

            ArrayList<String> serviceList;
            int i, j;
            if (supplierList != null) {

                for (i = 0; i < supplierList.size(); i++) {
                    try {
                        services = supplierList.get(i);
                        serviceList = Message.split(services, Message.FIELD_SEPARATE);
                        agentName = serviceList.get(0);
                        if (agentName.contains("TrainAgent")) {
                %>
                <table  border="1">
                    <tr>
                        <td valign="top"><p align="center"><strong><font color="#FF0000" size="2"><b>DANH SÁCH VÉ TÀU </b></font></strong></p>
                            <p align="center">&nbsp;</p>
                            <table border="1">
                                <tr>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Mã tàu </strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Mã vé tàu </strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nơi đi </strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nới đến </strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đi </strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đến</strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Loại ghế</strong></font></td>
                                    
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font size="1"><strong><font color="#FFFFFF" size="2">Giá vé </font></strong></font></td>
                                    
                                </tr>
                                <%
                                try {


                                    if (serviceList != null && serviceList.size() > 9) {
                                        log.info("FOR TrainAgent: " + services);
                                        out.println("<form action=\"bookTrain.jsp\" method=\"get\" id=\"form" + i + "\"> ");
                                        out.println("<input type=\"hidden\" name=\"agentName\" value=\"" + agentName + "\"/>");
                                        out.println("<input type=\"hidden\" name=\"msg\" value=\"" + services + "\"/>");
                                        log.info("---------------------------------");
                                %>
                                <tr bgcolor="#FFFFFF">
                                    
                                    
                                    <td width="52" align="center" valign="middle"><%=serviceList.get(1)%></td>
                                    <td width="71" align="center" valign="middle"><%=serviceList.get(2)%></td>
                                    <td width="60" align="center" valign="middle"><%=serviceList.get(3)%></td>
                                    <td width="70" align="center" valign="middle"><%=serviceList.get(4)%></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(5)%></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(6)%></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(7)%></td>
                                    <td width="71" align="center" valign="middle"><%=serviceList.get(8)%></td>
                                    
                                    <td width="52" align="center" valign="middle">
                                    <input type="submit" name="Submit" value="Đặt" /></td>                                    
                                    
                                </tr>
                                <%
                                        out.println("</form>");
                                    }
                                } catch (Exception e) {
                                    log.error(e);
                                }
                                %>
                            </table>
                        </td>
                    </tr>
                </table>
                <%
                            } else if (agentName.contains("HotelAgent")) {//HotelAgent

                                try {
                                    String supplier, hotelName, address;
                                    if (serviceList != null) {
                                        supplier = serviceList.get(0);
                                        hotelName = serviceList.get(6);
                                        address = serviceList.get(1);
                                        log.info("[HOTEL INFO] supplier: " + supplier + " hotelName: " + hotelName + " address: " + address);
                                        out.println("<form action=\"bookRoom.jsp\" method=\"get\" id=\"form" + i + "\"> ");
                                        out.println("" +//<div style=\"padding-left: 18px;\">
                                                "<div align=\"left\" style=\"border-bottom: 2px solid rgb(204, 204, 204); padding-bottom: 5px; padding-top: 5px; width: 99%; " +
                                                "margin-right: 5px; clear: both;\">" +
                                                "<div align=\"left\" style=\"overflow: hidden; padding-left: 0px;\">" +
                                                "<div style=\"padding: 5px 0px; text-transform: uppercase;\">");
                                        out.println("<a href=\"#\" onClick=\"\"><h1>" +
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
                                        out.println("<li>Kh&aacute;ch s&#7841;n: " + hotelName + "</li>");
                                        out.println("<li>&#272;&#7883;a ch&#7881;: " + address + "</li>");
                                        out.println("<li>" + serviceList.get(8) + " sao</li>");
                                        out.println("<li>V&#7883; tr&iacute;: " + serviceList.get(7) + "</li>");
                                        out.println("<li>Các tiện ích: " + serviceList.get(12) + "</li>");
                                        //*/
                                        out.println("</ul>");
                %>
                <table width="250" border="1">
                    <tr>
                        <td>Lo&#7841;i ph&ograve;ng</td>
                        <td>Gi&aacute;</td>
                        <td>&#272;&#417;n v&#7883;</td>
                    </tr>
                    <%
                            int k = 9;
                            try {
                                for (k = 9; k < size;) {
                    %>
                    <tr>
                        <td><%=serviceList.get(k)%></td>
                        <td><%=serviceList.get(k + 1)%></td>
                        <td><%=serviceList.get(k + 2)%></td>
                    </tr>
                    <%
                                    k += 4;

                                }
                            } catch (Exception e) {
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
                                    out.println("<input type=\"submit\" name=\"submit\" value=\"&#272;&#7863;t\"/>");
                                    out.println("</div><div id=\"B2FVNw_3D_3D\" style=\"overflow: hidden; padding-top: 3px; padding-bottom: 10px; display: none; clear: both;\">" +
                                            "</div></div></div>");
                                    out.println("</form>");

                                }
                            } catch (Exception e) {
                                log.error(e);
                            }

                        }
                    } catch (Exception e) {
                        log.error(e);
                    //return;
                    }
                }


            }%>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>


