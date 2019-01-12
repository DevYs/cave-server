$(document).ready(function() {

    var param1 = "admin";
    var param2 = "hanys1042";
//    var apiKey = "1234";

    var param = { "userId": param1, "password": param2 };

    $.ajax({
        url: 'http://localhost:1042/api/user/login',
        method: 'POST',
        dataType: 'json',
        data: param,
        success: function(result) {
            console.log(result);
        },
        error: function(xhr, textStatus) {
            console.log(xhr);
            console.log('status ' + xhr.status);
            console.log('textStatus ' + textStatus);
        }

    });

//    $.ajax({
//            url: 'http://localhost:1042/api/user/index',
//            method: 'POST',
//            dataType: 'json',
//            headers: {
//                'apiKey': apiKey
//            },
//            data: param,
//            success: function(result) {
//                console.log(result);
//            },
//            error: function(xhr, textStatus) {
//                console.log(xhr);
//                console.log('status ' + xhr.status);
//                console.log('textStatus ' + textStatus);
//            }
//
//        });

});