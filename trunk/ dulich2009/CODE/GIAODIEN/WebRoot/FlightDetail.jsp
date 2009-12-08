<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Du lịch Thái Lan, Du lich Trung Quoc, Du lich Ha Long, Du lich
Cat Ba, Du lich Singapore, Du lich Malaysia, du lich Hong Kong,
Campuchia, Châu Âu, Nhật Bản, Hàn Quốc, Ấn Độ, Mỹ, du lich sapa, Nha
Trang, Phú Quốc, Huế, Hội An, du lịch nước ngoài, trong nuoc, cong
ty du lich Viet Nam, du lich Sa Pa</title>
<meta name="description" content="Du lịch Thái Lan, Du lich Trung Quoc, Du lich Ha Long, Du lich Cat Ba, Du lich Singapore, Du lich Malaysia, du lich Hong Kong, Campuchia, Châu Âu, Nhật Bản, Hàn Quốc, Ấn Độ, Mỹ, du lich sapa, Nha Trang, Phú Quốc, Huế, Hội An, du lịch nước ngoài, trong nuoc, cong ty du lich Viet Nam, du lich Sa Pa"> 
<meta name="keywords" content="Du lịch Thái Lan, Du lich Trung Quoc, Du lich Ha Long, Du lich Cat Ba, Du lich Singapore, Du lich Malaysia, du lich Hong Kong, Campuchia, Châu Âu, Nhật Bản, Hàn Quốc, Ấn Độ, Mỹ, du lich sapa, Nha Trang, Phú Quốc, Huế, Hội An, du lịch nước ngoài, trong nuoc, cong ty du lich Viet Nam, du lich Sa Pa"> 
<meta name="owner" content="Vietnam Anz Travel">
<meta name="distribution" content="Global">
<meta name="revisit-after" content="1 days">
<meta http-equiv="audience" content="General">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link title="Normal" media="all" href="dulichag_files/normal.css" type="text/css" rel="stylesheet">
<link href="vistalandtravel_files/calendar.css" rel="stylesheet" type="text/css" media="screen">
<script language="Javascript" src="dulichag_files/js_lib.js" type="text/javascript"></script>

<style type="text/css">
<!--
.style1 {color: #FF0000}
.style4 {
	font-size: 12px;
	color: #FF0000;
	font-weight: bold;
}
.style5 {color: #FF0000; font-weight: bold; }
-->
</style>

<script src="vembay.php_files/AC_RunActiveContent.js" type="text/javascript"></script>
<link href="vembay.php_files/tet2009.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

var slideTime = 700;
var floatAtBottom = false;

function pepsi_floating_init()
{
	xMoveTo('floating_banner_right', 887 - (1024-screen.width), 0);

	winOnResize(); // set initial position
	xAddEventListener(window, 'resize', winOnResize, false);
	xAddEventListener(window, 'scroll', winOnScroll, false);
}
function winOnResize() {
	checkScreenWidth();
	winOnScroll(); // initial slide
}
function winOnScroll() {
  var y = xScrollTop();
  if (floatAtBottom) {
    y += xClientHeight() - xHeight('floating_banner_left');
  }
  
  xSlideTo('floating_banner_left', (screen.width - (800-778) - 770)/2-111 , y, slideTime);
  xSlideTo('floating_banner_right', (screen.width - (800-778) + 770)/2, y, slideTime);
}
	
function checkScreenWidth()
{
	if( screen.width <= 800 )
	{
		document.getElementById('floating_banner_left').style.display = 'none';
		document.getElementById('floating_banner_right').style.display = 'none';
	}
}

</script>
<script language="JavaScript" src="vembay.php_files/AdLibExt.js"></script>
<!-- InstanceBeginEditable name="head" -->


<link rel="stylesheet" type="text/css" href="vembay.php_files/theme.css">
<script type="text/javascript" src="vembay.php_files/calendar.js"></script>
<script type="text/javascript" src="vembay.php_files/calendar-vi.js"></script>
<script type="text/javascript" src="vembay.php_files/calendar-setup.js"></script>
<!-- InstanceEndEditable -->
<script charset="utf-8" id="injection_graph_func" src="vembay.php_files/injection_graph_func.js">
</script>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" style="background-color: rgb(255, 255, 255);">
<center>

	<table style="width: 960px;" align="center" border="0" cellpadding="0" cellspacing="0">
	
		<tbody>
		<tr>
			<td valign="top" nowrap="nowrap">
				<jsp:include page="header.jsp" flush="false"></jsp:include>
			</td>
		</tr>
		<tr>
		<td style="width: 960px; text-align: center;" valign="top" width="100%" align="center" nowrap="nowrap">
			<table style="border-collapse: collapse;" align="center" border="0" cellpadding="0" cellspacing="0">
			<tbody>
			<tr>
				<td valign="top" width="170" nowrap="nowrap"> <jsp:include page="leftMenu.jsp" flush="false"></jsp:include> </td>       
				<td style="width: 100%; padding-left: 15px; padding-right: 15px;" valign="top" width="100%" align="center"> 
				<jsp:include page="FlightDetailBody.jsp" flush="false"></jsp:include>
				</td> 
				<td valign="top" width="150" nowrap="nowrap"><jsp:include page="rightMenu.jsp" flush="false"></jsp:include></td>
			</tr>
			</tbody>
			</table>
		</td>
	</tr>
	<tr>
	<td valign="top" nowrap="nowrap"><jsp:include page="footer.jsp" flush="false"></jsp:include></td>
	</tr>
	</tbody>
	</table>				
	</center>
	</body></html>