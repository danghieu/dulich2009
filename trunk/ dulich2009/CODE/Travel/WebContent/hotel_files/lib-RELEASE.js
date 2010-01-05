function popup(href, windowname){
	if (! window.focus)return true;
	window.open(href,
				windowname, "resizable=yes,scrollbars=yes,menubar=no,toolbar=no,locationbar=no,status=no,width=600,height=600");
	return false;
}

function popUp(path){
	window.open(path,"popup","resizable=yes,scrollbars=yes,menubar=no,toolbar=no,locationbar=no,status=no,width=600,height=600");
}

// DOM / SET PROPERTY
// Works: IE5+, NS6+

	function setIdProperty(id,property,value) {
		var styleObject = document.getElementById( id );
		if (styleObject != null) {
			styleObject = styleObject.style;
			styleObject[ property ] = value;
		}
	}

// GENERIC DISPLAY VALUE SWITCHER
// Works: IE4+, NS4+

	function switchDisplay(thisId,value) {
		if (document.layers) {
			document.layers[thisId].display = value;
		} else if(document.getElementById) {
			setIdProperty(thisId,"display",value);
		} else {
			document.all[thisId].style.display = value;
		}
	}

// GENERIC IMAGE SWITCHING WO/ PRELOADING
// Works: IE4+, NS4+

	function toggleImg(masterImg,imgNewSource) {
		if(document.getElementById) {
			document.getElementById(masterImg).src = imgNewSource;
		} else {
			document.images[masterImg].src = imgNewSource;
		}
	}


// FOLDS NAV IN/OUT ON FRONTPAGE
// Works: IE4+, NS4+

	var navPos0 = 'in';
	var navPos1 = 'in';
	var navPos2 = 'in';
	var navPos3 = 'in';
	var navPos4 = 'in';
	var navPos5 = 'in';
	var navPos6 = 'out';
	var navPos7 = 'in';
	var navPos8 = 'in';
	var navPos9 = 'in';
	var navPos9 = 'in';
	var navPos10 = 'in';
	var navPos11 = 'in';
	var navPos12 = 'in';
	var navPos13 = 'in';
	var navPos14 = 'in';
	var navPos15 = 'in';
	var navPos16 = 'in';
	var navPos17 = 'in';
	var navPos18 = 'in';
	var navPos19 = 'out';


	function toggleNav(thisId){
		tempState = eval('navPos'+thisId);
		if (tempState == 'out') {
			switchDisplay('nav_list'+thisId,'none');
			switchDisplay('nav_listbtn'+thisId,'block');
			switchDisplay('nav_listContract'+thisId,'none');
			switchDisplay('footer_mod'+thisId,'none');
			switchDisplay('footer_mod2'+thisId,'block');
		eval('navPos' +thisId + ' = "in"');
		} else {
			switchDisplay('nav_list'+thisId,'block');
			switchDisplay('nav_listbtn'+thisId,'none');
			switchDisplay('nav_listContract'+thisId,'block');
			switchDisplay('footer_mod'+thisId,'block');
			switchDisplay('footer_mod2'+thisId,'none');
			eval('navPos' +thisId + ' = "out"');
		}
    }

// OPENS A POP-UP WINDOW
	function NewWindow(mypage,myname,w,h,scroll,pos){
		if (pos=="random") {
			LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;
			TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;
		}
		if(pos=="center"){
			LeftPosition=(screen.width)?(screen.width-w)/2:100;
			TopPosition=(screen.height)?(screen.height-h)/2:100;
		}
		else if((pos!="center" && pos!="random") || pos==null) {
			LeftPosition=0;TopPosition=20
		}
		settings='width='+w+',height='+h+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',location=no,directories=no,status=yes,menubar=no,toolbar=no,resizable=no';
		window.open(mypage,myname,settings);
	}

// Shows/hides a div

	function showhide (id, disp)
	{
		var di = document.getElementById(id);
		if (di == null) return;
		if (disp)
		{
			di.style.display = 'block';
		}
		else
		{
			di.style.display = 'none';
		}
	}

// toggle - show & hide HTML element
	function showHide(id){
		if(document.getElementById){
			var x = document.getElementById(id).style;
		} else if(document.all){
			var x = document.all[id].style;
		}

		if (x.display == 'none'){
			x.display = 'block';
		} else {
			x.display = 'none';
		}

	}


// Clear input box
function clearText(thefield){
if (thefield.defaultValue==thefield.value)
thefield.value = ""
}


// check box highlight blue
function toggleChkColorBlu( elem )
{
	var elm = elem;
    var style2 = document.getElementById('S'+elm);
	var style3 = document.getElementById('NS'+elm);
    style2.className = style2.className ? "lightBlue fltLft mgnLft5 padTop2":"weak fltLft mgnLft5 padTop2";
    style3.className = style3.className ? "weak fltLft mgnLft5 padTop2":"lightBlue fltLft mgnLft5 padTop2";
}

function toggleChkColorBlu1( elem )
{
	var elm = elem;
    var style2 = document.getElementById('S'+elm);
	var style3 = document.getElementById('NS'+elm);
    style2.className = style2.className ? "weak fltLft mgnLft5 padTop2":"lightBlue fltLft mgnLft5 padTop2";
    style3.className = style3.className ? "lightBlue fltLft mgnLft5 padTop2":"weak fltLft mgnLft5 padTop2";
}

function submitFormByID (action, id){
	document.getElementById(id).action = action;
	document.getElementById(id).submit();
}


