// Do nothing
function doNothing(){window.status='';}

// Open popup window
function doOpenLinkToPopupWindow(){
	var url = arguments[0]||'/';
	var width = arguments[1]||400;
	var height = arguments[2]||400;
	var resizable = arguments[3]||'yes';
	var scrollbars = arguments[3]||'yes';
	var left = ((document.body.clientWidth - width) / 2) + window.screenLeft;
	var top = (((document.body.clientHeight - height) / 2)) + window.screenTop;
	window.open(url, '', 'width='+width+',height='+height+',left='+left+',top='+top+',resizable='+resizable+',scrollbars='+scrollbars);
}

// Open banner
function OpenBanner(id,isGroup){
	if(isGroup==1)
	{
		eval("var current=crt" + id);
		eval("var itemID=bnrID" + id + current);
	}
	else
		var itemID=id;
	frmVHSHiddenForm.txtID.value = itemID;
	frmVHSHiddenForm.txtLocation.value = location.href;
	frmVHSHiddenForm.action = '../i-web/banner.asp?subobject=item&action=open';
	frmVHSHiddenForm.submit();
}

// Change banner (for web banner or special banner)
function doChangeWebPlusBanner(id,max){
	eval("var current=crt" + id);
	if(current<max)
	{
		eval("crt" + id + "++");
		current++;
	}
	else
	{
		eval("crt" + id + "=0");
		current=0;
	}	
	eval("var nextImg = bnr" + id + current);
	document.images['imgpapaCMS'+id].src = nextImg.src;
}

// Open web link
function doOpenWebPlusLink(url){
	frmVHSHiddenForm.txtLocation.value = location.href;
	frmVHSHiddenForm.action = url;
	frmVHSHiddenForm.submit();
}

// Information for updating hits counter
function doGetWebPlusCounterInfo(){
	// Browser
	var browser = '';
	if(navigator.appName=='Netscape')
		browser = 'NS';
	if(navigator.appName=='Microsoft Internet Explorer')
		browser = 'MSIE';
	if(navigator.appVersion.indexOf('MSIE 3')>0)
		browser = 'MSIE';
	// Screen resolution
	var screenWidth = screen.width;
	var screenHeight = screen.height; 
	// Color depth
	var colorDepth = 256;
	if (navigator.appName != 'Netscape')
		colorDepth = screen.colorDepth;
	else 
		colorDepth = screen.pixelDepth;
	// Referer
	var referer = '' + escape(document.referrer);
	// Counter URL
	return '&w=' + screenWidth + '&h=' + screenHeight + '&c=' + colorDepth + '&b=' + browser + '&r=' + referer
}

// Return the string determines full date of client (International)
function getFullDate(){
	var now = new Date();
	var month = "";
	var day = "";
	var first_date_num="";

  if (now.getDate() < 10)
		first_date_num="0";
	else
		first_date_num="";
			
	switch (now.getDay()){
		case 0: day="Sun";break;
		case 1: day="Mon";break;
		case 2: day="Tue";break;
		case 3: day="Wed";break;
		case 4: day="Thur";break;
		case 5: day="Fri";break;
		case 6: day="Sat";break;
	}
	
	switch (now.getMonth()){
		case  0: month="Jan";break;
		case  1: month="Feb";break;
		case  2: month="Mar";break;
		case  3: month="Apr";break;
		case  4: month="May";break;
		case  5: month="Jun";break;
		case  6: month="Jul";break;
		case  7: month="Aug";break;
		case  8: month="Sep";break;
		case  9: month="Oct";break;
		case 10: month="Nov";break;
		case 11: month="Dec";break;
	}
	
	return day + ", " + month + " " + first_date_num + now.getDate() + ", " + now.getFullYear();
}

// Return the string determines full date of client (Vietnamese)
function getFullDateInVietnamese(){
	var now = new Date();
	var month = "";
	var day = "";
	var first_date_num="";

  if (now.getDate() < 10)
  	first_date_num="0";
	else
		first_date_num="";
			
	switch (now.getDay()){
		case 0: day="Ch&#7911; nh&#7853;t";break;
		case 1: day="Th&#7913; hai";break;
		case 2: day="Th&#7913; ba";break;
		case 3: day="Th&#7913; t&#432;";break;
		case 4: day="Th&#7913; n&#259;m";break;
		case 5: day="Th&#7913; s&#225;u";break;
		case 6: day="Th&#7913; b&#7843;y";break;
	}
	
	return day + " ng&#224;y " + first_date_num + now.getDate() + " th&#225;ng " + (now.getMonth()+1) + " n&#259;m " + now.getFullYear();
}

// Return the string determines full date & time of client (GMT)
function getFullDateTimeInGMT(){
	var now = new Date();
	var month = "";
	var day = "";
	var date = now.getDate();

	if ((date==1)||(date==21)||(date==31)) {date = date + "st"};
	if ((date==2)||(date==22)) {date = date + "nd"};
	if ((date==3)||(date==23)) {date = date + "rd"};
			
	switch (now.getDay()){
		case 0: day="Sunday";break;
		case 1: day="Monday";break;
		case 2: day="Tuesday";break;
		case 3: day="Wednesday";break;
		case 4: day="Thursday";break;
		case 5: day="Friday";break;
		case 6: day="Saturday";break;
	}
	
	switch (now.getMonth()){
		case  0: month="January";break;
		case  1: month="February";break;
		case  2: month="March";break;
		case  3: month="April";break;
		case  4: month="May";break;
		case  5: month="June";break;
		case  6: month="July";break;
		case  7: month="August";break;
		case  8: month="September";break;
		case  9: month="October";break;
		case 10: month="November";break;
		case 11: month="December";break;
	}

	var time = new String(now.getUTCHours());
	if (time.length < 2){ time = "0"+ time};
		
	var minute = new String(now.getMinutes());
	if (minute.length < 2) {minute = "0"+ minute};
	
	time = time  + ":" + minute + " (GMT)"; 
	
	return day + ", " + date + " " + month + " " + now.getFullYear() + " " + time;
}

var isTabList="1";
function showTab(_tabID, SourceID)
{
	try
	{
		if (isTabList!='')
		{
			eval("document.all.startTime.innerHTML=''");
			eval("document.all.firstTab"+isTabList+".innerHTML='<img src=/images/3/firstTabOff.gif>'");
			eval("document.all.lastTab"+isTabList+".innerHTML='<img src=/images/3/lastTabOff.gif>'");
			eval("document.all.firstTab"+_tabID+".innerHTML='<img src=/images/3/firstTab.gif>'");
			eval("document.all.lastTab"+_tabID+".innerHTML='<img src=/images/3/LastTab.gif>'");
			eval("document.all.Tab"+isTabList+".style.display='none'");
			eval("document.all.Tab"+_tabID+".style.display=''");
			eval("document.all.col"+isTabList+".className='hotelTab'");
			eval("document.all.col"+_tabID+".className='hotelTabSelected'");
			isTabList = _tabID;
		}
		if (SourceID!='')
		{
			switchpanel(SourceID, 'Ajax'+_tabID);
		}
	}
	catch(r)
	{
		document.title = r.message;
	}
}
