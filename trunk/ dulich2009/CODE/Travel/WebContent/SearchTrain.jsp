<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        
        
    </head>
    <body>
        <div id="templatemo_maincontainer">
            <div id="templatemo_topsection">
                <jsp:include page="/pages/layout/header.jsp" flush="false"></jsp:include>
            </div>
            <div id="templatemo_left_column">
                <div id="templatemo_menu_top"></div>
                <div class="templatemo_menu"> 
                    <jsp:include page="/pages/layout/menu.jsp" flush="false"></jsp:include>
                </div>
                <div id="templatemo_contact">
                    <jsp:include page="/pages/layout/contact.jsp" flush="false"></jsp:include>  
                </div>
            </div>
            <div id="templatemo_right_column">
                <table border="0" cellpadding="0" cellspacing="0" width="59%">
                    <tbody><tr>
                            <td><img src="hotel_files/vietnam_train.jpg" width="640" height="120"/></td>
                        </tr>
                </tbody></table>
                <table width="640" border="1" height="120">
                    <tbody>
                        <tr>
                            <td colspan="2" style="font-weight: bold; color: blue; font-size: 14pt; font-family: Arial,Helvetica,sans-serif;" align="center">T&igrave;m ki&#7871;m v&eacute; t&agrave;u<br /></td>
                        </tr>
                        <tr>
                            <td align="right"><strong> Ng&agrave;y kh&#7903;i h&agrave;nh : </strong></td>
                            <td><input size="11" maxlength="10" name="checkin" value="" type="text" />
                            &nbsp;<a href="#" onclick="fPopCalendar(checkin,checkin); return false"></a>&nbsp;&nbsp;(n&#259;m-th&aacute;ng-ng&agrave;y)</td>
                        </tr>
                        <tr>
                            <td align="right"><strong>Ga xu&#7845;t ph&aacute;t : </strong></td>
                            <td><input size="36" maxlength="50" name="departure_station" value="" type="text" />
                            &nbsp;(<font color="#ff0000">*</font>)</td>
                        </tr>
                        <tr>
                            <td align="right"><strong>Ga &#273;&#7871;n : </strong></td>
                            <td><input size="36" maxlength="50" name="arrival_station" value="" type="text" />
                            &nbsp;(<font color="#ff0000">*</font>)</td>
                        </tr>
                        <tr> </tr>
                        <tr align="center">
                            <td colspan="2"><input class="button" name="sub_ok" value="Tìm" style="font-weight: bold;" type="submit" />
                                &nbsp;&nbsp;&nbsp;&nbsp;
                            <input value="Xóa" name="no_ok" class="button" style="font-weight: bold;" type="reset" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>