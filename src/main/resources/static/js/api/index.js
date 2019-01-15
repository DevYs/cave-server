$(document).ready(function() {
//    var apiUrl = 'http://localhost:1042/api/user/index';
    var apiUrl = 'http://www.devycave.de:1042/api/user/index';
    var authKey = $.getParameter('authKey');
    var userId = $.getParameter('userId');

    if(!authKey) {
        window.location.href = 'login.html';
    }

    console.log('authKey ' + authKey);
    console.log('userId ' + userId);

    $.ajax({
        url: apiUrl,
        method: 'POST',
        dataType: 'json',
        data: { 'authKey': authKey, 'userId': userId },
        success: function(data) {
            console.log(data);
            $("body").text(data.statusCode);
        },
        error: function(e) {
            console.log(e);
            window.location.href = "login.html";
        }
    });

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