<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'footer.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
<!--
.style2 {font-weight: bold}
.style4 {color: #FF0000}
.style12 {font-size: 18}
.style13 {font-weight: bold; font-size: 16px; }
.style14 {font-size: 18px}
.style17 {font-size: 16px}
.style18 {font-size: 16px; color: #000000; }
.style22 {font-weight: bold; font-size: 16px; color: #FFFFFF; }
-->
</style>
</head>

<body>
<table class="normal" width="769" align="center" bgcolor="#ffffff" border="0" cellpadding="3" cellspacing="0">



<tbody><tr>



                          <td align="left" valign="top"><table width="100%" border="0" cellpadding="4" cellspacing="0" bgcolor="#3399FF" class="normal">



                            <tbody><tr align="left" valign="top">



                              <td width="682" height="35" valign="middle" class="style4 titlepage" bgcolor="#6699FF"><span class="style22">D&#7882;CH V&#7908; &#272;&#7862;T V&Eacute; MAY BAY </span></td>



                              



                            </tr>



                          </tbody></table></td>



                        </tr>



                        <tr>



                          <td align="left" background="vembay.php_files/bg_dot_06.htm" height="3" valign="top"><hr></td>



                        </tr>



                        <tr>



                          <td class="normal" align="left" valign="top"><table class="normal" width="300" align="right" border="0" cellpadding="5" cellspacing="5">

                            

                            

                            <tbody><tr>

                              <td align="left" valign="top"><p class="style2" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;"><span class="style18"><strong>Lịch bay & bảng giá tuyến nội địa</strong></span><span class="style18"><img src="vembay.php_files/timetable.gif" style="margin-right: 3px;" width="100" align="left" height="86"></span></p>
							  <p class="style18">

Tham khảo lịch bay và giá vé tham khảo cho các tuyến bay nội địa của Vietnam Airlines và Pacific Airlines </p></td>

                            </tr>
                          </tbody></table>

                          <p class="style2" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;""><span class="style12"><span class="style14"><span class="style12"><span class="style17"><strong>T</strong>ại 
E-Tourism, bạn c&oacute; thể dễ d&agrave;ng đặt v&eacute; m&aacute;y bay theo h&agrave;nh tr&igrave;nh 
mong muốn. E-Tourism
luôn sẵn sàng tư vấn cho bạn về các đường bay phù hợp nhất với hành
trình và có chi phí hợp lý nhất. Trong trường hợp có yêu cầu, bạn có
thể nhận vé tại văn phòng làm việc hoặc tại nhà riêng trong nội thành.
Phương thức thanh toán rất linh hoạt, thuận tiện bằng tiền mặt, chuyển
khoản, hoặc bằng thẻ tín dụng. Với khách đi du lịch theo từng đoàn, đi
du học hoặc định cư, chúng tôi đều áp dụng những mức giá ưu đãi. </span></span></span></span></p>
                          <p class="style13" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;"">Mời
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

                              

                              <td width="100%">

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

                                    <td height="30" valign="middle" bgcolor="#6699FF"><span class="style22"><strong>T&Igrave;M KIẾM CHUY&Ecirc;N BAY TRONG NƯỚC</strong></span><br>                                    </td>

                                  </tr>

                                  <tr>

                                    <td align="left" bgcolor="" valign="top"><form name="book2" method="post" action="maybay_datcho_ok.php" onsubmit="return isMail2(this)">

                                        <table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF" class="normal10">

                                          <tbody><tr align="left" valign="top">

                                            <td width="50%" height="37" valign="middle"><span class="style18">Kh&#7903;i h&agrave;nh  (*)</span></td>

                                            <td width="50%" valign="middle" class="style18">&#272;i &#273;&#7871;n (*)</td>

                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="48" valign="middle"><select name="khoihanh" class="style18" id="khoihanh">

                                                
                                          <option >

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

                                                
                                          <option selected="selected">

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

                                            <td valign="middle"><select name="diden" class="style18" id="select3" valign="middle">
                                              <option > Buôn Ma Thuột </option>
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
                                              <option selected="selected"> Hồ Chí Minh </option>
                                              <option> Tuy Hoà </option>
                                              <option> Vinh </option>
                                            </select></td>
                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="36" valign="middle"><span class="style18">Ngày bay  (*)</span></td>

                                            <td valign="middle"><span class="style18">Gi&#7901; d&#7921; ki&#7871;n </span></td>
                                          </tr>

                                          <tr align="left" valign="top">

                                            <td height="37" valign="middle">
<input name="ngaybay1" id="ngaybay1" value="" type="hidden">
<input name="hienthingaybay1" type="text" class="style18" id="hienthingaybay1" size="16">
    <span id="btnngaybay1" title="Select publishing date">
    <a href="javascript:%20void(0);"><img src="vembay.php_files/img.gif" border="0"></a></span>
<script type="text/javascript">
    Calendar.setup({inputField : "ngaybay1", ifFormat : "%Y-%m-%d", displayArea : "hienthingaybay1", daFormat : "%d-%m-%Y", button : "btnngaybay1", showsTime : false});
</script></td>

                                            <td valign="middle"><select name="giobay" class="style18" id="giobay2">

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

                                            <td height="39" valign="middle"><span class="style18">H&#7841;ng v&eacute; </span></td>

                                            <td valign="middle"><select name="hangve" class="style18" id="hangve2">

                                            <option selected="selected">Economy</option>

                                                <option>Business</option>

                                            </select></td>
                                          </tr>

                                          

                                          <tr align="left" valign="top">
                                                <td height="34" valign="middle"> <span class="style18">S&#7889; l&#432;&#7907;ng </span></td>
                                                <td align="left" valign="top">
                                                  <input name="soluong" class="style18" id="soluong" size="2" type="text" />                                                 </td>
                                            </tr>
                                        </tbody></table>

                                  

                                    </form></td>
                                  </tr>
                                </tbody></table>
                                <p>&nbsp;</p>
                                <p>&nbsp;</p>
                                <form action="" method="post" name="form2" class="style13" id="form2">
                                  <label>
                                  <input name="timkiemtrongnuoc" type="submit" class="style13" value="Tìm Kiếm" />
                                  </label>
                                </form>                                <p>&nbsp;</p></td>

                            </tr>
                          </tbody></table>

                          </td>

                        </tr>



                        <tr>






                        </tr>



                        <tr>



                          <td align="left" valign="top"><hr noshade="noshade" size="1"></td>



                        </tr>



                       



                      </tbody></table>
  </body>
</html>
