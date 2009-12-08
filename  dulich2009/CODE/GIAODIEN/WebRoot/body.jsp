<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'body.jsp' starting page</title>
    
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
    <h1 style="margin-top: 1px; font-size: 11px; margin-bottom: 1px;" align="center"><font size="2">Du lịch Vietnam</font></h1>
<p style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;">Du 
lịch E-Tourism đ&atilde; c&oacute; nhiều năm kinh nghiệm trong lĩnh vực lữ h&agrave;nh quốc tế 
v&agrave; nội địa. Với đội ngũ nh&acirc;n vi&ecirc;n năng động, nhiệt t&igrave;nh, gi&agrave;u kinh 
nghiệm v&agrave; th&ocirc;ng thạo nhiều ngoại ngữ như : Th&aacute;i Lan, Anh, Ph&aacute;p v&agrave; Trung 
sẵn s&agrave;ng gi&uacute;p đỡ kh&aacute;ch h&agrave;ng của bạn trong mọi điều kiện kể cả trường 
hợp khẩn cấp. với phong c&aacute;ch phục vụ chuy&ecirc;n nghiệp ch&uacute;ng t&ocirc;i xin được 
gửi đến Qu&yacute; kh&aacute;ch những chương tr&igrave;nh du lịch đặc sắc, được tổ chức bằng 
tất cả t&acirc;m huyết của những người y&ecirc;u nghề v&agrave; sự tr&acirc;n trọng tuyệt đối 
với c&aacute;c sản phẩm tour. Ch&uacute;ng t&ocirc;i cam kết đem đến cho kh&aacute;ch h&agrave;ng những 
sản phẩm dịch vụ chất lượng tốt nhất.&nbsp;</p>
<br>

						<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tbody><tr>
                          
                        </tr>
                    
						
						<tr style="display: none;" id="tag2">
                          <td>
<table class="border_search" width="100%" border="0" cellpadding="0" cellspacing="0" height="180">
                            <tbody><tr valign="top">
                              <td>
							  <table width="100%" border="0" cellpadding="0" cellspacing="0" height="92%">
							  <form method="get" action="http://vistalandtravel.com/?mod=search" name="ADS_FORM"></form>
							  <input name="s_zone_id" id="s_zone_id" value="0" type="hidden">
							   <input name="mod" id="mod" value="search" type="hidden">
                               <!--
							    <tr>
                                  <td width="5"></td>
                                  <td width="25%">Tours name</td>
                                  <td>
                               <input name="s_keyword" type="text" class="text_form_search" style="width: 150px">
								  </td>
                                  <td></td>
                                </tr>
							-->
                                <tbody><tr>
                                  <td width="5%"></td>
                                  <td width="39%">Tour Type </td>
                                  <td width="51%">
										<select name="s_cat_id" style="width: 140px;" class="text_form_search">
											
											<option value="52">|-- Halong Bay Cruises</option>
											<option value="6">|-- Sapa tours</option>
											<option value="32">|-- Hanoi and vicinity </option>
											<option value="35">|-- Hoian and vicinity</option>
											<option value="29">|-- Mekong Delta Tours</option>
											<option value="58">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Mekong boats </option>
											<option value="5">|-- Inbound Tours</option>
											<option value="10">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Explorer Vietnam</option>
											<option value="11">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Classical Tour</option>
											<option value="38">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- HCMC and vicinity</option>
											<option value="36">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Hue and vicinity</option>
											<option value="31">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Mekong Cruises</option>
											<option value="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Budget travel by open bus </option>
											<option value="33">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- HALONG tours</option>
											<option value="18">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Honey Moon tours</option>
											<option value="19">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Adventure</option>
											<option value="42">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Trekking tours</option>
											<option value="37">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Northern treking </option>
											<option value="41">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Southern trekking</option>
											<option value="43">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Scuba diving</option>
											<option value="44">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Kayaking and Canoeing</option>
											<option value="45">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Motorbiking </option>
											<option value="46">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Cycling tours</option>
											<option value="39">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Beach vacation</option>
											<option value="40">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Golf tours</option>
											<option value="47">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- North golf tours</option>
											<option value="48">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- South golf tours</option>
											<option value="49">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Vietnam golf packages</option>
											<option value="26">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Customized Tour</option>
											<option value="53">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Rainbow Flags</option>
											<option value="54">|-- Outbound tours</option>
											<option value="55">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tour to Cambodia</option>
											<option value="56">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tour to Thailand</option>
											<option value="57">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|-- Tours to China</option>
										</select>								  </td>		
                                   <td width="5%"></td>
                                </tr>
                                
 							<tr>
                                  <td></td>
                                  <td>Depart</td>
                                  <td>
					<input class="text_form_search" name="s_tours_date_from" id="s_tours_date_from" style="width: 120px;" type="text">
					<img src="vistalandtravel_files/icon_calendar.jpg" alt="" onClick="popUpCalendar(this,document.getElementById('s_tours_date_from'), 'mm/dd/yyyy', fnSetDate);" align="absbottom" border="0"> </td>
                                  <td>&nbsp;</td>
                                </tr>
                                <tr style="display: none;">
                                  <td></td>
                                  <td>End</td>
                                  <td>
								  <input class="text_form_search" name="s_tours_date_to" id="s_tours_date_to" style="width: 120px;" type="text">
								  <img src="vistalandtravel_files/icon_calendar.jpg" alt="" onClick="displayCalendar(document.ADS_FORM.s_tours_date_to,'mm/dd/yyyy',this)" align="absbottom" border="0"> </td>
                                  <td>&nbsp;</td>
                                </tr>
                                <tr>
                                  <td></td>
                                  <td>&nbsp;</td>
                                  <td>
					<input name="s_submit" src="vistalandtravel_files/bt_search.jpg" type="image" border="0">							</td>
                                  <td>&nbsp;</td>
                                </tr>
                                <tr>
                                  <td>&nbsp;</td>
                                  <td>&nbsp;</td>
                                  <td>
								  <a class="customize_css" href="http://vistalandtravel.com/customize_tour/">
								  Customize Tour</a></td>
                                  <td>&nbsp;</td>
                                </tr>
 								
                              </tbody></table></td>
                            </tr>
                           </tbody></table>							</td>
                        </tr>						
                      </tbody></table>
			<table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr>
		<td class="categoryItemHome" bgcolor="#73aae7" nowrap="nowrap">
			<a href="">Du Lịch Vietnam</a>
		</td>
		<td width="99%">
			<img src="achau_files/rigthindicator.gif" align="absmiddle">		
		</td>
	</tr>

	<tr>
	
	</tr>
	<tr>
		<td colspan="2" bgcolor="#73aae7" height="1"></td>	
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody><tr><td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/79136F72E04682571707537531486322.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du Lịch Nha Trang</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/6FED9452E0468241E9FC737531316334.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du Lịch Phú Quốc</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/33934B7E37E2366C4717D1236E108663.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Sapa </a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/halong.jpg" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href=""> Du Lịch Hạ long </a>			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/1ED3EEBD1D0A89EC3D967512F240DC62.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Khởi Hành Hàng Ngày</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/636871841EDBBC2285CA43389A28931A.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Tuần Trăng Mật</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/21A1843920DC7BD517DFFEC30FED7983.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch trong tháng</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/21AABED5241193955BB2D402F1E9C082.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du Lịch Xuyên Việt</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/213A028E2411939552B911B2F1E9BF02.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Huế</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/8F50454C8D47C547843912F5332F22B2.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du Lịch Hà Nội</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="49%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2007EC01241193954167F942F1E9BCB2.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch miền Trung</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="51%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2716C8112331F5364273D27302E395E3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du Lịch Sài Gòn</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

</tr>
			</tbody></table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>	
	</tr>
</tbody></table><table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr>
		<td class="categoryItemHome" bgcolor="#73aae7" nowrap="nowrap">
			<a href="">Du lịch nước ngoài</a>
		</td>
		<td width="99%">
			<img src="achau_files/rigthindicator.gif" align="absmiddle">		
		</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#73aae7" height="1"></td>	
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody><tr><td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/3229302337E238705CB085336E13D4D3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Campuchia</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/35F2CE2F37E23870210F65F36E13CE43.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Việt-Lào-Thái </a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/1FB9F6AA1CD2656536B93CF3096662E3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Thái Lan</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href="c"><img src="achau_files/1EE9923F1CD2656523BF75A309666C83.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Trung Quốc</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/198F72661CD2656555D17033096665F3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Singapore- Malaixia</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/187882F11CD265654AAE794309667233.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Hồng Kông</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/1F779DC21CD265653A5F8A7309666F13.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Âu- Úc- Mỹ</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/1D44EF251CD265651968A40309667403.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Du lịch Hàn Quốc</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

</tr>
			</tbody></table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>	
	</tr>
</tbody></table><table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr>
		<td class="categoryItemHome" bgcolor="#73aae7" nowrap="nowrap">
			<a href="">Dịch vụ du lịch</a>
		</td>
		<td width="99%">
			<img src="achau_files/rigthindicator.gif" align="absmiddle">		
		</td>
	</tr>
	<tr>
		<td colspan="2" bgcolor="#73aae7" height="1"></td>	
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody><tr><td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2A2819E32BBC7C0019465E330B069EA3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Vé tàu</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2FBFEDB82BBC7C0040391B830B069263.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="Flight.jsp">Vé máy bay</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2B7CBD442BBC7C00C0C14430B0674630.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Thuê xe</a>
			</td>
		</tr>
	</tbody></table>
</td>
<td style="padding-right: 10px;" width="50%">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody><tr>
			<td>
				<a href=""><img src="achau_files/2F1E20162BBC7C004A25C1630B066DA3.gif" align="left" border="0"></a>
			</td>
			<td class="categpryItemHome" width="99%">
				<a href="">Hộ chiếu, thị thực, dịch vụ</a>
			</td>
		</tr>
	</tbody></table>
</td></tr>
<tr>

</tr>
			</tbody></table>
		</td>
	</tr>
	<tr>
		<td height="10"></td>	
	</tr>
	<tr>
	
	</tr>
</tbody></table>	
				
  </body>
</html>
