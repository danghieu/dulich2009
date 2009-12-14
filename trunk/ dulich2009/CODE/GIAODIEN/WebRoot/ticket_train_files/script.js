// JavaScript Document
<!--
function sendpage(myLink){
  if(!window.focus)return;
        var w=680;
        var h=500;
        var left = ((window.screen.width - w) / 2);
        var top = 0;
        var myWin=window.open(myLink,"","toolbar=no,status=1,menubar=1,scrollbars=1,resizable=no,width="+w+",height="+h+",left="+left+",top="+top);
        myWin.focus();
}
function viewImage(strLocation, strCaption){
	window.open("image_view.php?Location=" + strLocation + "&Caption=" + strCaption, "imgView", "top=50,left=100,width=595,height=450,status=no,menubar=no,scrollable=no,resizable=no,scrollbars=no");
}
function OpenWin(sURL,sName,x,y){
    var w = x; //510;
    var h = y; //460;
    var sb = 0;

    winId = window.open(sURL,sName,"width=" + w + ",height=" + h + ",menubar=0,location=0,toolbar=0,personalbar=0,status=0,scrollbars=" + sb + ",directories=0,resizable=0,copyhistory=1");// 'WIDTH=480, HEIGHT=450, CENTER, ');
    winId.focus();

    CenterWin(winId,w,h);
}
function CenterWin(oWin,x,y){
    var nRatio = 1;
    var sx = screen.width;
    var sy = screen.height;
    var hell;

    if((sx < x) && (sy < y))    {
        // Move window to default position
        oWin.moveTo(0,0)
        return;
    } else if((sx > x) && (sy < y)) {
        oWin.moveTo((sx - x) /2,0)
        return;
    }

    try{oWin.moveTo((sx - x) /2 ,((sy - y) /2) * .95);}catch(hell){}
}
function openLink(myLink){
  if(! window.focus)return;
  var myWin=window.open(myLink,"","toolbar=1,status=1,location=1,menubar=1,personalbar=1,scrollbars=1,resizable=1,top=0,left=0,directories=1,copyhistory=1");
  myWin.focus();
}
function openPopup(myLink){
  if(! window.focus)return;
  var myWin=window.open(myLink,"","toolbar=1,status=1,menubar=1,scrollbars=1,resizable=1,width=671,height=550,top=0");
  myWin.focus();
}

//////////////////////GET DAY////////////////////////////
function makeArray() {
     for (i = 0; i<makeArray.arguments.length; i++)
         this[i] = makeArray.arguments[i];
}
 
function getFullYear(d) {
    var y = d.getYear();
    if (y < 1000) {y += 1900};
    return y;
}

//var zone = "EDT";
var days = new makeArray("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday");
var months = new makeArray("January","February","March","April","May","June","July","August","September","October","November","December");

function format_time(t) {

    var Day = t.getDay();
    var Date = t.getDate();
    var Month = t.getMonth();
    var Year = t.getFullYear();

    timeString = "";
    timeString += days[Day];
    timeString += ", ";
    timeString += " ";
    timeString += months[Month];
    timeString += " ";
    timeString += Date;
    timeString += ", ";
    timeString += " ";
	timeString += Year;

	return timeString;
}
//////////////////////////////////////////////////
function isEmail(s) {
        if (s=="") return false;
        if(s.indexOf(" ")>0) return false;
        var i = 1;
        var sLength = s.length;
        if (s.indexOf(".")==sLength) return false;
        if (s.indexOf(".")<=0) return false;
        if (s.indexOf("@")!=s.lastIndexOf("@")) return false;
        while ((i < sLength) && (s.charAt(i) != "@"))  { i++  }
        if ((i >= sLength) || (s.charAt(i) != "@")) return false;
        else i += 2;
        while ((i < sLength) && (s.charAt(i) != "."))  { i++  }
        if ((i >= sLength - 1) || (s.charAt(i) != ".")) return false;
        var str="1234567890abcdefghikjlmnopqrstuvwxyzABCDEFGHIKJLMNOPQRSTUVWXYZ-@._";
        for(var j=0;j<s.length;j++)
                if(str.indexOf(s.charAt(j))==-1)
                        return false;
        return true;
}
function doWeatherCheck(cityCode){							
  if (cityCode=='') 
	return;	
  var w=175;
  var h=270;
  var left = ((window.screen.width - w) / 2);
  var top = ((window.screen.height - h) / 2);
  window.open("weather.php?Local=" + cityCode, "Weather", "width="+w+", height="+h+", left="+left+", top="+top+", status=no, menubar=no, scrollable=no, resizable=no, scrollbars=no");
}

function CheckRequiredInfo(){
	if (document.frm_reservation.guest_name.value == ""){
		alert("Please enter Your name!");
		document.frm_reservation.guest_name.focus();
		return false;
	}
	if (!isEmail(document.frm_reservation.guest_email.value)) {
        alert("Invalid Email, Please enter Your own email");
        document.frm_reservation.guest_email.focus();
        return false;
    }
	else if (document.frm_reservation.guest_email_confirm.value != document.frm_reservation.guest_email.value){
		alert("Please confirm Your email!");
		document.frm_reservation.guest_email_confirm.focus();
		return false;
	}
	if (document.frm_reservation.guest_address.value == ""){
		alert("Please enter your home address!");
		document.frm_reservation.guest_address.focus();
		return false;
	}
	if (document.frm_reservation.hotel_name.value == ""){
		alert("Please enter Hotel name!");
		document.frm_reservation.hotel_name.focus();
		return false;
	}
	if (document.frm_reservation.city_name.value == ""){
		alert("Please enter City / Province name!");
		document.frm_reservation.city_name.focus();
		return false;
	}
	if (document.frm_reservation.numofunit.value == 0 || document.frm_reservation.numofunit.value == ""){
		alert("Please enter number of Room!");
		document.frm_reservation.numofunit.focus();
		return false;
	}
	if (document.frm_reservation.numofadult.value == 0 || document.frm_reservation.numofadult.value == ""){
		alert("Please enter number of Person!");
		document.frm_reservation.numofadult.focus();
		return false;
	}
	return true;
}
function isNumber(obj)
{
  var checkOK;
	checkOK = "0123456789-.,";
  var checkStr;
  	checkStr = obj.value;
  var allValid;
  	allValid = true;
  var validGroups;
	validGroups = true;
  var decPoints;
  	decPoints = 0;
  var allNum;
  	allNum = "";
  var i,ch;	
  for (i = 0;  i < checkStr.length;  i++)
  {
    ch = checkStr.charAt(i);
	var j;
    for (j = 0;  j < checkOK.length;  j++)
      if (ch == checkOK.charAt(j))
        break;
    if (j == checkOK.length)
    {
      allValid = false;
      break;
    }
    if (ch == ".")
    {
      allNum += ".";
      decPoints++;
    }
    else if (ch == "," && decPoints != 0)
    {
      validGroups = false;
      break;
    }
    else if (ch != ",")
      allNum += ch;
  }
  if (!allValid)
  {
    obj.focus();
	return false;
  }
//
 else if (decPoints > 1 || !validGroups)
  {
    obj.focus();
	return false;
  }
  else 
  return true;
}

function jemail(user, domain, suffix){
	document.write('<a href="' + 'mailto:' + user + '@' + domain + '.' + suffix + '" title="Vietnam Tour Operator">' + user + '@' + domain + '.' + suffix + '</a>');
}
//-->
