<%@ page language="java" import="org.apache.log4j.Logger"%>
<%@ page language="java" import="com.ptit.travel.agent.communication.Protocol" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>E-Tourism: Du l&#7883;ch b&#7889;n ph&#432;&#417;ng</title>
        <link href="templatemo_style.css" rel="stylesheet" type="text/css" />
        <style type="text/css">

<!--
.style12 {font-size: 18}
.style13 {font-weight: bold; font-size: 16px; }
.style14 {font-size: 18px}
.style17 {font-size: 16px}
.style18 {font-size: 16px; color: #000000; }
.style2 {font-weight: bold}
.style22 {font-weight: bold; font-size: 16px; color: #FFFFFF; }
.style4 {color: #FF0000}

-->
        </style>

<!--[if lt IE 7]>
<link href="/legacybundles/rebrand.ie6-RELEASE.17.2.42391.css" type="text/css" rel="stylesheet" />
<![endif]--><script type="text/javascript" src="hotel_files/lib-RELEASE.js"></script>
<script type="text/javascript" src="hotel_files/yourHotels-RELEASE.js"></script>
<script type="text/javascript" src="hotel_files/yahoo-min-RELEASE.js"></script>
<script type="text/javascript" src="hotel_files/connection-min-RELEASE.js"></script>

<link href="http://asia.hotels.com/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon">
<style media="all" type="text/css">.js_inline_visible{display:inline;}.js_inline_hidden{display:none;}.js_visible{display:block;}.js_hidden{display:none;}.tab_inactive{display:none;}.custom_dropdown:hover .dropdown_menu{display:none;}.custom_dropdown a.focus iframe{display:none;}.custom_dropdown_ie6 .dropdown_menu{display:none;visibility:visible;}.header_row_3 .currency:hover{background-color:transparent;}.header_row_3 .currency:hover .copy,.header_row_3 .currency:hover .active_currency_symbol{color:#e6690e;}.header_row_3 .currency:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .currencyActive,.header_row_3 .currencyActive:hover{background-color:#404040;}.header_row_3 .currencyActive .copy,.header_row_3 .currencyActive .active_currency_symbol,.header_row_3 .currencyActive:hover .copy,.header_row_3 .currencyActive:hover .active_currency_symbol{color:#fff;}.header_row_3 .currencyActive .sprite_icon_dropdown,.header_row_3 .currencyActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .currencyActive .dropdown_menu{display:block;}.header_row_3 .language:hover{background-color:transparent;}.header_row_3 .language:hover .copy{color:#e6690e;}.header_row_3 .language:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .languageActive,.header_row_3 .languageActive:hover{background-color:#404040;}.header_row_3 .languageActive .copy,.header_row_3 .languageActive:hover .copy{color:#fff;}.header_row_3 .languageActive .sprite_icon_dropdown,.header_row_3 .languageActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .languageActive .dropdown_menu{display:block;}</style><style media="all" type="text/css">.js_inline_visible{display:inline;}.js_inline_hidden{display:none;}.js_visible{display:block;}.js_hidden{display:none;}.tab_inactive{display:none;}.custom_dropdown:hover .dropdown_menu{display:none;}.custom_dropdown a.focus iframe{display:none;}.custom_dropdown_ie6 .dropdown_menu{display:none;visibility:visible;}.header_row_3 .currency:hover{background-color:transparent;}.header_row_3 .currency:hover .copy,.header_row_3 .currency:hover .active_currency_symbol{color:#e6690e;}.header_row_3 .currency:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .currencyActive,.header_row_3 .currencyActive:hover{background-color:#404040;}.header_row_3 .currencyActive .copy,.header_row_3 .currencyActive .active_currency_symbol,.header_row_3 .currencyActive:hover .copy,.header_row_3 .currencyActive:hover .active_currency_symbol{color:#fff;}.header_row_3 .currencyActive .sprite_icon_dropdown,.header_row_3 .currencyActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .currencyActive .dropdown_menu{display:block;}.header_row_3 .language:hover{background-color:transparent;}.header_row_3 .language:hover .copy{color:#e6690e;}.header_row_3 .language:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .languageActive,.header_row_3 .languageActive:hover{background-color:#404040;}.header_row_3 .languageActive .copy,.header_row_3 .languageActive:hover .copy{color:#fff;}.header_row_3 .languageActive .sprite_icon_dropdown,.header_row_3 .languageActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .languageActive .dropdown_menu{display:block;}</style><script type="text/javascript"><!--//--><![CDATA[//><!--

    (function () {
        var d = document,
            css = '.js_inline_visible{display:inline;}.js_inline_hidden{display:none;}.js_visible{display:block;}.js_hidden{display:none;}.tab_inactive{display:none;}.custom_dropdown:hover .dropdown_menu{display:none;}.custom_dropdown a.focus iframe{display:none;}.custom_dropdown_ie6 .dropdown_menu{display:none;visibility:visible;}.header_row_3 .currency:hover{background-color:transparent;}.header_row_3 .currency:hover .copy,.header_row_3 .currency:hover .active_currency_symbol{color:#e6690e;}.header_row_3 .currency:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .currencyActive,.header_row_3 .currencyActive:hover{background-color:#404040;}.header_row_3 .currencyActive .copy,.header_row_3 .currencyActive .active_currency_symbol,.header_row_3 .currencyActive:hover .copy,.header_row_3 .currencyActive:hover .active_currency_symbol{color:#fff;}.header_row_3 .currencyActive .sprite_icon_dropdown,.header_row_3 .currencyActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .currencyActive .dropdown_menu{display:block;}.header_row_3 .language:hover{background-color:transparent;}.header_row_3 .language:hover .copy{color:#e6690e;}.header_row_3 .language:hover .sprite_icon_dropdown{background-position:-450px -200px;}.header_row_3 .languageActive,.header_row_3 .languageActive:hover{background-color:#404040;}.header_row_3 .languageActive .copy,.header_row_3 .languageActive:hover .copy{color:#fff;}.header_row_3 .languageActive .sprite_icon_dropdown,.header_row_3 .languageActive:hover .sprite_icon_dropdown{background-position:-450px -150px;}.header_row_3 .languageActive .dropdown_menu{display:block;}',
            style = d.createElement("style"),
            sheet,
            headElem = d.documentElement.firstChild;

        style.type = "text/css";
        style.media = "all";
        headElem.insertBefore(style, headElem.lastChild);
        sheet = style.styleSheet;

        return sheet ? sheet.cssText = css : style.appendChild(d.createTextNode(css));
    })();
  
//--><!]]></script>
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
              <div id="div">
                <form action="UserServlet" method="post" name="form" id="form" onsubmit="return isMail2(this)">
                  <input type="hidden" name="protocol" value="<%=Protocol.FLIGHT_AVAIL%>" />
                  <table class="normal"  align="center" bgcolor="#ffffff" border="0" cellpadding="3" cellspacing="0">
                    <tbody>
                      <tr>
                        <td align="left" valign="top"><table width="100%" border="0" cellpadding="4" cellspacing="0" bgcolor="#3399FF" class="normal">
                            <tbody>
                              <tr align="left" valign="top">
                                <td width="682" height="35" valign="middle" class="style4 titlepage" bgcolor="#6699FF"><span class="style22">D&#7882;CH V&#7908; &#272;&#7862;T V&Eacute; MAY BAY </span></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr>
                        <td align="left" background="vembay.php_files/bg_dot_06.htm" height="3" valign="top"><hr /></td>
                      </tr>
                      <tr>
                        <td class="normal" align="left" valign="top"><table class="normal" width="300" align="right" border="0" cellpadding="5" cellspacing="5">
                            <tbody>
                              <tr>
                                <td align="left" valign="top"><p class="style2" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;"><span class="style18"><strong>L&#7883;ch bay &amp; b&#7843;ng gi&aacute; tuy&#7871;n n&#7897;i &#273;&#7883;a</strong></span><span class="style18"><img src="images/timetable.gif" style="margin-right: 3px;" width="100" align="left" height="86" /></span></p>
                                    <p class="style18"> Tham kh&#7843;o l&#7883;ch bay v&agrave; gi&aacute; v&eacute; tham kh&#7843;o cho c&aacute;c tuy&#7871;n bay n&#7897;i &#273;&#7883;a c&#7911;a Vietnam Airlines v&agrave; Pacific Airlines </p></td>
                              </tr>
                            </tbody>
                          </table>
                            <p class="style2" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;"><span class="style12"><span class="style14"><span class="style17"><strong>T</strong>&#7841;i 
                              E-Tourism, b&#7841;n c&oacute; th&#7875; d&#7877; d&agrave;ng &#273;&#7863;t v&eacute; m&aacute;y bay theo h&agrave;nh tr&igrave;nh 
                              mong mu&#7889;n. E-Tourism
                              lu&ocirc;n s&#7861;n s&agrave;ng t&#432; v&#7845;n cho b&#7841;n v&#7873; c&aacute;c &#273;&#432;&#7901;ng bay ph&ugrave; h&#7907;p nh&#7845;t v&#7899;i h&agrave;nh
                              tr&igrave;nh v&agrave; c&oacute; chi ph&iacute; h&#7907;p l&yacute; nh&#7845;t. Trong tr&#432;&#7901;ng h&#7907;p c&oacute; y&ecirc;u c&#7847;u, b&#7841;n c&oacute;
                              th&#7875; nh&#7853;n v&eacute; t&#7841;i v&#259;n ph&ograve;ng l&agrave;m vi&#7879;c ho&#7863;c t&#7841;i nh&agrave; ri&ecirc;ng trong n&#7897;i th&agrave;nh.
                              Ph&#432;&#417;ng th&#7913;c thanh to&aacute;n r&#7845;t linh ho&#7841;t, thu&#7853;n ti&#7879;n b&#7857;ng ti&#7873;n m&#7863;t, chuy&#7875;n
                              kho&#7843;n, ho&#7863;c b&#7857;ng th&#7867; t&iacute;n d&#7909;ng. V&#7899;i kh&aacute;ch &#273;i du l&#7883;ch theo t&#7915;ng &#273;o&agrave;n, &#273;i
                              du h&#7885;c ho&#7863;c &#273;&#7883;nh c&#432;, ch&uacute;ng t&ocirc;i &#273;&#7873;u &aacute;p d&#7909;ng nh&#7919;ng m&#7913;c gi&aacute; &#432;u &#273;&atilde;i. </span></span></span></p>
                          <p class="style13" style="margin-top: 0px; font-weight: normal; margin-bottom: 0px; color: black; text-align: justify;">M&#7901;i
                            b&#7841;n &#273;i&#7873;n th&ocirc;ng tin &#273;&#7863;t v&eacute; theo y&ecirc;u c&#7847;u, E-Tourism s&#7869; cung c&#7845;p
                            &#273;&#7847;y &#273;&#7911; th&ocirc;ng tin cho b&#7841;n trong kho&#7843;ng th&#7901;i gian ng&#7855;n nh&#7845;t. </p>
                          <p align="justify">&nbsp;</p></td>
                      </tr>
                      <tr>
                        <td align="left" valign="top"><table class="normal10" width="100%" border="0" cellpadding="2" cellspacing="0">
                            <tbody>
                              <tr align="center" valign="top">
                                <td width="100%"><script>

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

                                                    alert ("B?n h&atilde;y nh?p Ng&agrave;y bay c?a b?n !");

                                                    document.book2.ngaybay.focus();

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
                                      <tbody>
                                        <tr>
                                          <td height="30" valign="middle" bgcolor="#6699FF"><span class="style22"><strong>T&Igrave;M KI&#7870;M CHUY&Ecirc;N BAY TRONG N&#431;&#7898;C</strong></span><br />
                                          </td>
                                        </tr>
                                        <tr>
                                          <td align="left" bgcolor="" valign="top"><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#FFFFFF" class="normal10">
                                              <tbody>
                                                <tr align="left" valign="top">
                                                  <td align="right" width="50%" height="37" valign="middle"><span class="style18">Kh&#7903;i h&agrave;nh  (*)</span></td>
                                                  <td width="50%" valign="middle" class="style18"><select name="depart" id="depart">
                                                      <option > Bu&ocirc;n Ma Thu&#7897;t </option>
                                                      <option> C&agrave; Mau </option>
                                                      <option> C&ocirc;n &#272;&#7843;o </option>
                                                      <option> &#272;&agrave; L&#7841;t </option>
                                                      <option> &#272;&agrave; N&#7861;ng </option>
                                                      <option> &#272;i&#7879;n Bi&ecirc;n Ph&#7911; </option>
                                                      <option> H&#7843;i Ph&ograve;ng </option>
                                                      <option selected="selected" value="Ha Noi"> H&agrave; N&#7897;i </option>
                                                      <option> Hu&#7871; </option>
                                                      <option> Nha Trang </option>
                                                      <option> Ph&uacute; Qu&#7889;c </option>
                                                      <option> Pleiku </option>
                                                      <option> Qui Nh&#417;n </option>
                                                      <option> R&#7841;ch Gi&aacute; </option>
                                                      <option> H&#7891; Ch&iacute; Minh </option>
                                                      <option> Tuy Ho&agrave; </option>
                                                      <option> Vinh </option>
                                                  </select></td>
                                                </tr>
                                                <tr align="left" valign="top">
                                                  <td align="right" height="48" valign="middle"><span class="style18"> &#272;i &#273;&#7871;n (*)</span></td>
                                                  <td valign="middle"><select name="destination" id="destination" valign="middle">
                                                      <option > Bu&ocirc;n Ma Thu&#7897;t </option>
                                                      <option> C&agrave; Mau </option>
                                                      <option> C&ocirc;n &#272;&#7843;o </option>
                                                      <option> &#272;&agrave; L&#7841;t </option>
                                                      <option> &#272;&agrave; N&#7861;ng </option>
                                                      <option> &#272;i&#7879;n Bi&ecirc;n Ph&#7911; </option>
                                                      <option> H&#7843;i Ph&ograve;ng </option>
                                                      <option> H&agrave; N&#7897;i </option>
                                                      <option> Hu&#7871; </option>
                                                      <option> Nha Trang </option>
                                                      <option> Ph&uacute; Qu&#7889;c </option>
                                                      <option> Pleiku </option>
                                                      <option> Qui Nh&#417;n </option>
                                                      <option> R&#7841;ch Gi&aacute; </option>
                                                      <option selected="selected" value="Ho Chi Minh"> H&#7891; Ch&iacute; Minh </option>
                                                      <option> Tuy Ho&agrave; </option>
                                                      <option> Vinh </option>
                                                  </select></td>
                                                </tr>
                                                <tr align="left" valign="top">
                                                  <td align="right" height="36" valign="middle"><span class="style18">Ng&agrave;y bay  (*)</span></td>
                                                  <td valign="middle"><span class="style18">
                                                    
<script type="text/javascript"><!--//--><![CDATA[//><!--

    window.hcom = window.hcom || {};
    
    window.hcom.configuration = {
        DEFAULT_DATE_FORMAT: "dd-MM-yyyy",
        FIRST_DAY_OF_WEEK: "0"
    }
    window.hcom.locale = {
        url: "asia.hotels.com",
        secureUrl: "secure-asia.hotels.com",
        rules: {
            OMNITURE_S_ACCOUNT: "hotelsasia, hotelsallprod"
            
        },
        firstDayOfWeek: 0,
        dateFormatSymbols: {
            months: [
                
                  "January", 
                  "February", 
                  "March", 
                  "April", 
                  "May", 
                  "June", 
                  "July", 
                  "August", 
                  "September", 
                  "October", 
                  "November", 
                  "December"
            ],
            shortMonths: [
                
                  "Jan", 
                  "Feb", 
                  "Mar", 
                  "Apr", 
                  "May", 
                  "Jun", 
                  "Jul", 
                  "Aug", 
                  "Sep", 
                  "Oct", 
                  "Nov", 
                  "Dec"
            ],
            shortWeekdays: [
                
                  "Sun", 
                  "Mon", 
                  "Tue", 
                  "Wed", 
                  "Thu", 
                  "Fri", 
                  "Sat"
            ],
            weekDays: [
                
                  "Sunday", 
                  "Monday", 
                  "Tuesday", 
                  "Wednesday", 
                  "Thursday", 
                  "Friday", 
                  "Saturday"
            ]
        },
        language: "en_AA",
        autosuggest: {
            asURL: "/suggest",
            isAsaActive: true,
            isHotelSearchActive: true,
            activationCharLength: 3,
            i18nMessages: {
                categories: {
                    cities: "Cities\/Areas",
                    hotels: "Hotels",
                    landmarks: "Landmarks",
                    airports: "Airports"
                },
                error: "Please type the name of a destination or hotel",
                noSuggestionMatch: "No suggestions match",
                close: "close",
                moreSuggestion: "...and more, keep typing to refine search",
                noSuggestion: "no suggestions"
            }
        }
    };
  
//--><!]]></script>
<script type="text/javascript"><!--//--><![CDATA[//><!--

//--><!]]></script>
<script type="text/javascript" src="hotel_files/hcom-RELEASE.js"></script>
<script type="text/javascript" defer="defer"><!--//--><![CDATA[//><!--

    $.loadModule("hcom.homepage.pages.home.legacy");
    
//--><!]]></script>
<script type="text/javascript"><!--//--><![CDATA[//><!--

  (function () {
      function setCookie(cookieName, cookieValue, days, domain, path) {
          var today = new Date(),
              expire = new Date(),
              i,
              cookie,
              cookieAttrs;
    
          if (!days) {
              days = 0;
          }
          if (!path) {
              path = "/";
          }
          expire.setTime(today.getTime() + 3600000 * 24 * days);

          cookieAttrs = [
                  [cookieName, cookieValue],
                  ["expires", expire.toGMTString()],
                  ["domain", domain],
                  ["path", path]
              ];

          for (i = 0; (cookie = cookieAttrs[i]); i++) {
              cookie[1] = window.escape(cookie[1]);
              cookieAttrs[i] = cookie.join("=");
          }

          document.cookie = cookieAttrs.join(";");
      }

      setCookie("jsEnabled", "true", 6, ".hotels.com");
  })();

//--><!]]></script>
<script type="text/javascript" src="hotel_files/oo_engine_generic-RELEASE.js"></script>
<script type="text/javascript" defer="defer"><!--//--><![CDATA[//><!--
s.prop5 = "144555";
    s.currencyCode = "VND";
    s.server = "asia.hotels.com";
    s.eVar34 = "50.2,42.3";
    s.prop4 = "1";
    s.pageName = "home page";
    s.eVar13 = "144555";
    s.prop27 = "05a6fef7-e92e-4c83-a829-655620180019";
    s.eVar35 = "50.2.1,42.3.1";
    s.eVar41 = "VND";
    s.channel = "home page";
    s.eVar26 = "VN";
    
    s.t();
    s.eVar34 = null;
    s.eVar35 = null;
  
//--><!]]></script>
<script type="text/javascript" src="hotel_files/ga.js"></script>
<script type="text/javascript"><!--//--><![CDATA[//><!--

    (function () {
        try {
            
            var TRACKER_COOKIE_TIMEOUT = "2592000", // sets timeout in seconds: FR 8_1: 30 days
                transactionTrackingOn = false;

            
                    var rollupTracker = _gat._getTracker("UA-5475254-2");
                    rollupTracker._setCookieTimeout(TRACKER_COOKIE_TIMEOUT);
                    rollupTracker._initData();
                    rollupTracker._trackPageview();
                

            if (transactionTrackingOn) {
                if (typeof(transaction) != 'undefined') {
                    posSpecificTracker._addTrans(transaction.orderId, // order ID - required
                            transaction.affiliation, // affiliation or store name
                            transaction.total, // total - required
                            transaction.taxes, // tax
                            transaction.shipping, // shipping
                            transaction.city, // city
                            transaction.state, // state or province
                            transaction.country); // country
                    posSpecificTracker._addItem(transaction.item.orderID, // order ID - required
                            transaction.item.SKU, // SKU/code
                            transaction.item.productName, // product name
                            transaction.item.bookingType, // category or variation
                            transaction.item.unitPrice, // unit price - required
                            transaction.item.quantity); // quantity - required
                    posSpecificTracker._trackTrans();
                }
            }
        } catch (e) {
            if (console && console.warn) {
                console.warn("google analitics script is unavailable");
            }
        }
    })();
  
//--><!]]></script>
<div class="d2mcolm_yel_Light">
  
  <!-- END: Step 1 -->
  <div class="mgnTop7">
    <div class="homepage_depart mgnLft2">
      
      <input name="takeOffDate" value="2010-02-01" id="hs_arrival" class="width88 hcomDate hcomCheckin" type="text">
      <img src="hotel_files/cal.gif" onmouseover="if (!document.forms['searchForm']['searchParams.departureDate'].disabled) this.style.cursor='pointer'; return;" id="hs_arrival_cal" alt="Calendar" class="mgnLftMin2 hcomDate" width="24" align="top" border="0" height="20"> </div>
   
  </div>
  <!-- END: Step 3 -->
  <!-- Tip: The following display of the "Rooms" dropdown is ONLY shown to the user if they have JavaScript disabled. -->

</div>
<div style="position: absolute; z-index: 100; top: 31px; left: 13px; display: none;" class="date_selector"><p class="month_nav"><a style="visibility: hidden;" href="#" class="prev"><div class="icon_sprite_calendar"><div class="calendar_0"></div></div></a> <span class="month_name">January 2010</span> <a style="visibility: visible;" href="#" class="next"><div class="icon_sprite_calendar"><div class="calendar_1"></div></div></a></p><table><thead><tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr></thead><tbody><tr><td class="unselected_month">27</td><td class="unselected_month">28</td><td class="unselected_month">29</td><td class="unselected_month">30</td><td class="unselected_month">31</td><td class="disabled_day"><a rel="01-01-2010">1</a></td><td class="disabled_day"><a rel="02-01-2010">2</a></td></tr><tr><td class="disabled_day"><a rel="03-01-2010">3</a></td><td class="today"><a href="#" rel="04-01-2010">4</a></td><td class="checkin_day"><a href="#" rel="05-01-2010">5</a></td><td><a href="#" rel="06-01-2010">6</a></td><td class="checkout_day"><a href="#" rel="07-01-2010">7</a></td><td><a href="#" rel="08-01-2010">8</a></td><td><a href="#" rel="09-01-2010">9</a></td></tr><tr><td><a href="#" rel="10-01-2010">10</a></td><td><a href="#" rel="11-01-2010">11</a></td><td><a href="#" rel="12-01-2010">12</a></td><td><a href="#" rel="13-01-2010">13</a></td><td><a href="#" rel="14-01-2010">14</a></td><td><a href="#" rel="15-01-2010">15</a></td><td><a href="#" rel="16-01-2010">16</a></td></tr><tr><td><a href="#" rel="17-01-2010">17</a></td><td><a href="#" rel="18-01-2010">18</a></td><td><a href="#" rel="19-01-2010">19</a></td><td><a href="#" rel="20-01-2010">20</a></td><td><a href="#" rel="21-01-2010">21</a></td><td><a href="#" rel="22-01-2010">22</a></td><td><a href="#" rel="23-01-2010">23</a></td></tr><tr><td><a href="#" rel="24-01-2010">24</a></td><td><a href="#" rel="25-01-2010">25</a></td><td><a href="#" rel="26-01-2010">26</a></td><td><a href="#" rel="27-01-2010">27</a></td><td><a href="#" rel="28-01-2010">28</a></td><td><a href="#" rel="29-01-2010">29</a></td><td><a href="#" rel="30-01-2010">30</a></td></tr><tr><td><a href="#" rel="31-01-2010">31</a></td><td class="unselected_month"><a href="#" rel="01-02-2010">1</a></td><td class="unselected_month"><a href="#" rel="02-02-2010">2</a></td><td class="unselected_month"><a href="#" rel="03-02-2010">3</a></td><td class="unselected_month"><a href="#" rel="04-02-2010">4</a></td><td class="unselected_month"><a href="#" rel="05-02-2010">5</a></td><td class="unselected_month"><a href="#" rel="06-02-2010">6</a></td></tr></tbody></table></div><div style="position: absolute; z-index: 100; top: 31px; left: 148px; display: none;" class="date_selector"><p class="month_nav"><a style="visibility: hidden;" href="#" class="prev"><div class="icon_sprite_calendar"><div class="calendar_0"></div></div></a> <span class="month_name">January 2010</span> <a style="visibility: visible;" href="#" class="next"><div class="icon_sprite_calendar"><div class="calendar_1"></div></div></a></p><table><thead><tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr></thead><tbody><tr><td class="unselected_month">27</td><td class="unselected_month">28</td><td class="unselected_month">29</td><td class="unselected_month">30</td><td class="unselected_month">31</td><td class="disabled_day"><a rel="01-01-2010">1</a></td><td class="disabled_day"><a rel="02-01-2010">2</a></td></tr><tr><td class="disabled_day"><a rel="03-01-2010">3</a></td><td class="disabled_day today"><a rel="04-01-2010">4</a></td><td class="checkin_day"><a href="#" rel="05-01-2010">5</a></td><td><a href="#" rel="06-01-2010">6</a></td><td class="checkout_day"><a href="#" rel="07-01-2010">7</a></td><td><a href="#" rel="08-01-2010">8</a></td><td><a href="#" rel="09-01-2010">9</a></td></tr><tr><td><a href="#" rel="10-01-2010">10</a></td><td><a href="#" rel="11-01-2010">11</a></td><td><a href="#" rel="12-01-2010">12</a></td><td><a href="#" rel="13-01-2010">13</a></td><td><a href="#" rel="14-01-2010">14</a></td><td><a href="#" rel="15-01-2010">15</a></td><td><a href="#" rel="16-01-2010">16</a></td></tr><tr><td><a href="#" rel="17-01-2010">17</a></td><td><a href="#" rel="18-01-2010">18</a></td><td><a href="#" rel="19-01-2010">19</a></td><td><a href="#" rel="20-01-2010">20</a></td><td><a href="#" rel="21-01-2010">21</a></td><td><a href="#" rel="22-01-2010">22</a></td><td><a href="#" rel="23-01-2010">23</a></td></tr><tr><td><a href="#" rel="24-01-2010">24</a></td><td><a href="#" rel="25-01-2010">25</a></td><td><a href="#" rel="26-01-2010">26</a></td><td><a href="#" rel="27-01-2010">27</a></td><td><a href="#" rel="28-01-2010">28</a></td><td><a href="#" rel="29-01-2010">29</a></td><td><a href="#" rel="30-01-2010">30</a></td></tr><tr><td><a href="#" rel="31-01-2010">31</a></td><td class="unselected_month"><a href="#" rel="01-02-2010">1</a></td><td class="unselected_month"><a href="#" rel="02-02-2010">2</a></td><td class="unselected_month"><a href="#" rel="03-02-2010">3</a></td><td class="unselected_month"><a href="#" rel="04-02-2010">4</a></td><td class="unselected_month"><a href="#" rel="05-02-2010">5</a></td><td class="unselected_month"><a href="#" rel="06-02-2010">6</a></td></tr></tbody></table></div>
                                                    <span id="btnngaybay1" title="Select publishing date"> <a href="javascript:%20void(0);"></a></span>
                                                    <script type="text/javascript"></script>
                                                  </span></td>
                                                </tr>
                                                <tr align="left" valign="top">
                                                  <td align="right" height="37" valign="middle"><span class="style18">
                                                    <script type="text/javascript">Calendar.setup({inputField : "ngaybay", ifFormat : "%Y-%m-%d", displayArea : "hienthingaybay1", daFormat : "%Y-%m-%d", button : "btnngaybay1", showsTime : false});
                                                                                </script>
                                                    H&#7841;ng v&eacute; </span></td>
                                                  <td valign="middle"><select name="ticket" id="hangve2">
                                                      <option selected="selected" value="economy">Ph&#7893; th&ocirc;ng</option>
                                                      <option value="business">Doanh nh&acirc;n</option>
                                                  </select></td>
                                                </tr>
                                                <tr align="left" valign="top">
                                                  <td align="right" height="34" valign="middle"><span class="style18">S&#7889; l&#432;&#7907;ng </span></td>
                                                  <td align="left" valign="top"><input name="quatity" class="style18" id="soluong" size="10" type="text" />
                                                  </td>
                                                </tr>
                                              </tbody>
                                          </table></td>
                                        </tr>
                                      </tbody>
                                    </table>
                                  <p>&nbsp;</p>
                                  <label>
                                    <input name="submit" type="submit" value="Tìm Kiếm" />
                                    </label>
                                    <p>&nbsp;</p></td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                      <tr></tr>
                      <tr>
                        <td align="left" valign="top"><hr noshade="noshade" size="1" /></td>
                      </tr>
                    </tbody>
                  </table>
                </form>
              </div>
            </div>
            <div id="templatemo_bot"></div>
        </div>
        <div id="templatemo_footer"> 
            <jsp:include page="/pages/layout/footer.jsp" flush="false"></jsp:include>  
        </div>
    </body>
</html>