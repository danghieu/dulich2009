<!--
function printPage()
{
  window.print();
}

function closeWindow()
{
  window.close();
}


function tax_change(room_id)
{
    taxType = document.getElementById(room_id+'_tax');
    taxTypePrice = document.getElementById(room_id+'_tax_price_div');
    taxTypePrice.style.display = 'none';

    switch(taxType.selectedIndex)
    {
       case 0:
          break;
       case 1:
          taxTypePrice.style.display = 'inline';
          break;
    }
}



function breakfast_change(room_id)
{
    breakfastType = document.getElementById(room_id+'_breakfast');
    breakfastTypePrice = document.getElementById(room_id+'_breakfast_price_div');
    breakfastTypeText = document.getElementById(room_id+'_breakfast_text_div');
    breakfastTypePrice.style.display = 'none';
    breakfastTypeText.style.display = 'none';

    switch(breakfastType.selectedIndex)
    {
       case 0:
          break;
       case 1:
          breakfastTypePrice.style.display = 'inline';
          break;
       case 2:
          breakfastTypeText.style.display = 'inline';
          break;
    }
}

function popupImageSelector(params)
{
  window.open('uploadimage.html?'+params, 'uploadimage', 'height=250, width=400, screenX=100, screenY=100, resizable=yes');
}

function genderSetTo(gender)
{
    if (document.getElementById('contactName').value ==
      document.getElementById('reservationsName').value)
    {
      if (gender == 'male')
      {
        document.getElementById('reservationsMale').checked = true;
      }
      else
      {
        document.getElementById('reservationsFemale').checked = true;
      }
    }
}



/* test */
function popup(pagename) {
  window.open(pagename,'newwin','height=40,width=40')
}

/* used in the index page 
function checkMail(email) {
  var x = email;
  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  if (filter.test(x)) return 1;
  else alert("Your email address does not appear to be valid");
}

 */

/* used in general.html */
function addContact(){
var name = document.forms[0].finance_contact_name.value;
    if (document.forms[0].reservations_name.value == "") {
        document.forms[0].reservations_name.value = name;
    }
}

function maxTextSize(field, max) {
    if (field.value.length > max) {
        field.value = field.value.substring(0, max);
    }
}

/* used in facilities.html */
function textCounter(field, countfieldmin, countfieldmax, minlimit, maxlimit) {
                                                                                                                                       
    if (field.value.length > maxlimit) // if too long...trim it!
        {field.value = field.value.substring(0, maxlimit);}
    else
     {countfieldmax.value = maxlimit - field.value.length;}
                                                                                                                                       
    if (field.value.length > minlimit)
        {countfieldmin.value=0;}
    else
        {countfieldmin.value = minlimit - field.value.length;}
}

/* used in roomsandrates.html */
function dupChecked(elementName) {
for (var number =1; number < 5; number++) {
    var target = "document.forms[0].room"+number+"_"+elementName+".checked";
    var stri = "room2"+elementName;
    document.forms[0].stri.checked;
    alert(target);
    }
}


/* Argument: which room number has been clicked */
/* then prefill with room1's data               */

function autocompleteRoom(room_name) {
  var form = document.forms[0];


  if(room_name == 'room1') return;

  // Only complete the form, if the roomName was not specified.
  thisEle = form[room_name + "_type_name"];
  if(thisEle.value != ''){return}

  thisEle = form[room_name + "_type_name"];
  thisEle.value = form.room1_type_name.value;

  thisEle = form[room_name + "_number"];
  thisEle.value = form.room1_number.value;

  thisEle = form[room_name + "_rate"] ;
  thisEle.value = form.room1_rate.value;

  thisEle = eval("form." + room_name   + "_facilities");
  for (i=0;i<form.room1_facilities.length;i++) {
    if (form.room1_facilities[i].checked) {
      thisEle[i].checked = true;
    }
  }

  thisEle = form[room_name + "_breakfast"];
  thisEle.value = form.room1_breakfast.value;

  thisEle = form[room_name + "_breakfast_price"];
  if (thisEle) {thisEle.value = form.room1_breakfast_price.value;}

  thisEle = form[room_name + "_breakfast_text"];
  if (thisEle) {thisEle.value = form.room1_breakfast_text.value ;}



  thisEle = form[room_name + "_tax"];
  thisEle.value = form.room1_tax.value;
  
  thisEle = form[room_name + "_tax_price"];
  if (thisEle) {thisEle.value = form.room1_tax_price.value ;}
  
  thisEle = form[room_name + "_tax_price_type"];
  if (thisEle) {thisEle.value = form.room1_tax_price_type.value ;}
  

  thisEle = form[room_name + "_closed_dates"];
  thisEle.value = form.room1_closed_dates.value;

  breakfast_change(room_name);
  tax_change(room_name);

  /* set the facility checkboxes  */


}
                                                                                                                                       
/* make sure the inclusive and exclusive boolean select
matches the value in the p.o.p.r. */
function setBoo(ele,booField) {
  var form = document.forms[0];
  ele.value > 0?  booField.value = 10 : booField.value = 0;
}





/* Define an object for a VatDefinition */
function VATSpec(countrycode, prefix, minlength, maxlength, example)
{
  this.countrycode = countrycode;
  this.prefix = prefix;
  this.minlength = minlength;
  this.maxlength = maxlength;
  this.example = example;
}


var VATSpecs = new Array(  
  new VATSpec('be', 'BE', 11, 12, 'BE012345678'),
  new VATSpec('dk', 'DK', 10, 10, 'DK01234567'),
  new VATSpec('de', 'DE', 11, 11, 'DE012345678'),
  new VATSpec('fi', 'FI', 10, 10, 'FI01234567'),
  new VATSpec('fr', 'FR', 13, 13, 'FR01234567891'),
  new VATSpec('gr', 'EL', 11, 11, 'EL012345678'),
  new VATSpec('ie', 'IE', 10, 10, 'IE01234567'),
  new VATSpec('it', 'IT', 13, 13, 'IT01234567891'),
  new VATSpec('lu', 'LU', 10, 10, 'LU01234567'),
  new VATSpec('nl', 'NL', 14, 14, 'NL012345678B10'),
  new VATSpec('at', 'AT', 11, 11, 'AT012345678'),
  new VATSpec('pt', 'PT', 11, 11, 'PT012345678'),
  new VATSpec('es', 'ES', 11, 11, 'ESX01234567'),
  new VATSpec('gb', 'GB', 11, 11, 'GB012345678'),
  new VATSpec('se', 'SE', 14, 14, 'SE012345678910'),
  new VATSpec('cy', 'CY', 11, 11, 'CY012345678'),
  new VATSpec('ee', 'EE', 11, 11, 'EE012345678'),
  new VATSpec('hu', 'HU', 10, 10, 'HU01234567'),
  new VATSpec('lv', 'LV', 13, 13, 'LV01234567891'),
  new VATSpec('lt', 'LT', 11, 14, 'LT012345678'),
  new VATSpec('mt', 'MT', 10, 10, 'MT01234567'),
  new VATSpec('pl', 'PL', 12, 12, 'PL0123456789'),
  new VATSpec('si', 'SI', 10, 10, 'SI01234567'),
  new VATSpec('sk', 'SK', 11, 12, 'SK012345678'),
  new VATSpec('cz', 'CZ', 13, 14, 'CZ01234567891')
);

                                                                                


/* Returns the VATSpec of the countrycode */
function getVATSpec(countrycode)
{
  for(var i=0; i<VATSpecs.length; i++)
  {
    if(VATSpecs[i].countrycode == countrycode)
    {
      return VATSpecs[i];
    }
  }
  return new VATSpec('', '', 0, 99, '');
}

/* Evaluates if a VATCode follows the country-specific format.
Returns:
 0: success 
 1: wrong countrycode
 2: not enough characters
 3: too many characters
*/
function evalVATCode(countrycode, VATcode) 
{
  vatspec = getVATSpec(countrycode);
  
  if (VATcode.slice(0,vatspec.prefix.length) != vatspec.prefix) 
    return 1;
  if(VATcode.length < vatspec.minlength)
    return 2;
  if(VATcode.length > vatspec.maxlength)
    return 3; 
  
  return 0;
}

--> 

