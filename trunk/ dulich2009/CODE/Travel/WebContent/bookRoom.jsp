<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            .hidden {
                display:none;
                float:left;
            }
        </style>
        <script type="text/javascript">
            function empty(){
                form1.elements["quantity"].value = ''; 
            }
            function choseRoomType()
            {
                var x=document.getElementById("roomType");
                var lbl = document.getElementById("lblRoomType");
                var bedType = document.getElementById("bedType");
                var quantity = document.getElementById("quantity");
                if(x.selectedIndex == 0) {	
                
                    lbl.style.display="none";
                    lbl.innerHTML="S? l??ng";	
                    bedType.style.display="none";
                    quantity.style.display="none";
                }
                if(x.selectedIndex == 1) {
                
                    lbl.style.display="inline";
                    lbl.innerHTML ="Lo?i gi??ng";
                    bedType.style.display="inline";
                    quantity.style.display="none";

                }
                if(x.selectedIndex == 2||x.selectedIndex == 3) {	
                
                    lbl.style.display="inline";
                    lbl.innerHTML="S? l??ng";	
                    bedType.style.display="none";
                    quantity.style.display="inline";
                }

            }
        </script>
    </head>
    <body>
        <form action="UserServlet" method="post" name="order" id="form1">
            <input type="hidden" name="protocol" value="<%=Protocol.HOTEL_RES%>">
            <%
            Logger log = Logger.getLogger("bookRoom.jsp");
            String supplier = request.getParameter("supplier");
            String hotelName = request.getParameter("hotelName");
            String services = request.getParameter("services");
            String address = request.getParameter("address");            
            String protocol = request.getParameter("protocol");
            // expected protocol:
            protocol = Protocol.HOTEL_AVAIL;
            if(protocol == null || "".equals(protocol)){
                log.error("INVALID INPUT");
                %>
                <jsp:forward page="/pages/hotel/searchRoom.jsp" /> 
                <%
                return;
            }
            log.info("supplier: " + supplier + "services: " + services);
            %>
            <input name="agentName" value="<%=supplier%>" type="hidden">
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
                    
                    
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tbody><tr> 
                                <td class="TitleLevel2" height="30" align="center">&nbsp;&nbsp;<img src="dat_files/node.jpg" width="10" align="absmiddle" height="10"><font color="#0000ff">&nbsp; 
                                ĐẶT PH&Ograve;NG KH&Aacute;CH SẠN</font> </td>
                            </tr>
                            
                            <tr> 
                                <td valign="top" align="left"><table border="0" cellpadding="0" cellspacing="0">
                                        <tbody><tr> 
                                                <td valign="top" align="left">
                                                    
                                                    <table border="0" cellpadding="0" cellspacing="0">
                                                        <tbody><tr> 
                                                                <td valign="top" align="center"><table width="442" border="0" cellpadding="5" cellspacing="0"  bgcolor="#e3e1b7">
                                                                        <tbody><tr bgcolor="#ccc87b"> 
                                                                                <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399CC"><div align="left" class="style1">
                                                                                        <div align="center">Thng 
                                                                                        tin người sử dụng</div>
                                                                                </div></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td width="30%" align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Khách 
                                                                                hàng (<font color="#ff0000">*</font>)</div></td>
                                                                                <td bgcolor="#FFFFFF"><input name="fullName" id="fullName" size="35" type="text"></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">CMND (<font color="#ff0000">*</font>)<br>
                                                                                </div></td>
                                                                                <td bgcolor="#FFFFFF"><input name="identityCard" id="identityCard" size="35" type="text" /></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">E-mail (<font color="#ff0000">*</font>)</div></td>
                                                                                <td bgcolor="#FFFFFF"><input name="email" id="email" size="35" type="text"></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Số điện thoại(<font color="#ff0000">*</font>)</div></td>
                                                                                <td bgcolor="#FFFFFF"><input name="phoneNumber" id="phoneNumber" size="35" type="text"></td>
                                                                            </tr>
                                                                            
                                                                            
                                                                            
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Fax 
                                                                                </div></td>
                                                                                <td bgcolor="#FFFFFF"><input name="txtFax" id="txtFax" size="35" type="text"></td>
                                                                            </tr>
                                                                </tbody></table></td>
                                                            </tr>
                                                            <tr> 
                                                                <td height="5"><img src="dat_files/none.htm" width="1" height="1"></td>
                                                            </tr>
                                                            <tr> 
                                                                <td valign="top" align="center"><table width="100%" bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0">
                                                                        <tbody><tr bgcolor="#ccc87b"> 
                                                                                <td width="30%" align="right" valign="middle" bgcolor="#FFFFFF"><div align="right"><strong>Thng 
                                                                                tin khách sạn</strong></div></td>
                                                                                
                                                                            </tr>                  
                                                                            <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Tên 
                                                                        khách sạn </div></td>
                                                                        <td bgcolor="#FFFFFF"><b><%=hotelName%> 
                                                                                <input name="hotelName" id="txtHotelName" value="<%=hotelName%>" type="hidden">
                                                                        </b></td>
                                                                    </tr>
																	<tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Địa 
                                                                                chỉ <br>
                                                                        </div></td>
                                                                        <td bgcolor="#FFFFFF"><b><%=address%>
                                                                            <input name="address" id="hotelAddress" value="<%=address%>" type="hidden">
                                                                        </b></td>
                                                                    </tr>
                                                                            
                                                                            <tr> 
                                                                                <td align="right" valign="top" bgcolor="#FFFFFF"><div align="right">Loại phòng </div></td>
                                                                                <td bgcolor="#FFFFFF" COLSPAN=2> <select name="roomType" size="1" id="roomType" onchange="choseRoomType()" >
                                                                                        <option value="bedding" selected="selected">Loại phòng</option>
                                                                                        <option value="livingRom">Phng ngủ - Livingroom</option>
                                                                                        <option value="meetingRoom">Phng họp - Meetingroom</option>
                                                                                        <option value="partyRoom">Phng tiệc - PartyRoom</option>
                                                                                        
                                                                                    </select>
                                                                                    số lượng 
                                                                                <input  name="quantity" id="quantity" size="10" type="text" onclick="empty()"/>                          </td>
                                                                            </tr>
                                                                            
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Ngày đến </div></td>
                                                                                <td bgcolor="#FFFFFF">
                                                                                <input name="fromdate" id="fromdate" size="35" type="text" /></td>
                                                                            </tr>
                                                                            <tr> 
                                                                                <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Ngy 
                                                                                về </div></td>
                                                                                <td bgcolor="#FFFFFF">
                                                                                <input name="todate" id="todate" size="35" type="text" /></td>
                                                                            </tr>
                                                                            
                                                                            <tr> 
                                                                                <td align="right" valign="top" bgcolor="#FFFFFF"><div align="right">Yu 
                                                                                cầu khác </div></td>
                                                                                <td bgcolor="#FFFFFF"><textarea name="txtComments" cols="30" rows="6" id="txtComments"></textarea></td>
                                                                            </tr>
                                                                </tbody></table></td>
                                                            </tr>
                                                            <tr> 
                                                                <td height="5"><img src="dat_files/none.htm" width="1" height="1"></td>
                                                            </tr>
                                                            <tr> 
                                                                <td valign="middle" align="center" height="50"></td>
                                                            </tr>
                                                            <tr> 
                                                                <td valign="top" align="center" height="1"><table width="50%" align="center" border="0" cellpadding="0" cellspacing="0">
                                                                        <tbody><tr> 
                                                                                <td><input name="submit" value="Đặt phòng" type="submit"></td>
                                                                                <td><input name="submit" value="Nhập lại" type="reset"></td>
                                                                            </tr>
                                                                </tbody></table></td>
                                                            </tr>
                                                    </tbody></table>
                                                </td>
                                            </tr>
                                </tbody></table></td>
                            </tr>
                            <tr>
                                <td valign="bottom" height="40"><div align="right">[ <a href="javascript:%20history.go(-1)" class="back_link"><strong>Go 
                                back</strong></a> ]</div></td>
                            </tr>
                    </tbody></table>
                </div>
                <div id="templatemo_bot"></div>
            </div>
            <div id="templatemo_footer"> 
                <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
            </div>
        </form>
    </body>
</html>
