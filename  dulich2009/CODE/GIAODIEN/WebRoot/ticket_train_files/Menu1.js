DropDownMenu.Registry = []
DropDownMenu.aniLen = 250
DropDownMenu.hideDelay = 500
DropDownMenu.minCPUResolution = 1
MenuURL = "http://www.tnktravelvietnam.com/html/"
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function DropDownMenu(id, dir, left, top, right, width, height){
	this.ie = document.all ? 1 : 0
	this.ns4 = document.layers ? 1 : 0
	this.dom = document.getElementById ? 1 : 0
	if (this.ie || this.ns4 || this.dom){
		this.id = id
		this.dir = dir
		this.orientation = dir == "left" || dir == "right" ? "h" : "v"
		this.dirType = dir == "right" || dir == "down" ? "-" : "+"
		this.dim = this.orientation == "h" ? width : height
		this.hideTimer = false
		this.aniTimer = false
		this.open = false
		this.over = false
		this.startTime = 0
		this.gRef = "DropDownMenu_"+id
		eval(this.gRef+"=this")
		DropDownMenu.Registry[id] = this
		var d = document
		var strCSS = '<style type="text/css">';
		strCSS += '#' + this.id + 'Container { visibility:hidden; '

		if (left >= 0) {
		strCSS += 'left:' + left + 'px; '
		}
		else {
			strCSS += 'right:' + right + 'px; '
		}
		strCSS += 'top:' + top + 'px; '
		strCSS += 'overflow:hidden; z-index:10000; }'
		strCSS += '#' + this.id + 'Container, #' + this.id + 'Content { position:absolute; '
		strCSS += 'width:' + width + 'px; '
		strCSS += 'height:' + height + 'px; '
		strCSS += 'clip:rect(0 ' + width + ' ' + height + ' 0); '
		strCSS += '}'
		strCSS += '</style>'
		d.write(strCSS)
		this.load()
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.prototype.load = function() {
	var d = document
	var lyrId1 = this.id + "Container"
	var lyrId2 = this.id + "Content"
	var obj1 = this.dom ? d.getElementById(lyrId1) : this.ie ? d.all[lyrId1] : d.layers[lyrId1]
	if (obj1) var obj2 = this.ns4 ? obj1.layers[lyrId2] : this.ie ? d.all[lyrId2] : d.getElementById(lyrId2)
	var temp
	if (!obj1 || !obj2) window.setTimeout(this.gRef + ".load()", 100)
	else {
		this.container = obj1
		this.menu = obj2
		this.style = this.ns4 ? this.menu : this.menu.style
		this.homePos = eval("0" + this.dirType + this.dim)
		this.outPos = 0
		this.accelConst = (this.outPos - this.homePos) / DropDownMenu.aniLen / DropDownMenu.aniLen 
		// set event handlers.
		if (this.ns4) this.menu.captureEvents(Event.MOUSEOVER | Event.MOUSEOUT);
		this.menu.onmouseover = new Function("DropDownMenu.showMenu('" + this.id + "')")
		this.menu.onmouseout = new Function("DropDownMenu.hideMenu('" + this.id + "')")
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		this.endSlide()
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.showMenu = function(id){
	var reg = DropDownMenu.Registry
	var obj = DropDownMenu.Registry[id]
	if (obj.container) {
		obj.over = true
		for (menu in reg) if (id != menu) DropDownMenu.hide(menu)
		if (obj.hideTimer) { 
			reg[id].hideTimer = window.clearTimeout(reg[id].hideTimer) 
		}
		if (!obj.open && !obj.aniTimer) reg[id].startSlide(true)
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.hideMenu = function(id){
	var obj = DropDownMenu.Registry[id]
	if (obj.container) {
		if (obj.hideTimer) window.clearTimeout(obj.hideTimer)
		obj.hideTimer = window.setTimeout("DropDownMenu.hide('" + id + "')", DropDownMenu.hideDelay);
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.hideAll = function(){
	var reg = DropDownMenu.Registry
	for (menu in reg) {
	DropDownMenu.hide(menu);
	if (menu.hideTimer) window.clearTimeout(menu.hideTimer);}
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.hide = function(id){
	var obj = DropDownMenu.Registry[id]
	obj.over = false
	if (obj.hideTimer) window.clearTimeout(obj.hideTimer)
	obj.hideTimer = 0
	if (obj.open && !obj.aniTimer) obj.startSlide(false)
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.prototype.startSlide = function(open) {
	this[open ? "onactivate" : "ondeactivate"]()
	this.open = open
	if (open) this.setVisibility(true)
	this.startTime = (new Date()).getTime() 
	this.aniTimer = window.setInterval(this.gRef + ".slide()", DropDownMenu.minCPUResolution)
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
DropDownMenu.prototype.slide = function() {
	var elapsed = (new Date()).getTime() - this.startTime
	if (elapsed > DropDownMenu.aniLen) this.endSlide()
	else {
	var d = Math.round(Math.pow(DropDownMenu.aniLen-elapsed, 2) * this.accelConst)
	if (this.open && this.dirType == "-") d = -d
	else if (this.open && this.dirType == "+") d = -d
	else if (!this.open && this.dirType == "-") d = -this.dim + d
	else d = this.dim + d
	this.moveTo(d)
	}
}
DropDownMenu.prototype.endSlide = function() {
this.aniTimer = window.clearTimeout(this.aniTimer)
this.moveTo(this.open ? this.outPos : this.homePos)
if (!this.open) this.setVisibility(false)
if ((this.open && !this.over) || (!this.open && this.over)) {
this.startSlide(this.over)
}
}
DropDownMenu.prototype.setVisibility = function(bShow) { 
var s = this.ns4 ? this.container : this.container.style
s.visibility = bShow ? "visible" : "hidden"
}
DropDownMenu.prototype.moveTo = function(p) { 
this.style[this.orientation == "h" ? "left" : "top"] = this.ns4 ? p : p + "px"
}
DropDownMenu.prototype.getPos = function(c) {
return parseInt(this.style[c])
}
DropDownMenu.prototype.onactivate = function() { }
DropDownMenu.prototype.ondeactivate = function() { }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
var output    =	'<div id="menu1Container">';
output 	+=		'<div id="menu1Content">'
output 	+=			'<table cellspacing=0 cellpadding=0 border=0 style="BORDER-TOP: #999999 1px solid">'
output 	+=			'<tr>'
output 	+=				'<td colspan=2 width=100%>'
output 	+=					'<table border=0 cellspacing=0 cellpadding=0 width=100% style="BORDER-TOP: #999999 0px solid; BORDER-LEFT: #999999 1px solid; BORDER-RIGHT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid; border-collapse: collapse" bgcolor="#f1f1f1">'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'contact.php">Vietnam Tour Booking</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'custom-trip.php">Customized Tour Booking</a></td></tr>'
output 	+=						'<tr><td class=LineMenu height=1 nowrap></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam-adventure-tours.php">Vietnam Adventure Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'set-departure-tours.php">Vietnam Set Departure Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'package-tours.php">Vietnam Travel Packages</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'Beach_Tours.htm">Vietnam Beachs Relaxing Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam-central-tours.php">Vietnam World Heritages Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'sapa-tours.php">Vietnam Sapa Daily Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'halong-tours.php">Ha Long Bay Explore Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'mekong-delta-tours.php">Vietnam Mekong Delta Tours</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'cambodia-tours.php">Extended Tours to Combodia</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'laos-tours.php">Extended Tours to Laos</a></td></tr>'
output 	+=						'<tr><td class=LineMenu height=1 nowrap></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'saigon-tours.php">Southern Vietnam Highlights Tour</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam-central-tours.php">Central Vietnam Highlights Tour</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'hanoi-tours.php">Northern Vietnam Highlights Tour</a></td></tr>'
output 	+=					'</table>'
output 	+=				'</td>'
output 	+=			'</tr>'
output 	+=			'</table>'
output 	+=		'</div>'
output 	+=		'</div>'

output  +=		'<div id="menu2Container">';
output 	+=		'<div id="menu2Content">'
output 	+=			'<table cellspacing=0 cellpadding=0 border=0 style="BORDER-TOP: #999999 1px solid">'
output 	+=			'<tr>'
output 	+=				'<td colspan=2 width=100%>'
output 	+=					'<table border=0 cellspacing=0 cellpadding=0 width=100% style="BORDER-TOP: #999999 0px solid; BORDER-LEFT: #999999 1px solid; BORDER-RIGHT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid; border-collapse: collapse" bgcolor="#f1f1f1">'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'hotel-reservation.php">Hotel & Resort Reservation</a></td></tr>'
output 	+=						'<tr><td class=LineMenu height=1 nowrap></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'index.php">Vietnam Hotels Directory</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'hotel-cambodia.php">Cambodia Hotels Directory</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'hotels-laos.php">Laos Hotels Directory</a></td></tr>'
output 	+=					'</table>'
output 	+=				'</td>'
output 	+=			'</tr>'
output 	+=			'</table>'
output 	+=		'</div>'
output 	+=		'</div>'

output  +=		'<div id="menu3Container">';
output 	+=		'<div id="menu3Content">'
output 	+=			'<table cellspacing=0 cellpadding=0 border=0 style="BORDER-TOP: #999999 1px solid">'
output 	+=			'<tr>'
output 	+=				'<td colspan=2 width=100%>'
output 	+=					'<table border=0 cellspacing=0 cellpadding=0 width=100% style="BORDER-TOP: #999999 0px solid; BORDER-LEFT: #999999 1px solid; BORDER-RIGHT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid; border-collapse: collapse" bgcolor="#f1f1f1">'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'ticket_airline.htm">Flight Tickets Booking</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'ticket_train.htm">Reunification Express Train</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam-railways.php">Victoria Express Train</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam-five-star-train.php">5 Star Travel Train</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'vietnam_visa.htm">Vietnam Visa Services</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'CarRental.htm">Vietnam Car Rental</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'airline_pickup.htm">Vietnam Airport Pickup</a></td></tr>'
output 	+=					'</table>'
output 	+=				'</td>'
output 	+=			'</tr>'
output 	+=			'</table>'
output 	+=		'</div>'
output 	+=		'</div>'

output  +=		'<div id="menu4Container">';
output 	+=		'<div id="menu4Content">'
output 	+=			'<table cellspacing=0 cellpadding=0 border=0 style="BORDER-TOP: #999999 1px solid">'
output 	+=			'<tr>'
output 	+=				'<td colspan=2 width=100%>'
output 	+=					'<table border=0 cellspacing=0 cellpadding=0 width=100% style="BORDER-TOP: #999999 0px solid; BORDER-LEFT: #999999 1px solid; BORDER-RIGHT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid; border-collapse: collapse" bgcolor="#f1f1f1">'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'links.htm">Travel Link Exchange</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'addsite.php">Add Link Exchange</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'links.php">Link Exchange Directory</a></td></tr>'
output 	+=					'</table>'
output 	+=				'</td>'
output 	+=			'</tr>'
output 	+=			'</table>'
output 	+=		'</div>'
output 	+=		'</div>'

output  +=		'<div id="menu5Container">';
output 	+=		'<div id="menu5Content">'
output 	+=			'<table cellspacing=0 cellpadding=0 border=0 style="BORDER-TOP: #999999 1px solid">'
output 	+=			'<tr>'
output 	+=				'<td colspan=2 width=100%>'
output 	+=					'<table border=0 cellspacing=0 cellpadding=0 width=100% style="BORDER-TOP: #999999 0px solid; BORDER-LEFT: #999999 1px solid; BORDER-RIGHT: #999999 1px solid; BORDER-BOTTOM: #999999 1px solid; border-collapse: collapse" bgcolor="#f1f1f1">'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'aboutus.html">About Us</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'contact.php">Contact Us</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + '../feedback/">Travel Guest Book</a></td></tr>'
output 	+=						'<tr><td class=DropDownMenu><a href="' + MenuURL + 'travel-news.php">Vietnam Travel News</a></td></tr>'
output 	+=					'</table>'
output 	+=				'</td>'
output 	+=			'</tr>'
output 	+=			'</table>'
output 	+=		'</div>'
output 	+=		'</div>'

document.write(output)

	var menus = [
		new DropDownMenu("menu1", "down", 216, 106, 0, 300, 300),
		new DropDownMenu("menu2", "down", 330, 106, 0, 240, 200),
		new DropDownMenu("menu3", "down", 562, 106, 0, 240, 200),
		new DropDownMenu("menu4", "down", 686, 106, 0, 240, 200),
		new DropDownMenu("menu5", "down", 759, 106, 0, 240, 200)
	]
	for (var i = 0; i < menus.length; i++) {
		menus[i].onactivate = new Function("document.getElementById('act" + (i + 1) + "').className='active';");
		menus[i].ondeactivate = new Function("document.getElementById('act" + (i + 1) + "').className='';");
	}
//function DropDownMenu(id, dir, left, top, right, width, height){

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////