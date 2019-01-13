$(document).ready(function() {
//    var apiUrl = 'http://www.devycave.de:1042/api/user/index';
    var apiUrl = 'http://localhost:1042/api/user/index';
    var parameters = {'param1': 'devy', 'param2': 1042};

    $.ajaxSetup(AJAX_SETUP(apiUrl, parameters));

    $.ajax({
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