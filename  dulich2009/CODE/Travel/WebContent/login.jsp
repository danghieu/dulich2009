<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Language" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
            <!--
            .style2 {font-size: 18px;
                color: #000000;
            }
            .style3 {color: #000000}
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
                
                
                <form id="form1" name="form1" method="post" action="UserServlet">
                    <input type="hidden" name="protocol" value="LOGIN">
                    <table  height="259" border="1">
                        <tr>
                            <td width="770" align="center" valign="top"><table width="456" height="323" border="1">
                                    <tr>
                                        <td width="446" height="25" align="center" valign="middle" bgcolor="#FFFFFF"><span class="style2">Th&ocirc;ng tin &#273;&#259;ng nh&#7853;p </span></td>
                                    </tr>
                                    <tr>
                                        <td height="290" align="center" valign="middle"><p class="style3">Xin vui l&ograve;ng nh&#7853;p &#273;&#7847;y &#273;&#7911; th&ocirc;ng tin </p>
                                            <table width="310" height="101" border="1">
                                                <tr>
                                                    <td width="102">Vai trò </td>
                                                    <td width="192"><select name="type" class="style3" id="loainhacungcap">
                                                            <option selected="selected" value="<%=Language.CUSTOMER%>">Khách hàng</option>
                                                            
                                                            <option selected="selected" value="<%=Language.HOTEL%>">Kh&aacute;ch s&#7841;n</option>
                                                            <option value="<%=Language.FLIGHT%>">&#272;&#7841;i l&yacute; b&aacute;n v&eacute; m&aacute;y bay </option>
                                                            <option value="<%=Language.TRAIN%>">&#272;&#7841;i l&yacute; b&aacute;n v&eacute; t&agrave;u h&#7887;a </option>
                                                            <option selected="selected" value="<%=Language.CONTROLLER%>">Quản trị hệ thống</option>
                                                    </select></td>
                                                </tr>
                                                <tr>
                                                    <td><span class="style3">T&ecirc;n &#273;&#259;ng nh&#7853;p </span></td>
                                                    <td><input name="userName" size="15" maxlength="11" type="text" /></td>
                                                </tr>
                                                <tr>
                                                    <td><span class="style3">M&#7853;t kh&#7849;u </span></td>
                                                    <td><input name="password" size="15" maxlength="11" type="password" /></td>
                                                </tr>
                                            </table>
                                            <p>&nbsp;</p>
                                            <p>&nbsp;</p>
                                            
                                            <label>
                                                <input name="Submit" type="submit" class="style2" value="Đăng nhập" />
                                            </label>
                                            
                                        <p>&nbsp;</p></td>
                                    </tr>
                            </table></td>
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