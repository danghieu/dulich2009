<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>

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
                
				<div align="center">
				<h1 style="color:#000000"><strong>Tìm Kiếm Tour</strong></h1>
                <form id="form1" name="form1" method="get" action="UserServlet">
                    <input type="hidden" name="protocol" value="<%=Protocol.TOURSERVICE_AVAIL%>" />
                    <table width="233" border="0">
                        <tr>
                            <td width="60">Xuất phát </td>
                            <td width="124">
                            <input type="text" name="beginPoit" /></td>
                        </tr>
                        <tr>
                            <td>Nơi đến </td>
                            <td><input type="text" name="endPoit" /></td>
                        </tr>
                        <tr>
                            <td>Ngày đi </td>
                            <td><input type="text" name="beginTime" /></td>
                        </tr>
                        <tr>
                            <td>Ngày về </td>
                            <td><input type="text" name="endTime" /></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>
                            <input type="submit" name="Submit" value="Tìm kiếm" /></td>
                        </tr>
                    </table>
                    
                </form>
				</div>
                <p>&nbsp;          </p>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>