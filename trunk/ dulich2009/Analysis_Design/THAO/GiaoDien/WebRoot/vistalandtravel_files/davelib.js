// ---------------------------------------------------------------------------
// Hotel library
// ---------------------------------------------------------------------------
function ViewThumbnails(HotelId,Thumbnail) {
openSmallWindow("/Hotel/ViewPhotos.aspx?i="+HotelId+"&t="+Thumbnail,0,0,700,500);
}
function ViewThumbnails1(HotelName,HotelId,Thumbnail) {
openSmallWindow("/Hotel/ViewPhotos.aspx?i="+HotelId+"&t="+Thumbnail,0,0,700,500);
}


// ---------------------------------------------------------------------------
// David Tran's Javascript library
// ---------------------------------------------------------------------------

function openSmallWindow(url,vtop,vleft,vwidth,vheight)
{
if(vtop==null)
if(vleft==null)
if(vwidth==null) {vwidth=550; }
if(vheight==null) {vheight=300; }
var win_param;
win_param="top="+vtop+",left="+vleft+",width="+vwidth+",height="+vheight+"channelmode=0,dependent=0,directories=0,fullscreen=0,location=0,menubar=0,resizable=1,scrollbars=1,status=0,toolbar=0,screenX=0,left=10,screenY=0";
msgWindow=open(url,"smallWindow",win_param);
msgWindow.focus();
msgWindow.opener= self;
return false;
}

function MM_SetClassName(objName,className) {
if ((obj=MM_findObj(objName))!=null) {
if (obj.className){
obj.className=className;
}
}
}
/*
function SetBorderRight(objName,color) {
alert(objName);
if ((obj=MM_findObj(objName))!=null) 
if (obj.style){
alert(obj.style);
obj.style.border-right=color;
}
}
*/
function MM_showHideTableRow(objName,visible) { //v6.0   visible=show|hide
if ((obj=MM_findObj(objName))!=null) 
if (obj.style)
if (visible=="show"){
//alert(obj.style.display);
if (obj.style.display=="none")
obj.style.display=document.all ? "block" : "table-row";
}else{
if (obj.style.display!="none")
obj.style.display="none";
}
}

function MM_findObj(n, d) { //v4.01
var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
if(!x && d.getElementById) x=d.getElementById(n); return x;
}

// MM_showHideLayers('laySearchTip','','hide') 
// MM_showHideLayers('laySearchTip','','show')
function MM_showHideLayers() { //v6.0
var i,p,v,obj,args=MM_showHideLayers.arguments;
for (i=0; i<(args.length-2); i+=3) 
if ((obj=MM_findObj(args[i]))!=null) { 
v=args[i+2];
if (obj.style) { 
obj=obj.style; 
v=(v=='show')?'visible':(v=='hide')?'hidden':v; 
}
obj.visibility=v; 
}
}

function FormatNumber(num,decimalNum,bolLeadingZero,bolParens,bolCommas)
/**********************************************************************
IN:
NUM - the number to format
decimalNum - the number of decimal places to format the number to
bolLeadingZero - true / false - display a leading zero for
numbers between -1 and 1
bolParens - true / false - use parenthesis around negative numbers
bolCommas - put commas as number separators.

RETVAL:
The formatted number!
**********************************************************************/
{ 
if (isNaN(parseInt(num))) return "NaN";

var tmpNum = num;
var iSign = num < 0 ? -1 : 1;		// Get sign of number

// Adjust number so only the specified number of numbers after
// the decimal point are shown.
tmpNum *= Math.pow(10,decimalNum);
tmpNum = Math.round(Math.abs(tmpNum))
tmpNum /= Math.pow(10,decimalNum);
tmpNum *= iSign;					// Readjust for sign


// Create a string object to do our formatting on
var tmpNumStr = new String(tmpNum);

// See if we need to strip out the leading zero or not.
if (!bolLeadingZero && num < 1 && num > -1 && num != 0)
if (num > 0)
tmpNumStr = tmpNumStr.substring(1,tmpNumStr.length);
else
tmpNumStr = "-" + tmpNumStr.substring(2,tmpNumStr.length);

// See if we need to put in the commas
if (bolCommas && (num >= 1000 || num <= -1000)) {
var iStart = tmpNumStr.indexOf(".");
if (iStart < 0)
iStart = tmpNumStr.length;

iStart -= 3;
while (iStart >= 1) {
tmpNumStr = tmpNumStr.substring(0,iStart) + "," + tmpNumStr.substring(iStart,tmpNumStr.length)
iStart -= 3;
}		
}

// See if we need to use parenthesis
if (bolParens && num < 0)
tmpNumStr = "(" + tmpNumStr.substring(1,tmpNumStr.length) + ")";

// David adds extra function at here
// 1234 -> 1234.00
// 1234.1 -> 1234.10
tmpNumStr = (tmpNumStr.indexOf(".") > 0) ? tmpNumStr + (String(Math.pow(10,decimalNum))).substr(1) : tmpNumStr + "." + (String(Math.pow(10,decimalNum))).substr(1);


return tmpNumStr;		// Return our formatted string!
}

function FormatPercent(num,decimalNum,bolLeadingZero,bolParens,bolCommas)
/**********************************************************************
IN:
NUM - the number to format
decimalNum - the number of decimal places to format the number to
bolLeadingZero - true / false - display a leading zero for
numbers between -1 and 1
bolParens - true / false - use parenthesis around negative numbers
bolCommas - put commas as number separators.										

RETVAL:
The formatted number!		
**********************************************************************/
{
var tmpStr = new String(FormatNumber(num*100,decimalNum,bolLeadingZero,bolParens,bolCommas));

if (tmpStr.indexOf(")") != -1) {
// We know we have a negative number, so place '%' inside of ')'
tmpStr = tmpStr.substring(0,tmpStr.length - 1) + "%)";
return tmpStr;
}
else
return tmpStr + "%";			// Return formatted string!
}



function FormatCurrency(num,decimalNum,bolLeadingZero,bolParens,bolCommas)
/**********************************************************************
IN:
NUM - the number to format
decimalNum - the number of decimal places to format the number to
bolLeadingZero - true / false - display a leading zero for
numbers between -1 and 1
bolParens - true / false - use parenthesis around negative numbers
bolCommas - put commas as number separators.										

RETVAL:
The formatted number!		
**********************************************************************/
{
var tmpStr = new String(FormatNumber(num,decimalNum,bolLeadingZero,bolParens,bolCommas));

if (tmpStr.indexOf("(") != -1 || tmpStr.indexOf("-") != -1) {
// We know we have a negative number, so place '$' inside of '(' / after '-'
if (tmpStr.charAt(0) == "(")
tmpStr = "($"  + tmpStr.substring(1,tmpStr.length);
else if (tmpStr.charAt(0) == "-")
tmpStr = "-$" + tmpStr.substring(1,tmpStr.length);

return tmpStr;
}
else
return "$" + tmpStr;		// Return formatted string!
}


function LTrim(str)
/***
PURPOSE: Remove leading blanks from our string.
IN: str - the string we want to LTrim

RETVAL: An LTrimmed string!
***/
{
var whitespace = new String(" \t\n\r");

var s = new String(str);

if (whitespace.indexOf(s.charAt(0)) != -1) {
// We have a string with leading blank(s)...

var j=0, i = s.length;

// Iterate from the far left of string until we
// don't have any more whitespace...
while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
j++;


// Get the substring from the first non-whitespace
// character to the end of the string...
s = s.substring(j, i);
}

return s;
}




function RTrim(str)
/***
PURPOSE: Remove trailing blanks from our string.
IN: str - the string we want to RTrim

RETVAL: An RTrimmed string!
***/
{
// We don't want to trip JUST spaces, but also tabs,
// line feeds, etc.  Add anything else you want to
// "trim" here in Whitespace
var whitespace = new String(" \t\n\r");

var s = new String(str);

if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
// We have a string with trailing blank(s)...

var i = s.length - 1;       // Get length of string

// Iterate from the far right of string until we
// don't have any more whitespace...
while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
i--;


// Get the substring from the front of the string to
// where the last non-whitespace character is...
s = s.substring(0, i+1);
}

return s;
}



function Trim(str)
/***
PURPOSE: Remove trailing and leading blanks from our string.
IN: str - the string we want to Trim

RETVAL: A Trimmed string!
***/
{
return RTrim(LTrim(str));
}




function Len(str)
/***
IN: str - the string whose length we are interested in

RETVAL: The number of characters in the string
***/
{  return String(str).length;  }



function Left(str, n)
/***
IN: str - the string we are LEFTing
n - the number of characters we want to return

RETVAL: n characters from the left side of the string
***/
{
if (n <= 0)     // Invalid bound, return blank string
return "";
else if (n > String(str).length)   // Invalid bound, return
return str;                // entire string
else // Valid bound, return appropriate substring
return String(str).substring(0,n);
}



function Right(str, n)
/***
IN: str - the string we are RIGHTing
n - the number of characters we want to return

RETVAL: n characters from the right side of the string
***/
{
if (n <= 0)     // Invalid bound, return blank string
return "";
else if (n > String(str).length)   // Invalid bound, return
return str;                     // entire string
else { // Valid bound, return appropriate substring
var iLen = String(str).length;
return String(str).substring(iLen, iLen - n);
}
}



function Mid(str, start, len)

// Keep in mind that strings in JavaScript are zero-based, so if you ask
// for Mid("Hello",1,1), you will get "e", not "H".  To get "H", you would
// simply type in Mid("Hello",0,1)

// You can alter the above function so that the string is one-based.  Just
// check to make sure start is not <= 0, alter the iEnd = start + len to
// iEnd = (start - 1) + len, and in your final return statement, just
// return ...substring(start-1,iEnd)


/***
IN: str - the string we are LEFTing
start - our string's starting position (0 based!!)
len - how many characters from start we want to get

RETVAL: The substring from start to start+len
***/
{
// Make sure start and len are within proper bounds
if (start < 0 || len < 0) return "";

var iEnd, iLen = String(str).length;
if (start + len > iLen)
iEnd = iLen;
else
iEnd = start + len;

return String(str).substring(start,iEnd);
}


function InStr(strSearch, charSearchFor)
/*
InStr(strSearch, charSearchFor) : Returns the first location a substring (SearchForStr)
was found in the string str.  (If the character is not
found, -1 is returned.)

Requires use of:
Mid function
Len function
*/
{
for (i=0; i < Len(strSearch); i++)
{
if (charSearchFor == Mid(strSearch, i, 1))
{
return i;
}
}
return -1;
}



