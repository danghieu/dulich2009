<%-- 
    Document   : searchRoomResult
    Created on : Dec 7, 2009, 10:18:37 AM
    Author     : D05CNPM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>search result</title>
    </head>
    <body>
        
        <%
        String protocol = request.getParameter("protocol");
        String msg = (String)request.getAttribute("msg");
        // deal with msg to view to user
        %>
        <%=protocol%>
    </body>
</html>
