<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

    <table align="center" width="80%" >
        <tr >
            <td class="listtitle" height="300">
                <logic:present name="error">
                    <font color="red"><h2><bean:message name="error" /></h2></font>
                </logic:present>
                <logic:present name="code">
                    <bean:message key="servererror.display" />
                    <img src="../../img/under.gif" name="under" width="140" align="center">
                </logic:present>
            </td>
        </tr>
    </table>
    