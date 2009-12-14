<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Tau.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 	<table border="0" cellpadding="0" cellspacing="0" width="59%">
  <tbody><tr>
    <td><img src="ticket_train_files/vietnam_train.jpg" width="640" height="120"/></td>
  </tr>
</tbody></table>

  <body>
  <table width="640" border="1" height="120">
  <tbody>
  
				  <tr><td colspan="2" style="font-weight: bold; color: blue; font-size: 14pt; font-family: Arial,Helvetica,sans-serif;" align="center">T&igrave;m kiếm v&eacute; t&agrave;u<br></td></tr>
		  <tr><td align="right"><strong>Số lượng : </strong></td>
		    <td><input maxlength="10" size="11" name="num_ticket" value="" type="text">&nbsp;(<font color="#ff0000">*</font>)</td></tr>
		  <tr><td align="right"><strong> Ngày khởi hành : </strong></td>
		    <td><input size="11" maxlength="10" name="checkin" value="" type="text">&nbsp;<a href="#" onClick="fPopCalendar(checkin,checkin); return false"><img src="ticket_train_booking.php_files/calendar.gif" border="0"></a>&nbsp;&nbsp;(tháng/ngày/năm)</td></tr>
		  <tr><td align="right"><strong> Thời gian khởi hành : </strong></td>
		    <td><input size="11" maxlength="12" name="time" value="" type="text"><select name="op_time" size="1"><option>AM</option><option>PM</option></select>&nbsp;(<font color="#ff0000">*</font>)&nbsp;(giờ : phút)</td></tr>
		  <tr><td align="right"><strong>Loại ghế : </strong></td>
		    <td><select name="seating" size="1"><option selected="selected">Select one</option><option>Ghế ngồi có điều hòa</option><option>Giường ngủ có điều hòa</option></select></td></tr>
		  <tr><td align="right"><strong>Ga xuất phát : </strong></td>
		    <td><input size="36" maxlength="50" name="departure_station" value="" type="text">&nbsp;(<font color="#ff0000">*</font>)</td></tr>
		  <tr><td align="right"><strong>Ga đến : </strong></td>
		    <td><input size="36" maxlength="50" name="arrival_station" value="" type="text">&nbsp;(<font color="#ff0000">*</font>)</td></tr>
		  
		  
		  
		   
		  <tr>
		   
			
			
			
		  
		
		  <tr align="center"><td colspan="2"><input class="button" name="sub_ok" value="Search" style="font-weight: bold;" type="submit">&nbsp;&nbsp;&nbsp;&nbsp;
			<input value="Reset" name="no_ok" class="button" style="font-weight: bold;" type="reset"></td></tr>
	</tbody>	</table>
<br>
  </body>
</html>
