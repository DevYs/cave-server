var SUC_LOGIN = 20000;

//var loginUrl = 'http://www.devycave.de:1042/api/user/login';
var loginUrl = 'http://localhost:1042/api/user/login';

var AJAX_SETUP_LOGIN = function() {
    return {
        url: loginUrl,
        data: $("form").serialize(),
        method: "POST",
        dataType: 'json'
    };
}

$(document).ready(function() {

    $("button").on("click", function() {
        $.ajaxSetup(AJAX_SETUP_LOGIN());

        $.ajax({
            success: function(auth) {
                console.log(auth);
                if(auth.apiStatus.statusCode === SUC_LOGIN) {
                    window.location.href = 'index.html?authKey=' + auth.contents.authKey;
                }
            },
            error: function(xhr, textStatus) {
                console.log(xhr);
                console.log('status ' + xhr.status);
                console.log('textStatus ' + textStatus);
            }
        });

        return false;
    });
});