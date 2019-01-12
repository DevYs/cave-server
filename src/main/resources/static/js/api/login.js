$(document).ready(function() {

    $("form").on("submit", function() {
        $.ajax({
            url: 'http://localhost:1042/api/user/login',
            method: 'POST',
            dataType: 'json',
            data: $("form").serialize(),
            success: function(result) {
                setCookie(result.apiKey);

                var c = document.cookie;
                console.log(c);

                return false;
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