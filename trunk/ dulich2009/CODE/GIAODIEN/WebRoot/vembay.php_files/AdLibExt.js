function adlistshow(AdList, AdName, CheckShow, ShowType, Interface, LimitWidth, LimitHeight, Extra){
	this.myid=AdName;
	this.checkshow=CheckShow;
	this.limitwidth=LimitWidth;
	this.limitheight=LimitHeight;
	this.showtype=ShowType;
	this.face=Interface;
	this.delay=15000;
	if (typeof(Extra) == 'undefined') {this.extra='';} else {this.extra=Extra;}
	switch (ShowType) {
		case 0:	case 4:	case 5: case 6: this.readtype=0; break;
		default: this.readtype=1; break;
	}
	var arTemp = new Array();
	var i, j, k, no, showcount;
	i=0;j=-1;k=0;no=-1;showcount=1;
	if (this.readtype==0){
		for (i=0,j=0;i<AdList.length;i++){
			if (this.checkShowThis(AdList[i])){
				arTemp[j]=AdList[i];
				j++;
			}
		}
	}
	else{
		for (i=0;i<AdList.length;i++){
			if (AdList[i][5]!=no){
				no=AdList[i][5];
				if (showcount>0) j++;
				showcount=0;
				k=0;
			}
			if (this.checkShowThis(AdList[i])){
				if (k==0) arTemp[j] = new Array();
				arTemp[j][k] = AdList[i];
				showcount++;
				k++;
			}
		}
	}
	this.itemcount=j+1;
	this.listvalue=arTemp;
	this.initialize();
}

adlistshow.prototype.initialize=function(){
	switch (this.showtype) {
		case 0: this.displayrotatorbanner(); break;
		case 1: case 7: this.displaylistbanner(); break;
		case 2: this.displayfloatbanner(0); break;
		case 3: this.displayfloatbanner(1); break;
		case 4: this.displayfloatbanner(2); break;
		case 5: this.displaypopupbanner(false); break;
		case 6: this.displaypopupbanner(true); break;
		default: break;
	}
}

adlistshow.prototype.checkLastObj=function(index){
	if (this.readtype==0){
		return true;
	}
	else {
		if (this.listvalue.length==index+1){
			return true;
		}
		else {
			return false;
		}
	}
}

adlistshow.prototype.displayrotatorbanner=function(){
	document.write('<table ', (this.limitwidth>0)?'width='.concat(this.limitwidth):'', ' cellspacing=0 cellpadding=0 border=0>');
	this.writerotatorbanner(this.listvalue,0);
	document.write('</table>');
}

adlistshow.prototype.displaylistbanner=function(){
	if (this.face==0){
		document.write('<table ', (this.limitwidth>0)?'width='.concat(this.limitwidth):'', ' cellspacing=0 cellpadding=0 border=0 align=center>');
	}
	for (var i=0;i<this.listvalue.length;i++){
		if (this.listvalue[i][0][5]<100){
			this.writerotatorbanner(this.listvalue[i],i,this.checkLastObj(i));
		}
		else {
			this.writerandombanner(this.listvalue[i],this.checkLastObj(i));
		}
	}
	if (this.face==0){
		document.write('</table>');
	}
}

adlistshow.prototype.displayfloatbanner=function(type){
	if (type==2){
		document.write('<DIV id="floatdiv',this.myid,'" style="position:absolute;overflow:hidden;left:-200;',(this.limitheight>0)?'height:'.concat(this.limitheight):'180',';',(this.limitwidth>0)?'width:'.concat(this.limitwidth):'115',';">');
		this.writebottomupbanner(this.listvalue);
	}
	else{
		document.write('<DIV id="floatdiv',this.myid,'" style="position:absolute;left:-200;',(this.limitwidth>0)?'width:'.concat(this.limitwidth):'115',';">');	
		this.displaylistbanner();
		//if (RelatedFolder==1 && type==1) document.writeln('<iframe id="a01a94e5" name="a01a94e5" src="http://203.146.251.72/max/www/delivery/afr.php?zoneid=6&cb=INSERT_RANDOM_NUMBER_HERE" framespacing="0" frameborder="no" scrolling="no" width="115" height="242"><a href="http://203.146.251.72/max/www/delivery/ck.php?n=a3ef72f6&amp;cb=INSERT_RANDOM_NUMBER_HERE" target="_blank"><img src="http://203.146.251.72/max/www/delivery/avw.php?zoneid=6&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=a3ef72f6" border="0" alt="" /></a></iframe>');
	}
	document.write('</DIV>');
	FloatTopDiv('floatdiv'.concat(this.myid),type);
}

adlistshow.prototype.displaypopupbanner=function(type){
	vIndex = this.listvalue[0][2].lastIndexOf(',');
	vID = this.listvalue[0][2].substr(vIndex + 2);
	sLink = buildLink(vID,this.listvalue[0][1]);
	var arrPara = this.listvalue[0][2].split(",");	
	openPopup(this.listvalue[0][0], sLink, 'Advertisment', arrPara[2], arrPara[9], arrPara[8], screen.height - arrPara[9] - 80, (screen.width - 770)/2 + 410 - 5, type);
}

adlistshow.prototype.writerotatorbanner=function(obj,index,lastobj){
	obj.sort(randOrd);
	if (this.face==1) {
		document.write('<tr><td id="', this.myid, '_', index, '" style="padding:4px;">');
	}
	else {
		document.write('<tr><td id="', this.myid, '_', index, '">');
	}
	document.write(buildhtml(obj[0],this.limitwidth,this.limitheight));
	if (obj.length>1){
		var idtemp = ''.concat(this.myid).concat('_').concat(index);
		var delaytime = this.delay + Math.round(Math.random()*10000);
		var limitwidth = this.limitwidth;
		var limitheight = this.limitheight;
		setTimeout(function(){changebanner(obj,0,idtemp,delaytime,limitwidth,limitheight)}, delaytime);
	}
	document.write('</td></tr>');
	if (this.face==0){
		document.write('<tr><td height="3"></td></tr>');
	}
	if (this.face==1 && (!lastobj)) {
		document.write('<tr><td height="1" bgcolor="#808080"></td></tr>');
	}
}

adlistshow.prototype.writerandombanner=function(obj,lastobj){
	obj.sort(randOrd);
	for (var i=0;i<obj.length;i++){
		if (this.face==1) {
			document.write('<tr><td style="padding:4px;">');
		}
		else{
			document.write('<tr><td>');
		}
		document.write(buildhtml(obj[i],this.limitwidth,this.limitheight));
		document.write('</td></tr>');
		if (this.face==0){
			document.write('<tr><td height="3"></td></tr>');
		}
		if (this.face==1 && (i<obj.length-1)) {
			document.write('<tr><td height="1" bgcolor="#808080"></td></tr>');
		}
	}
}

adlistshow.prototype.writebottomupbanner=function(obj){
	obj.sort(randOrd);
	document.write('<DIV id="subdiv',this.myid,'" style="position:absolute;',(this.limitheight>0)?'top:'.concat(this.limitheight):'180',';',(this.limitwidth>0)?'width:'.concat(this.limitwidth):'115',';',(this.limitheight>0)?'height:'.concat(this.limitheight):'180',';">');
	document.write(buildhtml(obj[0],this.limitwidth,this.limitheight));
	var idtemp = 'subdiv'.concat(this.myid);
	var delaytime = this.delay + Math.round(Math.random()*10000);
	var limitwidth = this.limitwidth;
	var limitheight = this.limitheight;
	var delaytime=this.delay + Math.round(Math.random()*10000);
	setTimeout(function(){startbottomupbanner(obj,0,idtemp,delaytime,limitwidth,limitheight)}, 500);
	document.write('</DIV>');
}

function changebanner(obj,index,bannerid,delaytime,limitwidth,limitheight){
	if (index==obj.length-1) {index=0;} else {index++;}
	document.getElementById(bannerid).innerHTML=buildhtml(obj[index],limitwidth,limitheight);
	setTimeout(function(){changebanner(obj,index,bannerid,delaytime,limitwidth,limitheight)},delaytime);
}

function startbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight){
	var ftlObj = document.getElementById(bannerid)
	if (!ftlObj) return;
	var pY = parseInt(ftlObj.style.top);
	pY = pY - Math.round(parseInt(ftlObj.style.top)/8) - 1;
	if (pY>0){
		ftlObj.style.top = pY;
		setTimeout(function(){startbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight)},10);
	}
	else{
		ftlObj.style.top = 0;
		setTimeout(function(){endbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight)},delaytime);		
	}
}

function endbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight){
	var ftlObj = document.getElementById(bannerid)
	if (!ftlObj) return;
	var pY = parseInt(ftlObj.style.top);
	pY = pY + Math.round((limitheight-parseInt(ftlObj.style.top))/8) + 1;
	if (pY<limitheight){
		ftlObj.style.top = pY;
		setTimeout(function(){endbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight)},10);
	}
	else{
		ftlObj.style.top = limitheight;
		if (index==obj.length-1) {index=0;} else {index++;}
		document.getElementById(bannerid).innerHTML=buildhtml(obj[index],limitwidth,limitheight);
		setTimeout(function(){startbottomupbanner(obj,index,bannerid,delaytime,limitwidth,limitheight)},15000);		
	}
}

function buildhtml(obj,limitwidth,limitheight){
	if (!obj) {return ''}
	
	//ngocta Tue, 16/01/2007 08:51:48
	//vIndex = this.listvalue[0][2].lastIndexOf(',');
	//vID = this.listvalue[0][2].substr(vIndex + 2);
	//if (vID=='557') {return''}
	
	var sTemp = '';
	var sImageLink = (Left(obj[0],7).toLowerCase() == 'http://')?obj[0]:PageHost.concat(obj[0]);
	var imagewidth = (limitwidth<obj[3] && limitwidth>0)?limitwidth:obj[3];
	var imageheight = (limitheight<obj[4] && limitheight>0)?limitheight:obj[4];
	if (Right(obj[0],4).toLowerCase() == '.swf'){
		sTemp = sTemp.concat('<object classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" border="0" width=').concat(imagewidth).concat(' height=').concat(imageheight).concat('>');
		sTemp = sTemp.concat('<param name="movie" value="').concat(sImageLink).concat('?link=').concat(escape(buildLink(obj[2].substring(obj[2].lastIndexOf(',')+2),obj[1]))).concat('">');
		sTemp = sTemp.concat('<param name="quality" value="High">');
		sTemp = sTemp.concat('<embed src="').concat(sImageLink).concat('?link=').concat(escape(buildLink(obj[2].substring(obj[2].lastIndexOf(',')+2),obj[1]))).concat('" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width=').concat(imagewidth).concat(' height=').concat(imageheight).concat('>');
		sTemp = sTemp.concat('</object>');
	}
	else{
		if (obj[1] != '') {
			sTemp = '<a href="'.concat(obj[1]).concat('" onClick="return openMeExt(this, ').concat(obj[2]).concat(', 1)"><img src="').concat(sImageLink).concat('" border=0 width=').concat(imagewidth).concat(' height=').concat(imageheight).concat('></a></td>'); 
		}
		else {
			sTemp = '<img src="'.concat(sImageLink).concat('" border=0 width=').concat(imagewidth).concat(' height=').concat(imageheight).concat('></td>');
		}
	}
	return sTemp;
}

adlistshow.prototype.checkShowThis=function(obj){
	var checkShow=false;

	var iCheck = obj[6];
	
	if (iCheck == 3 && this.checkshow < 4) checkShow=true;
	else if (iCheck == this.checkshow || iCheck == 0) checkShow=true;
	
	//Truong hop dac biet Son nguyen chi quang cao o muc thue va cho thue nha
	if (RelatedFolder==9998)
		if (obj[1].indexOf('http://www.sonnguyenvn.com')>=0)
		{
			if (window.location.href.indexOf("c=12")>0 || window.location.href.indexOf("c=14")>0)
				{return true} else {return false}
		}

	if (checkShow && this.showtype==7){
		checkShow=false;
		if (typeof(dtSubjectDate) == 'undefined') return;
		var dtFromDate = new Date(obj[7]);
		var dtToDate = new Date(obj[8]);
		var iFromDiff = (dtSubjectDate.getTime()-dtFromDate.getTime())/1000;
		var iToDiff   = (dtToDate.getTime()-dtSubjectDate.getTime())/1000;
		if (iFromDiff<5*86400 && iFromDiff>=0 && iToDiff>5*86400 && iToDiff>=0){
			checkShow=true;
		}
	}
	else{
		return checkShow;
	}
	return checkShow;
}

//function randOrd(){ return (Math.round(Math.random())-0.5); } 
function randOrd(){ return (Math.random()*10000)-5000; } 

function openPopup(vImage, vLink, vTitle, vScrollbars, vWidth, vHeight, vTop, vLeft, vHide)
{
	if (typeof(vHide) == 'undefined'){
		vHide = false;
	}

	if (vHide){
		var pw = open('/Library/PopupUnder.Asp?vImage='.concat(escape(vImage)).concat('&vLink=').concat(escape(vLink)).concat('&vTitle=').concat(escape(vTitle)), 'Popup_'.concat(ReplaceAll(vTitle, ' ', '_')), 'status=no,resizable=no,scrollbars='.concat(vScrollbars).concat(',toolbar=no,location=no,fullscreen=no,titlebar=no,height=').concat(vHeight).concat(',').concat('width=').concat(vWidth).concat(',').concat('top=').concat(0).concat(',').concat('left=').concat(0));
		var LinkStr = vLink;
		window.focus();
		return pw;
	}
	else{
		return open('/Library/Popup.Asp?vImage='.concat(escape(vImage)).concat('&vLink=').concat(escape(vLink)).concat('&vTitle=').concat(escape(vTitle)), 'Popup_'.concat(ReplaceAll(vTitle, ' ', '_')), 'status=no,resizable=no,scrollbars=no,toolbar=no,location=no,fullscreen=no,titlebar=yes,height='.concat(vHeight).concat(',').concat('width=').concat(vWidth).concat(',').concat('top=').concat(vTop).concat(',').concat('left=').concat(vLeft));
	}
}

function openMeExt(vLink, vStatus, vResizeable, vScrollbars, vToolbar, vLocation, vFullscreen, vTitlebar, vCentered, vHeight, vWidth, vTop, vLeft, vID, vCounter)
{
	var sLink = (typeof(vLink.href) == 'undefined') ? vLink : vLink.href;

	winDef = '';
	winDef = winDef.concat('status=').concat((vStatus) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('resizable=').concat((vResizeable) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('scrollbars=').concat((vScrollbars) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('toolbar=').concat((vToolbar) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('location=').concat((vLocation) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('fullscreen=').concat((vFullscreen) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('titlebar=').concat((vTitlebar) ? 'yes' : 'no').concat(',');
	winDef = winDef.concat('height=').concat(vHeight-140).concat(',');
	winDef = winDef.concat('width=').concat(vWidth).concat(',');

	if (vCentered){
		winDef = winDef.concat('top=').concat((screen.height - vHeight)/2).concat(',');
		winDef = winDef.concat('left=').concat((screen.width - vWidth)/2);
	}
	else{
		winDef = winDef.concat('top=').concat(vTop).concat(',');
		winDef = winDef.concat('left=').concat(vLeft);
	}

	if (typeof(vCounter) == 'undefined'){
		vCounter = 0;
	}

	if (typeof(vID) == 'undefined')	{
		vID = 0;
	}
	
	if (vCounter){
		sLink = buildLink(vID,sLink);
	}

	open(sLink, '_blank', winDef);

	if (typeof(vLink.href) != 'undefined')	{
		return false;
	}
}

function buildLink(vID, vLink){
	return 'http://srv.ngoisao.net/Counter/?n='.concat(vID).concat('&u=').concat(escape(vLink)).concat('&r=').concat(Math.random());
}

function FloatTopDiv(divid,type){
	var startX, startY;
	if (type==1) {startX = document.body.clientWidth - 115;} else {startX = 0;}
	if (type==2) {startY = document.body.clientHeight;} else {startY = 0;}
	if (document.body.clientWidth < 980) {startX = -115};

	window.stayFloat=function(ftlObj,type)
	{
		var startX, startY;
		var ns = (navigator.appName.indexOf("Netscape") != -1);
		if (type==1) {startX = document.body.clientWidth - 115;} else {startX = 0;}
		if (document.body.clientWidth < 980) {
			ftlObj.style.display = 'none';
		} 
		else {
			ftlObj.style.display = '';
			
			if (document.documentElement && document.documentElement.scrollTop)
				var pY = ns ? pageYOffset : document.documentElement.scrollTop;
			else if (document.body)
				var pY = ns ? pageYOffset : document.body.scrollTop;

			if (type==2){
				startY = document.body.clientHeight-183;
			}
			else{
				if (document.body.scrollTop > 71){startY = 3} else {startY = 71};
			}
			ftlObj.y += (pY + startY - ftlObj.y)/8;
			ftlObj.style.left=startX;
			ftlObj.style.top=ftlObj.y;
		}
		setTimeout(function(){stayFloat(ftlObj,type)}, 15);
	}

	var ftlObj = document.getElementById?document.getElementById(divid):document.all?d.all[divid]:document.layers[divid];
	if(!ftlObj) return;
	ftlObj.x = startX;
	ftlObj.y = startY;
	stayFloat(ftlObj,type);
}

function Left(str, n){
	if (n <= 0)
	    return "";
	else if (n > String(str).length)
	    return str;
	else
	    return String(str).substring(0,n);
}

function Right(str, n){
    if (n <= 0)
       return "";
    else if (n > String(str).length)
       return str;
    else {
       var iLen = String(str).length;
       return String(str).substring(iLen, iLen - n);
    }
}

//===================================================================================================================
//ngocta Wed, 25/10/2006 15:37:25
browser = new BrowserSniffer("Windows-Ie,Windows-Firefox,Mac-Firefox");
var m_arrRefExpand=new Array();		// main array
var m_intIndex=0;					// use for array, loop
var m_intInterval=0;				// millisecond
var m_strReplayAd='';				// "Replay Ad"
var m_strCloseAd='';				// "Close Ad"
var m_strNone='';					// "None"
var m_lngRepeated=false;			// first time ?
var m_strCurrentImage=''; 			// Current Image after setInterval()
var m_blnClosingInProgress=false; 	// Close Done OR In Progress Closing ?
var m_strHTMLLinksClose='';			// strHTMLLinkCloseAd +' - '+strHTMLLinkNone;
var m_strHTMLLinksReplay='';		// strHTMLLinkReplayAd+' - '+strHTMLLinkNone;
var m_strHTMLShowFlash=''; 			// HTML big flash when click ReplayAd(), expanded
var m_strHTMLShowImage='';			// HTML tag img when click CloseAd(), collapsed
var m_strBgColor='#FFFFFF'			// BackgroundColor when moving down ('white')
var m_intPixelHeight=0;				// for moving effect (important)

function BrowserSniffer(targettedBrowsers) {
	var browsersSupported = new Array("Firefox","Safari","Ie","Mac"); 
	for (var k=0; k < browsersSupported.length; k++) eval("this.is" + browsersSupported[k] + "=false;this.is" + browsersSupported[k] + "Targetted=false;"); 
	var browsersTargetted = targettedBrowsers.replace(/\s/g,"").split(",");this.isAdPlayable=false;this.agent=navigator.userAgent.toLowerCase();
	for (var i=0; i < browsersTargetted.length; i++) {
		var browsersTargettedInfo = browsersTargetted[i].replace(/\s/g,"").split("-");
		var targettedPlatform=browsersTargettedInfo[0];
		var targettedAgent=browsersTargettedInfo[1];
		if ( (this.agent.indexOf(targettedAgent.toLowerCase()) != -1) && (this.agent.indexOf(targettedPlatform.toLowerCase()) != -1) ){
			var thisVar = targettedAgent.substring(0,1).toUpperCase()+targettedAgent.substring(1,targettedAgent.length);
			if ( (this.agent.indexOf(targettedAgent.toLowerCase())!=-1) ) {this.isAdPlayable = true;eval("this.is"+thisVar+"=true;");break;}
		}
	}
	this._checkOnFlash = function() { 
		if (navigator.plugins && navigator.plugins.length) { 
			if ( (navigator.plugins["Shockwave Flash"]) || (navigator.plugins["Shockwave Flash 2.0"]) ) {var y;
				if (navigator.plugins["Shockwave Flash"]) y=this.isFlashVersion=navigator.plugins["Shockwave Flash"].description;
				if (navigator.plugins["Shockwave Flash 2.0"]) y=this.isFlashVersion=navigator.plugins["Shockwave Flash 2.0"].description;
				this.isFlashVersion=y.charAt(y.indexOf('.')-1);return true; 
			} else if (navigator.mimeTypes && navigator.mimeTypes.length) { 
				var x = navigator.mimeTypes['application/x-shockwave-flash']; if (x && x.enabledPlugin) return true; else return false; 
			} else return false; 
		} else if (this.isIe) { 
			document.write('<script language=vbscript>\nfunction vbflash()\non error resume next\ntest = IsObject(CreateObject("ShockwaveFlash.ShockwaveFlash." & 6 ))\nif test then\nvbflash = 1\nelse\nvbflash = 0\nend if\nend function\n<\/script>'); return vbflash();
		} else return false; 
	} 
	if (this.isAdPlayable) {this.isFlash = this._checkOnFlash();this.isAdPlayable = this.isFlash;}
}

function closeThisAd() {m_intPixelHeight=120;cleanUp2(0);}

function replayAd() {if (m_blnClosingInProgress) return;m_blnClosingInProgress=true;m_intPixelHeight=60;/*flashreplay();*//*tt(12,'user_replay');*/stay=1;cleanUp2(1);}

function cleanUp2(open){
	if (open) {
		document.getElementById("addiv").style.backgroundColor=m_strBgColor;
		document.getElementById("addiv").innerHTML='';
		m_intPixelHeight = 60;
		moveIt(open);
	} else {
		document.getElementById("addiv").style.backgroundColor='';
		document.getElementById("addiv").innerHTML=m_strHTMLShowImage;m_intPixelHeight = 120;
		moveIt(open);
	}
}

function moveIt(open) {
	if (open){
		if (m_intPixelHeight<120) {
				document.getElementById("addiv").style.pixelHeight=(m_intPixelHeight+5);
				if (browser.isFirefox) document.getElementById("addiv").style.height=(m_intPixelHeight+5)+"px";
				m_intPixelHeight = m_intPixelHeight+5;
				setTimeout('moveIt(1)',1);
			} else {
				document.getElementById("addiv").innerHTML=m_strHTMLShowFlash;
				document.getElementById("lnkdiv").innerHTML=m_strHTMLLinksClose;
			}
	} else {
		if (m_intPixelHeight>60) {
			m_intPixelHeight = 60;
			//document.getElementById("addiv").style.pixelHeight=(m_intPixelHeight-5);
			document.getElementById("addiv").style.pixelHeight=(m_intPixelHeight);
			if (browser.isFirefox) document.getElementById("addiv").style.height=(m_intPixelHeight-5)+"px";
			//m_intPixelHeight =m_intPixelHeight-5;
			if (browser.isFirefox) document.getElementById("addiv").style.opacity= (Math.round((300-m_intPixelHeight)/1.5))/100;
			document.getElementById("addiv").style.filter='alpha(opacity='+(Math.round((300-m_intPixelHeight)/1.5))+')';
			setTimeout('moveIt(0)',1);
		} else {
			document.getElementById("addiv").style.filter='alpha(opacity=100)';
			document.getElementById("lnkdiv").innerHTML=m_strHTMLLinksReplay;
			if (m_strHTMLShowImage!='')
			{
				document.getElementById("addiv").innerHTML=m_strHTMLShowImage;
				m_blnClosingInProgress=false;
			}
		}
	}
}

function None()
{
	document.getElementById('addiv').style.display='none';
	document.getElementById('lnkdiv').style.display='none';	
}

function GetSwfURL(intAdID, strURL)
{
	var strReturn=''
	if (Right(strURL,1)!="/")
		strURL+="/";
	if (Left(strURL,7)!="http://")
		strURL="http://"+strURL;
	strReturn="http://srv.ngoisao.net/Counter/?n="+intAdID+escape("&u=")+escape(strURL)
	return strReturn;
}

function ChangeExpandBanner()
{	
	m_intIndex++;
	if (m_intIndex>=m_arrRefExpand.length) m_intIndex=0;

	m_blnClosingInProgress=false;

	ShowExpandBanner(
		  m_arrRefExpand[m_intIndex][0]
		, m_arrRefExpand[m_intIndex][1]
		, m_arrRefExpand[m_intIndex][2]
		, m_arrRefExpand[m_intIndex][3]
		, Math.round(Math.random()*10000))	;
		
	setTimeout("ChangeExpandBanner()",m_intInterval);	
}

function ShowExpandBanner(strExpandImage, strExpandFlash, strRefURL, intAdID, random)	
{
	m_strCurrentImage=strExpandImage;		

	var strHTMLLinkReplayAd='<a href="javascript:replayAd();">'+m_strReplayAd+'</a>';
	var strHTMLLinkNone='<a href="javascript:None()">'+m_strNone+' </a>';
	var strHTMLLinkCloseAd='<a  href="javascript:closeThisAd();">'+m_strCloseAd+'</a>';
	m_strHTMLLinksClose=strHTMLLinkCloseAd+' - '+strHTMLLinkNone;
	m_strHTMLLinksReplay=strHTMLLinkReplayAd+' - '+strHTMLLinkNone;	
	
	m_strHTMLShowFlash  = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width=728 height=120>';
	m_strHTMLShowFlash +=		'<param name=movie value="'+strExpandFlash+'?link='+GetSwfURL(intAdID, strRefURL)+'">';
	m_strHTMLShowFlash +=		'<param name=quality value=autohigh>';
	m_strHTMLShowFlash +=		'<param name=loop value=true>';
	m_strHTMLShowFlash +=		'<param name="allowScriptAccess" value="always" />';
	m_strHTMLShowFlash +=		'<EMBED ';
	m_strHTMLShowFlash +=			'src=' + strExpandFlash +'?link='+GetSwfURL(intAdID, strRefURL)+ ' ';
	m_strHTMLShowFlash +=			'quality=high ';
	m_strHTMLShowFlash +=			'width=728 ';
	m_strHTMLShowFlash +=			'height=120 ';
	m_strHTMLShowFlash +=			'type="application/x-shockwave-flash" ';
	m_strHTMLShowFlash +=			'pluginspage="http://www.macromedia.com/go/getflashplayer" ';
	m_strHTMLShowFlash +=			'allowScriptAccess="always" ';
	m_strHTMLShowFlash +=		'></EMBED>';
	m_strHTMLShowFlash += '</object>';	
	
	
	m_strHTMLShowImage='<img onmouseover="replayAd()" src="'+strExpandImage+'" width=728 height=60 border=0>';	
	
	if (!m_lngRepeated)
	{
		m_lngRepeated=true;
		temp = ('<div id=addiv style="position:relative;height:60;width:728"></div><div id=lnkdiv style="text-align:center">'+m_strHTMLLinksReplay+'</div>');
		document.write(temp);
		document.getElementById("addiv").style.backgroundColor='';
		document.getElementById("addiv").innerHTML=m_strHTMLShowImage;
		m_intPixelHeight = 120;	
		if (m_arrRefExpand.length>1)
			setTimeout("ChangeExpandBanner()",m_intInterval);	
	}
	else
	{
		document.getElementById("addiv").style.height=60;
		document.getElementById("addiv").innerHTML=m_strHTMLShowImage;
	}

}

function ShowExpandBannerFlash(strExpandImage, strExpandFlash, strRefURL, intAdID, random)	
{
	m_strCurrentImage=strExpandImage;		

	var strHTMLLinkReplayAd='<a class="ExpandBanner" href="javascript:replayAd();">'+m_strReplayAd+'</a>';
	var strHTMLLinkNone='<a class="ExpandBanner" href="javascript:None()">'+m_strNone+' </a>';
	var strHTMLLinkCloseAd='<a class="ExpandBanner" href="javascript:closeThisAd();">'+m_strCloseAd+'</a>';
	m_strHTMLLinksClose=strHTMLLinkCloseAd+' - '+strHTMLLinkNone;
	m_strHTMLLinksReplay=strHTMLLinkReplayAd+' - '+strHTMLLinkNone;	
	
	m_strHTMLShowFlash  = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width=728 height=120>';
	m_strHTMLShowFlash +=		'<param name=movie value="'+strExpandFlash+'?link='+strRefURL+'">';
	m_strHTMLShowFlash +=		'<param name=quality value=autohigh>';
	m_strHTMLShowFlash +=		'<param name=loop value=true>';
	m_strHTMLShowFlash +=		'<param name="allowScriptAccess" value="always" />';
	m_strHTMLShowFlash +=		'<EMBED ';
	m_strHTMLShowFlash +=			'src=' + strExpandFlash +'?link='+strRefURL;
	m_strHTMLShowFlash +=			'quality=high ';
	m_strHTMLShowFlash +=			'width=728 ';
	m_strHTMLShowFlash +=			'height=120 ';
	m_strHTMLShowFlash +=			'type="application/x-shockwave-flash" ';
	m_strHTMLShowFlash +=			'pluginspage="http://www.macromedia.com/go/getflashplayer" ';
	m_strHTMLShowFlash +=			'allowScriptAccess="always" ';
	m_strHTMLShowFlash +=		'></EMBED>';
	m_strHTMLShowFlash += '</object>';	
	
	m_strHTMLShowImage  = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width=728 height=60>';
	m_strHTMLShowImage +=		'<param name=movie value="'+strExpandImage+'?link='+strRefURL+'">';
	m_strHTMLShowImage +=		'<param name=quality value=autohigh>';
	m_strHTMLShowImage +=		'<param name=loop value=true>';
	m_strHTMLShowImage +=		'<param name="allowScriptAccess" value="always" />';
	m_strHTMLShowImage +=		'<EMBED ';
	m_strHTMLShowImage +=			'src=' + strExpandImage +'?link='+strRefURL;
	m_strHTMLShowImage +=			'quality=high ';
	m_strHTMLShowImage +=			'width=728 ';
	m_strHTMLShowImage +=			'height=60 ';
	m_strHTMLShowImage +=			'type="application/x-shockwave-flash" ';
	m_strHTMLShowImage +=			'pluginspage="http://www.macromedia.com/go/getflashplayer" ';
	m_strHTMLShowImage +=			'allowScriptAccess="always" ';
	m_strHTMLShowImage +=		'></EMBED>';
	m_strHTMLShowImage += '</object>';	
	
	
	
	//m_strHTMLShowImage='<img onmouseover="replayAd()" src="'+strExpandImage+'" width=728 height=60 border=0>';	
	
	if (!m_lngRepeated)
	{
		m_lngRepeated=true;
		temp = ('<div id="addiv" onmouseover="replayAd()" style="position:relative;height:60;width:728"></div><div id=lnkdiv style="text-align:center">'+m_strHTMLLinksReplay+'</div>');
		document.write(temp);
		document.getElementById("addiv").style.backgroundColor='';
		document.getElementById("addiv").innerHTML=m_strHTMLShowImage;
		m_intPixelHeight = 120;	
		if (m_arrRefExpand.length>1)
			setTimeout("ChangeExpandBanner()",m_intInterval);	
	}
	else
	{
		document.getElementById("addiv").style.height=60;
		document.getElementById("addiv").innerHTML=m_strHTMLShowImage;
	}

}



//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//RefExpand
//	comment: RefImage, RefFlash, RefURL, AdID, ImgW, ImgH, SwfW, SwfH, PopAttrList, Priority, CheckShow
//	example: new Array('/AdImages/listerine(25).gif', '/AdImages/listerine(25).swf', 'http://www.listerine.com', 428, 728, 60, 728, 120, '1, 1, 1, 1, 1, 0, 1, 1, 600, 800, 0, 0, 428', 100, 0)
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/***************************************************************************\
|	-------------------------------											|				
|	ngocta Fri, 27/10/2006 11:47:51											|
|	ngocta Thu, 09/11/2006 09:58:21											|
|	-------------------------------											|
|	arrRefExpand - AdArray													|
|		arrRefExpand[n][0] - RefImage 	('/AdImages/listerine.gif')			|
|		arrRefExpand[n][1] - RefFlash	('/AdImages/listerine.swf')			|
|		arrRefExpand[n][2] - RefURL		('http://www.girlspace.com.vn/')	|
|		arrRefExpand[n][3] - AdID		(433)								|	
|		arrRefExpand[n][4] - FolderList	('1,2,3,4,5,7,23')	[not use] 		|
|			Image=728x60; Flash=728x120 (fixed size)						|
|	strReplayAd  - Replay Ad String 	("Replay Ad")						|
|	strCloseAd   - Close Ad String		("Close Ad")						|
|	strNone		 - None Ad String		("None")							|
|	intInterval  - Interval				(15000)								|
|																			|
|	//////////////////////////////											|
|	// Fixed Dimension Infos	//											|
|	// ImageWidth	= 728		//											|
|	// ImageHeight	= 060		//											|
|	// FlashWidth	= 728		//											|
|	// FlashHeight	= 120		//											|
|	//////////////////////////////											|
\***************************************************************************/
function InitializeExpandBanner(arrRefExpand, strReplayAd, strCloseAd, strNone, intInterval)	
{
	m_arrRefExpand	= arrRefExpand.sort(randOrd);
	m_intInterval	= intInterval
	m_strReplayAd	= strReplayAd; 
	m_strCloseAd 	= strCloseAd; 
	m_strNone	 	= strNone;

	if (m_arrRefExpand.length>0)// && ReplaceAll(m_arrRefExpand[0][4]," ","").indexOf(","+RelatedFolder+",")>0)
	{
		ShowExpandBannerFlash(
			  m_arrRefExpand[0][0]
			, m_arrRefExpand[0][1]
			, m_arrRefExpand[0][2]
			, m_arrRefExpand[0][3]
			, Math.round(Math.random()*10000))	;		
	}
	else
	{
		document.getElementById('div_ExpandBanner').style.display = 'none';
	}
}

//alert('end of file');








////////////////////////////////////////////////////////////////////////////////////////////////////////
// RUNNING AD Sat, 31/03/2007 15:21:56 /////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////


var flashContent;


var X, app;
var x_offset;
var image_height = 100;

x_offset = 2;

var time = 30000; //==Just for left, right Run Down Fllow logo
var _timout_for_hide_menu = 32000; //== not known
var _timout_for_stop_menu = 25; // == not Use
var _timout_for_play_menu = 45000; //== Sum(Flash_Run_Time + Stop_Time) in ms
var _flash_run_time = 30000;

function InitializeRunningBanner(vFlashURL, vWidth, vHeight)
{
	flashContent  = '<object classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" border="0" width="'+vWidth+'" height="'+vHeight+'">';
	flashContent += '<param name="movie" value="'+vFlashURL+'">';
	flashContent += '<param name="quality" value="High">';
	flashContent += '<param name="wmode" value="transparent">';
	flashContent += '<embed src="'+vFlashURL+'" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="'+vWidth+'" height="'+vHeight+'">';
	flashContent += '</object>';
}

//var _timout_for_play_menu
function window_load() {
	//all_table.height = document.body.clientHeight;

	app = new xDI3(
    "#000000", // header bg
    "#ffffff", // col1 bg
    "#ffffff", // col2 bg
    "#ffffff", // footer bg
    0,  // header height
    0,  // footer height
    0, // column 1 width
    0,  // vertical margin
    0,  // horizontal margin
    0,   // inner margin
    x_offset,  // menu x offset
    image_height-document.body.clientHeight,  // menu y offset
    .4   // menu slide factor
  );
  xRemoveFlashContent('menu');
  xLoadFlashContent('menu',flashContent);
  app.paint();
  xAddEventListener(window, "resize", winResizeListener, false);
  xAddEventListener(window, "scroll", winScrollListener, false);
  
  
	setTimeout("down()", time);
	setTimeout("xHide(menu)", _timout_for_hide_menu);
  
	setTimeout("window_load()", _timout_for_play_menu);
  
}

function down(){
	//xSlideTo(menu, x_offset, xTop(menu) + 0, 0);
	xSlideTo('menu', x_offset, xTop('menu') + 0, 0);
}
	

//window.onload = function() {window_load();}

function winResizeListener() {
  if (document.layers) location.replace(location.href);
  else app.paint();
}
function winScrollListener() {
  var y = 0, st = xScrollTop();
  if (st > app.my) y = st - app.my;
  xSlideTo('menu', xLeft('menu'), y, 1500);
//  menuSlide(y);
}

function xDI3(hdrBg, col1Bg, col2Bg, ftrBg, hdrHeight, ftrHeight, col1Width, vMargin, hMargin, iMargin, mXOffset, mYOffset, mSlideFactor) {
  // Properties
  this.hbg = hdrBg;
  this.c1bg = col1Bg;
  this.c2bg = col2Bg;
  this.fbg = ftrBg;
  this.hh = hdrHeight;
  this.fh = ftrHeight;
  this.cw = col1Width;
  this.vm = vMargin;
  this.hm = hMargin;
  this.im = iMargin;
  this.mx = mXOffset;
  this.my = mYOffset;
  this.msf = mSlideFactor;
  this.mm = false;
  this.mt = 0;
  // Methods
  this.paint = function() {
    xBackground('header', this.hbg);
    xBackground('col1', this.c1bg);
    xBackground('col2', this.c2bg);
    xBackground('footer', this.fbg);
    xBackground('bMargin', 'transparent');
    xWidth('col2', (xClientWidth()-(2*this.hm))-this.cw-this.im);
    var ch = xHeight('col2');
    var hw = xClientWidth()-(2*this.hm);
    xResizeTo('header', hw, this.hh);
    xMoveTo('header', this.hm, this.vm);
    xShow('header');
    xResizeTo('col1', this.cw, ch);
    xMoveTo('col1', this.hm, this.hh+this.vm+this.im);
    xShow('col1');
    xMoveTo('col2', xWidth('col1')+this.hm+this.im, xTop('col1'));
    xShow('col2');
    xResizeTo('footer', hw, this.fh);
    xMoveTo('footer', this.hm, xTop('col1')+ch+this.im);
    xShow('footer');
    xResizeTo('bMargin', hw, this.vm);
    xMoveTo('bMargin', this.hm, xTop('footer')+this.fh);
    xShow('bMargin');
    xWidth('menu', this.cw-(2*this.mx));
    this.mx = (parseInt(screen.width)-780)/2-120;//Edit for Center Horz Menu, 120 just a nearly number
    xMoveTo('menu', this.mx, ch);
    xShow('menu');
    winScrollListener(); // initial slide of menu
  } // end paint() method
} // end class xDI3


function menuSlide(iY, iterating) {
  var delta, currentY;
  if (!app.mm) {app.mt = iY;}
  else if (!iterating) {
    app.mt = iY;
    return;
  }
  currentY = xTop('menu');
  app.mm = true;
  if (app.msf < 1) {
    delta = app.msf * Math.abs(Math.abs(currentY) - Math.abs(app.mt));
    if (delta < 1) delta = 1;
  }
  else {delta = app.msf;}
  if (currentY < app.mt) {
    if (currentY + delta <= app.mt) xTop('menu', currentY + delta);
    else xTop('menu', app.mt);
  }
  else if (currentY > app.mt) {
    if (currentY - delta >= app.mt) xTop('menu', currentY - delta);
    else xTop('menu', app.mt);
  }
  else {
    app.mm = false;
    return;
  }
  setTimeout("menuSlide("+app.mt+","+true+")",0);
}

////////////////////////////////////////////////////////////////////////////////////////////////////////
// x.js
// This library is distributed under the terms of the LGPL (gnu.org)

// Variables:
var xVersion='3.8',xOp7=false,xOp5or6=false,xIE4Up=false,xNN4=false,xUA=navigator.userAgent.toLowerCase();
if(window.opera){
  xOp7=(xUA.indexOf('opera 7')!=-1 || xUA.indexOf('opera/7')!=-1);
  if (!xOp7) xOp5or6=(xUA.indexOf('opera 5')!=-1 || xUA.indexOf('opera/5')!=-1 || xUA.indexOf('opera 6')!=-1 || xUA.indexOf('opera/6')!=-1);
}
else if(document.layers) xNN4=true;
else {xIE4Up=document.all && xUA.indexOf('msie')!=-1 && parseInt(navigator.appVersion)>=4;}

// Appearance:
function xShow(e) {
  if(!(e=xGetElementById(e))) return;
  if(e.style && xDef(e.style.visibility)) e.style.visibility='inherit';
  else if(xDef(e.visibility)) e.visibility='show';
}
function xLoadFlashContent(e,qcContent){
  if(!(e=xGetElementById(e))) return;
  e.innerHTML = qcContent;
}
function xRemoveFlashContent(e){
  if(!(e=xGetElementById(e))) return;
  e.innerHTML = '';
}

function xHide(e) {
  if(!(e=xGetElementById(e))) return;
  if(e.style && xDef(e.style.visibility)) e.style.visibility='hidden';
  else if(xDef(e.visibility)) e.visibility='hide';
}
function xZIndex(e,uZ) {
  if(!(e=xGetElementById(e))) return 0;
  if(e.style && xDef(e.style.zIndex)) {
    if(arguments.length>1) e.style.zIndex=uZ;
    else uZ=e.style.zIndex;
  }
  else if(xDef(e.zIndex)) {
    if(arguments.length>1) e.zIndex=uZ;
    else uZ=e.zIndex;
  }
  return uZ;
}
function xColor(e,sColor) {
  if(!(e=xGetElementById(e))) return "";
  var c="";
  if(e.style && xDef(e.style.color)) {
    if(arguments.length>1) e.style.color=sColor;
    c=e.style.color;
  }
  return c;
}
function xBackground(e,sColor,sImage) {
  if(!(e=xGetElementById(e))) return "";
  var bg="";
  if(e.style) {
    if(arguments.length>1) e.style.backgroundColor=sColor;
    if(arguments.length==3) e.style.backgroundImage=(sImage && sImage!="")? "url("+sImage+")" : null;
    bg=e.style.backgroundColor;
  }
  else if(xDef(e.bgColor)) {
    if(arguments.length>1) e.bgColor=sColor;
    bg=e.bgColor;
    if(arguments.length==3) e.background.src=sImage;
  }
  return bg;
}

// Position:
function xMoveTo(e,iX,iY) {
  xLeft(e,iX);
  xTop(e,iY);
}
function xLeft(e,iX) {
  if(!(e=xGetElementById(e))) return 0;
  var css=xDef(e.style);
  if (css && xDef(e.style.left) && typeof(e.style.left)=="string") {
    if(arguments.length>1) e.style.left=iX+"px";
    else {
      iX=parseInt(e.style.left);
      if(isNaN(iX)) iX=0;
    }
  }
  else if(css && xDef(e.style.pixelLeft)) {
    if(arguments.length>1) e.style.pixelLeft=iX;
    else iX=e.style.pixelLeft;
  }
  else if(xDef(e.left)) {
    if(arguments.length>1) e.left=iX;
    else iX=e.left;
  }
  return iX;
}
function xTop(e,iY) {
  if(!(e=xGetElementById(e))) return 0;
  var css=xDef(e.style);
  if(css && xDef(e.style.top) && typeof(e.style.top)=="string") {
    if(arguments.length>1) e.style.top=iY+"px";
    else {
      iY=parseInt(e.style.top);
      if(isNaN(iY)) iY=0;
    }
  }
  else if(css && xDef(e.style.pixelTop)) {
    if(arguments.length>1) e.style.pixelTop=iY;
    else iY=e.style.pixelTop;
  }
  else if(xDef(e.top)) {
    if(arguments.length>1) e.top=iY;
    else iY=e.top;
  }
  return iY;
}
function xPageX(e) {
  if (!(e=xGetElementById(e))) return 0;
  if (xDef(e.pageX)) return e.pageX;
  var x = 0;
  while (e) {
    if (xDef(e.offsetLeft)) x += e.offsetLeft;
    e = xParent(e);
  }
  return x;
}
function xPageY(e) {
  if (!(e=xGetElementById(e))) return 0;
  if (xDef(e.pageY)) return e.pageY;
  var y = 0;
  while (e) {
    if (xDef(e.offsetTop)) y += e.offsetTop;
    e = xParent(e);
  }
  return y;
}
function xSlideTo(e,x,y,uTime) {
  if (!(e=xGetElementById(e))) return;
  if (!e.timeout){
  	e.timeout = _timout_for_stop_menu;
  }	 
  e.xTarget = x; e.yTarget = y; e.slideTime = uTime; e.stop = false;
  e.yA = e.yTarget - xTop(e); e.xA = e.xTarget - xLeft(e); // A = distance
  e.B = Math.PI / (2 * e.slideTime); // B = period
  e.yD = xTop(e); e.xD = xLeft(e); // D = initial position
  var d = new Date(); e.C = d.getTime();
  if (!e.moving) xSlide(e);
}

function xSlide(e) {
  if (!(e=xGetElementById(e))) return;
  var now, s, t, newY, newX;
  now = new Date();
  t = now.getTime() - e.C;
  if (e.stop) { e.moving = false; }
  else if (t < e.slideTime) {
    setTimeout("xSlide('"+e.id+"')", e.timeout);
    s = Math.sin(e.B * t);
    newX = Math.round(e.xA * s + e.xD);
    newY = Math.round(e.yA * s + e.yD);
    xMoveTo(e, newX, newY);
    e.moving = true;
  }  
  else {
    xMoveTo(e, e.xTarget, e.yTarget);
    e.moving = false;
  }  
}

// Size:
function xResizeTo(e,uW,uH) {
  xWidth(e,uW);
  xHeight(e,uH);
}
function xWidth(e,uW) {
  if(!(e=xGetElementById(e)) || (uW && uW<0)) return 0;
  uW=Math.round(uW);
  var css=xDef(e.style);
  if(css && xDef(e.style.width,e.offsetWidth) && typeof(e.style.width)=="string") {
    if(arguments.length>1) xSetCW(e, uW);
    uW=e.offsetWidth;
  }
  else if(css && xDef(e.style.pixelWidth)) {
    if(arguments.length>1) e.style.pixelWidth=uW;
    uW=e.style.pixelWidth;
  }
  else if(xDef(e.clip) && xDef(e.clip.right)) {
    if(arguments.length>1) e.clip.right=uW;
    uW=e.clip.right;
  }
  return uW;
}
function xHeight(e,uH) {
  if(!(e=xGetElementById(e)) || (uH && uH<0)) return 0;
  uH=Math.round(uH);
  var css=xDef(e.style);
  if(css && xDef(e.style.height,e.offsetHeight) && typeof(e.style.height)=="string") {
    if(arguments.length>1) xSetCH(e, uH);
    uH=e.offsetHeight;
  }
  else if(css && xDef(e.style.pixelHeight)) {
    if(arguments.length>1) e.style.pixelHeight=uH;
    uH=e.style.pixelHeight;
  }
  else if(xDef(e.clip) && xDef(e.clip.bottom)) {
    if(arguments.length>1) e.clip.bottom=uH;
    uH=e.clip.bottom;
  }
  return uH;
}
// thank moz for the next 2000 bytes
function xGetCS(ele,sP){return parseInt(document.defaultView.getComputedStyle(ele,"").getPropertyValue(sP));}
function xSetCW(ele,uW){
  if(uW<0) return;
  var pl=0,pr=0,bl=0,br=0;
  if(xDef(document.defaultView) && xDef(document.defaultView.getComputedStyle)){
    pl=xGetCS(ele,"padding-left");
    pr=xGetCS(ele,"padding-right");
    bl=xGetCS(ele,"border-left-width");
    br=xGetCS(ele,"border-right-width");
  }
  else if(xDef(ele.currentStyle,document.compatMode)){
    if(document.compatMode=="CSS1Compat"){
      pl=parseInt(ele.currentStyle.paddingLeft);
      pr=parseInt(ele.currentStyle.paddingRight);
      bl=parseInt(ele.currentStyle.borderLeftWidth);
      br=parseInt(ele.currentStyle.borderRightWidth);
    }
  }
  else if(xDef(ele.offsetWidth,ele.style.width)){
    ele.style.width=uW+"px";
    pl=ele.offsetWidth-uW;
  }
  if(isNaN(pl)) pl=0; if(isNaN(pr)) pr=0; if(isNaN(bl)) bl=0; if(isNaN(br)) br=0;
  var cssW=uW-(pl+pr+bl+br);
  if(isNaN(cssW)||cssW<0) return;
  else ele.style.width=cssW+"px";
}
function xSetCH(ele,uH){
  if(uH<0) return;
  var pt=0,pb=0,bt=0,bb=0;
  if(xDef(document.defaultView) && xDef(document.defaultView.getComputedStyle)){
    pt=xGetCS(ele,"padding-top");
    pb=xGetCS(ele,"padding-bottom");
    bt=xGetCS(ele,"border-top-width");
    bb=xGetCS(ele,"border-bottom-width");
  }
  else if(xDef(ele.currentStyle,document.compatMode)){
    if(document.compatMode=="CSS1Compat"){
      pt=parseInt(ele.currentStyle.paddingTop);
      pb=parseInt(ele.currentStyle.paddingBottom);
      bt=parseInt(ele.currentStyle.borderTopWidth);
      bb=parseInt(ele.currentStyle.borderBottomWidth);
    }
  }
  else if(xDef(ele.offsetHeight,ele.style.height)){
    ele.style.height=uH+"px";
    pt=ele.offsetHeight-uH;
  }
  if(isNaN(pt)) pt=0; if(isNaN(pb)) pb=0; if(isNaN(bt)) bt=0; if(isNaN(bb)) bb=0;
  var cssH=uH-(pt+pb+bt+bb);
  if(isNaN(cssH)||cssH<0) return;
  else ele.style.height=cssH+"px";
}
function xClip(e,iTop,iRight,iBottom,iLeft) {
  if(!(e=xGetElementById(e))) return;
  if(e.style) {
    if (arguments.length == 5) e.style.clip="rect("+iTop+"px "+iRight+"px "+iBottom+"px "+iLeft+"px)";
    else e.style.clip="rect(0 "+parseInt(e.style.width)+"px "+parseInt(e.style.height)+"px 0)";
  }
  else if(e.clip) {
    if (arguments.length == 5) { e.clip.top=iTop; e.clip.right=iRight; e.clip.bottom=iBottom; e.clip.left=iLeft; }
    else { e.clip.top=0; e.clip.right=xWidth(e); e.clip.bottom=xHeight(e); e.clip.left=0; }
  }
}

// Event:
function xAddEventListener(e,eventType,eventListener,useCapture) {
  if(!(e=xGetElementById(e))) return;
  eventType=eventType.toLowerCase();
  if((!xIE4Up && !xOp7) && e==window) {
    if(eventType=='resize') { window.xPCW=xClientWidth(); window.xPCH=xClientHeight(); window.xREL=eventListener; xResizeEvent(); return; }
    if(eventType=='scroll') { window.xPSL=xScrollLeft(); window.xPST=xScrollTop(); window.xSEL=eventListener; xScrollEvent(); return; }
  }
  var eh="e.on"+eventType+"=eventListener";
  if(e.addEventListener) e.addEventListener(eventType,eventListener,useCapture);
  else if(e.attachEvent) e.attachEvent("on"+eventType,eventListener);
  else if(e.captureEvents) {
    if(useCapture||(eventType.indexOf('mousemove')!=-1)) { e.captureEvents(eval("Event."+eventType.toUpperCase())); }
    eval(eh);
  }
  else eval(eh);
}
function xRemoveEventListener(e,eventType,eventListener,useCapture) {
  if(!(e=xGetElementById(e))) return;
  eventType=eventType.toLowerCase();
  if((!xIE4Up && !xOp7) && e==window) {
    if(eventType=='resize') { window.xREL=null; return; }
    if(eventType=='scroll') { window.xSEL=null; return; }
  }
  var eh="e.on"+eventType+"=null";
  if(e.removeEventListener) e.removeEventListener(eventType,eventListener,useCapture);
  else if(e.detachEvent) e.detachEvent("on"+eventType,eventListener);
  else if(e.releaseEvents) {
    if(useCapture||(eventType.indexOf('mousemove')!=-1)) { e.releaseEvents(eval("Event."+eventType.toUpperCase())); }
    eval(eh);
  }
  else eval(eh);
}
function xEvent(evt) { // cross-browser event object prototype
  this.type = "";
  this.target = null;
  this.pageX = 0;
  this.pageY = 0;
  this.offsetX = 0;
  this.offsetY = 0;
  var e = evt ? evt : window.event;
  if(!e) return;
  // type
  if(e.type) this.type = e.type;
  // target
  if(xNN4) this.target = xLayerFromPoint(e.pageX, 1500);//e.pageY);
  else if(e.target) this.target = e.target;
  else if(e.srcElement) this.target = e.srcElement;
  // pageX, pageY
  if(xOp5or6) { this.pageX = e.clientX; this.pageY = e.clientY; }
  else if(xDef(e.pageX,e.pageY)) { this.pageX = e.pageX; this.pageY = e.pageY; }
  else if(xDef(e.clientX,e.clientY)) { this.pageX = e.clientX + xScrollLeft(); this.pageY = e.clientY + xScrollTop(); }
  // offsetX, offsetY
  if(xDef(e.layerX,e.layerY)) { this.offsetX = e.layerX; this.offsetY = e.layerY; }
  else if(xDef(e.offsetX,e.offsetY)) { this.offsetX = e.offsetX; this.offsetY = e.offsetY; }
  else { this.offsetX = this.pageX - xPageX(this.target); this.offsetY = this.pageY - xPageY(this.target); }
}
function xResizeEvent() { // window resize event simulation
  setTimeout("xResizeEvent()", 250);
  var cw = xClientWidth(), ch = xClientHeight();
  if (window.xPCW != cw || window.xPCH != ch) { window.xPCW = cw; window.xPCH = ch; window.xREL(); }
}
function xScrollEvent() { // window scroll event simulation
  setTimeout("xScrollEvent()", 250);
  var sl = xScrollLeft(), st = xScrollTop();
  if (window.xPSL != sl || window.xPST != st) { window.xPSL = sl; window.xPST = st; window.xSEL(); }
}

// Object:
function xGetElementById(e) {
  if(typeof(e)!="string") return e;
  if(document.getElementById) e=document.getElementById(e);
  else if(document.all) e=document.all[e];
  else if(document.layers) e=xLayer(e);
  else e=null;
  return e;
}
function xLayer(id,root) { // only for nn4
  var i,layer,found=null;
  if (!root) root=window;
  for(i=0; i<root.document.layers.length; i++) {
    layer=root.document.layers[i];
    if(layer.id==id) return layer;
    if(layer.document.layers.length) found=xLayer(id,layer);
    if(found) return found;
  }
  return null;
}
function xLayerFromPoint(x,y,root) { // only for nn4
  var i, hn=null, hz=-1, cn;
  if (!root) root = window;
  for (i=0; i < root.document.layers.length; ++i) {
    cn = root.document.layers[i];
    if (cn.visibility != "hide" && x >= cn.pageX && x <= cn.pageX + cn.clip.right && y >= cn.pageY && y <= cn.pageY + cn.clip.bottom ) {
      if (cn.zIndex > hz) { hz = cn.zIndex; hn = cn; }
    }
  }
  if (hn) {
    cn = xLayerFromPoint(x,y,hn);
    if (cn) hn = cn;
  }
  return hn;
}
function xParent(e){
  if (!(e=xGetElementById(e))) return null;
  var p=null;
  if (e.parentLayer){if (e.parentLayer!=window) p=e.parentLayer;}
  else{
    if (e.offsetParent) p=e.offsetParent;
    else if (e.parentNode) p=e.parentNode;
    else if (e.parentElement) p=e.parentElement;
  }
  return p;
}
function xDef() {
  for(var i=0; i<arguments.length; ++i){if(typeof(arguments[i])=="" || typeof(arguments[i])=="undefined") return false;}
  return true;
}

// Window:
function xScrollLeft() {
  var offset=0;
  if(xDef(window.pageXOffset)) offset=window.pageXOffset;
  else if(document.documentElement && document.documentElement.scrollLeft) offset=document.documentElement.scrollLeft;
  else if(document.body && xDef(document.body.scrollLeft)) offset=document.body.scrollLeft;
  return offset;
}
function xScrollTop() {
  var offset=0;
  if(xDef(window.pageYOffset)) offset=window.pageYOffset;
  else if(document.documentElement && document.documentElement.scrollTop) offset=document.documentElement.scrollTop;
  else if(document.body && xDef(document.body.scrollTop)) offset=document.body.scrollTop;
  return offset;
}
function xClientWidth() {
  var w=0;
  if(xOp5or6) w=window.innerWidth;
  else if(xIE4Up && document.documentElement && document.documentElement.clientWidth)
    w=document.documentElement.clientWidth;
  else if(document.body && document.body.clientWidth)
    w=document.body.clientWidth;
  else if(xDef(window.innerWidth,window.innerHeight,document.height)) {
    w=window.innerWidth;
    if(document.height>window.innerHeight) w-=16;
  }
  return w;
}
function xClientHeight() {
  var h=0;
  if(xOp5or6) h=window.innerHeight;
  else if(xIE4Up && document.documentElement && document.documentElement.clientHeight)
    h=document.documentElement.clientHeight;
  else if(document.body && document.body.clientHeight)
    h=document.body.clientHeight;
  else if(xDef(window.innerWidth,window.innerHeight,document.width)) {
    h=window.innerHeight;
    if(document.width>window.innerWidth) h-=16;
  }
  return h;
}

// end x.js