<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'SignIn.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
.style2 {
	font-size: 18px;
	color: #000000;
}
.style3 {color: #000000}
-->
</style>
</head>

<body>
<table width="780" height="259" border="1">
  <tr>
    <td width="770" align="center" valign="top"><table width="456" height="323" border="1">
      <tr>
        <td width="446" height="25" align="center" valign="middle" bgcolor="#FFFFFF"><span class="style2">Th&ocirc;ng tin đăng nhập </span></td>
      </tr>
      <tr>
        <td height="290" align="center" valign="middle"><p class="style3">Xin vui lòng nhập đầy đủ thông tin </p>
          <table width="310" height="101" border="1">
		   <tr>
            <td width="102"><span class="style3">Nhà cung cấp </span></td>
            <td width="192"><select name="loainhacungcap" class="style3" id="loainhacungcap">

                                                <option selected="selected" value="hotel">Khách sạn</option>

                                                <option value="flight">Đại lý bán vé máy bay </option>
												<option value="train">Đại lý bán vé tàu hỏa </option>

                </select></td>
          </tr>
          <tr>
            <td><span class="style3">Tên đăng nhập </span></td>
            <td><input name="username" size="15" maxlength="11" type="text"></td>
          </tr>
          <tr>
            <td><span class="style3">Mật khẩu </span></td>
            <td><input name="password" size="15" maxlength="11" type="text"></td>
          </tr>
        </table>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <form id="form1" name="form1" method="post" action="">
            <label>
              <input name="Submit" type="submit" class="style2" value="Đăng nhập" />
              </label>
          </form>          <p>&nbsp;</p></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
