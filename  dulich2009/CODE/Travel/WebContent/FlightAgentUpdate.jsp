<%@ page language="java" import="java.util.*"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Message" %>
<%@ page language="java" import="org.apache.log4j.Logger"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            <!--
            .style1 {font-size: 18px;
                font-weight: bold;
                color: #000000;
            }
            -->
        </style>
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
            Logger log = Logger.getLogger("flightAgentUpdate.jsp");
            String isValidate = (String) session.getAttribute("isValidate");
            if (!"true".equals(isValidate)) {
                %>
                <jsp:forward page="/login.jsp" /> 
                <%                } else {
                    String result = (String) request.getAttribute("result");
                    result = "M_VN219_B4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&13.0#_&#_&M_VN219_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&89.0#_&#_&M_VN219_E4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&67.0#_&#_&M_VN217_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN217@_&Noi Bai-Ha Noi@_&11:00,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&13:00,Cung ngay@_&VN217@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&76.0#_&#_&#_&M_VN219_B5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&4.0#_&#_&M_VN219_E1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&100.0#_&#_&M_VN213_B5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&14.0#_&#_&M_VN219_E2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&60.0#_&#_&M_VN217_E5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN217@_&Noi Bai-Ha Noi@_&11:00,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&13:00,Cung ngay@_&VN217@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&82.0#_&#_&M_VN219_E5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&107.0#_&#_&M_BL805_E1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL797@_&Noi Bai-Ha Noi@_&16:20,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&18:20,Cung ngay@_&BL797@_&Boeing737@_&economy@_&1300000.0VND@_&100.0@_&28.0#_&#_&#_&M_BL791_E2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&35.0#_&#_&M_BL805_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL797@_&Noi Bai-Ha Noi@_&16:20,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&18:20,Cung ngay@_&BL797@_&Boeing737@_&economy@_&1300000.0VND@_&100.0@_&35.0#_&#_&M_BL791_E5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&45.0#_&#_&M_BL791_E4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&35.0#_&#_&M_VN213_B4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&16.0#_&#_&M_VN213_E1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&75.0#_&#_&M_VN219_B1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&12.0#_&#_&M_BL805_E4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL797@_&Noi Bai-Ha Noi@_&16:20,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&18:20,Cung ngay@_&BL797@_&Boeing737@_&economy@_&1300000.0VND@_&100.0@_&33.0#_&#_&M_BL791_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1200000.0VND@_&100.0@_&15.0#_&M_BL791_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&15.0#_&#_&M_VN213_B1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2000000.0VND@_&30.0@_&7.0#_&M_VN213_B1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&7.0#_&#_&M_BL805_E2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL797@_&Noi Bai-Ha Noi@_&16:20,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&18:20,Cung ngay@_&BL797@_&Boeing737@_&economy@_&1300000.0VND@_&100.0@_&24.0#_&#_&M_VN219_B2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&7.0#_&#_&M_VN217_E4_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN217@_&Noi Bai-Ha Noi@_&11:00,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&13:00,Cung ngay@_&VN217@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&85.0#_&#_&M_BL791_E1_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL791@_&Noi Bai-Ha Noi@_&09:10,2010-02-01@_&Tan Son Nhat-Ho Chi Minh@_&11:10,Cung ngay@_&BL791@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&20.0#_&#_&M_VN213_B2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&8:00,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&17.0#_&#_&M_BL799_E3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL799@_&Noi Bai-Ha Noi@_&14:30,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&16:30,Cung ngay@_&BL799@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&20.0#_&#_&M_BL805_E5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL797@_&Noi Bai-Ha Noi@_&16:20,2010-02-05@_&Tan Son Nhat-Ho Chi Minh@_&18:20,Cung ngay@_&BL797@_&Boeing737@_&economy@_&1300000.0VND@_&100.0@_&32.0#_&#_&M_VN213_B3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN213@_&Noi Bai-Ha Noi@_&08:00,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&10:00,Cung ngay@_&VN213@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&5.0#_&#_&M_VN217_E2_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN217@_&Noi Bai-Ha Noi@_&11:00,2010-02-02@_&Tan Son Nhat-Ho Chi Minh@_&13:00,Cung ngay@_&VN217@_&Boeing777@_&economy@_&1600000.0VND@_&250.0@_&98.0#_&#_&M_VN219_B3_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Vietnam Airlines@_&VN219@_&Noi Bai-Ha Noi@_&13:00,2010-02-03@_&Tan Son Nhat-Ho Chi Minh@_&15:00,Cung ngay@_&VN219@_&Boeing777@_&business@_&2050000.0VND@_&30.0@_&8.0#_&#_&M_BL799_E5_2@_&ANZ-73 Tay Son,Ha Noi-anz@gmail.com-0438945835-0438903704@_&Pacific Airlines@_&BL799@_&Noi Bai-Ha Noi@_&14:30,2010-02-04@_&Tan Son Nhat-Ho Chi Minh@_&16:30,Cung ngay@_&BL799@_&AirbusA320@_&economy@_&1300000.0VND@_&100.0@_&28.0#_&#_&";
                    ArrayList<String> supplierList = Message.split(result, Message.OBJECT_SEPARATE);
                    String msg = "";
                    ArrayList<String> supplier;
                    ArrayList<String> contact;
                    if (supplierList != null && supplierList.size() > 0) {
                        int i, size = supplierList.size();
                        //for (i = 0; i < size; i++) 
                        {

                            msg = supplierList.get(0);
                            log.debug("RESULT: " + msg);
                            supplier = Message.split(msg, Message.FIELD_SEPARATE);
                            if (supplier != null && supplier.size() > 12) {
                                try {
                                    contact = Message.split(supplier.get(1), "-");
                                    log.info("CONTACT: " + contact);

                %>
                <table width="645"  border="0">
                    <tr>
                        <td><table width="637" border="0">
                                <tr>
                                    <td  height="30" align="center" valign="top"><span class="style1">Th&ocirc;ng tin &#273;&#7841;i l&yacute; b&aacute;n v&eacute; m&aacute;y bay</span></td>
                                </tr>
                                <tr>
                                    <td  height="32" valign="top" >T&ecirc;n &#273;&#7841;i l&yacute;: <%=contact.get(0)%></td>
                                </tr>
                                <tr>
                                    <td  height="32" valign="top" ><table width="627" border="0">
                                            <tr>
                                                <td >&#272;&#7883;a ch&#7881;: <%=contact.get(1)%></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                    </table></td>
                                </tr>
                                <tr>
                                    <td  height="32" valign="top" ><table width="626" border="0">
                                            <tr>
                                              <td width="143" >Email: <%=contact.get(2)%></td>
                                                <td width="21" >&nbsp;</td>
                                                <td width="24">Fax: </td>
                                                <td width="114" ><%=contact.get(3)%></td>
                                              <td width="93">S&#7889; &#273;i&#7879;n tho&#7841;i:</td>
                                                <td width="205"> <%=contact.get(4)%></td>
                                            </tr>
                                    </table></td>
                                </tr>
                        </table></td>
                    </tr>
                    <tr>
                        <td valign="middle" align="center"></td>
                    </tr>
                    <tr>
                        <td><table width="632"  border="1">
                                <tr>
                                    <td width="43"  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>H&atilde;ng h&agrave;ng kh&ocirc;ng </strong></font></td>
                                    <td width="50"  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>S&#7889; hi&#7879;u chuy&#7871;n bay </strong></font></td>
                                    <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>N&#417;i &#273;i </strong></font></td>
                                    <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ng&agrave;y gi&#7901; &#273;i </strong></font></td>
                                    <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>N&#417;i &#273;&#7871;n </strong></font></td>
                                    <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ng&agrave;y gi&#7901; &#273;&#7871;n</strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Lo&#7841;i m&aacute;y bay</strong></font></td>
                                    <td align="center" valign="middle" bgcolor="#3366FF"><table width="200" border="0">
                                            <tr>
                                                <td align="center" valign="middle"><strong><font color="#FFFFFF" size="2">Th&ocirc;ng tin chi ti&#7871;t </font></strong></td>
                                            </tr>
                                            <tr>
                                                <td><table border="1">
                                                        <tr>
                                                            <td align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>H&#7841;ng v&eacute; </strong></font></td>
                                                            <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Gi&aacute; v&eacute; </strong></font></td>
                                                            <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>T&#7893;ng s&#7889; v&eacute; </strong></font></td>
                                                            <td  align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>S&#7889; l&#432;&#7907;ng &#273;&atilde; &#273;&#7863;t </strong></font> </td>
                                                        </tr>
                                                </table></td>
                                            </tr>
                                    </table></td>
                                    <td  align="center" valign="middle" bgcolor="#3366FF"><strong><font color="#FFFFFF" size="2"><b>T&aacute;c v&#7909; </b></font></strong></td>
                                </tr>
                                <%for (i = 0; i < size; i++) {

                            msg = supplierList.get(0);                            
                            supplier = Message.split(msg, Message.FIELD_SEPARATE);
                            %>
                                <tr bgcolor="#FFFFFF">
                                    <td align="center" valign="middle"><%=supplier.get(2)%></td>
                                    <td wi="60" align="center" valign="middle" ><%=supplier.get(3)%></td>
                                    <td width="30" align="center" valign="middle"><%=supplier.get(4)%></td>
                                    <td width="42" align="center" valign="middle" ><%=supplier.get(5)%></td>
                                    <td width= "32" align="center" valign="middle"><%=supplier.get(6)%></td>
                                    <td width="43" align="center" valign="middle"><%=supplier.get(7)%></td>
                                    <td width="40" align="center" valign="middle"><%=supplier.get(9)%></td>
                                    <td width="218" align="center" valign="middle"><table width="200" border="0">
                                            <tr>
                                                <td><table width="200" border="1">
                                                        <tr>
                                                            <td width="58" align="center" valign="middle"><%=supplier.get(10)%></td>
                                                            <td width="58" align="center" valign="middle"><%=supplier.get(11)%></td>
                                                            <td width="58" align="center" valign="middle"><%=supplier.get(12)%></td>
                                                            <td width="58" align="center" valign="middle"><%=supplier.get(13)%></td>
                                                        </tr>
                                                </table></td>
                                                <td width="58" align="center" valign="middle"></td>
                                                <td width="58" align="center" valign="middle"></td>
                                                <td width="58" align="center" valign="middle"></td>
                                            </tr>
                                    </table></td>
                                    <td width="76" align="center" valign="middle">Xem chi ti&#7871;t </td>
                                </tr>
                                <%}
                                    %>
                        </table></td>
                    </tr>
                </table>
                <%        } catch (Exception e) {
                                log.error(e);
                            }
                        }
                    }
                }
            }
                %>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>