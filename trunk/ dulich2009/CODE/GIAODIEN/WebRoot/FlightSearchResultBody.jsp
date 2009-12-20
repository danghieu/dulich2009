<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FlightSearchResultBody.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<table width="769"  border="1">
  <tr>
    <td width="769" valign="top"><p align="center"><strong><font color="#FF0000" size="2"><b>DANH SÁCH CÁC CHUYẾN BAY </b></font></strong></p>
<p align="center">&nbsp;</p>
<div align="center">
  <table width="769" border="1">
  <tr>
    <td width="58" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Hãng hàng không </strong></font></td>
    <td width="65" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Số hiệu chuyến bay </strong></font></td>
    <td width="93" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nơi đi </strong></font></td>
    <td width="76" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đi </strong></font></td>
    <td width="90" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nơi đến </strong></font></td>
    <td width="84" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đến</strong></font></td>
    <td width="72" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Loại m&aacute;y bay</strong></font></td>
    <td width="70" align="center" valign="middle" bgcolor="#3366FF"><strong><font color="#FFFFFF" size="2">Hạng dịch vụ</font></strong></td>
    <td width="68" align="center" valign="middle" bgcolor="#3366FF"><font size="1"><strong><font color="#FFFFFF" size="2">Giá vé </font></strong></font></td>
    <td width="93" align="center" valign="middle" bgcolor="#3366FF"><strong><font color="#FFFFFF" size="2"><b>Thông tin bổ sung</b></font></strong></td>
  </tr>
 
</table>
</td>
  </tr>
</table>

</body></html>


