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
                <input type="hidden" name="protocol" value="<%=Protocol.FLIGHT_RES%>" />
                <%
        Logger log = Logger.getLogger("bookFlight.jsp");
        String agentName = request.getParameter("agentName");
        String msg = request.getParameter("msg");
        if(msg == null){
            log.error("No Airplain selected yet!");
            // FOR TEST
            msg = "VN213_20100201_E  Infomation  Vietnam Airlines  VN213  Noi Bai-Ha Noi  8:00,2010-02-01  Tan Son Nhat-Ho Chi Minh  10:00,Cung ngay  Boeing777  economy  1500000.0VND ";
            //return;
        }
        ArrayList<String> serviceList = Message.split(msg, Message.FIELD_SEPARATE);

        out.println("<input type=\"hidden\" name=\"agentName\" value=\"" + agentName + "\"/>");
        out.println("<input type=\"hidden\" name=\"id\" value=\"" + serviceList.get(0) + "\"/>");
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
                                  tin chuy&#7871;n bay </div>
                            </div></td>
                          </tr>
                          <tr>
                            <td width="34%" height="39" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">&#272;&#7841;i l&yacute; b&aacute;n v&eacute; m&aacute;y bay:</div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(1)%></td>
                            - </tr>
                          <tr>
                            <td width="34%" height="39" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">H&atilde;ng h&agrave;ng kh&ocirc;ng </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(2)%></td>
                          </tr>
                          <tr>
                            <td width="34%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">S&#7889; hi&#7879;u chuy&#7871;n bay </div></td>
                            <td width="62%" bgcolor="#FFFFFF"><%=serviceList.get(3)%> - <%=serviceList.get(8) %></td>
                          </tr>
                          <tr>
                            <td height="35" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center"><span class="style4">Xu&#7845;t ph&aacute;t</span><br />
                            </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(5) %> - <%=serviceList.get(4) %> </td>
                          </tr>
                          <tr>
                            <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">&#272;&#7871;n</div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(7) %> - <%=serviceList.get(6) %></td>
                          </tr>
                          <tr>
                            <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">Lo&#7841;i v&eacute; </div></td>
                            <td bgcolor="#FFFFFF"><%=serviceList.get(9) %> , Gi&aacute;: <%=serviceList.get(10)%> </td>
                          </tr>
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
                              <td width="173" align="left">H&#7885; t&ecirc;n </td>
                              <td width="882" align="left" valign="top"><input name="fullName" class="form" id="fullName" size="30" type="text" />
                                Nam/N&#7919;:
                                <select name="sex" class="form_date" id="select">
                                  <option value="male" selected="selected">Nam </option>
                                  <option value="female">N&#7919; </option>
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
                            <tr align="left" valign="top">
                              <td width="173">T&ecirc;n n&#432;&#7899;c </td>
                              <td valign="top"><select name="country" class="form_date" id="country">
                                  <option value="" selected="selected">Ch&#7885;n t&ecirc;n n&#432;&#7899;c</option>
                                  <option value="Albania">Albania </option>
                                  <option value="Argentina">Argentina </option>
                                  <option value="Australia">Australia </option>
                                  <option value="Austria">Austria </option>
                                  <option value="Bangladesh">Bangladesh </option>
                                  <option value="Belarus">Belarus </option>
                                  <option value="Belgium">Belgium </option>
                                  <option value="Bolivia">Bolivia </option>
                                  <option value="Brazil">Brazil </option>
                                  <option value="Bulgaria">Bulgaria </option>
                                  <option value="Canada">Canada </option>
                                  <option value="Chile">Chile </option>
                                  <option value="China">China </option>
                                  <option value="Colombia">Colombia </option>
                                  <option value="Czech Republic">Czech Republic</option>
                                  <option value="Denmark">Denmark </option>
                                  <option value="Ecuador">Ecuador </option>
                                  <option value="Estonia">Estonia </option>
                                  <option value="Finland">Finland </option>
                                  <option value="France">France </option>
                                  <option value="French Guiana">French Guiana </option>
                                  <option value="Georgia">Georgia </option>
                                  <option value="Germany">Germany </option>
                                  <option value="Greece">Greece </option>
                                  <option value="HongKong">HongKong</option>
                                  <option value="Hungary">Hungary </option>
                                  <option value="Iceland ">Iceland </option>
                                  <option value="India">India </option>
                                  <option value="Indonesia">Indonesia </option>
                                  <option value="Ireland">Ireland </option>
                                  <option value="Italy">Italy </option>
                                  <option value="Japan">Japan </option>
                                  <option value="Latvia">Latvia </option>
                                  <option value="Lithuania">Lithuania </option>
                                  <option value="Luxembourg">Luxembourg </option>
                                  <option value="Malaysia">Malaysia </option>
                                  <option value="Mexico">Mexico </option>
                                  <option value="Netherlands">Netherlands </option>
                                  <option value="North Korea">North Korea </option>
                                  <option value="Norway">Norway </option>
                                  <option value="Paraguay">Paraguay </option>
                                  <option value="Peru">Peru </option>
                                  <option value="Poland">Poland </option>
                                  <option value="Portugal">Portugal </option>
                                  <option value="Romania">Romania </option>
                                  <option value="Russia">Russia </option>
                                  <option value="Slovakia">Slovakia </option>
                                  <option value="South Africa">South Africa </option>
                                  <option value="South Korea">South Korea </option>
                                  <option value="Spain">Spain </option>
                                  <option value="Suriname">Suriname </option>
                                  <option value="Sweden">Sweden </option>
                                  <option value="Switzerland">Switzerland </option>
                                  <option value="Turkey">Turkey </option>
                                  <option value="Ukraine">Ukraine </option>
                                  <option value="United Kingdom">United Kingdom </option>
                                  <option value="United States">United States </option>
                                  <option value="Uruguay">Uruguay </option>
                                  <option value="Venezuela">Venezuela </option>
                                  <option value="Vietnam">Vietnam</option>
                              </select></td>
                            </tr>
                            <tr>
                              <td align="left">&nbsp;</td>
                              <td align="center" valign="top"><input name="datvemaybay" id="datvemaybay" value="   Đặt   " type="submit"></td>
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