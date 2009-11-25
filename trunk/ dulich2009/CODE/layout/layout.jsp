<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<!--%@ taglib uri="http://displaytag.sf.net" prefix="display" %!-->


<html:html>
    
    <head>
        <title><tiles:importAttribute name="title" /><bean:message name="title" /> </title>
        <html:base/>
        <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/menu.css'/>" charset="UTF-8" />     
        <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/style.css'/>" charset="UTF-8"/>
        <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/displaytag.css'/>" charset="UTF-8"/>        
        <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/subModal.css'/>" charset="UTF-8"/> 
        
        <script type="text/javascript" src="<html:rewrite page='/js/common.js'/>"></script>
        <script type="text/javascript" src="<html:rewrite page='/js/subModal.js'/>"></script>
        <script type="text/javascript" src="<html:rewrite page='/js/validate.js'/>"></script>
        <script type="text/javascript" src="<html:rewrite page='/js/menu.js'/>"></script>
        <script type="text/javascript" src="<html:rewrite page='/js/ajax.js'/>"></script>
        <script type="text/javascript" src="<html:rewrite page='/js/RowHandlers.js'/>"></script>
    </head>

    
    <body style="margin-top:0px;margin-bottom:0px;" bgcolor="#1A50B8">
        <TABLE ID="fullheight" border="0" width="800" cellspacing="0" CELLPADDING="0" ALIGN="CENTER">
            <!-- header panel-->
            <tr><td colspan="2" height="100"><tiles:insert attribute="header"/></td></tr>
            <tr><td colspan="2" height="20"><tiles:insert attribute="menu"/></td></tr>
            <tr valign="top" bgcolor="#FFFFDC">
                <!-- main panel-->
                <td align="center"><tiles:insert attribute="body" />
                <div id="tooltip"  style="z-index:1000;width:200px;position:absolute;display:none;background-color:#FFFF99;padding:3px;font-family:Arial;font-size:smaller;"></div>
                <!-- news panel-->
                <td><tiles:insert attribute="news" /></td>
            </tr>  
            
            <!-- advertisment panel-->
            <tr bgcolor="#FFFFDC"><td colspan="2" width="120" height="70"><tiles:insert attribute="adv" /></td></tr>
            
            <!-- footer panel-->
            <tr valign="bottom"><td colspan="2" height="30px"><tiles:insert attribute="footer"/></td></tr>
        </TABLE>
    </body>
</html:html>
