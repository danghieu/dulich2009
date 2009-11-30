<!--
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table align="center">
<tr>
<td align="right">User Name:</td>
<td><input type="text" name="us"/> </td>
</tr>
<tr>
<td align="right">Password:</td>
<td><input type="password" name="psw"/> </td>
</tr>
<tr>
<td><input type="submit" title="Logon"/> </td>
<td><input type="reset" title="Reset"/> </td>
</tr>
</table>
</form>
</body>
</html>
 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Website bán sách tr&#7921;c tuy&#7871;n BookOnline</title>
<style type="text/css">
<!--
.style6 {
	color: #FFFFFF;
	font-weight: bold;
}
.style8 {color: #030303}
.style9 {color: #030303; font-weight: bold; }
.style10 {
	color: #181818;
	font-weight: bold;
}
body {
	background-color: #C0DEFE;
}
.style11 {
	color: #FD2243;
	font-weight: bold;
}
.style12 {
	color: #F7172E;
	font-weight: bold;
}
.style13 {
	color: #DE0E32;
	font-weight: bold;
}
.style14 {
	color: #EE2241;
	font-weight: bold;
}
.style15 {
	font-size: 24px;
	font-weight: bold;
}
-->
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><title>FormDangNhap</title></head>
<body topmargin="0" bottommargin="0" leftmargin="0" rightmargin="0" background="">

<table bacellpadding="2" width="979" align="center" background="index.jsp_files/ev_bcd.gif" border="1" cellspacing="2" height="1585">

	<tbody><tr height="100" dir="rtl">
	<td colspan="3" align="center" height="120"><p><img src="index.jsp_files/banner.jpg" alt="1" width="824" height="122" /></p>	  </td></tr>
	  <tr><td colspan="3"><table width="967" border="0">
        <tbody><tr>
          <td width="50%" bgcolor="#b5dbbd"><b><a href="">Trang chủ</a> |  <a href="">Tin tức</a>| <a href=""> Nhà xuất bản </a>| <a href="">Thanh toán và vận chuyển </a>|  </b></td>
		  <td width="50%" bgcolor="#b5dbbd"><div align="right"><b><a><%=username%></a>|<a href="register.jsp"> Đăng ký </a> |<a href="logoutprocess.jsp"> <%=thoat %> </a>> |<a href=""> Giới thiệu | Liên hệ</a></b> </div></td>
        </tr>
		<tr>
			<td colspan="3"><img src="index.jsp_files/BarDong.GIF" alt="2" width="960" height="11"/></td>
		</tr>
      </tbody></table>   
	  </td>
	  </tr>
	
	
	<tr height="1000">
	  <td valign="top" width="18%"><table width="170" border="0" height="109">
        <tbody><tr>
          <td width="160" bgcolor="#93bbfd"><div class="style6" align="center">
            <div class="style8" align="center">
              <h4>Ch&#7911; &#273;&#7873; sách </h4>
            </div>
          </div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb" height="24"><div align="left"><span class="style9"><a href="">V&#259;n h&#7885;c n&#432;&#7899;c ngoài </a></span></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div class="style10" align="right">
            <div align="left"><a href="">V&#259;n h&#7885;c Vi&#7879;t Nam </a></div>
          </div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><strong><a href="">Truy&#7879;n ki&#7871;m hi&#7879;p </a></strong></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><span class="style9"><a href="">Sách kinh t&#7871; </a></span></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><span class="style9"><a href="">Sách ngo&#7841;i ng&#7919; </a></span> </div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><span class="style9"><a href="">Sách tin h&#7885;c </a></span></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><span class="style9"><a href="">T&#7915; &#273;i&#7875;n </a></span></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div class="style9" align="left"><a href="">T&#7911; sách H&#7841;t gi&#7889;ng tâm h&#7891;n </a></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><strong><a href="">T&#7911; sách S&#7889;ng &#273;&#7865;p </a></strong></div></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Tin h&#7885;c</a></strong> </td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Chu d&#7883;ch - Kinh d&#7883;ch </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Ki&#7871;n trúc - Xây d&#7921;ng </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Chính tr&#7883; - l&#7883;ch s&#7917; </a> </strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Tri&#7871;t h&#7885;c </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Danh nhân </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Du l&#7883;ch </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Y h&#7885;c </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">V&#259;n hóa ngh&#7879; thu&#7853;t</a> </strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Âm nh&#7841;c </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Khoa h&#7885;c t&#7921; nhiên </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Th&#7875; d&#7909;c th&#7875; thao </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Nông - lâm nghi&#7879;p </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Tâm lý giáo d&#7909;c </a> </strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Sách kin t&#7871; </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Sách giáo khoa </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Khoa h&#7885;c xã h&#7897;i </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Sách tôn giáo </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><strong><a href="">Sách thi&#7871;u nhi </a></strong></td>
        </tr>
        <tr>
          <td bgcolor="#b5ddfb"><div align="left"><strong><a href="">V&#259;n h&#7885;c Hán - Nôm </a></strong></div></td>
        </tr>
      </tbody></table>	  
	  <p>
	    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" title="1" width="167" height="175">
          <param name="movie" value="../Hinhanh/hlclock.swf"/>
          <param name="quality" value="high"/>
          <embed src="index.jsp_files/hlclock.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="167" height="175"/>
        </object>
	  </p>
	  <p class="style15" align="center">Qu&#7843;ng cáo </p>
	  <p align="center">
	    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" title="1" width="141" height="762">
          <param name="movie" value="../Hinhanh/haiben.swf"/>
          <param name="quality" value="high"/>
          <embed src="index.jsp_files/haiben.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="141" height="762"/>
        </object>
</p></td>
	  <td valign="top" width="56%"><table width="542" border="0">
        <tbody><tr>
          <td width="262" bgcolor="#b5e2fd"><form id="form1" name="form1" method="post" action="">
            <input name="textfield" id="textfield" type="text"/>
            <label for="Submit"></label>
            <input name="Submit" value="Tìm ki&#7871;m" id="Submit" type="submit"/>
          </form>          </td>
          <td width="270" bgcolor="#c1e3ff"><form id="form2" name="form2" method="post" action="">
Theo
<label for="select"></label>
            <select name="select" id="select">
              <option selected="selected">Tên sách</option>
              <option>Tác gi&#7843;</option>
              <option>Th&#7875; lo&#7841;i</option>
              <option>Nhà xu&#7845;t b&#7843;n</option>
            </select>
                                        </form>          </td>
        </tr>
      </tbody></table>
      <p><img src="index.jsp_files/book1.JPG" alt="3" width="42" height="34"/><img src="index.jsp_files/menu1.JPG" alt="4" width="499" height="24"/></p>
      <table width="527" border="0" height="261">
        <tbody><tr>
          <td width="140" height="257"><img src="index.jsp_files/toi_la_ai.jpg" alt="4" width="115" height="166"/></td>
          <td width="377"><table width="375" border="0">
            
            <tbody><tr>
              <td colspan="2"><div align="center"><strong>TÔI LÀ AI ? </strong></div></td>
              </tr>
            <tr>
              <td width="159">Tác gi&#7843; </td>
              <td>Ni s&#432; Ayya Khema </td>
            </tr>
            <tr>
              <td>Nhà xu&#7845;t b&#7843;n</td>
              <td>Ph&#432;&#417;ng &#272;ông </td>
            </tr>
            <tr>
              <td>Kh&#7893; sách </td>
              <td>14.5X20.5</td>
            </tr>
            <tr>
              <td>N&#259;m xu&#7845;t b&#7843;n </td>
              <td>2008</td>
            </tr>
            <tr>
              <td>S&#7889; trang </td>
              <td>220</td>
            </tr>
            <tr>
              <td>CD kèm theo </td>
              <td>Không</td>
            </tr>
            <tr>
              <td>Giá bìa </td>
              <td><span class="style14">37000 VN&#272; </span></td>
            </tr>
            <tr>
              <td>Giá gi&#7843;m </td>
              <td><span class="style13">33000 VN&#272; </span></td>
            </tr>
            <tr>
              <td colspan="2"><a href="">Xem n&#7897;i dung chi ti&#7871;t ....</a></td>
            </tr>
            <tr>
              <td colspan="2"><form id="form3" name="form3" method="post" action="">
                <label for="label"></label>
                
                  <div align="right"><img src="index.jsp_files/giohang.JPG" alt="1" width="42" height="37"/>
                      <input name="Mua" value="Mua" id="label" type="submit"/>
                    </div>
              </form>                <a href=""></a></td>
              </tr>
          </tbody></table></td>
        </tr>
      </tbody></table>
      <p>&nbsp;</p>
      <table width="528" border="0" height="84">
        <tbody><tr>
          <td width="140"><img src="index.jsp_files/72_1194178892_2658216_hhao_120x160_small.jpg" alt="4" width="112" height="160"/></td>
          <td width="378"><table width="375" border="0">
            
            <tbody><tr>
              <td colspan="2"><div align="center"><strong>&#272;I&#7878;P VIÊN HOÀN H&#7842;O </strong></div></td>
              </tr>
            <tr>
              <td width="158">Tác gi&#7843; </td>
              <td width="207">Larry Berman </td>
            </tr>
            <tr>
              <td>Nhà xu&#7845;t b&#7843;n</td>
              <td>V&#259;n hóa - thông tin </td>
            </tr>
            <tr>
              <td>Kh&#7893; sách </td>
              <td>14.5X20.5</td>
            </tr>
            <tr>
              <td>N&#259;m xu&#7845;t b&#7843;n </td>
              <td>2008</td>
            </tr>
            <tr>
              <td>S&#7889; trang </td>
              <td>438</td>
            </tr>
            <tr>
              <td>CD kèm theo </td>
              <td>Không</td>
            </tr>
            <tr>
              <td>Giá bìa </td>
              <td><span class="style12">90000 VN&#272; </span></td>
            </tr>
            <tr>
              <td>Giá gi&#7843;m </td>
              <td><span class="style11">72000 VN&#272; </span></td>
            </tr>
            <tr>
              <td colspan="2"><a href="">Xem n&#7897;i dung chi ti&#7871;t ....</a></td>
            </tr>
            <tr>
              <td colspan="2"><div align="right"><a href=""><img src="index.jsp_files/giohang.JPG" alt="1" width="42" height="37"/>
                  <input name="Mua2" value="Mua" id="Mua" type="submit"/>
              </a></div></td>
            </tr>
          </tbody></table></td>
        </tr>
      </tbody></table>
      <p>&nbsp;</p>
      <p><img src="index.jsp_files/book2.JPG" alt="5" width="42" height="34"/><img src="index.jsp_files/menu2.JPG" alt="6" width="500" height="31"/></p>
      <table width="533" border="1" height="772">
        <tbody><tr>
          <td width="278" height="286"><div align="center"><p><img src="index.jsp_files/67_1223696158_9779982_30293_115x200_small.jpg" alt="1" width="115" height="162"/></p>
              <p><strong>TH&#7886;A THU&#7852;N V&#7898;I CHÍNH MÌNH</strong></p>
              <p><strong><font color="red">Gía bán : 45000 VN&#272; </font></strong></p>
          </div></td>
          <td width="278"><div align="center">
            <p><img src="index.jsp_files/78_1223696621_7377885_lamitdcnhieu_115x200_small.jpg" alt="2" width="115" align="top" height="162"/></p>
            <p><strong>WORK LESS MAKE MORE </strong></p>
            <p><strong><font color="red">Gía bán: 56000 VN&#272; </font></strong></p>
          </div></td>
        </tr>
        <tr>
          <td width="278" height="287"><div align="center">
            <p><img src="index.jsp_files/80_1223264342_8507530_phonglan_fahasa_115x200_small.jpg" alt="3" width="115" height="164"/></p>
            <p><strong>N&#7918; HOÀNG PH&#431;&#7906;NG LAN</strong></p>
            <p><strong><font color="red">Giá bán : 92000 VN&#272;</font> </strong></p>
          </div></td>
          <td><div align="center"><p><img src="index.jsp_files/sp32926_177058.jpg" alt="4" width="160" height="200"/></p>
            <p><strong>10 BÍ M&#7852;T C&#7910;A TÌNH YÊU</strong></p>
            <p><strong><font color="red">Giá bán: 35000 VN&#272; </font></strong></p>
          </div></td>
        </tr>
        <tr>
          <td><div align="center"><p><img src="index.jsp_files/52_1223697163_8582596_30274_115x200_small.jpg" alt="5" width="115" height="169"/></p>
              <p><strong>GIAO TI&#7870;P &#272;I&#7878;N THO&#7840;I TRONG TH&#431;&#416;NG M&#7840;I</strong></p>
              <p><strong><font color="red">Gía bán: 78000 VN&#272; </font></strong></p>
          </div></td>
          <td><div align="center">
            <p><img src="index.jsp_files/66_1189013309_2087901_resizeimg_120x160_small.jpg" alt="6" width="101" height="160"/></p>
            <p><strong>KI&#7870;P SAU</strong></p>
            <p><strong><font color="red">Giá bán: 95000 VN&#272; </font></strong></p>
          </div></td>
        </tr>
      </tbody></table>      
      <p>&nbsp;</p></td>
	  <td valign="top" width="24%" align="center" height="1257">
	  <form id="form1" name="form1" method="post" action="process.jsp"> 
		<jsp:include page="DangNhap.jsp" flush="false"/>
		</td>
	</tr>
	
	
	<tr height="100">
	  <td colspan="3" align="center" height="143"><p>Copyright vs1.0</p>
      <p><a href="http://it2pro.net/">www.BookOnline.com</a></p></td>
	</tr>
</tbody></table>

</body>

</html>
