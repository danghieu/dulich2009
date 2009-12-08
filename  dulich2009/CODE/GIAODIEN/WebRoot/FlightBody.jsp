<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FlightBody.jsp' starting page</title>
    
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
   
 <table class="normal" width="745" align="center" bgcolor="#ffffff" border="0" cellpadding="3" cellspacing="0">



<tbody><tr>



                          <td align="left" valign="top"><table class="normal" width="100%" border="0" cellpadding="4" cellspacing="0">



                            <tbody><tr align="left" valign="top">



                              <td class="titlepage" height="35" valign="middle">D&#7882;CH V&#7908; &#272;&#7862;T V&Eacute; MAY BAY </td>



                              <td width="80">&nbsp;</td>



                            </tr>



                          </tbody></table></td>



                        </tr>



                        <tr>



                          <td align="left" background="vembay.php_files/bg_dot_06.htm" height="3" valign="top"><hr></td>



                        </tr>



                        <tr>



                          <td class="normal" align="left" valign="top"><table class="normal" width="300" align="right" border="0" cellpadding="5" cellspacing="5">

                            

                            

                            <tbody><tr>

                              <td align="left" valign="top"><p><span><strong>Lịch bay & bảng giá tuyến nội địa</strong></span><img src="vembay.php_files/timetable.gif" style="margin-right: 3px;" width="100" align="left" height="86"></p>
							  <p>

Tham khảo lịch bay và giá vé tham khảo cho các tuyến bay nội địa của Vietnam Airlines và Pacific Airlines </p></td>

                            </tr>

                          </tbody></table>

                          <p align="justify" class="normal"><strong>T</strong>ại 
E-Tourism, bạn c&oacute; thể dễ d&agrave;ng đặt v&eacute; m&aacute;y bay theo h&agrave;nh tr&igrave;nh 
mong muốn. E-Tourism
luôn sẵn sàng tư vấn cho bạn về các đường bay phù hợp nhất với hành
trình và có chi phí hợp lý nhất. Trong trường hợp có yêu cầu, bạn có
thể nhận vé tại văn phòng làm việc hoặc tại nhà riêng trong nội thành.
Phương thức thanh toán rất linh hoạt, thuận tiện bằng tiền mặt, chuyển
khoản, hoặc bằng thẻ tín dụng. Với khách đi du lịch theo từng đoàn, đi
du học hoặc định cư, chúng tôi đều áp dụng những mức giá ưu đãi. </p>
                          <p align="justify">Mời
bạn điền thông tin đặt vé theo yêu cầu, E-Tourism sẽ cung cấp
đầy đủ thông tin cho bạn trong khoảng thời gian ngắn nhất. </p>
                          <p align="justify">&nbsp;</p>
                          <p align="justify">&nbsp;</p>
                          <p align="justify">&nbsp;</p></td>



                        </tr>



                        <tr>


                          <td align="left" valign="top">

                          <table class="normal10" width="100%" border="0" cellpadding="2" cellspacing="0">

                            <tbody><tr align="center" valign="top">

                              <td width="50%"><script>

function view_composer1(ob1) {

	if (document.getElementById(ob1).style.display == "none") {

		document.getElementById(ob1).style.display = "";

	}

}



		function isMail1(frm) {



			var selObj = document.getElementById('bietden');

			var selIndex = selObj.selectedIndex;

			var selecteED = selObj.options[selIndex].value;



			if (document.book1.diden.value == "") {

				alert ("B?n hãy nh?p noi d?n c?a b?n !");

				document.book1.diden.focus();

				return false;

			}

			

			if (document.book1.ngaybay.value == "") {

				alert ("B?n hãy nh?p Ngày bay c?a b?n !");

				document.book1.ngaybay.focus();

				return false;

			}

			

			if (document.book1.ngayve.value == "") {

				alert ("B?n hãy nh?p Ngày v? c?a b?n !");

				document.book1.ngayve.focus();

				return false;

			}



			if (document.book1.hoten.value == "") {

				alert ("B?n hãy nh?p tên c?a b?n !");

				document.book1.hoten.focus();

				return false;

			}

			if (document.book1.email.value == "") {

				alert ("B?n hãy nh?p e-mail!");

				document.book1.email.focus();

				return false;

			} else {

				var check = emailcheck(document.book1.email.value)

				if (!check) {

					alert("Sai d?nh d?ng E-mail !");

					document.book1.email.focus();

					return false;

				}

			}

			if (document.book1.dienthoai.value == "") {

				alert ("B?n hãy nh?p Ði?n tho?i c?a b?n !");

				document.book1.dienthoai.focus();

				return false;

			}

			

			if (document.book1.yeucau.value == "") {

				alert ("B?n hãy nh?p Yêu c?u c?a b?n !");

				document.book1.yeucau.focus();

				return false;

			}

			if (selecteED == "0") {

				alert ("B?n hãy ch?n M?c bi?t dulichhe.com t? dâu ! ");

				return false;

			}

		}

	

		function emailcheck(mailValue) {

			var chk = false;

			for(i=0; i<mailValue.length; i++) {

				if(mailValue.charAt(i)=='@') {

					chk = true;

					break;

				}

			}

			return chk;

		}



                                </script>
                                <form id="form1" name="form1" method="post" action="">
                                  <label></label>
                                  <table class="normal" width="100%" border="0" cellpadding="1" cellspacing="1">
                                    <tbody>
                                      <tr>
                                        <td align="center" bgcolor="#6699FF" valign="middle" height="30"><span class="style5">T&Igrave;M KI&#7870;M CHUY&#7870;N BAY QU&#7888;C T&#7870; </span></td>
                                      </tr>
                                      <tr>
                                        <td align="left" bgcolor="" valign="top"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF" class="normal10">
                                            <tbody>
                                              <tr align="left" valign="top">
                                                <td width="50%" height="37" valign="middle">Kh&#7903;i h&agrave;nh<span class="normalred">(*)</span></td>
                                                <td width="50%" valign="middle">&#272;i &#273;&#7871;n <span class="normalred"> (*)</span></td>
                                              </tr>
                                              <tr align="left">
                                                <td height="47" valign="middle"><select name="select" class="form_date" id="select">
                                                    <option selected="selected">Hồ Chí Minh</option>

                                                <option>Hà Nội</option>

                                                <option>Buôn Mê Thuộc</option>

                                                <option>Đà Nẵng</option>

                                                </select></td>
                                                <td valign="middle"><input name="diden2" class="form" id="diden" size="25" type="text" /></td>
                                              </tr>
                                              <tr align="left" valign="top">
                                                <td height="38" valign="middle">Ngày bay <span class="normalred"> (*)</span></td>
                                                <td valign="middle">Gi&#7901; bay d&#7921; ki&#7871;n </td>
                                              </tr>
                                              <tr align="left" valign="top">
                                                <td height="37" valign="middle"><input name="ngaybay" id="ngaybay" value="" type="hidden" />
                                                    <input name="hienthingaybay" class="search_form" id="hienthingaybay" size="16" type="text" />
                                                    <span id="btnngaybay" title="Select publishing date"> <a href="javascript:%20void(0);"><img src="vembay.php_files/img.gif" border="0" /></a></span>
                                                <script type="text/javascript">
    Calendar.setup({inputField : "ngaybay", ifFormat : "%Y-%m-%d", displayArea : "hienthingaybay", daFormat : "%d-%m-%Y", button : "btnngaybay", showsTime : false});
                                    </script></td>
                                                <td valign="middle"><select name="select" class="form_date" id="select2">
                                                    <option selected="selected">0:00 AM</option>
                                                    <option>1:00 AM</option>
                                                    <option>2:00 AM</option>
                                                    <option>3:00 AM</option>
                                                    <option>4:00 AM</option>
                                                    <option>5:00 AM</option>
                                                    <option>6:00 AM</option>
                                                    <option>7:00 AM</option>
                                                    <option>8:00 AM</option>
                                                    <option>9:00 AM</option>
                                                    <option>10:00 AM</option>
                                                    <option>11:00 AM</option>
                                                    <option>12:00 AM</option>
                                                    <option>1:00 PM</option>
                                                    <option>2:00 PM</option>
                                                    <option>3:00 PM</option>
                                                    <option>4:00 PM</option>
                                                    <option>5:00 PM</option>
                                                    <option>6:00 PM</option>
                                                    <option>7:00 PM</option>
                                                    <option>8:00 PM</option>
                                                    <option>9:00 PM</option>
                                                    <option>10:00 PM</option>
                                                    <option>11:00 PM</option>
                                                </select></td>
                                              </tr>
                                              
                                              <tr align="left" valign="top">
                                                <td height="37" valign="middle">H&#7841;ng v&eacute; </td>
                                                <td valign="middle"><select name="select" class="form_date" id="hangve">
                                                    <option selected="selected">Economy</option>
                                                    <option>Business</option>
                                                </select></td>
                                              </tr>
                                      
                                              <tr align="left" valign="middle">
                                                <td height="31" valign="middle"> S&#7889; l&#432;&#7907;ng </td>
                                                <td align="left" valign="top">
                                                  <input name="soluongquocte" class="form_date" id="soluongquocte" size="2" type="text" />                                                 </td>
                                              </tr>
                                              <tr align="center" valign="top">
                                                <td height="3" colspan="2"></td>
                                              </tr>
                                            </tbody>
                                        </table></td>
                                      </tr>
                                    </tbody>
                                  </table>
                                </form>                                
                                <form id="form3" name="form3" method="post" action="">
                                  <label>
                                  <input type="submit" name="timkiemquocte" value="Tìm kiếm" />
                                  </label>
                                </form>                                <p>&nbsp;</p></td>

                              <td width="50%">

                                <script>

function view_composer2(ob1) {

	if (document.getElementById(ob1).style.display == "none") {

		document.getElementById(ob1).style.display = "";



	}

}



		function isMail2(frm) {



			var selObj = document.getElementById('bietden2');

			var selIndex = selObj.selectedIndex;

			var selecteED = selObj.options[selIndex].value;

			

			if (document.book2.ngaybay.value == "") {

				alert ("B?n hãy nh?p Ngày bay c?a b?n !");

				document.book2.ngaybay.focus();

				return false;

			}

			

			if (document.book2.ngayve.value == "") {

				alert ("B?n hãy nh?p Ngày v? c?a b?n !");

				document.book2.ngayve.focus();

				return false;

			}



			

			if (document.book2.hoten.value == "") {

				alert ("B?n hãy nh?p tên c?a b?n !");

				document.book2.hoten.focus();

				return false;

			}

			if (document.book2.email.value == "") {

				alert ("B?n hãy nh?p e-mail!");

				document.book2.email.focus();

				return false;

			} else {

				var check = emailcheck2(document.book2.email.value)

				if (!check) {

					alert("Sai d?nh d?ng E-mail !");

					document.book2.email.focus();

					return false;

				}

			}

			

			if (document.book2.dienthoai.value == "") {

				alert ("B?n hãy nh?p Ði?n tho?i c?a b?n !");

				document.book1.dienthoai.focus();

				return false;

			}

			if (document.book2.yeucau.value == "") {

				alert ("B?n hãy nh?p Yêu c?u c?a b?n !");

				document.book2.yeucau.focus();

				return false;

			}

			if (selecteED == "0") {

				alert ("B?n hãy ch?n M?c bi?t dulichhe.com t? dâu ! ");

				return false;

			}

		}

	

		function emailcheck2(mailValue) {



			var chk = false;

			for(i=0; i<mailValue.length; i++) {

				if(mailValue.charAt(i)=='@') {

					chk = true;

					break;

				}

			}

			return chk;

		}



                                </script>

                                <table class="normal" width="100%" border="0" cellpadding="1" cellspacing="1">

                                  <tbody><tr>

                                    <td align="center" bgcolor="#6699FF" valign="middle" height="30"><span class="style5">T&Igrave;M KI&#7870;M CHUY&Ecirc;N BAY TRONG N&#431;&#7898;C </span></td>

                                  </tr>

                                  <tr>

                                    <td align="left" bgcolor="" valign="top"><form name="book2" method="post" action="maybay_datcho_ok.php" onsubmit="return isMail2(this)">

                                        <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF" class="normal10">

                                          <tbody><tr align="left" valign="top">

                                            <td width="50%" height="37" valign="middle">Kh&#7903;i h&agrave;nh <span class="normalred"> (*)</span></td>

                                            <td width="50%" valign="middle">&#272;i &#273;&#7871;n <span class="normalred">(*)</span></td>

                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="48" valign="middle"><select name="khoihanh" class="form_date" id="khoihanh">

                                                
                                          <option selected="selected">

                                                  Buôn Ma Thuột                                              </option>

                                                
                                          <option>

                                                  Cà Mau                                              </option>

                                                
                                          <option>

                                                  Côn Đảo                                              </option>

                                                
                                          <option>

                                                  Đà Lạt                                              </option>

                                                
                                          <option>

                                                  Đà Nẵng                                              </option>

                                                
                                          <option>

                                                  Điện Biên Phủ                                              </option>

                                                
                                          <option>

                                                  Hải Phòng                                              </option>

                                                
                                          <option>

                                                  Hà Nội                                              </option>

                                                
                                          <option>

                                                  Huế                                              </option>

                                                
                                          <option>

                                                  Nha Trang                                              </option>

                                                
                                          <option>

                                                  Phú Quốc                                              </option>

                                                
                                          <option>

                                                  Pleiku                                              </option>

                                                
                                          <option>

                                                  Qui Nhơn                                              </option>

                                                
                                          <option>

                                                  Rạch Giá                                              </option>

                                                
                                          <option>

                                                  Hồ Chí Minh                                              </option>

                                                
                                          <option>

                                                  Tuy Hoà                                              </option>

                                                
                                          <option>

                                                  Vinh                                              </option>


                                            </select></td>

                                            <td valign="middle"><select name="diden" class="form_date" id="select3" valign="middle">
                                              <option selected="selected"> Buôn Ma Thuột </option>
                                              <option> Cà Mau </option>
                                              <option> Côn Đảo </option>
                                              <option> Đà Lạt </option>
                                              <option> Đà Nẵng </option>
                                              <option> Điện Biên Phủ </option>
                                              <option> Hải Phòng </option>
                                              <option> Hà Nội </option>
                                              <option> Huế </option>
                                              <option> Nha Trang </option>
                                              <option> Phú Quốc </option>
                                              <option> Pleiku </option>
                                              <option> Qui Nhơn </option>
                                              <option> Rạch Giá </option>
                                              <option> Hồ Chí Minh </option>
                                              <option> Tuy Hoà </option>
                                              <option> Vinh </option>
                                            </select></td>
                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="36" valign="middle">Ngày bay <span class="normalred"> (*)</span></td>

                                            <td valign="middle">Gi&#7901; d&#7921; ki&#7871;n </td>
                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="37" valign="middle">
<input name="ngaybay1" id="ngaybay1" value="" type="hidden">
<input name="hienthingaybay1" class="search_form" id="hienthingaybay1" size="16" type="text">
    <span id="btnngaybay1" title="Select publishing date">
    <a href="javascript:%20void(0);"><img src="vembay.php_files/img.gif" border="0"></a></span>
<script type="text/javascript">
    Calendar.setup({inputField : "ngaybay1", ifFormat : "%Y-%m-%d", displayArea : "hienthingaybay1", daFormat : "%d-%m-%Y", button : "btnngaybay1", showsTime : false});
</script></td>

                                            <td valign="middle"><select name="giobay" class="form_date" id="giobay2">

                                            <option selected="selected">0:00 AM</option>

                                                <option>1:00 AM</option>

                                                <option>2:00 AM</option>

                                                <option>3:00 AM</option>

                                                <option>4:00 AM</option>

                                                <option>5:00 AM</option>

                                                <option>6:00 AM</option>

                                                <option>7:00 AM</option>

                                                <option>8:00 AM</option>

                                                <option>9:00 AM</option>

                                                <option>10:00 AM</option>

                                                <option>11:00 AM</option>

                                                <option>12:00 AM</option>

                                                <option>1:00 PM</option>

                                                <option>2:00 PM</option>

                                                <option>3:00 PM</option>

                                                <option>4:00 PM</option>

                                                <option>5:00 PM</option>

                                                <option>6:00 PM</option>

                                                <option>7:00 PM</option>

                                                <option>8:00 PM</option>

                                                <option>9:00 PM</option>

                                                <option>10:00 PM</option>

                                                <option>11:00 PM</option>

                                            </select></td>
                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="39" valign="middle">H&#7841;ng v&eacute; </td>

                                            <td valign="middle"><select name="hangve" class="form_date" id="hangve2">

                                            <option selected="selected">Economy</option>

                                                <option>Business</option>

                                            </select></td>
                                          </tr>

                                          

                                          <tr align="left" valign="top">
                                                <td height="34" valign="middle"> S&#7889; l&#432;&#7907;ng </td>
                                                <td align="left" valign="top">
                                                  <input name="soluongtrongnuoc" class="form_date" id="soluongtrongnuoc" size="2" type="text" />                                                 </td>
                                            </tr>

                                          
                                        </tbody></table>

                                  

                                    </form></td>
                                  </tr>
                                </tbody></table>
                                <form id="form2" name="form2" method="post" action="">
                                  <label>
                                  <input type="submit" name="timkiemtrongnuoc" value="Tìm Kiếm" />
                                  </label>
                                </form>                                <p>&nbsp;</p></td>

                            </tr>
                          </tbody></table>

                          </td>

                        </tr>



                        <tr>



                          <td align="left" valign="top"><p>&nbsp;</p>



                            </td>



                        </tr>



                        <tr>



                          <td align="left" valign="top"><hr noshade="noshade" size="1"></td>



                        </tr>



                        <tr>



                          <td align="center" valign="top"><a href="" class="linkBlue"><br></a></td>



                        </tr>



                      </tbody></table>
  </body>
</html>
