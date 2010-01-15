<%-- 
    Document   : bookTrain
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
              <form action="UserServlet" method="post">
                <input type="hidden" name="protocol" value="<%=Protocol.TRAIN_RES%>" />
                <%
        Logger log = Logger.getLogger("bookTrain.jsp");
        String agentName = request.getParameter("agentName");
        String msg = request.getParameter("msg");
        if(msg == null){
            log.error("No train selected yet!");
            // FOR TEST
            msg="TrainAgent@_&SE1@_&HN_PL_SE1_1@_&Ha Noi@_&Phu Ly@_&2009-12-29,19:00:00@_&2009-12-29,20:02:00@_&Ghe mem@_&24000.0 VND@_&@_&";
      //      msg = "VN213_20100201_E  Infomation  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND ";
            //return;
        }
        ArrayList<String> serviceList = Message.split(msg, Message.FIELD_SEPARATE);

        out.println("<input type=\"hidden\" name=\"agentName\" value=\"" + agentName + "\"/>");
        out.println("<input type=\"hidden\" name=\"id\" value=\"" + serviceList.get(2) + "\"/>");
        out.println("<input type=\"hidden\" name=\"agent\" value=\"" + serviceList.get(1) + "\"/>");
        %>
                <script>

            function view_composer1(ob1) {

                if (document.getElementById(ob1).style.display == "none") {

                    document.getElementById(ob1).style.display = "";

                }

            }
            function isMail1(frm) {

                var selObj = document.getElementById('bietden');

                var selIndex = selObj.selectedIndex;

                var selecteED = selObj.options[selIndex].value;
                if (document.book1.fullName.value == "") {

                    alert ("B&#7841;n h&atilde;y nh&#7853;p t&ecirc;n c&#7911;a b&#7841;n !");

                    document.book1.fullName.focus();

                    return false;

                }

                if (document.book1.email.value == "") {

                    alert ("B&#7841;n h&atilde;y nh&#7853;p e-mail!");

                    document.book1.email.focus();

                    return false;

                } else {

                    var check = emailcheck(document.book1.email.value)

                    if (!check) {

                        alert("Sai &#273;&#7883;nh d&#7841;ng E-mail !");

                        document.book1.email.focus();

                        return false;

                    }

                }

                if (document.book1.phoneNumber.value == "") {

                    alert ("B&#7841;n h&atilde;y nh&#7853;p &#272;i&#7879;n tho&#7841;i c&#7911;a b&#7841;n !");

                    document.book1.phoneNumber.focus();

                    return false;

                }

            }

        

            function emailcheck(mailValue) {

                var chk = false;

                for(i=0; i<mailValue.length; i++) {

                    if(mailValue.charAt(i)=='@') {

                        chk = true;

                        break;

                    }

                }

                return chk;

            }



              </script>
                <table border="1">
                  <tr align="center" valign="top">
                    <td><table width="621" border="0" cellpadding="5" cellspacing="0" bgcolor="#e3e1b7" 794px; height:="height:" 291px;">
                        <tbody>
                          <tr bgcolor="#ccc87b">
                            <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399FF"><div align="left" class="style1">
                                <div align="center" class="style1 style2">Th&ocirc;ng 
                                  tin đặt vé tàu </div>
                            </div></td>
                          </tr>
                          <tr>
                           
                            - </tr>
                          <tr>
                      <td width="34%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">S&#7889; hi&#7879;u chuy&#7871;n tàu </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(1)%> </td>
                          </tr>
                          <tr>
                            <td width="34%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">Mã vé tàu </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(2)%> </td>
                          </tr>
                          <tr>
                            <td height="35" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center"><span class="style4">Xu&#7845;t ph&aacute;t</span><br />
                            </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(3) %> - <%=serviceList.get(5) %> </td>
                          </tr>
                          <tr>
                            <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">&#272;&#7871;n</div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(4) %> - <%=serviceList.get(6) %></td>
                          </tr>
                          <tr>
                            <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">Lo&#7841;i v&eacute; </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(7) %> , Gi&aacute;: <%=serviceList.get(8)%> </td>
                          </tr>`
                          <tr>
                            <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">S&#7889; l&#432;&#7907;ng v&eacute; </div></td>
                            <td bgcolor="#FFFFFF"><input type="text" name="booknumber" size="12" />
                            </td>
                          </tr>
                        </tbody>
                    </table></td>
                  </tr>
                  <tr>
                    <td valign="middle" bgcolor="#3399FF"><div align="center" class="style1 style2">Th&ocirc;ng tin kh&aacute;ch h&agrave;ng</div></td>
                  </tr>
                  <tr align="center" valign="top">
                    <td colspan="2"><div class="small" id="composer_div">
                        <table class="normal10" width="56%" border="0" cellpadding="2" cellspacing="1">
                          <tbody>
                            <tr>
                              <td align="left">H&#7885; t&ecirc;n </td>
                              <td align="left" valign="top"><input name="fullName" class="form" id="fullName" size="30" type="text" />
                                                             </td>
                              <td align="left">Tuổi</td>
                              <td align="left" valign="top"><input name="age" class="form" id="age" size="5" type="text" /></td>
                            </tr>
                            <tr>
							<td align="left">Nghề nghiệp</td>
							<td align="left"><select name="job" class="form" id="job" >
                                                      
                                                      <option selected="selected" value="teacher"> Giáo viên </option>
                                                      <option selected="selected" value="student"> Sinh viên </option>
                                                      <option selected="selected" value="doctor"> Bác sỹ </option>
                                                      <option selected="selected" value="engineering"> Kỹ sư </option>
                                                                                                        </select></td>
                              
                              
                            </tr>
							     <tr>
                              <td align="left">Email*</td>
                              <td align="left" valign="top"><input name="email" class="form" id="email" size="30" type="text" /></td>
                            </tr>
                            <tr>
                              <td align="left">&#272;i&#7879;n tho&#7841;i </td>
                              <td align="left" valign="top"><input name="phoneNumber" class="form" size="30" id="phoneNumber" type="text" /></td>
                            </tr>
                            <tr>
                              <td align="left">&#272;&#7883;a ch&#7881; </td>
                              <td align="left" valign="top"><input name="specificAddress" class="form" size="30" id="specificAddress" type="text" /></td>
                            </tr>
                            <tr>
                              <td align="left">Th&agrave;nh ph&#7889; </td>
                              <td align="left" valign="top"><input name="city" class="form" size="30" id="city" type="text" /></td>
                            </tr>
                            
                            <tr>
                              <td align="left">&nbsp;</td>
                              <td align="center" valign="top"><input name="datvetau" id="datvetau" value="  Đặt   " type="submit"></td>
                            </tr>
                          </tbody>
                        </table>
                    </div>
                        <span style="padding: 5px 0px 0px 10px;">
                        <input name="action" id="action" value="quocte" type="hidden" />
                      </span></td>
                  </tr>
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