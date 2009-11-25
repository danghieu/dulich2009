<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>


<html:form styleId="frmLogin" action="/loginAction.do?action=login" method="post">    
    <P CLASS="maintitle"><bean:message  key="login.title1" /></P>
    <table id="formEdit" align="center" border="0">
        <tr>
            <td class="label"><bean:message key="username.label"/></td><td><html:text styleClass="text" property="userName" value="CS2" /></td>
        </tr>
        <tr>
            <td class="label"><bean:message key="passwrd.label"/></td><td><html:password styleClass="text" property="password" value="123456"/></td>
        </tr>
        <%--
        <tr>
            <td class="label"><bean:message key="distributor.label"/></td>
            <td>
                <html:select property="distributorId" styleClass="combo" onmouseover="showTip(this, event)" onmouseout="hideTip()">
                    <html:option value=""><bean:message key="select.label" /><bean:message key="distributor.label"/></html:option>
                    <html:optionsCollection name="distributors" label="distributorName" value="distributorId"/>
                </html:select>   
            </td>
        </tr>
        --%>
    </table>
    <html:submit property="Submit" styleClass="button"><bean:message key="login.button"/></html:submit>
</html:form>
