<!--
var contractURL = "";


function popUpContract(URL) 
{
  day = new Date();
  id = day.getTime();
  eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=1,location=0,statusbar=0,menubar=0,resizable=1,width=400,height=700');"); 
}

function showContract() 
{
  var err_msg;
  if (!document.forms[0].language_code.value) {
    err_msg = "Please fill in your preferred language";
  }
  if (!document.forms[0].cc1.value) {
    err_msg = err_msg + "\nPlease fill in your hotel's country";
  }
  if (err_msg) {
    alert(err_msg);
  } else {
  
    var url = '~show_contract.html?lang=' + document.forms[0].language_code.value
      + ';cc1=' + document.forms[0].cc1.value; 
    
    popUpContract(url);

    /* show the radios if the page has been viewed */
    var form = document.forms[0];
    for (i=0;i<form.agreed_t_and_c.length;i++) {
      form.agreed_t_and_c[i].disabled = false;
    }
  }
}

-->

