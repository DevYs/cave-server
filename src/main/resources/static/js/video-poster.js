$(document).ready(function() {
    $('.video-poster').each(function(e) {
        var v = $(this).find('img');

        $.ajax({
            url : "/api/video/poster/" + $(this).attr('id'),
            success : function(result){
                v.attr('src', result.videoPosterUrl);
            }
        })
    });
});
