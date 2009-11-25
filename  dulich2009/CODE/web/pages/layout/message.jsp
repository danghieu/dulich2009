<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<% 
String msg = request.getParameter("msg");
request.setAttribute("msg", msg);
String title = request.getParameter("title");
request.setAttribute("title", title);
String url = request.getParameter("url");
request.setAttribute("url", url);
String type = request.getParameter("type");
request.setAttribute("type", type);
String pic = request.getParameter("pic");
pic = "../../img/" + pic;
request.setAttribute("pic", pic);
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><bean:message name="title" /></title>
    <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/style.css'/>" charset="UTF-8"/>
    <html:base/>
  </head>
  <%--<body bgcolor="#D4D0C8" style="margin-top:0;margin-left:0;margin-right:0">--%>
  <body style="margin-top:0;margin-left:0;margin-right:0;background-image: url(../../img/dlg_bg.png);background-repeat: repeat;">    

    <table id="fullheight" width="100%">
      <tr><td>
          <form method="post" name="frmMessage">
            <table width="90%" align="center" border="0">
              <tr><td><img src="<bean:write name="pic" />" width="24" height="24"></td> <td align="left" valign="top"><font face="Arial" size="2" ><bean:message name="msg"/></font></td></tr>
              <tr><td height="10"></td></tr>
              <tr><td align="center" colspan="2" valign="bottom">
                  <input type="hidden" name="url" id="url" value="<bean:write name="url"/>" />
                  <logic:equal name="type" value="yesno">
                    <html:button styleClass="button" property="yes" onclick="closeDialog('yes');"><bean:message key="button.yes" /></html:button>
                    <html:button styleClass="button" property="no" onclick="closeDialog('no');"><bean:message key="button.no" /></html:button>
                  </logic:equal>
                  <logic:equal name="type" value="ok">
                    <html:button styleClass="button" property="ok" onclick="closeDialog('ok');"><bean:message key="button.ok" /></html:button>
                  </logic:equal>
              </td></tr>
          </table></form>
    </td></tr></table>
    <script language="javascript">
    function closeDialog(buttonId){
        window.top.hidePopWin(true, buttonId, document.getElementById("url").value);                  
    }
    </script>
  </body>
</html>
