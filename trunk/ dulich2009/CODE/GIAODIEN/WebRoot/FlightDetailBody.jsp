<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'FlightDetailBody.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
 <!--  
  <body>
  <script>

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

				alert ("Bạn hãy nhập nơi đến của bạn !");

				document.book1.diden.focus();

				return false;

			}

			

			if (document.book1.ngaybay.value == "") {

				alert ("Bạn hãy nhập Ngày bay của bạn !");

				document.book1.ngaybay.focus();

				return false;

			}

			

			if (document.book1.ngayve.value == "") {

				alert ("Bạn hãy nhập Ngày về của bạn !");

				document.book1.ngayve.focus();

				return false;

			}



			if (document.book1.hoten.value == "") {

				alert ("Bạn hãy nhập tên của bạn !");

				document.book1.hoten.focus();

				return false;

			}

			if (document.book1.email.value == "") {

				alert ("Bạn hãy nhập e-mail!");

				document.book1.email.focus();

				return false;

			} else {

				var check = emailcheck(document.book1.email.value)

				if (!check) {

					alert("Sai định dạng E-mail !");

					document.book1.email.focus();

					return false;

				}

			}

			if (document.book1.dienthoai.value == "") {

				alert ("Bạn hãy nhập Điện thoại của bạn !");

				document.book1.dienthoai.focus();

				return false;

			}

			

			if (document.book1.yeucau.value == "") {

				alert ("Bạn hãy nhập Yêu cầu của bạn !");

				document.book1.yeucau.focus();

				return false;

			}

			if (selecteED == "0") {

				alert ("Bạn hãy chọn Mục biết dulichhe.com từ đâu ! ");

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
   <table width="200" border="1">
  <tr>
    <td><table width="701" bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0" height="238">
      <tbody>
        <tr bgcolor="#ccc87b">
          <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399FF"><div align="left" class="style1">
              <div align="center" class="style1 style2">Th&ocirc;ng 
                tin chuy&#7871;n bay </div>
          </div></td>
        </tr>
        <tr>
          <td width="31%" height="39" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">H&atilde;ng h&agrave;ng kh&ocirc;ng </div></td>
          <td width="69%" bgcolor="#FFFFFF"><input name="airline" id="airline" size="35" type="hidden" /></td>
        </tr>
        <tr>
          <td width="31%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">S&#7889; hi&#7879;u chuy&#7871;n bay </div></td>
          <td width="69%" bgcolor="#FFFFFF"><input name="flightNumber" id="flightNumber" size="35" type="hidden" />
            ,
            <input name="airplaneType" id="airpalneType" size="35" type="hidden" /></td>
        </tr>
        <tr>
          <td height="35" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center"><span class="style4">Xu&#7845;t ph&aacute;t</span><br />
          </div></td>
          <td bgcolor="#FFFFFF"><input name="airport" id="airport" size="15" type="hidden" />
            ,
            <input name="city" id="city" size="15" type="hidden" />
            ,
            <input name="country" id="country" size="15" type="hidden" />
            :
            <input name="time" id="time" size="15" type="hidden" />
            <input name="date" id="date" size="25" type="hidden" /></td>
        </tr>
        <tr>
          <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">&#272;&#7871;n</div></td>
          <td bgcolor="#FFFFFF"><input name="airport2" id="airport2" size="15" type="hidden" />
            ,
            <input name="city2" id="city2" size="15" type="hidden" />
            ,
            <input name="country2" id="country2" size="15" type="hidden" />
            :
            <input name="time2" id="time2" size="15" type="hidden" />
            <input name="date2" id="date2" size="25" type="hidden" /></td>
        </tr>
        <tr>
          <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">Lo&#7841;i v&eacute; </div></td>
          <td bgcolor="#FFFFFF"><input name="flightClass" id="flightClass" size="20" type="hidden" />
            , Gi&aacute;:
            <input name="price" id="price" size="20" type="hidden" /></td>
        </tr>
      </tbody>
    </table></td>
  </tr>
  <tr>
    <td><table width="63%" bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0">
              <tbody>
                <tr>
                  <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">
                    <textarea name="flightInformation" rows="6" cols="60"  wrap="virtual" type="hidden" onkeydown="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang, remLenmax_own_lang,100,500);" onkeyup="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang, remLenmax_own_lang,100,500);" onfocus="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang ,remLenmax_own_lang,100,500);">

                    </textarea>
                  </div></td>
                </tr>
              </tbody>
          </table>	</td>
  </tr>
  <tr align="center" valign="top">

                                            <td colspan="2"><a href="javascript:%20view_composer1('composer_div');"><img src="vembay.php_files/button-vembay.gif" width="156" border="0" height="37"></a>

                                                <div class="small" style="display: none;" id="composer_div">

                                                  <table class="normal10" width="42%" border="0" cellpadding="2" cellspacing="1">

                                                    <tbody><tr>

                                                      <td width="173" align="left">H&#7885; t&ecirc;n </td>

                                                      <td width="882" align="left" valign="top"><input name="hoten" class="form" id="hoten" size="30" type="text"> 
                                                      Nam/N&#7919;:  <select name="gioitinh" class="form_date" id="select">
                                                       
                                                        <option value="nam" selected="selected">Nam </option>
                                                        <option value="nu">Nữ </option>
                                                        
                                                      </select></td>
													  

                                                    </tr>

                                                    <tr>

                                                      <td align="left">Email*</td>

                                                      <td align="left" valign="top"><input name="email" class="form" id="email" size="30" type="text"></td>

                                                    </tr>

                                                    <tr>

                                                      <td align="left">&#272;i&#7879;n tho&#7841;i </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													<tr>

                                                      <td align="left">&#272;&#7883;a ch&#7881; </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													<tr>

                                                      <td align="left">Th&agrave;nh ph&#7889; </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													

                                                    <tr align="left" valign="top">

                                                      <td width="173">T&ecirc;n n&#432;&#7899;c </td>

                                                      <td valign="top"><select name="country" class="form_date" id="nhomtuoi">

                                                     <option value="" selected="selected">Chọn tên nước</option>
                            <option value="Albania">Albania </option>
                            <option value="Argentina">Argentina </option>
                            <option value="Australia">Australia </option>
                            <option value="Austria">Austria </option>
                            <option value="Bangladesh">Bangladesh </option>
                            <option value="Belarus">Belarus </option>
                            <option value="Belgium">Belgium </option>
                            <option value="Bolivia">Bolivia </option>
                            <option value="Brazil">Brazil </option>
                            <option value="Bulgaria">Bulgaria </option>
                            <option value="Canada">Canada </option>
                            <option value="Chile">Chile </option>
                            <option value="China">China </option>
                            <option value="Colombia">Colombia </option>
                            <option value="Czech Republic">Czech Republic</option>
                            <option value="Denmark">Denmark </option>
                            <option value="Ecuador">Ecuador </option>
                            <option value="Estonia">Estonia </option>
                            <option value="Finland">Finland </option>
                            <option value="France">France </option>
                            <option value="French Guiana">French Guiana </option>
                            <option value="Georgia">Georgia </option>
                            <option value="Germany">Germany </option>
                            <option value="Greece">Greece </option>
                            <option value="HongKong">HongKong</option>
                            <option value="Hungary">Hungary </option>
                            <option value="Iceland ">Iceland </option>
                            <option value="India">India </option>
                            <option value="Indonesia">Indonesia </option>
                            <option value="Ireland">Ireland </option>
                            <option value="Italy">Italy </option>
                            <option value="Japan">Japan </option>
                            <option value="Latvia">Latvia </option>
                            <option value="Lithuania">Lithuania </option>
                            <option value="Luxembourg">Luxembourg </option>
                            <option value="Malaysia">Malaysia </option>
                            <option value="Mexico">Mexico </option>
                            <option value="Netherlands">Netherlands </option>
                            <option value="North Korea">North Korea </option>
                            <option value="Norway">Norway </option>
                            <option value="Paraguay">Paraguay </option>
                            <option value="Peru">Peru </option>
                            <option value="Poland">Poland </option>
                            <option value="Portugal">Portugal </option>
                            <option value="Romania">Romania </option>
                            <option value="Russia">Russia </option>
                            <option value="Slovakia">Slovakia </option>
                            <option value="South Africa">South Africa </option>
                            <option value="South Korea">South Korea </option>
                            <option value="Spain">Spain </option>
                            <option value="Suriname">Suriname </option>
                            <option value="Sweden">Sweden </option>
                            <option value="Switzerland">Switzerland </option>
                            <option value="Turkey">Turkey </option>
                            <option value="Ukraine">Ukraine </option>
                            <option value="United Kingdom">United Kingdom </option>
                            <option value="United States">United States </option>
                            <option value="Uruguay">Uruguay </option>
                            <option value="Venezuela">Venezuela </option>
                            <option value="Vietnam">Vietnam</option>

                                                          
                                                      </select></td>

                                                    </tr>

                                                   
                                                    <tr>

                                                      <td align="left">&nbsp;</td>

                                                      <td align="center" valign="top"><input name="datvemaybay" id="datvemaybay" value="ĐẶT VÉ MÁY BAY" type="submit"></td>

                                                    </tr>

</tbody></table>

                                              </div>

                                              <span style="padding: 5px 0px 0px 10px;">

                                                <input name="action" id="action" value="quocte" type="hidden">

                                              </span></td>

                                          </tr>
</table>
  </body> --> 
  
  <body>
  <script>

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

				alert ("Bạn hãy nhập nơi đến của bạn !");

				document.book1.diden.focus();

				return false;

			}

			

			if (document.book1.ngaybay.value == "") {

				alert ("Bạn hãy nhập Ngày bay của bạn !");

				document.book1.ngaybay.focus();

				return false;

			}

			

			if (document.book1.ngayve.value == "") {

				alert ("Bạn hãy nhập Ngày về của bạn !");

				document.book1.ngayve.focus();

				return false;

			}



			if (document.book1.hoten.value == "") {

				alert ("Bạn hãy nhập tên của bạn !");

				document.book1.hoten.focus();

				return false;

			}

			if (document.book1.email.value == "") {

				alert ("Bạn hãy nhập e-mail!");

				document.book1.email.focus();

				return false;

			} else {

				var check = emailcheck(document.book1.email.value)

				if (!check) {

					alert("Sai định dạng E-mail !");

					document.book1.email.focus();

					return false;

				}

			}

			if (document.book1.dienthoai.value == "") {

				alert ("Bạn hãy nhập Điện thoại của bạn !");

				document.book1.dienthoai.focus();

				return false;

			}

			

			if (document.book1.yeucau.value == "") {

				alert ("Bạn hãy nhập Yêu cầu của bạn !");

				document.book1.yeucau.focus();

				return false;

			}

			if (selecteED == "0") {

				alert ("Bạn hãy chọn Mục biết dulichhe.com từ đâu ! ");

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
   <table width="200" border="1">
  <tr>
    <td><table bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0" style="width: 794px; height: 291px;">
      <tbody>
        <tr bgcolor="#ccc87b">
          <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399FF"><div align="left" class="style1">
              <div align="center" class="style1 style2">Th&ocirc;ng 
                tin chuy&#7871;n bay </div>
          </div></td>
        </tr>
        <tr>
          <td width="31%" height="39" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">H&atilde;ng h&agrave;ng kh&ocirc;ng </div></td>
          <td width="69%" bgcolor="#FFFFFF">Vietnam Airlines</td>
        </tr>
        <tr>
          <td width="31%" height="38" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style5">S&#7889; hi&#7879;u chuy&#7871;n bay </div></td>
          <td width="69%" bgcolor="#FFFFFF">VN217 - Boeing 777
         </td>
        </tr>
        <tr>
          <td height="35" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center"><span class="style4">Xu&#7845;t ph&aacute;t</span><br />
          </div></td>
          <td bgcolor="#FFFFFF">11:00 - 01/01/2010 - Nội Bài
            ,
             Hà Nội
            ,
            Việt Nam
            </td>
        </tr>
        <tr>
          <td height="34" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">&#272;&#7871;n</div></td>
          <td bgcolor="#FFFFFF">13:00 - 01/01/2010 - Tân Sơn Nhất
            ,
             TP.Hồ Chí Minh
            ,
            Việt Nam</td>
        </tr>
        <tr>
          <td height="50" align="right" valign="middle" bgcolor="#FFFFFF"><div align="center" class="style4">Lo&#7841;i v&eacute; </div></td>
          <td bgcolor="#FFFFFF">Economy
            , Gi&aacute;: 1,500,000 VND
            </td>
        </tr>
      </tbody>
    </table></td>
  </tr>
  <tr>
    <td><table bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0" style="width: 519px; height: 96px;">
              <tbody>
                <tr>
                  <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">
                    <textarea name="flightInformation" rows="4" cols="60"  wrap="virtual" type="hidden" onkeydown="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang, remLenmax_own_lang,100,500);" onkeyup="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang, remLenmax_own_lang,100,500);" onfocus="textCounter(this.form.quick_description_own_lang,this.form.remLenmin_own_lang ,remLenmax_own_lang,100,500);">
 Được phục vụ một bữa ăn nhẹ miễn phí
                    </textarea>
                  </div></td>
                </tr>
              </tbody>
          </table>	</td>
  </tr>
  <tr align="center" valign="top">

                                            <td colspan="2"><a href="javascript:%20view_composer1('composer_div');"><img src="vembay.php_files/button-vembay.gif" width="156" border="0" height="37"></a>

                                                <div class="small" style="display: none;" id="composer_div">

                                                  <table class="normal10" width="56%" border="0" cellpadding="2" cellspacing="1">

                                                    <tbody><tr>

                                                      <td width="173" align="left">H&#7885; t&ecirc;n </td>

                                                      <td width="882" align="left" valign="top"><input name="hoten" class="form" id="hoten" size="30" type="text"> 
                                                      Nam/N&#7919;:  <select name="gioitinh" class="form_date" id="select">
                                                       
                                                        <option value="nam" selected="selected">Nam </option>
                                                        <option value="nu">Nữ </option>
                                                        
                                                      </select></td>
													  

                                                    </tr>

                                                    <tr>

                                                      <td align="left">Email*</td>

                                                      <td align="left" valign="top"><input name="email" class="form" id="email" size="30" type="text"></td>

                                                    </tr>

                                                    <tr>

                                                      <td align="left">&#272;i&#7879;n tho&#7841;i </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													<tr>

                                                      <td align="left">&#272;&#7883;a ch&#7881; </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													<tr>

                                                      <td align="left">Th&agrave;nh ph&#7889; </td>

                                                      <td align="left" valign="top"><input name="dienthoai" class="form" id="dienthoai" type="text"></td>

                                                    </tr>
													

                                                    <tr align="left" valign="top">

                                                      <td width="173">T&ecirc;n n&#432;&#7899;c </td>

                                                      <td valign="top"><select name="country" class="form_date" id="nhomtuoi">

                                                     <option value="" selected="selected">Chọn tên nước</option>
                            <option value="Albania">Albania </option>
                            <option value="Argentina">Argentina </option>
                            <option value="Australia">Australia </option>
                            <option value="Austria">Austria </option>
                            <option value="Bangladesh">Bangladesh </option>
                            <option value="Belarus">Belarus </option>
                            <option value="Belgium">Belgium </option>
                            <option value="Bolivia">Bolivia </option>
                            <option value="Brazil">Brazil </option>
                            <option value="Bulgaria">Bulgaria </option>
                            <option value="Canada">Canada </option>
                            <option value="Chile">Chile </option>
                            <option value="China">China </option>
                            <option value="Colombia">Colombia </option>
                            <option value="Czech Republic">Czech Republic</option>
                            <option value="Denmark">Denmark </option>
                            <option value="Ecuador">Ecuador </option>
                            <option value="Estonia">Estonia </option>
                            <option value="Finland">Finland </option>
                            <option value="France">France </option>
                            <option value="French Guiana">French Guiana </option>
                            <option value="Georgia">Georgia </option>
                            <option value="Germany">Germany </option>
                            <option value="Greece">Greece </option>
                            <option value="HongKong">HongKong</option>
                            <option value="Hungary">Hungary </option>
                            <option value="Iceland ">Iceland </option>
                            <option value="India">India </option>
                            <option value="Indonesia">Indonesia </option>
                            <option value="Ireland">Ireland </option>
                            <option value="Italy">Italy </option>
                            <option value="Japan">Japan </option>
                            <option value="Latvia">Latvia </option>
                            <option value="Lithuania">Lithuania </option>
                            <option value="Luxembourg">Luxembourg </option>
                            <option value="Malaysia">Malaysia </option>
                            <option value="Mexico">Mexico </option>
                            <option value="Netherlands">Netherlands </option>
                            <option value="North Korea">North Korea </option>
                            <option value="Norway">Norway </option>
                            <option value="Paraguay">Paraguay </option>
                            <option value="Peru">Peru </option>
                            <option value="Poland">Poland </option>
                            <option value="Portugal">Portugal </option>
                            <option value="Romania">Romania </option>
                            <option value="Russia">Russia </option>
                            <option value="Slovakia">Slovakia </option>
                            <option value="South Africa">South Africa </option>
                            <option value="South Korea">South Korea </option>
                            <option value="Spain">Spain </option>
                            <option value="Suriname">Suriname </option>
                            <option value="Sweden">Sweden </option>
                            <option value="Switzerland">Switzerland </option>
                            <option value="Turkey">Turkey </option>
                            <option value="Ukraine">Ukraine </option>
                            <option value="United Kingdom">United Kingdom </option>
                            <option value="United States">United States </option>
                            <option value="Uruguay">Uruguay </option>
                            <option value="Venezuela">Venezuela </option>
                            <option value="Vietnam">Vietnam</option>

                                                          
                                                      </select></td>

                                                    </tr>

                                                   
                                                    <tr>

                                                      <td align="left">&nbsp;</td>

                                                      <td align="center" valign="top"><input name="datvemaybay" id="datvemaybay" value="ĐẶT VÉ MÁY BAY" type="submit"></td>

                                                    </tr>

</tbody></table>

                                              </div>

                                              <span style="padding: 5px 0px 0px 10px;">

                                                <input name="action" id="action" value="quocte" type="hidden">

                                              </span></td>

                                          </tr>
</table>
  </body>
</html>
