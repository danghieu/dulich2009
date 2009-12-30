<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
            
            function setHotel()
            {
                var hotel = document.getElementById("search_hotel");
				var flight = document.getElementById("search_flight");
				var car = document.getElementById("search_car");
				var train = document.getElementById("search_train");
                var city = document.getElementById("city");
				var climate = document.getElementById("climate");
                if(hotel.checked == true) {	
                    city.style.display="inline";
                    city.innerHTML="Thành phố";	
					climate.style.display="inline";
                    climate.innerHTML ="Khí hậu";
                }if(car.checked == true ||flight.checked == true ||
				train.checked == true ) {	
                    city.style.display="inline";
                    city.innerHTML="Xuất phát";	
					climate.style.display="inline";
                    climate.innerHTML ="Đích";
                }

            }
        </script>
</head>

<body>
<div class="innertube">
    <h1>Tin mới nhất  </h1>
    <p><a href="#">Lễ hội Du lịch Hạ Long 2010</a><br />
   Lễ hội Du lịch Hạ Long 2010 dự kiến sẽ diễn ra từ ngày 29-4 đến 2-5-2010 tại Khu du lịch Bãi Cháy và Trung tâm TP Hạ Long.	<a href="#">	<em>Xem tiếp</em> <img src="http://tintuc.congdulich.com/images/ic_next_03.gif" alt="Xem tiếp" border="0" /></a></p>
    <p><a href="#"><strong>Hậu Giang: Khai mạc hội chợ ẩm thực và chợ nổi trên sông</strong></a><br />
   Nằm trong các hoạt động Festival lúa gạo Việt Nam, chiều ngày 28/11/2009 đã khai mạc Hội chợ ẩm thực và chợ nổi trên sông. <a href="#"><em>Xem tiếp</em> <img src="http://tintuc.congdulich.com/images/ic_next_03.gif" alt="Xem tiếp" border="0" /></a></p>
  </div>
  <div id="templatemo_destination">
    <h2>ĐỊA ĐIỂM </h2>
    <p><img src="images/templatemo_photo1.jpg" alt="templatemo.com" width="85" height="85" /> <img src="images/templatemo_photo2.jpg" alt="templatemo.com" width="85" height="85" /> <img src="images/templatemo_photo3.jpg" alt="templatemo.com" width="85" height="85" /></p>
    <h2>SỰ KIỆN </h2>
    <p><strong><span class="post_date">Du lịch Việt Nam </span><br />
    </strong>
      Nội dung. </p>
    <p><a href="http://validator.w3.org/check?uri=referer"></a> <a href="http://jigsaw.w3.org/css-validator/check/referer"></a></p>
  </div>
  <div id="templatemo_search">
    <div class="search_top"></div>
    <div class="sarch_mid">
      <form id="form1" name="form1" method="get" action="UserServlet">
        <table width="247">
          <tr>
            <td width="74"><input type="radio" name="search" value="radio" id="search_flight" onchange="setHotel()" />
              <strong>Máy bay </strong></td>
            <td width="171"><label>
              <input type="radio" name="search" value="radio" id="search_hotel" onchange="setHotel()" />
              <strong>Khách sạn </strong></label></td>
          </tr>
          <tr>
            <td><input type="radio" name="search" value="radio" id="search_car" onchange="setHotel()" />
              <strong>Car</strong></td>
            <td><label>
              <input type="radio" name="search" value="radio" id="search_train" onchange="setHotel()" />
              <strong>Tầu hỏa </strong></label></td>
          </tr>
          <tr>
            <td><strong><span  id="city">Xuất phát</span> </strong></td>
            <td><label>
              <input type="text" name="destination_from" id="destination_from" />
            </label></td>
          </tr>
          <tr>
            <td><strong><span  id="climate">Đích</span> </strong></td>
            <td><label>
              <input type="text" name="destination_to" id="destination_to" />
            </label></td>
          </tr>
          <tr>
            <td><strong>Ngày đi </strong></td>
            <td><label>
              <input name="depart" type="text" id="depart" value="dd-MM-yyyy" size="14" />
            </label></td>
          </tr>
          <tr>
            <td><strong>Ngày về </strong></td>
            <td><input name="return" type="text" id="return" value="dd-MM-yyyy" size="14" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input name="submit" src="images/templatemo_search_button.jpg" alt="templatemo.com" width="78" height="28" border="0"  type="image" /></td>
          </tr>
        </table>
        </form>
      </div>
    <div class="search_bot"></div>
  </div>
</body>
</html>
