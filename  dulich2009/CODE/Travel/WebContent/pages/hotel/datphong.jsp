<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Booking room</title>
        <link rel="stylesheet" type="text/css"
              href="css/hotel.css" />
        
        <!--
.style1 {
        color: #FF0000;
        font-weight: bold;
}
        
        <style type="text/css">
            .hidden {
                display:none;
                float:left;
            }
        </style>
        -->
        <script type="text/javascript">
            function empty(){
                form1.elements["quantity"].value = ''; 
            }
            function choseRoomType()
            {
                var x=document.getElementById("roomType");
                var lbl = document.getElementById("lblRoomType");
                var bedType = document.getElementById("bedType");
                var quantity = document.getElementById("quantity");
                if(x.selectedIndex == 0) {	
                
                    lbl.style.display="none";
                    lbl.innerHTML="Số lượng";	
                    bedType.style.display="none";
                    quantity.style.display="none";
                }
                if(x.selectedIndex == 1) {
                
                    lbl.style.display="inline";
                    lbl.innerHTML ="Loại giường";
                    bedType.style.display="inline";
                    quantity.style.display="none";

                }
                if(x.selectedIndex == 2||x.selectedIndex == 3) {	
                
                    lbl.style.display="inline";
                    lbl.innerHTML="Số lượng";	
                    bedType.style.display="none";
                    quantity.style.display="inline";
                }

            }
        </script>
    </head>
    
    <body>
        <form action="UserServlet" method="get" name="order" id="form1">
        <% 
        Logger log = Logger.getLogger("datphong.jsp");
        String supplier = request.getParameter("supplier");
		
        String services = request.getParameter("services");
        String address = request.getParameter("address"); 
        log.info("supplier: " + supplier + "services: " + services);
        %>
        <input type="hidden" name="protocol" value="<%=Protocol.HOTEL_RES%>">
        <input type="hidden" name="supplier" value="<%=supplier%>">
        <input type="hidden" name="services" value="<%=services%>">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tbody><tr> 
                    <td class="TitleLevel2" height="30" align="center">&nbsp;&nbsp;<img src="dat_files/node.jpg" width="10" align="absmiddle" height="10"><font color="#0000ff">&nbsp; 
                    ĐẶT PH&Ograve;NG KH&Aacute;CH SẠN</font> </td>
                </tr>
                <tr> 
                    <td valign="top" align="left" height="8"><img src="dat_files/none.htm" width="1" height="1"></td>
                </tr>
                <tr> 
                    <td valign="top" align="left"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                            <tbody><tr> 
                                    <td valign="top" align="left">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                <tbody><tr> 
                                                        <td valign="top" align="center"><table width="100%" bgcolor="#e3e1b7" border="0" cellpadding="5" cellspacing="0">
                                                                <tbody><tr bgcolor="#ccc87b"> 
                                                                        <td height="25" colspan="2" align="right" valign="middle" bgcolor="#3399CC"><div align="left" class="style1">
                                                                                <div align="center">Thông 
                                                                                tin người sử dụng</div>
                                                                        </div></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td width="30%" align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Khách 
                                                                        hàng (<font color="#ff0000">*</font>)</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtCustomer" id="txtCustomer" size="35" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Người 
                                                                                liên hệ (<font color="#ff0000">*</font>)<br>
                                                                        </div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtContactperson" id="txtContactperson" size="35" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Hộp 
                                                                        thư điện tử (<font color="#ff0000">*</font>)</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtEmail" id="txtEmail" size="35" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Địa 
                                                                        chỉ (<font color="#ff0000">*</font>)</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtAddress" id="txtAddress" size="35" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Tỉnh/Thành 
                                                                        phố (<font color="#ff0000">*</font>)</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtCity" id="txtCity" size="35" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Tên 
                                                                        nước (<font color="#ff0000">*</font>)</div></td>
                                                                        <td bgcolor="#FFFFFF"><select name="txtCountry" id="txtCountry">
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
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Mã 
                                                                        vùng </div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtCode" id="txtCode" size="35" type="text" /></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Điện 
                                                                        thoại (<font color="#ff0000">*</font>) </div></td>
                                                                        <td bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                                                <tbody><tr> 
                                                                                        <td><input name="txtPhone" id="txtPhone" value=" Nơi làm việc " size="12" type="text"></td>
                                                                                        <td bgcolor="#FFFFFF"><input name="txtHome" id="txtHome" value=" Nhà riêng " size="12" type="text"></td>
                                                                                    </tr>
                                                                        </tbody></table></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Fax 
                                                                        </div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtFax" id="txtFax" size="35" type="text"></td>
                                                                    </tr>
                                                        </tbody></table></td>
                                                    </tr>
                                                    <tr> 
                                                        <td height="5"><img src="dat_files/none.htm" width="1" height="1"></td>
                                                    </tr>
                                                    <tr> 
                                                        <td valign="top" align="center"><table width="100%" bgcolor="#e3e1b7" border="1" cellpadding="5" cellspacing="0">
                                                                <tbody><tr bgcolor="#ccc87b"> 
                                                                        <td width="30%" align="right" valign="middle" bgcolor="#FFFFFF"><div align="right"><strong>Thông 
                                                                        tin khách sạn</strong></div></td>
                                                                        
                                                                    </tr>
                                                                    
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Tên 
                                                                        khách sạn </div></td>
                                                                        <td bgcolor="#FFFFFF"><b><%=hotelName%> 
                                                                                <input name="hotelName" id="txtHotelName" value="<%=hotelName%>" type="hidden">
                                                                        </b></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Địa 
                                                                                chỉ <br>
                                                                        </div></td>
                                                                        <td bgcolor="#FFFFFF"><b><%=address%>
                                                                            <input name="txtHotelsAddress" id="txtHotelsAddress" value="<%=address%>" type="hidden">
                                                                        </b></td>
                                                                    </tr>
                                                                    
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Số 
                                                                        người </div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtNoguest" id="txtNoguest" size="30" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Số 
                                                                        phòng </div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtNoroom" id="txtNoroom" size="30" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="top" bgcolor="#FFFFFF"><div align="right">Loại 
                                                                        phòng </div></td>
                                                                        <td bgcolor="#FFFFFF" COLSPAN=2> <select name="roomType" size="1" id="roomType" onchange="choseRoomType()" >
                                                                                <option value="bedding" selected="selected">Loại phòng</option>
                                                                                <option value="livingRom">Phòng ngủ - Livingroom</option>
                                                                                <option value="meetingRoom">Phòng họp - Meetingroom</option>
                                                                                <option value="partyRoom">Phòng tiệc - PartyRoom</option>
                                                                                
                                                                            </select>
                                                                            <span  id="lblRoomType">Loai giuong </span>
                                                                            <select  name="bedType" size="1" id="bedType">
                                                                                <option value="Bedding" selected="selected">Loại giường</option>
                                                                                <option value="Giường đơn">Giường đơn</option>
                                                                                <option value="Giường đôi">Giường đôi</option>
                                                                                <option value="Hai giường đơn">Hai giường đơn</option>
                                                                                <option value="Một đơn + một đôi">Một đơn 
                                                                                + một đôi</option>
                                                                            </select>
                                                                            <input  name="quantity" id="quantity" size="10" type="text" value="số lượng" onclick="empty()"/>
                                                                        </td>
                                                                    </tr>
                                                                    
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Ngày 
                                                                        đến </div></td>
                                                                        <td bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                                                <tbody><tr> 
                                                                                        <td><select name="sctDatearr" size="1" id="sctDatearr" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option selected="selected" value="Date">Ngày</option>
                                                                                                <option value="1">1</option>
                                                                                                <option value="2">2</option>
                                                                                                <option value="3">3</option>
                                                                                                <option value="4">4</option>
                                                                                                <option value="5">5</option>
                                                                                                <option value="6">6</option>
                                                                                                <option value="7">7</option>
                                                                                                <option value="8">8</option>
                                                                                                <option value="9">9</option>
                                                                                                <option value="10">10</option>
                                                                                                <option value="11">11</option>
                                                                                                <option value="12">12</option>
                                                                                                <option value="13">13</option>
                                                                                                <option value="14">14</option>
                                                                                                <option value="15">15</option>
                                                                                                <option value="16">16</option>
                                                                                                <option value="17">17</option>
                                                                                                <option value="18">18</option>
                                                                                                <option value="19">19</option>
                                                                                                <option value="20">20</option>
                                                                                                <option value="21">21</option>
                                                                                                <option value="22">22</option>
                                                                                                <option value="23">23</option>
                                                                                                <option value="24">24</option>
                                                                                                <option value="25">25</option>
                                                                                                <option value="26">26</option>
                                                                                                <option value="27">27</option>
                                                                                                <option value="28">28</option>
                                                                                                <option value="29">29</option>
                                                                                                <option value="30">30</option>
                                                                                                <option value="31">31</option>
                                                                                        </select> </td>
                                                                                        <td><select name="sctMonarr" size="1" id="sctMonarr" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option value="Month" selected="selected">Tháng</option>
                                                                                                <option value="1">1</option>
                                                                                                <option value="2">2</option>
                                                                                                <option value="3">3</option>
                                                                                                <option value="4">4</option>
                                                                                                <option value="5">5</option>
                                                                                                <option value="6">6</option>
                                                                                                <option value="7">7</option>
                                                                                                <option value="8">8</option>
                                                                                                <option value="9">9</option>
                                                                                                <option value="10">10</option>
                                                                                                <option value="11">11</option>
                                                                                                <option value="12">12</option>
                                                                                        </select></td>
                                                                                        <td><select name="sctYeararr" size="1" id="sctYeararr" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option value="Year" selected="selected">Năm</option>
                                                                                                <option value="2007">2007</option>
                                                                                                <option value="2008">2008</option>
                                                                                                <option value="2009">2009</option>
                                                                                                <option value="2010">2010</option>
                                                                                        </select></td>
                                                                                    </tr>
                                                                        </tbody></table></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Ngày 
                                                                        về </div></td>
                                                                        <td bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                                                <tbody><tr> 
                                                                                        <td><select name="sctDateFinish" size="1" id="sctDateFinish" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option selected="selected" value="Date">Ngày</option>
                                                                                                <option value="1">1</option>
                                                                                                <option value="2">2</option>
                                                                                                <option value="3">3</option>
                                                                                                <option value="4">4</option>
                                                                                                <option value="5">5</option>
                                                                                                <option value="6">6</option>
                                                                                                <option value="7">7</option>
                                                                                                <option value="8">8</option>
                                                                                                <option value="9">9</option>
                                                                                                <option value="10">10</option>
                                                                                                <option value="11">11</option>
                                                                                                <option value="12">12</option>
                                                                                                <option value="13">13</option>
                                                                                                <option value="14">14</option>
                                                                                                <option value="15">15</option>
                                                                                                <option value="16">16</option>
                                                                                                <option value="17">17</option>
                                                                                                <option value="18">18</option>
                                                                                                <option value="19">19</option>
                                                                                                <option value="20">20</option>
                                                                                                <option value="21">21</option>
                                                                                                <option value="22">22</option>
                                                                                                <option value="23">23</option>
                                                                                                <option value="24">24</option>
                                                                                                <option value="25">25</option>
                                                                                                <option value="26">26</option>
                                                                                                <option value="27">27</option>
                                                                                                <option value="28">28</option>
                                                                                                <option value="29">29</option>
                                                                                                <option value="30">30</option>
                                                                                                <option value="31">31</option>
                                                                                        </select> </td>
                                                                                        <td><select name="sctMonFinish" size="1" id="select2" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option value="Month" selected="selected">Tháng</option>
                                                                                                <option value="1">1</option>
                                                                                                <option value="2">2</option>
                                                                                                <option value="3">3</option>
                                                                                                <option value="4">4</option>
                                                                                                <option value="5">5</option>
                                                                                                <option value="6">6</option>
                                                                                                <option value="7">7</option>
                                                                                                <option value="8">8</option>
                                                                                                <option value="9">9</option>
                                                                                                <option value="10">10</option>
                                                                                                <option value="11">11</option>
                                                                                                <option value="12">12</option>
                                                                                        </select></td>
                                                                                        <td><select name="sctYearFinish" size="1" id="select3" style="font-family: Verdana,Arial,Helvetica; font-size: 9pt;">
                                                                                                <option value="Year" selected="selected">Năm</option>
                                                                                                <option value="2007">2007</option>
                                                                                                <option value="2008">2008</option>
                                                                                                <option value="2009">2009</option>
                                                                                                <option value="2010">2010</option>
                                                                                        </select></td>
                                                                                    </tr>
                                                                        </tbody></table></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Lựa 
                                                                        chọn KS khác #1</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtAlternateHotel1" id="txtAlternateHotel1" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF"><div align="right">Lựa 
                                                                        chọn KS khác #2</div></td>
                                                                        <td bgcolor="#FFFFFF"><input name="txtAlternateHotel2" id="txtAlternateHotel2" type="text"></td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td colspan="2" align="right" valign="middle" bgcolor="#FFFFFF">Trong trường 
                                                                            hợp khách sạn bạn lựa chọn đã hết phòng, chúng tôi sẽ 
                                                                        gửi đến bạn thông tin về khách sạn khác mà bạn yêu cầu.</td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="top" bgcolor="#FFFFFF"><div align="right">Yêu 
                                                                        cầu khác </div></td>
                                                                        <td bgcolor="#FFFFFF"><textarea name="txtComments" cols="30" rows="6" id="txtComments"></textarea></td>
                                                                    </tr>
                                                        </tbody></table></td>
                                                    </tr>
                                                    <tr> 
                                                        <td height="5"><img src="dat_files/none.htm" width="1" height="1"></td>
                                                    </tr>
                                                    <tr> 
                                                        <td valign="top" align="center"><table width="100%" border="0" cellpadding="3" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#e3e1b7">
                                                                <tbody><tr bgcolor="#ccc87b"> 
                                                                        <td width="30%" align="right" valign="middle" bgcolor="#FFFFFF"><div align="right"><strong>Phương 
                                                                        thức thanh toán</strong></div></td>
                                                                        <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                    </tr>
                                                                    <tr bgcolor="#FFFFFF"> 
                                                                        <td align="right" valign="middle"><div align="right">Lựa 
                                                                        chọn phương thức thanh toán</div></td>
                                                                        <td>&nbsp;</td>
                                                                    </tr>
                                                                    <tr> 
                                                                        <td align="right" valign="middle" bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td bgcolor="#FFFFFF"><select size="1" name="Payment">
                                                                                <option value="Payment">&nbsp;&nbsp;&nbsp;Thanh toán&nbsp;&nbsp;&nbsp;</option>
                                                                                <option selected="selected" value="Chuyển khoản">Chuyển khoản</option>
                                                                                <option value="Tiền mặt">Tiền mặt</option>
                                                                                <option value="Séc">Séc</option>
                                                                                <option value="Visa">Thẻ Visa</option>
                                                                                <option value="Mastercard">Thẻ Mastercard</option>
                                                                        </select></td>
                                                                    </tr>
                                                                    <tr bgcolor="#FFFFFF"> 
                                                                        <td colspan="2" align="right" valign="middle"><div align="left"><strong>Ghi 
                                                                                chú :</strong> <font color="#ff0000">Những thông tin 
                                                                        được đanh dấu (*) là yêu cầu tối thiểu.</font></div></td>
                                                                    </tr>
                                                        </tbody></table></td>
                                                    </tr>
                                                    <tr> 
                                                        <td valign="middle" align="center" height="50"><div align="justify">Vui 
                                                                lòng bấm chọn nút <font color="#990000">"<strong>Đặt 
                                                                phòng</strong>"</font> để cập nhật thông tin đặt phòng 
                                                        khách sạn vào hệ thống của Du Lịch Online.</div></td>
                                                    </tr>
                                                    <tr> 
                                                        <td valign="top" align="center" height="1"><table width="50%" align="center" border="0" cellpadding="0" cellspacing="0">
                                                                <tbody><tr> 
                                                                        <td align="center"><input name="Button" onClick="clickSubmit();" value="  Đặt phòng  " type="button"></td>
                                                                        <td align="center"><input name="clear" value=" Nhập lại " type="reset"></td>
                                                                    </tr>
                                                        </tbody></table></td>
                                                    </tr>
                                            </tbody></table>
                                    </td>
                                </tr>
                    </tbody></table></td>
                </tr>
                <tr>
                    <td valign="bottom" height="40"><div align="right">[ <a href="javascript:%20history.go(-1)" class="back_link"><strong>Go 
                    back</strong></a> ]</div></td>
                </tr>
        </tbody></table>
        </form>
    </body>
</html>
