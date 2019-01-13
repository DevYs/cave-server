var authKey = '';
var AJAX_SETUP = null;

$(document).ready(function() {
    authKey = $.getParameter('authKey');

    if(!authKey) {
        window.location.href = 'login.html';
    }

    AJAX_SETUP = function(apiUrl, parameters) {
        console.log(authKey);
        console.log(parameters);

        return {
            method: 'POST',
            headers: {
               'authKey': authKey
            },
            dataType: 'json',
            url: apiUrl,
            data: parameters
        };
    }
});

$.getParameter = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null) {
       return null;
    }
    return decodeURI(results[1]) || 0;
}

$.href = function(page) {
    window.location.href = page + '?authKey=' + authKey;
}