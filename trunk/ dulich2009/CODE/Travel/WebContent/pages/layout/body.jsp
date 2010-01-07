<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="text/javascript">
            
            function typeSearch()
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
    </script></head>
    
    <body>
        <div class="innertube">
            <h1>Tin m&#7899;i nh&#7845;t   </h1>
          <p><a href=#">L&#7877; h&#7897;i Du l&#7883;ch H&#7841; Long 2010</a><br />
              L&#7877; h&#7897;i Du l&#7883;ch H&#7841; Long 2010 d&#7921; ki&#7871;n s&#7869; di&#7877;n ra t&#7915; ng&agrave;y 29-4 &#273;&#7871;n 2-5-2010 t&#7841;i Khu du l&#7883;ch B&atilde;i Ch&aacute;y v&agrave; Trung t&acirc;m TP H&#7841; Long.	<a href="#">	<em>Xem ti&#7871;p </em> <img src="http://tintuc.congdulich.com/images/ic_next_03.gif" alt="Xem tiáº¿p" border="0" /></a></p>
            <p><a href="#">H&#7853;u Giang: Khai m&#7841;c h&#7897;i ch&#7907; &#7849;m th&#7921;c v&agrave; ch&#7907; n&#7893;i tr&ecirc;n s&ocirc;ng</a><br />
              L&#7877; h&#7897;i Du l&#7883;ch H&#7841; Long 2010 d&#7921; ki&#7871;n s&#7869; di&#7877;n ra t&#7915; ng&agrave;y 29-4 &#273;&#7871;n 2-5-2010 t&#7841;i Khu du l&#7883;ch B&atilde;i Ch&aacute;y v&agrave; Trung t&acirc;m TP H&#7841; Long. <a href="#"><em>Xem ti&#7871;p </em> <img src="http://tintuc.congdulich.com/images/ic_next_03.gif" alt="Xem tiáº¿p" border="0" /></a></p>
        </div>
        <div id="templatemo_destination">
            <h2>ĐỊA ĐIỂM </h2>
            <p><img src="images/templatemo_photo1.jpg" alt="templatemo.com" width="85" height="85" /> <img src="images/templatemo_photo2.jpg" alt="templatemo.com" width="85" height="85" /> <img src="images/templatemo_photo3.jpg" alt="templatemo.com" width="85" height="85" /></p>
            <h2>S&#7920; KI&#7878;N </h2>
            <p><strong><span class="post_date">Du l&#7883;ch Vi&#7879;t Nam </span><br />
                </strong>
            N&#7897;i dung. </p>
            <p><a href="http://validator.w3.org/check?uri=referer"></a> <a href="http://jigsaw.w3.org/css-validator/check/referer"></a></p>
        </div>
        <div id="templatemo_search">
            <div class="search_top"></div>
            <div class="sarch_mid">
                <form id="form1" name="form1" method="get" action="UserServlet">
                    <table width="247">
                        <tr>
                            <td width="74"><input type="radio" name="protocol" value="<%=Protocol.FLIGHT_AVAIL%>" id="search_flight" onchange="typeSearch()" />
                            <strong>M&aacute;y bay </strong></td>
                            <td width="171"><label>
                                    <input type="radio" name="protocol" value="<%=Protocol.HOTEL_AVAIL%>" id="search_hotel" onchange="typeSearch()"  checked="checked" />
                            <strong>Kh&aacute;ch s&#7841;n </strong></label></td>
                        </tr>
                        <tr>
                            <td><input type="radio" name="protocol" value="<%=Protocol.CAR_AVAIL%>" id="search_car" onchange="typeSearch()" />
                            <strong>Car</strong></td>
                            <td><label>
                                    <input type="radio" name="protocol" value="<%=Protocol.TRAIN_AVAIL%>" id="search_train" onchange="typeSearch()" />
                            <strong>T&agrave;u h&#7887;a </strong></label></td>
                        </tr>
                        <tr>
                            <td><strong><span  id="city">Xu&#7845;t ph&aacute;t </span> </strong></td>
                            <td><label>
                                    <input type="text" name="city" id="destination_from" />
                            </label></td>
                        </tr>
                        <tr>
                            <td><strong><span  id="climate">&#272;&iacute;ch</span></strong></td>
                            <td><label>
                                    <input type="text" name="climate" id="destination_to" />
                            </label></td>
                        </tr>
                        <tr>
                            <td><strong>Ng&agrave;y &#273;i </strong></td>
                            <td><label>
                                    <input name="startDate" type="text" id="startDate" value="17-01-2010" size="14" />
                            </label></td>
                        </tr>
                        <tr>
                            <td><strong>Ng&agrave;y v&#7873; </strong></td>
                            <td><input name="returnDate" type="text" id="returnDate" value="01-02-2010" size="14" /></td>
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
