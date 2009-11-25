<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<table id="fullheight" width="100%" align="center" cellpadding="0" cellspacing="0">
<tr>
    <td align="left" colspan="2">
        <ul id="menu">
            <%
            String isValidate = (String) session.getAttribute("isValidate");
            if (isValidate != null) {
            %>
            <logic:present name="userContext" scope="session">
                <logic:equal name="userContext" property="grantedRight" value="0">
                    <li class="parent"><a><bean:message key="menu.adm" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/userAction.do?action=prepareCreate&type=1" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/userAction.do?action=list&type=1" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li>                    
                    <li class="parent"><a><bean:message key="menu.dis" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/distributorAction.do?action=prepareCreate" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/distributorAction.do?action=getDistributors" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li>
                    <li class="parent"><a><bean:message key="menu.mot" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/userAction.do?action=prepareCreate&type=4" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/userAction.do?action=list&type=4" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li>                       
                </logic:equal>
                <logic:equal name="userContext" property="grantedRight" value="1">                    
                    <li class="parent"><a><bean:message key="menu.cs" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/userAction.do?action=prepareCreate&type=2" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/userAction.do?action=list&type=2" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li>                    
                    <li class="parent"><a href="<html:rewrite page="/auditAction.do?action=listAuditBPs" />"><bean:message key="menu.audit.view" /></a></li>

                    <li class="parent"><a><bean:message key="menu.productType" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/productTypeAction.do?action=initProductType" />"><bean:message key="menu.productType.create" /></a></li>
                            <li><a href="<html:rewrite page="/productTypeAction.do?action=viewProductType" />"><bean:message key="menu.productType.view" /></a></li>
                        </ul>                                
                    </li>                    
                    <li class="parent"><a><bean:message key="menu.mk" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/userAction.do?action=prepareCreate&type=5" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/userAction.do?action=list&type=5" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li> 
                    <li class="parent"><a><bean:message key="menu.rule" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/orderPointAction.do?action=prepareOrderPoint" />"><bean:message key="rule.order.point" /></a></li>
                            <li><a href="<html:rewrite page="/ruleAction.do?action=prepareCreate" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/ruleAction.do?action=listRules" />"><bean:message key="sub.list" /></a></li>
                            <%--<li><a href="<html:rewrite page="/paramTypeAction.do?action=prepareCreate" />"><bean:message key="sub.create.param"/></a></li>--%>
                        </ul>        
                    </li>
                    <li class="parent"><a><bean:message key="order.management" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/viewNewOrderAction.do?action=viewOrders" />"><bean:message key="order.view" /></a></li>
                        </ul>        
                    </li>                                        
                </logic:equal>
                <logic:equal name="userContext" property="grantedRight" value="2">
                   <li class="parent"><a><bean:message key="order.management" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/orderAction.do?action=initOrder"/>"><bean:message key="order.create" /></a></li>
                            <li><a href="<html:rewrite page="/viewOrderAction.do?action=viewOrders"/>"><bean:message key="order.view" /></a></li>
                        </ul>        
                    </li>                    
                    <li class="parent"><a><bean:message key="menu.lm" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/customerAction.do?action=prepareCreate" />"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/customerAction.do?action=getCustomers" />"><bean:message key="sub.list" /></a></li>
                        </ul>        
                    </li>
                    <li class="parent"><a><bean:message key="menu.gift" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/giftAction.do?action=prepareCreate"/>"><bean:message key="sub.create" /></a></li>
                            <li><a href="<html:rewrite page="/giftAction.do?action=listGifts"/>"><bean:message key="sub.list" /></a></li>
                            <li><a href="<html:rewrite page="/giftAction.do?action=prepareExchange" />"><bean:message key="sub.change.bp.gift" /></a></li>
                        </ul>        
                    </li>
                </logic:equal>                    
                <logic:equal name="userContext" property="grantedRight" value="4">
                    <li class="parent"><a><bean:message key="menu.monitor" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/monitorAction.do?action=monitorLM" />"><bean:message key="sub.monitorLM" /></a></li>                            
                            <li><a href="<html:rewrite page="/monitorAction.do?action=monitorGiftNew" />"><bean:message key="sub.monitorGifts" /></a></li>                            
                            <li><a href="<html:rewrite page="/monitorAction.do?action=monitorCS&type=2" />"><bean:message key="sub.monitorCS" /></a></li>                            
                            <li><a href="<html:rewrite page="/monitorAction.do?action=monitorCS&type=1" />"><bean:message key="sub.monitorAdmin" /></a></li>                            
                        </ul>        
                    </li>
                </logic:equal>
                <logic:equal name="userContext" property="grantedRight" value="5">
                    <li class="parent"><a><bean:message key="menu.mk" /></a>
                        <ul>
                            <li><a href="<html:rewrite page="/orderAction.do?action=listVerify" />"><bean:message key="sub.list.verify" /></a></li>
                            <li><a href="<html:rewrite page="/orderAction.do?action=listProcess" />"><bean:message key="sub.list.isprocessing" /></a></li>
                            <li><a href="<html:rewrite page="/orderAction.do?action=listPrinted" />"><bean:message key="sub.list.printed" /></a></li>
                            <li><a href="<html:rewrite page="/orderAction.do?action=listShifted" />"><bean:message key="sub.list.shifted" /></a></li>
                        </ul>        
                    </li>                                 
                </logic:equal>           
            </logic:present>
            <%
            }
            else {
            %>
            <li class="parent"><a><bean:message key="menu.bp" /></a>
                <ul>
                    <li><a href="<html:rewrite page="/bonusPointAction.do?action=prepareAddBonusPoint"/>"><bean:message key="sub.addbp" /></a></li>
                    <li><a href="<html:rewrite page="/bonusPointAction.do?action=prepareViewBonusPoint"/>"><bean:message key="sub.viewbp" /></a></li>
                </ul>        
            </li>
            <li class="parent"><a><bean:message key="menu.register.member" /></a>
                <ul>
                    <li><a href="<html:rewrite page="/memberAction.do?action=initMember"/>"><bean:message key="menu.register" /></a></li>
                </ul>        
            </li>
            <%
            }
            %>
        </ul>
</td></tr>
<tr bgcolor="#FFB437">
    <td align="left" height="25" bgcolor="#FFB437"> 
        <logic:present name="userContext" scope="session">
            <font face="Arial" size="-1" color="white"><bean:message key="message.welcome" /><html:link page="/userAction.do?action=profile"><bean:write name="userContext" property="fullName" />!</html:link>
            <a href="<html:rewrite page='/loginAction.do?action=logout'/>">[<bean:message key="logout.button" />]</a></font>
        </logic:present>
        <logic:notPresent name="userContext" scope="session">
            <font face="Arial" size="-1" color="white"><bean:message key="message.notlogin" /><a href="<html:rewrite page='/loginAction.do?action=login'/>">[<bean:message key="login.button" />]</a></font>
        </logic:notPresent>
    </td>
    <td align="right"><!--input type="text" name="keyword" value=""-->
        <a href="<html:rewrite page="/changeLocale.do?lang=en" />"><html:img page="/img/en_flag.gif" title="English" height="12" /></a> <a href="<html:rewrite page="/changeLocale.do?lang=vi"/>"><html:img page="/img/vi_flag.gif" title="Ti&#7871;ng Vi&#7879;t" height="12" /></a>
    </td>
</td></tr>
</tr></table>