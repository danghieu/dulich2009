<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

    <table align="center" width="80%">
        <tr>
            <td align="center" class="listtitle" height="300">
                <logic:present name="message">
                    <font color="blue"><h2><bean:message name="message" /></h2></font>
                </logic:present>
            </td>
        </tr>
    </table>
