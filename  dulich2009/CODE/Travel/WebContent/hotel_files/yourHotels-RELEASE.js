/* Function to send an xmlhttp request to add hotel in myhotel module */
function saveHotel(hotelId, arrivalDate, departureDate, roomInfo) {
    var addLink = 'alink' + hotelId;
    var removeLink = 'rlink' + hotelId;
    var highLightlink = 'highLight' + hotelId;
    var params = { 'hotelId' : hotelId,
        'arrival' : arrivalDate,
        'departure' : departureDate,
        'roomInfo' : roomInfo,
        'type' : 'SAVED'
    };
    var url = buildUrl('/yourHotelsAdd.do', params);
    var callback = {
        success : function(o) {
            updateYourHotelsModule(o);
            swapLinks(addLink, removeLink);
            var highlight = document.getElementById(highLightlink);
        },
        failure : function(o) {
            document.write('please look into the error' + errObj.toString);
        }
    };
    YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
/* Function to send an xmlhttp request to remove hotel in myhotel shortlist hotel */
function removeHotel(hotelId, type) {
    var addLink = 'alink' + hotelId;
    var removeLink = 'rlink' + hotelId;
    var highLightlink = 'highLight' + hotelId;
    var justAddedParam;
    var url = '/yourHotelsRemove.do?type=' + type + '&hotelId=' + hotelId;
    var callback = {
        success : function(o) {
            updateYourHotelsModule(o);
            swapLinks(removeLink, addLink);
            var highlight = document.getElementById(highLightlink);
        },
        failure : function(o) {
            document.write('please look into the error' + errObj.toString);
        }
    };
    YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
/* function to show the Save hotel link according to the state hotel (i.e to be saved or already saved) */
function swapLinks(link1, link2) {
    var srcLink = document.getElementById(link1);
    var targetLink = document.getElementById(link2);
    if (srcLink != null) {
        srcLink.style.display = 'none';
    }
    if (targetLink != null) {
        targetLink.style.display = '';
    }
}
/* Fuction to populate empty <div> block of my hotels module*/
function updateYourHotelsModule(o) {
    var data = o.responseText;
    var element = document.getElementById('yourHotelsModule');
    element.innerHTML = data;
}
function buildUrl(url, params) {
    var queryString = "";
    for (var key in params) {
        if (queryString.length > 0) {
            queryString += "&";
        }
        queryString += key + "=" + escape(params[key]).replace(/\+/g, '%2B').replace(/\"/g, '%22').replace(/\'/g, '%27');
    }
    return url + "?" + queryString;
}

