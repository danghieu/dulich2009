<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        
        <title>E-tourism - Hotel</title>
        
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
        <form action="UserServlet" method="get"> 
            <table width="200" border="1">
            <tr>
            <td>
            <table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tbody><tr>
                <td valign="top">
                    <table border="0" cellpadding="0" cellspacing="0" width="59%">
                        <tbody><tr>
                                <td><img src="hotel_files/banner_7.jpg" /></td>
                            </tr>
                    </tbody></table>
                </td>
            </tr>
            <tr>
            <td>
            <table border="0" cellpadding="5" cellspacing="0" width="100%">
            <tbody><tr>
            <td valign="top">
            <table border="0" cellpadding="0" cellspacing="0" width="59%">
                <tbody><tr>
                    <td width="64%">
                        <table width="604" height="40">
                            <tbody><tr>
                                    
                                    <td width="596" align="center" bgcolor="#3399FF"><span class="style1">Tìm kiếm khách sạn</span></td>
                                    
                                    
                                </tr>
                                
                        </tbody></table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table width="605" border="0" cellpadding="0" cellspacing="0" height="212">
                            <tbody><tr>
                                <td width="6"></td>
                                <td width="79"></td>
                            </tr>
                            <tr valign="top"></tr>
                            <tr>
                                <td height="49"></td>
                                <td>Thành phố</td>
                                <td><select name="city" id="city" style="width: 140px;" class="text_form_search" onChange="javascript:ajaxLoadURL('loader.php?mod=services&amp;act=city&amp;q='+this.value,'GET','','city',2,2);s_cat_id.value=this.value;">
                                        <option value="null"> Lựa chọn </option>
                                        <option value="Ha Noi">Hà Nội</option>
                                        <option value="Hai Phong">Hải Phòng</option>
                                        <option value="Nam Dinh">Nam Định</option>
										<option value="Khanh Hoa">Khánh Hòa</option>
                                </select>								  </td>
                                <td>Vị tr&iacute;<br></td>
                                <td><select name="location" id="v" style="width: 140px;" class="text_form_search" onChange="javascript:ajaxLoadURL('loader.php?mod=services&amp;act=city&amp;q='+this.value,'GET','','city',2,2);s_cat_id.value=this.value;">
                                        <option value=""> Lựa chọn</option>
                                        <option value="inside">inside </option>
                                        <option value="outside">outside</option>
                                        <option value="deck">deck</option>
                                        <option value ="lowerLevel">lowerLevel</option>
                                        <option value="upperLevel">upperLevel </option>
                                </select>								  </td>
                                
                            </tr>
                            <tr>
                                <td height="56"></td>
                                <td width="79">Khí hậu</td>
                                <td width="205">
                                    <select name="climate" id="climate" style="width: 140px;" class="text_form_search" onChange="s_cat_id.value=this.value">
                                        <option value="cool"> mát mẻ </option>
										<option value="warm"> ấm áp </option>
										<option value="snow"> có tuyết </option>
                                </select>								  </td>
                                <td width="79">Ngày đến </td>
                                <td width="236">
                                    <input class="text_form_search" id="dchkout" name="beginDay" style="width: 120px;" type="text"> 	
                                <img src="vistalandtravel_files/icon_calendar.jpg" alt="" onClick="popUpCalendar(this,document.getElementById('dchkout'), 'mm/dd/yyyy', fnSetDate);" align="absbottom" border="0">								  </td>
                                
                            </tr>
                            
                            <tr>
                                <td></td>
                                <td width="79">Giá($)</td>
                                <td width="205">
                                    <select name="price" class="text_form_search" style="width: 140px;">
                                        <option value="2">Dưới   $ 20.00</option>
                                        <option value="3">$ 20.00 - $ 50.00</option>
                                        <option value="4">$ 50.00 - $ 100.00</option>
                                        <option value="5">$ 100.00 - $ 200.00</option>
                                        <option value="6">Trên $ 200.00</option>
                                </select>								  </td>
                                <td width="79">Ng&agrave;y đi<br></td>
                                <td width="236">
                                    <input class="text_form_search" id="dchkout" name="endDay" style="width: 120px;" type="text"> 	
                                <img src="vistalandtravel_files/icon_calendar.jpg" alt="" onClick="popUpCalendar(this,document.getElementById('dchkout'), 'mm/dd/yyyy', fnSetDate);" align="absbottom" border="0">								  </td>
                            </tr>
                            <tr>
                                <td></td>
                                
                                <td>                                  </td>
                            </tr>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                            <td></td>
                            <td width="79">&nbsp;</td>
                            <td width="205" align="center" class="style3">Tiện nghi </td>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table width="605" height="110">
                            <tbody><tr>
                                    <td width="85" height="63"><input name="karaoke" value="Karaoke" type="checkbox">Karaoke</td>
                                    <td width="144"><input name="fitnessCenter" value="FitnessCenter" type="checkbox">Phòng tập đa năng</td>
                                    <td width="90"><input name="swimmingpool" value="SwimmingPool" type="checkbox">Bể bơi</td>
                                    <td width="112"><input name="spa" value="Spa" type="checkbox">Spa</td>
                                    <td width="150"><input name="meetingRoom" value="meetingRoom" type="checkbox">Phòng hội thảo</td>
                                </tr>
                                <tr>
                                    <td height="39"><input name="restaurant" value="restaurant" type="checkbox">Nhà hàng</td>
                                    <td><input name="nightClub" value="nightClub" type="checkbox">
                                    Câu lạc bộ đêm</td>
                                    <td><input name="parking" value="Parking" type="checkbox">Khu để xe</td>
                                    <td><input name="tennis" value="tennis" type="checkbox">Sân tennis</td>
                                    <td><input name="gardenCafe" value="GardenCafe" type="checkbox">Cafe vườn</td>
                                </tr>
                        </tbody></table>
                    </td>
                    
                </tr>
                <tr> <td>
                        <table width="584" height="40">
                            <tbody><tr>
                                    <td width="154">&nbsp;</td>
                                    <td width="69"><input name="submit" src="vistalandtravel_files/bt_search.jpg" type="image" border="0" /></td>
                                    <td width="345" align="center">&nbsp;</td>
                                    
                                </tr>
                                
                        </tbody></table>
                    </td>
                </tr>
                <tr>                    
                </tr>
                <tr>
                <td>
                
                <table border="0" cellpadding="0" cellspacing="0" width="91%">
                    <tbody><tr> 
                            <td height="5"><img src="hotel_files/pixel_1_1.gif" height="1" width="1"></td>
                        </tr>
                        <tr valign="top"> 
                            <td width="300"><div align="center"><font color="#ffffff"><img src="hotel_files/vietnammap.jpg" height="420" width="300"></font></div></td>
                            <td width="5"><div align="center"></div></td>
                            <td width="663" bgcolor="#73baff"><div align="center"> 
                                    <table border="0" cellpadding="0" cellspacing="0" width="75%">
                                        <tbody><tr> 
                                                <td><div align="center"><img src="hotel_files/destinations.jpg" height="71" width="303"></div></td>
                                            </tr>
                                            <tr> 
                                                <td><div align="center"> 
                                                        <table border="0" cellpadding="0" cellspacing="0" width="180">
                                                            <tbody><tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=1#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành 
                                                                    phố Cần Thơ</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=2#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Đảo 
                                                                    Cát Bà</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=3#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành 
                                                                    phố Đà Lạt</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=4#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành 
                                                                    Phố Đà Nẵng</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=5#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Vịnh 
                                                                    Hạ Long</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=6#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thủ 
                                                                    đô Hà Nội</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=7#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TP. 
                                                                    Hồ Chí Minh</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=8#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thị 
                                                                    xã Hội An</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=9#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành 
                                                                    phố Huế</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=10#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành 
                                                                    phố Nha Trang</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=11#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Phan 
                                                                    Thiết</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=12#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Đảo 
                                                                    Phú Quốc</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=13#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quy 
                                                                    Nhơn</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=14#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thị 
                                                                    trấn Sapa</td>
                                                                </tr>
                                                                <tr> 
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=15#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Vũng 
                                                                    Tàu</td>
                                                                </tr>
                                                        </tbody></table>
                                                </div></td>
                                            </tr>
                                            <tr> 
                                                <td><div align="center"></div></td>
                                            </tr>
                                    </tbody></table>
                            </div></td>
                        </tr>
                </tbody></table>
                
                
                <td width="36%">
                <tr>
                <tr>
                    <td>
                    
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody><tr> 
                                <td height="5" align="left" valign="top"><img src="hotel_files/none.htm" height="1" width="1"></td>
                            </tr>
                    </tbody></table>
                    
                    <td>
                </tr>
                <tr>
                    <td>
                    <td>
                </tr>
                
                <tr>
                    <td>
                    
                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tbody><tr> 
                                <td class="TitleLevel2" height="30">&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="5">&nbsp;</td>
                            </tr>
                            <tr> 
                                
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td height="5">&nbsp;</td>
                            </tr>
                    </tbody></table>
                    <td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                
                
                <tr>
                    <td>&nbsp;</td>
                </tr>
                
                
                
            </table>
            <input type="hidden" name="protocol" value="<%=Protocol.HOTEL_AVAIL%>">
        </form>
    </body>
</html>
