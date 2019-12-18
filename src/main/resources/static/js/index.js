var PLAYLIST_ID = 'PLRHrA8PrvZBqKwpJaZm0wfkbZiO1l0Yf6';
var PART = 'snippet';
var MAX_RESUSLTS = 12;
var KEY = 'AIzaSyASag-hnXY1mqkhNHJ3NXgkLkOlhaw362A';
var PLAYLIST_URL = 'https://www.googleapis.com/youtube/v3/playlistItems?playlistId=' + PLAYLIST_ID + '&part=' + PART + '&maxResults=' + MAX_RESUSLTS + '&key=' + KEY;
var YOUTUBE_ID = '{youtube_id}';
var YOUTUBE_IFRAME = '<li><iframe src="https://www.youtube.com/embed/' + YOUTUBE_ID + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></li>';


$(document).ready(function() {
    requestYoutubeList();

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

function requestYoutubeList() {
    $.ajax({
        method:'get',
        url: PLAYLIST_URL,
        success:function(result) {
            console.log(result);
            makeYoutubeList(result);
        }
    });

}

function makeYoutubeList(result) {
    var length = result.items.length;
    for(var i=0; i<length; i++) {
        console.log(result.items[i].snippet.resourceId.videoId);
        var item = YOUTUBE_IFRAME.replace(YOUTUBE_ID, result.items[i].snippet.resourceId.videoId);
        $('#youtube-playlist ul').append(item);
    }
    $('#youtube-playlist ul li').eq(0).addClass('show');
    $('#youtube-playlist ul li').eq(1).addClass('right');
    $('#youtube-playlist ul li').eq(length - 1).addClass('left');
}

