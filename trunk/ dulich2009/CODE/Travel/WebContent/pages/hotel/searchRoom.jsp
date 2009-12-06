<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>

<head>
  <title>search room</title>
  <script type="text/javascript" src="../js/datepickercontrol.js" ></script>
  <link type="text/css" rel="stylesheet" href="../css/datepickercontrol_bluegray.css">
  <link type="text/css" rel="stylesheet" href="../css/content.css">
  <script language="JavaScript">
  function abc()
  {
  	var date = new Date();
	cal8 = document.getElementById("beginDate");
	cal8.setMinDate(date);
  }
  
  DatePickerControl.onSelect = function(inputid)
  {
  	if (inputid == "beginDate"){
  	
		cal8 = document.getElementById("beginDate");		
		cal9 = document.getElementById("endDate");
		cal9.setMinDate(cal8.value);
		cal9.disabled = false;
		setTimeout("document.getElementById('calendar9').focus()", 5);
	}
  }
  </script>
</head>

<body id="theme2" onload="abc()">

<form name="form1" action="SearchHotel" method="get">
<table width="450" border="0">
<tbody><tr>
<td>Địa điểm</td>
<td><select id="stateid" style="width: 160px; font-size: 11px; height: 22px;" name="stateid">
<option value="0">- - - Chọn Điểm đến - - -</option>
<option value="B2dVMVFp" selected="selected">TP HCM</option>
<option value="B2FVMVFp">Hà Nội</option>
<option value="B2hVMFFt">Cần Thơ</option>
<option value="B2VVMVFp">Đà Nẵng</option>
<option value="B2hVMVFt">An Giang</option>
<option value="B2dVMFFv">Bà Rịa - Vũng Tàu</option>
<option value="B2VVMVFv">Bình Định</option>
<option value="B2dVMFFp">Bình Dương</option>
<option value="B2dVMVFv">Bình Phước</option>
<option value="B2dVMFFt">Bình Thuận</option>
<option value="B2JVMVFv">Bắc Cạn</option>
<option value="B2JVM1Fp">Bắc Giang</option>
<option value="B2JVM1Fr">Bắc Ninh</option>
<option value="B2hVM1Fp">Bạc Liêu</option>
<option value="B2hVMFFp">Bến Tre</option>
<option value="B2hVM1Fr">Cà mau</option>
<option value="B2JVMVFr">Cao Bằng</option>
<option value="B2ZVMVFv">Daknông</option>
<option value="B2dVMFFr">Đồng Nai</option>
<option value="B2hVMVFr">Đồng Tháp</option>
<option value="B2ZVMVFt">Đắc Lắc</option>
<option value="B2NVMVFv">Điện Biên</option>
<option value="B2ZVMVFr">Gia Lai</option>
<option value="B2JVMVFp">Hà Giang</option>
<option value="B2FVMFFp">Hà Nam</option>
<option value="B2RVMVFt">Hà Tĩnh</option>
<option value="B2FVMVFt">Hà Tây</option>
<option value="B2NVMVFt">Hòa Bình</option>
<option value="B2hVM1Ft">Hậu Giang</option>
<option value="B2FVMVFv">Hải Dương</option>
<option value="B2FVMVFr">Hải Phòng</option>
<option value="B2FVMVFh">Hưng Yên</option>
<option value="B2VVMFFp">Khánh Hoà</option>
<option value="B2hVMFFr">Kiên Giang</option>
<option value="B2ZVMVFp">Kon Tum</option>
<option value="B2JVMVFt">Lào Cai</option>
<option value="B2dVMVFr">Lâm Đồng</option>
<option value="B2NVMVFp">Lai Châu</option>
<option value="B2JVMVFh">Lạng Sơn</option>
<option value="B2hVMVFp">Long An</option>
<option value="B2FVMFFr">Nam Định</option>
<option value="B2RVMVFr">Nghệ An</option>
<option value="B2FVMFFv">Ninh Bình</option>
<option value="B2dVMVFt">Ninh Thuận</option>
<option value="B2JVMFFv">Phú Thọ</option>
<option value="B2VVMVFh">Phú Yên</option>
<option value="B2RVMVFv">Quảng Bình</option>
<option value="B2VVMVFr">Quảng Nam</option>
<option value="B2VVMVFt">Quảng Ngãi</option>
<option value="B2JVM1Ft">Quảng Ninh</option>
<option value="B2RVMVFh">Quảng Trị</option>
<option value="B2hVMFFh">Sóc Trăng</option>
<option value="B2NVMVFr">Sơn La</option>
<option value="B2dVMVFh">Tây Ninh</option>
<option value="B2FVMFFt">Thái Bình</option>
<option value="B2JVMFFt">Thái Nguyên</option>
<option value="B2RVMFFp">Thừa Thiên - Huế</option>
<option value="B2RVMVFp">Thanh Hóa</option>
<option value="B2hVMVFv">Tiền Giang</option>
<option value="B2hVMFFv">Trà Vinh</option>
<option value="B2JVMFFp">Tuyên Quang</option>
<option value="B2hVMVFh">Vĩnh Long</option>
<option value="B2FVMVFs">Vĩnh Phúc</option>
<option value="B2JVMFFr">Yên Bái</option>
<option value="B2lVOFFh">Không xác định</option>
</select></td></tr>
<tr>
<td>Ng&agrave;y đến:</td>
<td><input type="text" name="beginDate" id="beginDate" size="13" datepicker="true" datepicker_format="DD/MM/YYYY"></td></tr>
<tr>
<td>Ng&agrave;y đi:</td>
<td><input type="text" name="endDate" id="endDate" size="13" datepicker="true" datepicker_format="DD/MM/YYYY" disabled="true"></td></tr>
<tr>
<td>Loại KS:</td>
<td><select style="width: 160px; font-size: 11px; height: 22px;" name="numberStar">
<option value="0">- - Chọn Loại KS - -</option>
<option value="1">1 sao</option>
<option value="2">2 sao</option>
<option value="3">3 sao</option>
<option value="4">4 sao</option>
<option value="5">5 sao</option>
<option value="47">Mini</option>
</select></td></tr>
<tr>
<td>Gi&aacute;:</td>
<td><select style="width: 160px; font-size: 11px; height: 22px;" name="price">
<option value="0">- - - Tất cả - -</option>
<option value="100">Form 100 ->150 USD</option>
<option value="">Form 150 ->200 USD</option>
<option value="">Form 100 ->150 USD</option>
<option value="">Form 100 ->150 USD</option>
</select></td></tr>
</tbody></table><p> 
 
 <input type="submit" value="Tìm" name="submit">
</p></form>


</body>
</html>
