<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
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
  <table style="width: 100%; border-collapse: collapse;">
<tbody>
<tr>
			<td valign="top" nowrap="nowrap">
				<table style="width: 100%; border-collapse: collapse;">
<tbody>
<tr>
<td style="padding-right: 7px; text-align: center;">
<table style="width: 100%; border-collapse: collapse;">
<tbody>
<tr>
<td width="17%"><a title="Vietnam tours" href="trangchu.jsp"><img style="margin-top: 5px; margin-bottom: 5px; margin-right: 10px;" alt="Vietnam travel" src="dulichag_files/banner_logo.jpg" width="200" border="0" height="100"></a></td>
<td width="40%" style="vertical-align: middle; width: 100%; text-align: center;">
<table style="border: medium none ; margin-left: 20px; margin-right: 20px; border-collapse: collapse;" align="center" cellpadding="0" cellspacing="0">
<tbody>
<tr>
<td style="vertical-align: middle; white-space: nowrap; text-align: left;" colspan="4">
<p align="left"><strong>Du lịch E-Tourism: </strong>123 Nguyễn Trãi - Thanh Xuân - Hà Nội </p></td></tr>
<tr>
<td style="vertical-align: middle; text-align: left;"><span style="color: rgb(0, 0, 255); text-decoration: underline;"><img id="Home1_Banner1_Image4" alt="" src="dulichag_files/Email.gif" width="16" border="0" height="10"></span></td>
<td style="vertical-align: middle; text-align: left;"><a href="mailto:booking@vietnamanztravel.com"><strong>booking@vietnamtravel.com</strong></a></td>
<td style="vertical-align: middle; text-align: left;"><strong><img id="Home1_Banner1_Image5" alt="" src="dulichag_files/Phone.gif" width="16" border="0" height="13"></strong></td>
<td style="vertical-align: middle; white-space: nowrap; text-align: left;">0912 377 644</td></tr>
<tr>
<td style="vertical-align: middle; text-align: left;"><span style="color: rgb(0, 0, 255); text-decoration: underline;"><strong><img id="Home1_Banner1_Image5" alt="" src="dulichag_files/Phone.gif" width="16" border="0" height="13"></strong></span></td>
<td style="vertical-align: middle; text-align: left;"><a href="mailto:booking@vietnamanztravel.com"></a><strong>(</strong>04) 38123456 - 38888888<strong> </strong></td>
<td style="vertical-align: middle; text-align: left;"><img id="Home1_Banner1_Image5" alt="" src="dulichag_files/Phone.gif" width="16" border="0" height="13"></td>
<td style="vertical-align: middle; white-space: nowrap; text-align: left;">0942 868 677</td></tr></tbody></table></td>
<td width="43%"><img style="margin-top: 6px; margin-bottom: 6px;" alt="Vietnam Package Tours" src="dulichag_files/quangchai.jpg" width="400" align="right" border="0" height="70"></td>
</tr></tbody></table>

<table border="0" cellpadding="0" cellspacing="0" background="dulichag_files/bgheademenu.gif" bgcolor="#FFFFFF" style="background-position: 0% 0%; background-attachment: scroll; background-image: url(/image/system/bgheademenu.gif); margin-bottom: 15px; width: 100%; background-repeat: repeat; height: 24px;" summary="" nowrap="">
<tbody>
<tr>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Du lich" href=""><strong><span style="color: rgb(255, 255, 255);">Trang chủ</span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="" href=""><strong><span style="color: rgb(255, 255, 255);">Giới thiệu </span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Khách sạn, khachsan, khách sạn việt nam" href=""><strong><span style="color: rgb(255, 255, 255);">Khách sạn</span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Vé máy bay, vé tàu, thuê xe" href=""><strong><a title="Tour du lịch" href="../../../../SVN%20Client/%20dulich2009/CODE/GIAODIEN/WebRoot/"><strong><span style="color: rgb(255, 255, 255);">Tour du lịch</span></strong></a></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Các điểm du lịch thú vị" href=""><strong><span style="color: rgb(255, 255, 255);">Địa điểm du lịch</span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Tour du lịch" href="Flight.jsp"><strong><span style="color: rgb(255, 255, 255);">V&eacute; m&aacute;y bay<br></span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="" href=""><strong><span style="color: rgb(255, 255, 255);">V&eacute; t&agrave;u<br></span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Giới thiệu" href=""><strong><span style="color: rgb(255, 255, 255);">Giới thiệu</span></strong></a></td>
<td style="text-align: center;"><strong><span style="color: rgb(255, 255, 255);">|</span></strong></td>
<td bgcolor="#6699FF" style="text-align: center;"><a title="Liên hệ" href="p"><strong><span style="color: rgb(255, 255, 255);">Liên hệ</span></strong></a></td>
</tr></tbody></table>
<p><img src="dulichag_files/banner_image.jpg" width="950" height="79"></p></td></tr>
<tr>

<td style="font-weight: bold;" align="right"><a href="">Đăng nhập </a> | <a href="">Register</a> | <a href="">Quên mật khẩu</a></td>
</tr>
</tbody></table>
     <br>
  </body>
</html>
