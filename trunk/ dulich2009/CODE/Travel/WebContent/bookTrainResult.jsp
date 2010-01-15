<%-- 
    Document   : bookTrainResult
    Created on : Jan 8, 2010, 4:52:57 PM
    Author     : Ariang
--%>

<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Message" %>
<%@ page language="java" import="java.util.ArrayList" %>
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
        Logger log = Logger.getLogger("bookTrainResult.jsp");
        String agentName = request.getParameter("agentName");
        String msg = request.getParameter("msg");
        if(msg == null){
            log.error("No train selected yet!");
            // FOR TEST
          //msg= " HN_PL_SE1_138546204@_&Hanh38546204@_&Hanh@_&HN_PL_SE1_1@_&5.0@_&120000.0";

      //      msg="TrainAgent@_&SE1@_&HN_PL_SE1_1@_&Ha Noi@_&Phu Ly@_&2009-12-29,19:00:00@_&2009-12-29,20:02:00@_&Ghe mem@_&24000.0 VND@_&@_&";
      //      msg = "VN213_20100201_E  Infomation  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND ";
            //return;
        }
        //String agentName = msg.substring(0, msg.indexOf(Message.FIELD_SEPARATE));
        ArrayList<String> serviceList = Message.split(msg, Message.FIELD_SEPARATE);

   //     out.println("<input type=\"hidden\" name=\"agentName\" value=\"" + agentName + "\"/>");
    //    out.println("<input type=\"hidden\" name=\"id\" value=\"" + serviceList.get(0) + "\"/>");
   //     out.println("<input type=\"hidden\" name=\"agent\" value=\"" + serviceList.get(1) + "\"/>");
        %>

                <table border="1">
                  <tr align="center" valign="top">
                    <td><table width="621" border="0" cellpadding="5" cellspacing="0" bgcolor="#e3e1b7" 794px; height:="height:" 291px;">
                        <tbody>
                          <tr bgcolor="#ccc87b">
                            <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399FF"><div align="left" class="style1">
                                <div align="center" class="style1 style2">Thông báo k?t qu? ??t vé tàu </div>
                            </div></td>
                          </tr>
                          <tr>
                           
                            - </tr>
                          <tr>
                      <td width="34%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">Mã ??t vé tàu </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(0)%> </td>
                          </tr>
                          <tr>
                            <td width="34%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">Mã an toàn </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(1)%> </td>
                          </tr>
                          <tr>
                            <td height="35" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center"><span class="style4">Tên khách hàng </span><br />
                            </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(2) %></td>
                          </tr>
                          <tr>
                            <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">Mã vé tàu </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(3) %></td>
                          </tr>
                          <tr>
                            <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">S? l??ng ??t </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(4) %> </td>
                          </tr>`
						   <tr>
                            <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">T?ng giá vé </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(5) %></td>
                          </tr>
                          <tr>
                           
                          </tr>
                        </tbody>
                    </table></td>
                  </tr>
                  <tr>
                    <td valign="middle" bgcolor="#3399FF">&nbsp;</td>
                  </tr>
                  <tr align="center" valign="top">
                    <td colspan="2"><span style="padding: 5px 0px 0px 10px;">
  <input name="action" id="action" value="quocte" type="hidden" />
                    </span></td></tr>
                </table>
              </form>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>