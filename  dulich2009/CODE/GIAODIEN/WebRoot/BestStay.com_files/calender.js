/*--SET MONTH NAMES --*/
var arMonth = new Array("January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

/*--SET DAY NAMES --*/
var arNameDay = new Array("S", "M", "T", "W", "T", "F", "S");
var firstDate = new Date();
var calDay = "";
var calDate = "";
var calYear = "";
var calMonth = "";
function calcDates(fYear, fMonth, fDate) {
	firstDate = new Date(fYear, fMonth, fDate);
	calDay = firstDate.getDay();
	calDate = firstDate.getDate();
	calYear = firstDate.getFullYear();
	calMonth = firstDate.getMonth();
	return
}
function showCal(fYear, fMonth, fDate, inOut) {
 thisDate = today.getDate();
	var oneYear = new Date(today.getFullYear() + 1, today.getMonth() - 1, today.getDate());
	var cal_win = window.open("", "Calendar", ",width=300,height=230,top=200,left=350");
	var cal_doc = cal_win.document;
 var bodyCode = new String();
	calcDates(fYear, fMonth, 1);
	if(calMonth == 1 && calYear%4 == 0) {
		ar_month_length[1] = "29";
	}
	else {
	ar_month_length[1] = "28";
	}
	var prefixCode = new String("<html>\n" +
	"<head>\n" +
	"<title>Calender</title>\n" +
	"<script language=\"JavaScript\" type=\"text/javascript\">\n" +
	"<!--\n" +
	"function sendBack(fYear, fMonth, fDate, inOut) {\n" +
	"		if(inOut == \"checkIn\") {\n" +
	"			window.opener.document.searchForm.doa_dd.options[fDate - 1].selected = true;\n" +
	"			window.opener.document.searchForm.doa_mm.options[fMonth].selected = true;\n" +
	"			window.opener.loadDates(inOut);\n" +
	"		}\n" +
	"		else {\n" +
	"			window.opener.document.searchForm.dod_dd.options[fDate - 1].selected = true;\n" +
	"			window.opener.document.searchForm.dod_mm.options[fMonth].selected = true;\n" +
	"			window.opener.loadDates(inOut);\n" +
	"		}\n" +
	"		window.self.close();\n" +
	"}\n" +
	"-->\n" +
	"</script>\n" +
	"<style type=\"text/css\">\n" +
	"<!--\n" +
	"body{margin-top:2px;}\n" +
	".year {\n" +
	"font-family: Arial, Helvetica, sans-serif;\n" +
	"font-size: 16px;\n" +
	"}\n" +
	".month {\n" +
	"font-family: Arial, Helvetica, sans-serif;\n" +
	"font-size: 14px;\n" +
	"}\n" +
	".days {\n" +
	"font-family: Arial, Helvetica, sans-serif;\n" +
	"font-size: 12px;\n" +
	"}\n" +
	"a:visited {\n" +
	"color: #0000FF;\n" +
	"}\n" +
	"a:hover {\n" +
	"color: #FF0000;\n" +
	"}\n" +
	"-->\n" +
	"</style>\n" +
	"</head>\n" +
	"<body>\n" +
	"<table width=\"150\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" +
	"<tr><td colspan=\"3\" align =\"center\" class=\"year\"><b>" + calYear + "</b></td>\n" +
	"</tr>\n" +
	"<tr><td width=\"33%\">\n");
	
/*-- SHOW BACK ARROW --*/
 if(firstDate >= today) {
		prefixCode += "<a href=\"javascript:window.opener.showCal(" + calYear + ", " + (calMonth - 1) + ", " + calDate + ", " + "'" + inOut + "'" + ");\">\n" +
		"<img src=\"images/arrow_left.gif\" border=\"0\"></a>\n"
	}

/*-- SHOW MONTH NAME --*/
	prefixCode += "</td>\n" +
	"<td width=\"33%\" align=\"center\" class=\"month\"><b>" + arMonth[calMonth] + "</b></td>\n" +
	"<td width=\"33%\" align=\"right\">\n"
	
/*-- SHOW FORWARD ARROW --*/
 if(firstDate < oneYear) {
		prefixCode += "<a href=\"javascript:window.opener.showCal(" + calYear + ", " + (calMonth + 1) + ", " + calDate + ", " + "'" + inOut + "'" + ");\">\n" +
		"<img src=\"images/arrow_right.gif\" border=\"0\"></a>\n"
	}
	prefixCode += "</td>\n" +
	"</tr>\n" +
	"<table width=\"150\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\" class=\"days\" align=\"center\">\n" +
	"<tr>\n"
	
/*-- PLOT DAYS --*/
 for (d=0; d<=6; d++) {
		bodyCode += "<td width=\"9%\" align=\"center\"><b>" + arNameDay[d] + "</b></td>\n";
	}
	bodyCode +="</tr><tr>\n";
	
/*-- PLOT DATES --*/
 var p = 1;
	for(i=0; i < 42; i++) {
		
/*-- FILL IN BLANKS FOR UNUSED DAYS --*/
  if(i < calDay) {
			bodyCode+="<td>&nbsp;</td>\n";
		}
		else if(p <= ar_month_length[calMonth]){
			if(i%7 == 0 && i != 0){
				bodyCode += "</tr><tr>\n";
			}
			if(inOut == "checkOut" && p < ci_date.getDate()+1 && calMonth == ci_date.getMonth() && calYear == ci_date.getYear()){
				bodyCode += "<td align=\"center\"><b>" + p + "</b></td>\n";
				p++;
			}
			else if(i < (today.getDate() + calDay - 1) && calMonth == today.getMonth() && calYear == today.getFullYear()) {
				bodyCode += "<td align=\"center\"><b>" + p + "</b></td>\n";
				p++;
			}
			else if(i == (today.getDate() + calDay - 1) && calMonth == today.getMonth() && calYear == today.getYear()) {
				bodyCode += "<td bgcolor=\"FFE495\" align=\"center\"><a href=\"javascript:sendBack(" + calYear + ", " + calMonth + ", " + p + ", " + "'" + inOut + "'" + ");\"><b>" + p + "</b></a></td>\n";
				p++;
			}
			else {
				bodyCode += "<td align=\"center\"><a href=\"javascript:sendBack(" + calYear + ", " + calMonth + ", " + p + ", " + "'" + inOut + "'" + ");\"><b>" + p + "</b></a></td>\n";
				p++;
			}
		}
		else if(i%7 == 0) {
			break;
		}
		else {
			bodyCode += "<td>&nbsp;</td>\n";
		}
	}
	var suffixCode = new String("</tr></table>\n" +
	"</table>\n" +
	"<table width=\"150\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
	"<tr><td><img src=\"images/spacer10.gif\"></td></tr>\n" +
	"<tr>\n" +
	"<td align=\"center\">\n" +
	"<img src=\"images/logo_sm.gif\" alt=\"BestStay.com\">\n" +
	"</tr><tr><td align=\"right\"><a href=\"javascript:self.close();\">Close</a></td></tr></td></table>\n" +
	"</body>\n" +
	"</html>");
	cal_doc.write(prefixCode + bodyCode + suffixCode);
	cal_doc.close();
}