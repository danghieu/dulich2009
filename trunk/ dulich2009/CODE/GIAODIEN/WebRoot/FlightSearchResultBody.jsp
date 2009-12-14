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
<p align="center"><strong><font color="#FF0000" size="2"><b>DANH SÁCH CÁC CHUYẾN BAY </b></font></strong></p>
<p align="center">&nbsp;</p>
<div align="center">
  <table width="1079" border="1">
  <tr>
    <td width="75" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Hãng hàng không </strong></font></td>
    <td width="82" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Số hiệu chuyến bay </strong></font></td>
    <td width="146" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nơi đi </strong></font></td>
    <td width="102" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đi </strong></font></td>
    <td width="124" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Nơi đến </strong></font></td>
    <td width="106" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Ngày giờ đến</strong></font></td>
    <td width="88" align="center" valign="middle" bgcolor="#3366FF"><font color="#FFFFFF" size="2"><strong>Loại m&aacute;y bay</strong></font></td>
    <td width="83" align="center" valign="middle" bgcolor="#3366FF"><strong><font color="#FFFFFF" size="2">Hạng dịch vụ</font></strong></td>
    <td width="82" align="center" valign="middle" bgcolor="#3366FF"><font size="1"><strong><font color="#FFFFFF" size="2">Giá vé </font></strong></font></td>
    <td width="127" align="center" valign="middle" bgcolor="#3366FF"><strong><font color="#FFFFFF" size="2"><b>Thông tin bổ sung</b></font></strong></td>
  </tr>
  <!-- 
  <tr>
    <td align="center" valign="middle">Vietnam Airlines</td>
    <td align="center" valign="middle">VN217</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>11:00</p>
      <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>13:00</p>
      <p>01/01/2010</p></td>
    <td align="center" valign="middle">Boeing 777 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,500,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
  <tr>
    <td align="center" valign="middle">Vietnam Airlines</td>
    <td align="center" valign="middle">VN219</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>13:00</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>15:00</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Boeing 777 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,500,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
  <tr>
    <td align="center" valign="middle">Vietnam Airlines</td>
    <td align="center" valign="middle">VN213</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>08:00</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>10:00</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Boeing 777 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,500,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
  <tr>
    <td align="center" valign="middle">Pacific Airlines</td>
    <td align="center" valign="middle">BL797</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>13:10</p>
      <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>15h:10</p>
      <p>01/01/2010</p></td>
    <td align="center" valign="middle">Boeing 737 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,350,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
  <tr>
    <td align="center" valign="middle">Pacific Airlines</td>
    <td align="center" valign="middle">BL791</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>11:10</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>13h:10</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Boeing 737 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,350,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
  <tr>
    <td align="center" valign="middle">Pacific Airlines</td>
    <td align="center" valign="middle">BL799</td>
    <td align="center" valign="middle">Hà Nội </td>
    <td align="center" valign="middle"><p>14:30</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Hồ Chí Minh </td>
    <td align="center" valign="middle"><p>16h:30</p>
        <p>01/01/2010</p></td>
    <td align="center" valign="middle">Airbus A320 </td>
    <td align="center" valign="middle">Economy</td>
    <td align="center" valign="middle"><strong>1,350,000VND</strong></td>
    <td align="center" valign="middle"><a href="#">Xem chi tiết</a></td>
  </tr>
   -->
</table>
<p>&nbsp;</p>
<p>&nbsp;</p>
</div>
</body></html>


