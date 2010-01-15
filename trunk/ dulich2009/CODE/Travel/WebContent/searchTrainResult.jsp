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
            Logger log = Logger.getLogger("searchResult.jsp");
            String result = (String) request.getAttribute("result");

            if(result == null || "".equals(result)){
                log.error("String received from UserServlet " + result);
                out.println("There is no result to deplay");
                return;
            }
            //result = "FlightAgent  VN213_20100201_E  Information  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN217_20100201_E  Vietnam Airlines  VN217  Noi Bai-Ha Noi  11:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  13:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&VN219_20100201_E  Vietnam Airlines  VN219  Noi Bai-Ha Noi  13:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  15:00,Cung ngay  Boeing777  economy  1500000.0VND  #_&#_&";
            String agentName = result.substring(0, result.indexOf(Message.FIELD_SEPARATE));
            result = result.replace(agentName, "");
      //      log.info(result);
            String services;
            ArrayList<String> supplierList = Message.split(result, Message.OBJECT_SEPARATE);
            ArrayList<String> serviceList;
            int i, j;
            if (supplierList != null) {

                log.info("SIZE: " + result);
                for (i = 0; i < supplierList.size(); i++) {
                    try {
                        services = supplierList.get(i);
                        serviceList = Message.split(services, Message.FIELD_SEPARATE);
                        if (serviceList != null && serviceList.size() > 5) {
                            log.info(services);
                             out.println("<form action=\"bookTrain.jsp\" method=\"get\" id=\"form" + i + "\"> ");
                             out.println("<input type=\"hidden\" name=\"agentName\" value=\"" + agentName + "\"/>");
                             out.println("<input type=\"hidden\" name=\"msg\" value=\"" + services + "\"/>");
                                %>
                                <tr bgcolor="#FFFFFF">
                                    
                                    <td width="71" align="center" valign="middle"><%=serviceList.get(0) %></td>                                    
                                    <td width="52" align="center" valign="middle"><%=serviceList.get(1) %></td>
                                    <td width="71" align="center" valign="middle"><%=serviceList.get(2) %></td>
                                    <td width="60" align="center" valign="middle"><%=serviceList.get(3) %></td>
                                    <td width="70" align="center" valign="middle"><%=serviceList.get(4) %></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(5) %></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(6) %></td>
                                    <td width="69" align="center" valign="middle"><%=serviceList.get(7) %></td>
                                    
									
                                    <td width="52" align="center" valign="middle">
                                  <input type="submit" name="Submit" value="Đặt" /></td>                                    
                                    
                                </tr>
                                <%
                            out.println("</form>");    
                        }


                    } catch (Exception e) {
                        log.error(e);
                        return;
                    }
                }


            }


                                %>
                            </table>
                        </td>
                    </tr>
                </table>
                
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>


