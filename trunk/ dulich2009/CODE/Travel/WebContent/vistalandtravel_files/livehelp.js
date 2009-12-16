var WEBPATH = "http://www.vistalandtravel.com/livesupport/"; 
//-----------------------------------------------------------------
// File: livehelp.js :
//      - This is the client side Javascript file to control the 
//        image shown on the clients website. It should be called
//        on the clients HTML page as a javascript include such as:
//        script src="http://yourwebsite.com/livehelp/livehelp_js.php"
//        This js file will show the image of online.gif if an operator
//        is online otherwise it will show offline.gif . Also a 
//        second image is placed on the site as a control image 
//        where the width of the image controls the actions made by 
//        the operator to the poor little visitor..  
// 
//-----------------------------------------------------------------

// GLOBALS..
//------------
// This is the control image where the width of it controls the 
// actions made by the operator. 
cscontrol= new Image;
popcontrol= new Image;
popcontrol2= new Image;
popcontrol3= new Image;

   keyhundreds= new Image;
   keytens= new Image;
   keyones= new Image;

   keyhundreds_value= 0;
   keytens_value= 0;
   keyones_value= 0;
place =1;
// this is a flag to control if the image is set on the page 
// yet or not..
var csloaded = false;

// just to make sure that people do not just open up the page 
// and leave it open the requests timeout after 99 requests.
var csTimeout = 15;

// The id of the page request. 
var csID = null;

// if the operator requests a chat we only want to open one window... 
var openLiveHelpalready = false;

var openDHTMLalready = false;
var openDHTMLlayer = false;

var ismac = navigator.platform.indexOf('Mac');
// ismac =1;

 var defaultlayer = 1; 
    
    
////////////////////////////////////////////////////////////////
//BEGIN INCLUDED LIBRARY
// Dynamic Layer Object
// sophisticated layer/element targeting and animation object which provides the core functionality needed in most DHTML applications
// 19990604

// Copyright (C) 1999 Dan Steinman
// Distributed under the terms of the GNU Library General Public License
// Available at http://www.dansteinman.com/dynapi/

// updated 20011228 by Bob Clary <bc@bclary.com>
// to support Gecko

function aDynLayer(id,nestref,frame) {
	//bc:maybe? if (!is.ns5 && !aDynLayer.set && !frame) aDynLayerInit()
	if (!aDynLayer.set && !frame) aDynLayerInit()
	this.frame = frame || self
	//bc:if (is.ns) {
	if (is.ns4) {
		if (is.ns4) {
			if (!frame) {
				if (!nestref) var nestref = aDynLayer.nestRefArray[id]
				if (!aDynLayerTest(id,nestref)) return
				this.css = (nestref)? eval("document."+nestref+".document."+id) : document.layers[id]
			}
			else this.css = (nestref)? eval("frame.document."+nestref+".document."+id) : frame.document.layers[id]
			this.elm = this.event = this.css
			this.doc = this.css.document
		}
		//bc:else if (is.ns5) {
		//bc:	this.elm = document.getElementById(id)
		//bc:	this.css = this.elm.style
		//bc:	this.doc = document
		//bc: }
		this.x = this.css.left
		this.y = this.css.top
		this.w = this.css.clip.width
		this.h = this.css.clip.height
	}
	//bc:else if (is.ie) {
	else if (is.ie || is.ns5) {
    //bc:
    if (is.ie)
		this.elm = this.event = this.frame.document.all[id]
    //bc:
    else 
		this.elm = this.event = this.frame.document.getElementById(id)

		//bc:this.css = this.frame.document.all[id].style
		this.css = this.elm.style
		this.doc = document
		this.x = this.elm.offsetLeft
		this.y = this.elm.offsetTop
		this.w = (is.ie4)? this.css.pixelWidth : this.elm.offsetWidth
		this.h = (is.ie4)? this.css.pixelHeight : this.elm.offsetHeight
	}
	this.id = id
	this.nestref = nestref
	this.obj = id + "aDynLayer"
	eval(this.obj + "=this")
}
function aDynLayerMoveTo(x,y) {
	if (x!=null) {
		this.x = x
		//bc:if (is.ns) this.css.left = this.x
		if (is.ns4) this.css.left = this.x
		//bc:else this.css.pixelLeft = this.x
		else if (is.ie) this.css.pixelLeft = this.x
		else if (is.ns5) this.css.left = Math.floor(this.x) + 'px'
	}
	if (y!=null) {
		this.y = y
		//bc:if (is.ns) this.css.top = this.y
		if (is.ns4) this.css.top = this.y
		//bc:else this.css.pixelTop = this.y
		else if (is.ie) this.css.pixelTop = this.y
		else if (is.ns5) this.css.top = Math.floor(this.y) + 'px'
	}
}
function aDynLayerMoveBy(x,y) {
	this.moveTo(this.x+x,this.y+y)
}
function aDynLayerShow() {
	this.css.visibility = (is.ns4)? "show" : "visible"
}
function aDynLayerHide() {
	this.css.visibility = (is.ns4)? "hide" : "hidden"
}
aDynLayer.prototype.moveTo = aDynLayerMoveTo
aDynLayer.prototype.moveBy = aDynLayerMoveBy
aDynLayer.prototype.show = aDynLayerShow
aDynLayer.prototype.hide = aDynLayerHide
aDynLayerTest = new Function('return true')

// aDynLayerInit Function
function aDynLayerInit(nestref) {
	if (!aDynLayer.set) aDynLayer.set = true
	//bc:if (is.ns) {
	if (is.ns4) {
		if (nestref) ref = eval('document.'+nestref+'.document')
		else {nestref = ''; ref = document;}
		for (var i=0; i<ref.layers.length; i++) {
			var divname = ref.layers[i].name
			aDynLayer.nestRefArray[divname] = nestref
			var index = divname.indexOf("Div")
			if (index > 0) {
				eval(divname.substr(0,index)+' = new aDynLayer("'+divname+'","'+nestref+'")')
			}
			if (ref.layers[i].document.layers.length > 0) {
				aDynLayer.refArray[aDynLayer.refArray.length] = (nestref=='')? ref.layers[i].name : nestref+'.document.'+ref.layers[i].name
			}
		}
		if (aDynLayer.refArray.i < aDynLayer.refArray.length) {
			aDynLayerInit(aDynLayer.refArray[aDynLayer.refArray.i++])
		}
	}
	else if (is.ie) {
		for (var i=0; i<document.all.tags("DIV").length; i++) {
			var divname = document.all.tags("DIV")[i].id
			var index = divname.indexOf("Div")
			if (index > 0) {
				eval(divname.substr(0,index)+' = new aDynLayer("'+divname+'")')
			}
		}
	}
  //bc:
	else if (is.ns5) {
    var nodeList = document.getElementsByTagName('div');
		for (var i=0; i<nodeList.length; i++) {
			var divname = nodeList[i].id
			var index = divname.indexOf("Div")
			if (index > 0) {
				eval(divname.substr(0,index)+' = new aDynLayer("'+divname+'")')
			}
		}
	}
	return true
}
aDynLayer.nestRefArray = new Array()
aDynLayer.refArray = new Array()
aDynLayer.refArray.i = 0
aDynLayer.set = false

// Slide Methods
function aDynLayerSlideTo(endx,endy,inc,speed,fn) {
	if (endx==null) endx = this.x
	if (endy==null) endy = this.y
	var distx = endx-this.x
	var disty = endy-this.y
	this.slideStart(endx,endy,distx,disty,inc,speed,fn)
}
function aDynLayerSlideBy(distx,disty,inc,speed,fn) {
	var endx = this.x + distx
	var endy = this.y + disty
	this.slideStart(endx,endy,distx,disty,inc,speed,fn)
}
function aDynLayerSlideStart(endx,endy,distx,disty,inc,speed,fn) {
	if (this.slideActive) return
	if (!inc) inc = 10
	if (!speed) speed = 20
	var num = Math.sqrt(Math.pow(distx,2) + Math.pow(disty,2))/inc
	if (num==0) return
	var dx = distx/num
	var dy = disty/num
	if (!fn) fn = null
	this.slideActive = true
	this.slide(dx,dy,endx,endy,num,1,speed,fn)
}
function aDynLayerSlide(dx,dy,endx,endy,num,i,speed,fn) {
	if (!this.slideActive) return
	if (i++ < num) {
		this.moveBy(dx,dy)
		this.onSlide()
		if (this.slideActive) setTimeout(this.obj+".slide("+dx+","+dy+","+endx+","+endy+","+num+","+i+","+speed+",\""+fn+"\")",speed)
		else this.onSlideEnd()
	}
	else {
		this.slideActive = false
		this.moveTo(endx,endy)
		this.onSlide()
		this.onSlideEnd()
		eval(fn)
	}
}
function aDynLayerSlideInit() {}
aDynLayer.prototype.slideInit = aDynLayerSlideInit
aDynLayer.prototype.slideTo = aDynLayerSlideTo
aDynLayer.prototype.slideBy = aDynLayerSlideBy
aDynLayer.prototype.slideStart = aDynLayerSlideStart
aDynLayer.prototype.slide = aDynLayerSlide
aDynLayer.prototype.onSlide = new Function()
aDynLayer.prototype.onSlideEnd = new Function()

// Clip Methods
function aDynLayerClipInit(clipTop,clipRight,clipBottom,clipLeft) {
	//bc:if (is.ie) {
	if (is.ie||is.ns5) {
		if (arguments.length==4) this.clipTo(clipTop,clipRight,clipBottom,clipLeft)
		else if (is.ie4) this.clipTo(0,this.css.pixelWidth,this.css.pixelHeight,0)
    //bc:
		else if (is.ns5) this.clipTo(0,this.elm.offsetWidth,this.elm.offsetHeight,0)
	}
}
function aDynLayerClipTo(t,r,b,l) {
	if (t==null) t = this.clipValues('t')
	if (r==null) r = this.clipValues('r')
	if (b==null) b = this.clipValues('b')
	if (l==null) l = this.clipValues('l')
	//bc:if (is.ns) {
	if (is.ns4) {
		this.css.clip.top = t
		this.css.clip.right = r
		this.css.clip.bottom = b
		this.css.clip.left = l
	}
	//bc:else if (is.ie) this.css.clip = "rect("+t+"px "+r+"px "+b+"px "+l+"px)"
	else if (is.ie||is.ns5) this.css.clip = "rect("+t+"px "+r+"px "+b+"px "+l+"px)"
}
function aDynLayerClipBy(t,r,b,l) {
	this.clipTo(this.clipValues('t')+t,this.clipValues('r')+r,this.clipValues('b')+b,this.clipValues('l')+l)
}
function aDynLayerClipValues(which) {
	//bc:if (is.ie) var clipv = this.css.clip.split("rect(")[1].split(")")[0].split("px")
	if (is.ie||is.ns5) var clipv = this.css.clip.split("rect(")[1].split(")")[0].split("px")
	//bc:if (which=="t") return (is.ns)? this.css.clip.top : Number(clipv[0])
	if (which=="t") return (is.ns4)? this.css.clip.top : Number(clipv[0])
	//bc:if (which=="r") return (is.ns)? this.css.clip.right : Number(clipv[1])
	if (which=="r") return (is.ns4)? this.css.clip.right : Number(clipv[1])
	//bc:if (which=="b") return (is.ns)? this.css.clip.bottom : Number(clipv[2])
	if (which=="b") return (is.ns4)? this.css.clip.bottom : Number(clipv[2])
	//bc:if (which=="l") return (is.ns)? this.css.clip.left : Number(clipv[3])
	if (which=="l") return (is.ns4)? this.css.clip.left : Number(clipv[3])
}
aDynLayer.prototype.clipInit = aDynLayerClipInit
aDynLayer.prototype.clipTo = aDynLayerClipTo
aDynLayer.prototype.clipBy = aDynLayerClipBy
aDynLayer.prototype.clipValues = aDynLayerClipValues

// Write Method
function aDynLayerWrite(html) {
	//bc:if (is.ns) {
	if (is.ns4) {
		this.doc.open()
		this.doc.write(html)
		this.doc.close()
	}
	//bc:else if (is.ie) {
	else if (is.ie||is.ns5) {
		this.event.innerHTML = html
	}
}
aDynLayer.prototype.write = aDynLayerWrite

// BrowserCheck Object
function BrowserCheck() {
	var b = navigator.appName
	if (b=="Netscape") this.b = "ns"
	else if (b=="Microsoft Internet Explorer") this.b = "ie"
	else this.b = b
	this.version = navigator.appVersion
	this.v = parseInt(this.version)
	this.ns = (this.b=="ns" && this.v>=4)
	this.ns4 = (this.b=="ns" && this.v==4)
	this.ns5 = (this.b=="ns" && this.v==5)
	this.ie = (this.b=="ie" && this.v>=4)
	this.ie4 = (this.version.indexOf('MSIE 4')>0)
	this.ie5 = (this.version.indexOf('MSIE 5')>0)
	this.min = (this.ns||this.ie)
}
is = new BrowserCheck()

// CSS Function
function css(id,left,top,width,height,color,vis,z,other) {
	if (id=="START") return '<STYLE TYPE="text/css">\n'
	else if (id=="END") return '</STYLE>'
	var str = (left!=null && top!=null)? '#'+id+' {position:absolute; left:'+left+'px; top:'+top+'px;' : '#'+id+' {position:relative;'
	if (arguments.length>=4 && width!=null) str += ' width:'+width+'px;'
	if (arguments.length>=5 && height!=null) {
		str += ' height:'+height+'px;'
		if (arguments.length<9 || other.indexOf('clip')==-1) str += ' clip:rect(0px '+width+'px '+height+'px 0px);'
	}
	//bc:if (arguments.length>=6 && color!=null) str += (is.ns)? ' layer-background-color:'+color+';' : ' background-color:'+color+';'
	if (arguments.length>=6 && color!=null) str += (is.ns4)? ' layer-background-color:'+color+';' : ' background-color:'+color+';'
	if (arguments.length>=7 && vis!=null) str += ' visibility:'+vis+';'
	if (arguments.length>=8 && z!=null) str += ' z-index:'+z+';'
	if (arguments.length==9 && other!=null) str += ' '+other
	str += '}\n'
	return str
}
function writeCSS(str,showAlert) {
	str = css('START')+str+css('END')
	document.write(str)
	if (showAlert) alert(str)
}
// CreateLayer and DestroyLayer Functions
// enables you to dynamically create a layer after the page has been loaded, can only truely delete layers in IE
// 19990326

// Copyright (C) 1999 Dan Steinman
// Distributed under the terms of the GNU Library General Public License
// Available at http://www.dansteinman.com/dynapi/

// updated 20011228 by Bob Clary <bc@bclary.com>
// to support Gecko

function createLayer(id,nestref,left,top,width,height,content,bgColor,visibility,zIndex) {
	//bc:if (is.ns) {
	if (is.ns4) {
		if (nestref) {
			var lyr = eval("document."+nestref+".document."+id+" = new Layer(width, document."+nestref+")")
		}
		else {
			var lyr = document.layers[id] = new Layer(width)
			eval("document."+id+" = lyr")
		}
		lyr.name = id
		lyr.left = left
		lyr.top = top
		if (height!=null) lyr.clip.height = height
		if (bgColor!=null) lyr.bgColor = bgColor
		lyr.visibility = (visibility=='hidden')? 'hide' : 'show'
		if (zIndex!=null) lyr.zIndex = zIndex
		if (content) {
			lyr.document.open()
			lyr.document.write(content)
			lyr.document.close()
		}
	}
	//bc:else if (is.ie) {
	else if (is.ie || is.ns5) {
		var str = '\n<DIV id='+id+' style="position:absolute; left:'+left+'; top:'+top+'; width:'+width
		if (height!=null) {
			str += '; height:'+height
			str += '; clip:rect(0,'+width+','+height+',0)'
		}
		if (bgColor!=null) str += '; background-color:'+bgColor		
		if (zIndex!=null) str += '; z-index:'+zIndex
		if (visibility) str += '; visibility:'+visibility
		str += ';">'+((content)?content:'')+'</DIV>'
    //bc:
    var elmref;
		if (nestref) {
			index = nestref.lastIndexOf(".")
			var nestlyr = (index != -1)? nestref.substr(index+1) : nestref
      //bc:
      if (is.ie)
			document.all[nestlyr].insertAdjacentHTML("BeforeEnd",str);
      else
      {
      elmref = document.getElementById(nestlyr);
      elmref.innerHTML += str;
      }
		}
		else {
      //bc:
      if (is.ie)
			document.body.insertAdjacentHTML("BeforeEnd",str)
      else
      {
      elmref = document.body;
      elmref.innerHTML += str;
      }
		}
	}
}
function destroyLayer(id,nestref) {
	//bc:if (is.ns) {
	if (is.ns4) {
		if (nestref) eval("document."+nestref+".document."+id+".visibility = 'hide'")
		else document.layers[id].visibility = "hide"
	}
	else if (is.ie) {
		document.all[id].innerHTML = ""
		document.all[id].outerHTML = ""
	}
  //bc:
  else if (is.ns5) {
    var elmref = document.getElementById(id);
    if (elmref)
      elmref.parentNode.removeChild(elmref);
  }
}

//END INCLUDED LIBRARY
////////////////////////////////////////////////////////////////

//-----------------------------------------------------------------
// loop though checking the image for updates from operators.
function csrepeat()
{
     // if the request has timed out do not do anything.
     if (csTimeout < 1)
       	return;

     csTimeout--;

     // update image for requests from operator. 
     csgetimage();     

     // do it again. 
     setTimeout('csrepeat()', 7000);
}	

//-----------------------------------------------------------------
// Update the control image. This is the image that the operators 
// use to communitate with the visitor. 
function csgetimage()
{	 

	 // set a number to identify this page .
	 if (csID==null) csID=Math.round(Math.random()*9999);
	 randu=Math.round(Math.random()*9999);
	 
   cscontrol = new Image;
   locationvar = '' + document.location;
   locationvar = locationvar.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   locationvar = locationvar.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1");
   locationvar = locationvar.replace(new RegExp("[\.]","g"),"--dot--");
   locationvar = locationvar.replace(new RegExp("http://","g"),"");
   locationvar = locationvar.replace(new RegExp("https://","g"),"");   
   locationvar = locationvar.substr(0,250);
   var_title = '' + document.title;
   var_title = var_title.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_title = var_title.substr(0,100);
   var_referrer = '' + document.referrer;
   var_referrer = var_referrer.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_referrer = var_referrer.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1"); 
   var_referrer = var_referrer.replace(new RegExp("[\.]","g"),"--dot--");
   var_referrer = var_referrer.replace(new RegExp("http://","g"),"");
   var_referrer = var_referrer.replace(new RegExp("https://","g"),"");      
   var_referrer = var_referrer.substr(0,250);
   
	 var u = WEBPATH + 'image.php?' + 
					'what=userstat' + 
					'&page=' + escape(locationvar) + 
					'&randu=' + randu +
					'&pageid=' + csID +
					'&department=' + 1 +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&title=' + escape(var_title) + 
					'&referer=' + escape(var_referrer) + 					
					'&cslheg=1&serversession=1';
     	 
	 if (ismac > -1){
       document.getElementById("imageformac").src= u;
       document.getElementById("imageformac").onload = cslookatimage;
    } else {
       cscontrol.src = u;
       cscontrol.onload = cslookatimage;
    }      	
}

// detect browser 
NS4 = (document.layers) ? 1 : 0; 
IE4 = (document.all) ? 1 : 0; 
// W3C stands for the W3C standard 
W3C = (document.getElementById) ? 1 : 0;   

function makeVisible ( name ) { 
  var ele; 

  if ( W3C ) { 
    ele = document.getElementById(name); 
  } else if ( NS4 ) { 
    ele = document.layers[name]; 
  } else { // IE4 
    ele = document.all[name]; 
  } 

  if ( NS4 ) { 
    ele.visibility = "show"; 
  } else {  // IE4 & W3C & Mozilla 
    ele.style.visibility = "visible"; 
    ele.style.display = "inline"; 
  } 
} 

function makeInvisible ( name ) { 
  if (W3C) { 
    document.getElementById(name).style.visibility = "hidden"; 
    document.getElementById(name).style.display = "none"; 
  } else if (NS4) { 
    document.layers[name].visibility = "hide"; 
  } else { 
    document.all[name].style.visibility = "hidden"; 
    document.all[name].style.style.display = "none"; 
  } 
} 


// looks at the size of the control image and if the width is 55 
// then open the chat.
//-----------------------------------------------------------------
function cslookatimage(){

	if(typeof(cscontrol) == 'undefined' ){
    return; 
  }  
 
	 var w = 0;
	 
	 	if (ismac > -1)
      w = document.getElementById("imageformac").width;
    else
      w = cscontrol.width;        
  
    // if the browser is dumb:
    if((ismac > -1) && (w == 0)){
      makeVisible('imagesfordumbmac');     
      w = document.getElementById("imageformac").width;
      makeInvisible('imagesfordumbmac');         
    }
  
      // pop up window:
      if ((w == 55) && (openLiveHelpalready != 1)) {
  		  openWantsToChat_1();
		    openLiveHelpalready = 1;
	    } 
 
      // layer invite:
	    if ((w == 25) && !(openDHTMLalready)) {    
  		   loadKey_1();
	    } 	        	        
    
      delete cscontrol;
 
}


//-----------------------------------------------------------------
// opens live help
function openLiveHelp(department)
{
  if(openDHTMLlayer == 1)
    destroyLayer('mylayer1Div');
  openDHTMLlayer = 0;  
  openDHTMLalready = true;  
  csTimeout=0; 
      window.open(WEBPATH + 'livehelp.php?department=' + department + '&cslheg=1&serversession=1', 'chat54050872', 'width=585,height=390,menubar=no,scrollbars=1,resizable=1');
  }

function getKeys_1(){
  	if (ismac > -1){
      w3 = document.getElementById("imageformac_hun").width;
      w2 = document.getElementById("imageformac_ten").width;
      w1 = document.getElementById("imageformac_one").width;
      
      if(w1 == 0){
        makeVisible('imagesfordumbmac'); 
        w1 = document.getElementById("imageformac_one").width;
        makeInvisible('imagesfordumbmac');         
      }

      if(w2 == 0){
        makeVisible('imagesfordumbmac'); 
        w2 = document.getElementById("imageformac_ten").width;
        makeInvisible('imagesfordumbmac');         
      }

      if(w3 == 0){
        makeVisible('imagesfordumbmac'); 
        w3 = document.getElementById("imageformac_hun").width;
        makeInvisible('imagesfordumbmac');         
      }              
            
    } else {
      w3 = keyhundreds.width;  
      w2 = keytens.width;  
      w1 = keyones.width;              
    }      
 
    if(w1<100) w1 = 100;
    if(w2<100) w2 = 100;
    if(w3<100) w3 = 100;    
   // alert('w1='+w1+'w2='+w2+'w3='+w3);
    
    total = ((w3-100)*100) + ((w2-100)*10) + (w1-100);
    
   // alert(total);
    openDHTML_1(total);    
}

//-----------------------------------------------------------------
// gets primary key of layerinvite sent using 3 images.. could use XML HTTP
// but this is more compatable...
function loadKey_1(){  
   
	 randu=Math.round(Math.random()*9999);

	 if(place == 3){
	 var u3 = WEBPATH + 'image.php?' + 
					'what=getlayerinvite&whatplace=hundreds' + 
					'&randu=' + randu +
					'&department=' + 1 +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
      if (ismac > -1){
       document.getElementById("imageformac_hun").src= u3;
       document.getElementById("imageformac_hun").onload = getKeys_1;       
      } else {
       keyhundreds.src = u3;
       keyhundreds.onload = getKeys_1; }
    }			
	 if(place == 2){
	 	    place = 3;
	 var u2 = WEBPATH + 'image.php?' + 
					'what=getlayerinvite&whatplace=tens' + 
					'&randu=' + randu +
					'&department=' + 1 +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
      if (ismac > -1){
       document.getElementById("imageformac_ten").src= u2;
       document.getElementById("imageformac_ten").onload = loadKey_1;       
      } else {
       keytens.src = u2;
       keytens.onload = loadKey_1;      
      }
    }    								   
	 if(place == 1){
	     place = 2;	
	    var u1 = WEBPATH + 'image.php?' + 
					'what=getlayerinvite&whatplace=ones' + 
					'&randu=' + randu +
					'&department=' + 1 +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
      if (ismac > -1){
       document.getElementById("imageformac_one").src= u1;
       document.getElementById("imageformac_one").onload = loadKey_1;       
      } else {
       keyones.src = u1;
       keyones.onload = loadKey_1;      
      }
    }
}
//-----------------------------------------------------------------
// opens DHTML help
function openDHTML_1(total)
{ 
  var html = '';
		     
  if (total == 1)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-Subsilver.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,419,216><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,216,319,279><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=326,218,429,280></MAP>'
if (total == 2)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-Help_button.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,400,197><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,157,213,257><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=237,157,400,257></MAP>'
if (total == 3)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-Man_invite.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,400,197><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,157,213,257><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=237,157,400,257></MAP>'
if (total == 4)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-Woman_invite.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,400,197><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,157,213,257><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=237,157,400,257></MAP>'
if (total == 5)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-Phone.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,472,150><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=241,150,484,256><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=0,150,241,250></MAP>'
if (total == 6)
    html = '<img src=' + WEBPATH + 'layer_invites/layer-default.gif  border=0 usemap=#myimagemap></a><MAP NAME=myimagemap><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,0,400,197><AREA HREF=javascript:openLiveHelp(1) SHAPE=RECT COORDS=0,157,213,257><AREA HREF=javascript:closeDHTML() SHAPE=RECT COORDS=237,157,400,257></MAP>'
  //alert(html);
  createLayer('mylayer1Div',null,100,200,550,400,html,null,null,999)
	mylayer1 = new aDynLayer('mylayer1Div');

	 var u = WEBPATH + 'image.php?' + 
					'what=changestat' + 
					'&towhat=invited' +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
	 popcontrol2.src = u;	
	 stillopen = 1;
   setTimeout('moveDHTML_1()', 9000);
	 openDHTMLalready = true;
	 openDHTMLlayer = true;
}

//-----------------------------------------------------------------
// opens DHTML help
function closeDHTML()
{ 
	destroyLayer('mylayer1Div');
	openDHTMLlayer = 0;
	stillopen = 0;
	
	var u4 = WEBPATH + 'image.php?' + 
					'what=changestat' + 
					'&towhat=stopped' +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
  popcontrol3.src = u4;	
	 	 
}

//-----------------------------------------------------------------
// opens DHTML help
function moveDHTML_1()
{ 
  if(stillopen==1){
   if(navigator.appName.indexOf("Netscape") != -1){
    myWidth	=   window.pageXOffset;
    myHeight	= window.pageYOffset
   } else {
    myWidth	=  document.body.scrollLeft;
    myHeight	=  document.body.scrollTop;
  }
 
   mylayer1.moveTo(myWidth+200,myHeight+100);
   setTimeout('moveDHTML_1()', 9000);
  }
}

//-----------------------------------------------------------------
// The Operator wants to chat with the visitor about something. 
function openWantsToChat_1()
{  
  // ok we asked them .. now lets not ask them again for awhile...


   locationvar = '' + document.location;
   locationvar = locationvar.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   locationvar = locationvar.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1");
   locationvar = locationvar.replace(new RegExp("[\.]","g"),"--dot--");   
   locationvar = locationvar.replace(new RegExp("http://","g"),"");
   locationvar = locationvar.replace(new RegExp("https://","g"),"");
   locationvar = locationvar.substr(0,250);
   var_title = '' + document.title;
   var_title = var_title.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_title = var_title.substr(0,100);
   var_referrer = '' + document.referrer;
   var_referrer = var_referrer.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_referrer = var_referrer.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1");
   var_referrer = var_referrer.replace(new RegExp("[\.]","g"),"--dot--");  
   var_referrer = var_referrer.replace(new RegExp("http://","g"),"");
   var_referrer = var_referrer.replace(new RegExp("https://","g"),"");      
   
   var_referrer = var_referrer.substr(0,250);
   
  var u = WEBPATH + 'image.php?' + 
					'what=browse' + 
					'&page=' + escape(locationvar) + 
					'&title=' + escape(var_title) + 
					'&referer=' + escape(var_referrer) + 
					'&pageid=' + csID +
					'&department=' + 1 +
					'&cslhVISITOR=' + '0afecacb9a768435fc93a377cf697db6' +
					'&cslheg=1&serversession=1';
  cscontrol.src = u;  

  // open the window.. 
  window.open(WEBPATH + 'livehelp.php?what=chatinsession&department=1&cslhVISITOR=0afecacb9a768435fc93a377cf697db6&cslheg=1&serversession=1', 'chat54050872', 'width=585,height=390,menubar=no,scrollbars=1,resizable=1');
}

   locationvar = '' + document.location;
   locationvar = locationvar.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   locationvar = locationvar.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1");
   locationvar = locationvar.replace(new RegExp("[\.]","g"),"--dot--");   
   locationvar = locationvar.replace(new RegExp("http://","g"),"");
   locationvar = locationvar.replace(new RegExp("https://","g"),"");   
   locationvar = locationvar.substr(0,250);
   var_title = '' + document.title;   
   var_title = var_title.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_title = var_title.substr(0,100);
   var_referrer = '' + document.referrer;
   var_referrer = var_referrer.replace(new RegExp("[^A-Za-z0-9_)\+\^{}~( ',\.\&\%=/\\?#:-]","g"),"");
   var_referrer = var_referrer.replace(new RegExp("=[a-z0-9]{32}","g"),"x=1");
   var_referrer = var_referrer.replace(new RegExp("[\.]","g"),"--dot--");  
   var_referrer = var_referrer.replace(new RegExp("http://","g"),"");
   var_referrer = var_referrer.replace(new RegExp("https://","g"),"");         
   var_referrer = var_referrer.substr(0,250);
   	
  var urltohelpimage = 'http://www.vistalandtravel.com/livesupport/image.php?what=getstate&department=1&nowis=20091129032138&cslhVISITOR=0afecacb9a768435fc93a377cf697db6' + 
					'&page=' + escape(locationvar) + 
					'&referer=' + escape(var_referrer) + 					
					'&title=' + escape(var_title) + 
					'&cslheg=1&serversession=1';

  var urltocreditimage = 'http://www.vistalandtravel.com/livesupport/image.php?what=getcredit&department=1&nowis=20091129032138&cslhVISITOR=0afecacb9a768435fc93a377cf697db6' + 
					'&page=' + escape(locationvar) + 
					'&referer=' + escape(var_referrer) +					
					'&title=' + escape(var_title) + 
					'&cslheg=1&serversession=1';
					
//  document.write(urltohelpimage);			
  
  document.write('<table border="0" cellspacing="0" cellpadding="0"><tr><td align="center" valign="top">');
  document.write('<a name="chatRef" href="javascript:openLiveHelp(1)"  onclick="javascript:csTimeout=0;"><img name="csIcon" src="' + urltohelpimage + '" alt="Powered by Crafty Syntax" border="0"></a>');
  document.write('</td></tr><tr><td align="center" valign="top">');
  document.write('<a name="byRef" href="http://www.craftysyntax.com/" target="_blank"><img name="myIcon" src="' + urltocreditimage + '" alt="Powered by Crafty Syntax" border="0"></a>');
  document.write('</td></tr></table>');

// macs do not see images in cache:
if (ismac > -1) {
	randu=Math.round(Math.random()*9999);
  document.write('<div id=imagesfordumbmac style=display:none>');
  document.write('<img id="imageformac" name="imageformac" src="' + WEBPATH + 'images/blank.gif" border="0"><img id="imageformac_one" name="imageformac_one" src="' + WEBPATH + 'images/blank.gif" border="0"><img id="imageformac_ten" name="imageformac_ten" src="' + WEBPATH + 'images/blank.gif" border="0"><img id="imageformac_hun" name="imageformac_hun" src="' + WEBPATH + 'images/blank.gif" border="0">');
  document.write('</div>');
}

   setTimeout('csgetimage()', 1000); 	