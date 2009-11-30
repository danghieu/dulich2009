/////////////////////////////////////////////////////////////////////////////////////////////////////
function ConvertUnicodeEnglish(strFrom){
	var strText = strFrom;
	strValueReturn = new String("");
	for(intCount=0; intCount < strText.length; intCount++){
		strValueReturn = strValueReturn + ConvertCharEnglish(strText.charCodeAt(intCount));
	}
	while(strValueReturn.search("--")!= -1){
		strValueReturn = strValueReturn.replace("--", "-");		
	}
	return strValueReturn;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
function ConvertCharEnglish(intCode){
	var intCount;
	var arrayA = new Array(65, 97,192,224,193,225,7842,7843,195,227,7840,7841,258,259,7856,7857,7854,7855,7858,7859,7860,7861,7862,7863,194,226,7846,7847,7844,7845,7848,7849,7850,7851,7852,7853);
	var arrayE = new Array(69,101,200,232,201,233,7866,7867,7868,7869,7864,7865,202,234,7872,7873,7870,7871,7874,7875,7876,7877,7878,7879);
	var arrayI = new Array(73,105,204,236,205,237,7880,7881,296,297,7882,7883);
	var arrayO = new Array(79,111,210,242,211,243,7886,7887,213,245,7884,7885,212,244,7890,7891,7888,7889,7892,7893,7894,7895,7896,7897,416,417,7900,7901,7898,7899,7902,7903,7904,7905,7906,7907);
	var arrayU = new Array(85,117,217,249,218,250,7910,7911,360,361,7908,7909,431,432,7914,7915,7912,7913,7916,7917,7918,7919,7920,7921);
	var arrayY = new Array(89,121,7922,7923,221,253,7926,7927,7928,7929,7924,7925);
	var arrayD = new Array(272,273);
	var arrayOther = new Array(32,126,96,33,64,35,36,37,94,38,42,40,41,43,123,125,91,93,92,124,59,58,34,39,60,44,62,46,63,47)
	for(intCount=0; intCount < arrayA.length; intCount++){
		if(intCode==arrayA[intCount])
			return 'a';
	}
	for(intCount=0; intCount < arrayE.length; intCount++){
		if(intCode==arrayE[intCount])
			return 'e';
	}
	for(intCount=0; intCount < arrayI.length; intCount++){
		if(intCode==arrayI[intCount])
			return 'i';
	}
	for(intCount=0; intCount < arrayO.length; intCount++){
		if(intCode==arrayO[intCount])
			return 'o';
	}
	for(intCount=0; intCount < arrayU .length; intCount++){
		if(intCode==arrayU[intCount])
			return 'u';
	}
	for(intCount=0; intCount < arrayY .length; intCount++){
		if(intCode==arrayY[intCount])
			return 'y';
	}
	for(intCount=0; intCount < arrayD.length; intCount++){
		if(intCode==arrayD[intCount])
			return 'd';
	}
	for(intCount=0; intCount < arrayOther.length; intCount++){
		if(intCode==arrayOther[intCount])
			return '-';
	}
	return String.fromCharCode(intCode);
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function selectAll(objForm){
	intTotal = objForm.chkTotal.value;
	for(intCount = 0; intCount < intTotal; intCount++){
		eval('chkNum = objForm.chk' + intCount);
		if(chkNum.checked)
			chkNum.checked = false;
		else
			chkNum.checked = true;
	}
	return false;
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function BoxOver(imgObj, BoxName) {
	var objLayer = document.getElementById(BoxName);
	var ShowFlag = document.getElementById(BoxName + 'Show');
	var HoverFlag = document.getElementById(BoxName + 'Hover');
	if (ShowFlag.value == '1') imgObj.src = 'Images/System/TitleUp.gif';
	else imgObj.src = 'Images/System/TitleDown.gif';	
	HoverFlag.value = (HoverFlag.value=='1'?'0':'1');
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function BoxOut(imgObj, BoxName) {
	var objLayer = document.getElementById(BoxName);
	var ShowFlag = document.getElementById(BoxName + 'Show');
	var HoverFlag = document.getElementById(BoxName + 'Hover');
	if (ShowFlag.value == '1') imgObj.src = 'Images/System/TitleUp.gif';
	else imgObj.src = 'Images/System/TitleDown.gif';	
	HoverFlag.value = (HoverFlag.value=='1'?'0':'1');
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function ShowHideBox(imgObj, BoxName) { 
	var objLayer = document.getElementById(BoxName);
	var ShowFlag = document.getElementById(BoxName + 'Show');
	var HoverFlag = document.getElementById(BoxName + 'Hover');
	ShowFlag.value = (ShowFlag.value=='1'?'0':'1');
	if (ShowFlag.value == '1') {
		if (HoverFlag.value == '1') imgObj.src = 'Images/System/TitleUpHover.gif';
		else imgObj.src =  'Images/System/TitleUp.gif';	
		objLayer.style.display = "block";
	} else {
		if (HoverFlag.value == '1') imgObj.src = 'Images/System/TitleDownHover.gif'
		else imgObj.src = 'Images/System/TitleDown.gif';
		objLayer.style.display = "none";	
	}
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function ProperControl(objControl){
	objControl.value = ProperCase(objControl.value);
}
/////////////////////////////////////////////////////////////////////////////////////////////////
function ProperCase(STRING){
	var strReturn_Value = "";
	while(STRING.search("  ")!= -1){
		STRING = STRING.replace("  ", " ");
	}
	var iTemp = STRING.length;
	if(iTemp==0){
		return "";
	}
	var UcaseNext = false;
	strReturn_Value += STRING.charAt(0).toUpperCase();
	for(var iCounter=1;iCounter < iTemp;iCounter++){
		if(UcaseNext == true){
			strReturn_Value += STRING.charAt(iCounter).toUpperCase();
		}
		else{
			strReturn_Value += STRING.charAt(iCounter).toLowerCase();
		}
		var iChar = STRING.charCodeAt(iCounter);
		if(iChar == 32 || iChar == 45 || iChar == 46){
			UcaseNext = true;
		}
		else{
			UcaseNext = false
		}
		if(iChar == 99 || iChar == 67){
			if(STRING.charCodeAt(iCounter-1)==77 || STRING.charCodeAt(iCounter-1)==109){
				UcaseNext = true;
			}
		}
	}
	return strReturn_Value;
}
////////////////////////////////////////////////////////////////////////////////
function IsDate(strValue){

	if(IsEmpty(strValue))
		return true;


	var arrDate = strValue.split("/");
	if(arrDate.length < 3)
		return false;
	var strDay = arrDate[0];
	var strMonth = arrDate[1];
	var strYear = arrDate[2];
	if(strYear.charAt(0) != 1&&strYear.charAt(0) != 2){
		if(strYear.charAt("0") == 0){
			strYear = "20" + strYear;
		}
		else{
			strYear = "19" + strYear;
		}
	}
	var strDate = strMonth + "/" + strDay + "/" + strYear;
	var testDate = new Date(strDate);
	if(testDate.getYear() < 90 || testDate.getYear() > 2010){
		return false;
	}
	if(testDate.getMonth() + 1 == strMonth){
		return true;
	} 
	else{
		return false;
	}
	
}
////////////////////////////////////////////////////////////////////////////////
function FormatVietnameseDate(strValue){
	var arrDate = strValue.split("/");
	var strDay = arrDate[0];
	var strMonth = arrDate[1];
	var strYear = arrDate[2];
	var strDate = strMonth + "/" + strDay + "/" + strYear;
	return new Date(strDate);	
}

////////////////////////////////////////////////////////////////////////////////
function DateDiff(strValue1, strValue2){
	if(IsEmpty(strValue2))
		return 0;
	var testDate1 = FormatVietnameseDate(strValue1);
	var testDate2 = FormatVietnameseDate(strValue2);
	if(testDate1 > testDate2)
		return 1;
	else
		return -1;
}
////////////////////////////////////////////////////////////////////////////////
function IsNumeric(strValue){
	if(IsEmpty(strValue))
		return true;
	var strValidChars = "0123456789.,-";
	var intCount; 
	var intDecimal;
	var intNumber;
	intDecimal = 0;
	intNumber = 0;
	for(intCount = 0; intCount < strValue.length; intCount++){
		if(strValidChars.indexOf(strValue.charAt(intCount)) == -1) 
			return false;
		if(strValue.charAt(intCount) == "."||strValue.charAt(intCount) == ","||strValue.charAt(intCount) == "-")
			intDecimal++;
		else
			intNumber++;		
		if(intDecimal > 1)
			return false;
	}
	if(intNumber < 1)
		return false;
	return true;	   
}
////////////////////////////////////////////////////////////////////////////////
function CommaReplacement(fltValue){
	var strValue = new String(fltValue);
	strValue = strValue.replace(",", ".");
	return parseFloat(strValue);
}
////////////////////////////////////////////////////////////////////////////////
function RoundFloat(fltValue, intDecimal){
	var fltDecimal;
	var intIntegerPart;
	var strDecimal;
	intIntegerPart = Math.floor(fltValue);
	fltDecimal = fltValue - intIntegerPart;
	fltDecimal = Math.round(fltDecimal*1000);
	fltDecimal = fltDecimal/1000;
	if(Math.round(fltDecimal) == fltDecimal)
		return (intIntegerPart + fltDecimal);
	else{
		strDecimal = new String(fltDecimal.toString());
		strDecimal = strDecimal.substr(2)
		return parseFloat(intIntegerPart.toString() + "." + strDecimal);
	}
}
////////////////////////////////////////////////////////////////////////////////
function StringFloat(fltValue, intDecimal){
	var strFormat = new String(fltValue);
	strFormat = strFormat.replace(".", ",")
	if(Math.round(fltValue) == fltValue){
		return strFormat + ",000";
	}
	if(Math.round(fltValue*10) == fltValue*10){
		return strFormat + "00";
	}	
	if(Math.round(fltValue*100) == fltValue*100){
		return strFormat + "0";
	}	
	return strFormat;
}
////////////////////////////////////////////////////////////////////////////////
function FormatFloat(fltValue, intDecimal){
	return StringFloat(RoundFloat(fltValue, intDecimal), intDecimal)
}
////////////////////////////////////////////////////////////////////////////////
function IsInteger(strValue){
	if(IsEmpty(strValue))
		return true;
	var strValidChars = "0123456789-";
	var intCount; 
	for(intCount = 0; intCount < strValue.length; intCount++){
		if(strValidChars.indexOf(strValue.charAt(intCount)) == -1) 
			return false;
	}
	return true;	   
}
////////////////////////////////////////////////////////////////////////////////
function IsEmpty(strValue){
	if(strValue=="")
		return true;
	var strValueTest = new String(strValue);
	while(strValueTest.search(" ")!= -1)
		strValueTest = strValueTest.replace(" ", "");
	return (strValueTest.length== 0);
}
////////////////////////////////////////////////////////////////////////////////
function ConvertProperName(strValue){
	if(strValue == "")
		return "";
	var strValueTest = new String(strValue);
	while(strValueTest.search("  ")!= -1)
		strValueTest = strValueTest.replace("  ", " ");
	if(strValueTest.charAt(0)== " ")
		strValueTest = strValueTest.substr(strValueTest, strValueTest.length - 1, 1);	
	return (strValueTest);
}
////////////////////////////////////////////////////////////////////////////////
function RemoveSpace(strValue){
	if(strValue == "")
		return "";
	var strValueTest = new String(strValue);
	while(strValueTest.search(" ")!= -1)
		strValueTest = strValueTest.replace(" ", "");
	return (strValueTest);
}
////////////////////////////////////////////////////////////////////////////////
function RemoveCharacter(strValue, strCharacter){
	if(strValue == "")
		return "";
	var strValueTest = new String(strValue);
	while(strValueTest.search(strCharacter)!= -1)
		strValueTest = strValueTest.replace(strCharacter, "");
	return (strValueTest);
}
////////////////////////////////////////////////////////////////////////////////
function ConvertFileName(strValue){
	if(strValue == "")
		return "";
	var strValue;
	strValue = RemoveCharacter(strValue, " ");
	strValue = RemoveCharacter(strValue, "-");
	return (strValueTest);
}
///////////////////////////////////////////////////////////////////////////////////////////////

function doNavigate(strNavigatorList, strUrl, strActionType, intActionValue){
	if(strActionType == "Row")
		strUrl = "navigator.asp?NavigatorType=Navigate&NavigateAction=" + strUrl + "&DisplayRows=" + intActionValue + "&NavigatorList=" + strNavigatorList;
	if(strActionType == "Page")
		strUrl = "navigator.asp?NavigatorType=Navigate&NavigateAction=" + strUrl + "&DisplayPage=" + intActionValue +  "&NavigatorList=" + strNavigatorList;
	location.href = strUrl;
}
///////////////////////////////////////////////////////////////////////////////////////////////

function jumpPage(strNavigatorList, strUrl, intstrPage){
	strUrl = "navigator.asp?NavigatorType=Navigate&NavigateAction=" + strUrl + "&DisplayPage=" + intstrPage +  "&NavigatorList=" + strNavigatorList;
	location.href = strUrl;
}
function BrowseImage(strControlName, strCategory, strFileName){
	window.open("image-upload.asp?Category=" + strCategory +"&ControlName=" + strControlName + "&FileName=" + strFileName, "imgView", "top=50,left=100,width=650,height=450,status=no,menubar=no,scrollable=no,resizable=no,scrollbars=no");
}
function viewImage(strLocation, strCaption){
	window.open("image-view.asp?Location=" + strLocation + "&Caption=" + strCaption, "imgView", "top=1,left=1,width=1,height=1,status=no,statusbar:no,menubar=no,scrollable=no,resizable=no,scrollbars=no");
}
///////////////
function OpenDictionaryAdmin(strDictionName){
	window.open("dictionary-admin.asp?Dictionary=" + strDictionName)
}
