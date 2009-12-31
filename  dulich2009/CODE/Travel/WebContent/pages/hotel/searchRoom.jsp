<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
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
            <table >
            <tr>
            <td>

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

            <tbody><tr>
            <td valign="top">
            
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
                        <table width="605" border="0" cellpadding="0" cellspacing="0" height="166">
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
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
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
                                <td width="79"></td>
                                <td width="236">&nbsp;</td>
                            </tr>
                            
                        <tr>
                                <td height="49"></td>
                                <td width="79">V&#7883; tr&iacute; </td>
                                <td width="236"><select name="location" id="select" style="width: 140px;" class="text_form_search" onChange="javascript:ajaxLoadURL('loader.php?mod=services&amp;act=city&amp;q='+this.value,'GET','','city',2,2);s_cat_id.value=this.value;">
                                    <option value=""> L&#7921;a ch&#7885;n </option>
                                    <option value="inside">inside </option>
                                    <option value="outside">outside</option>
                                    <option value="deck">deck</option>
                                    <option value ="lowerLevel">lowerLevel</option>
                                    <option value="upperLevel">upperLevel </option>
                                </select></td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
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
                            <td width="205" align="center" class="style3"><input name="submit" src="vistalandtravel_files/bt_search.jpg" type="image" border="0" /></td>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        
                    </td>
                    
                </tr>
                <tr> <td>
                        <table width="584" height="40">
                            <tbody>
                                
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
                                                                    <td class="mnhotel" onMouseOver="this.className='mnhotel_ac';" onMouseOut="this.className='mnhotel';" onClick="window.location.href='?lang=0&amp;mn1=1&amp;mn2=8&amp;city=1#destination';" height="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thành phố cần thơ </td>
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
