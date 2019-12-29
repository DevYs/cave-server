var PLAYLIST_ID = 'PLRHrA8PrvZBqKwpJaZm0wfkbZiO1l0Yf6';
var PART = 'snippet';
var MAX_RESUSLTS = 12;
var KEY = 'AIzaSyASag-hnXY1mqkhNHJ3NXgkLkOlhaw362A';
var PLAYLIST_URL = 'https://www.googleapis.com/youtube/v3/playlistItems?playlistId=' + PLAYLIST_ID + '&part=' + PART + '&maxResults=' + MAX_RESUSLTS + '&key=' + KEY;
var YOUTUBE_ID = '{youtube_id}';
var YOUTUBE_TITLE = '{youtube_title}';
var YOUTUBE_THUMBNAIL = '{youtube_thumbnail}';
var YOUTUBE_DESC = '{youtube_desc}';
var YOUTUBE_ITEM = '<li><h4>' + YOUTUBE_TITLE + '</h4><img src="' + YOUTUBE_THUMBNAIL + '" alt= "' + YOUTUBE_DESC + '" /></li>';

var INDICATOR_ITEM_NO = '{indicatorItemNo}';
var INDICATOR_ITEM = '<a href="#">' + INDICATOR_ITEM_NO + '</a>';

var timer = 0;
var START_DELAY = 1000 * 5;
var PERIOD = 1000 * 3;
var ytbShow = 0;
var ytbPrev = 0;
var ytbNext = 0;
var isRunning = true;

$(document).ready(function() {
    requestYoutubeList();

    $('#play-video').on('click', function(e) {
        e.preventDefault();
        var index = $('#youtube-playlist ul li').index($('#youtube-playlist ul li.show'));
        location.href = $('.recent .poster a:first-child').eq(index).attr('href');
    });

    $('#pager-control .onOff').on('click', onClickPagerControl);
    $('#pager-control .pager-control-prev').on('click', onClickPrev);
    $('#pager-control .pager-control-next').on('click', onClickNext);
    $('#pager-control .pager-control-prev').on('mouseleave', function(e) {
        isOnPager(1);
    });
    $('#pager-control .pager-control-next').on('mouseleave', function(e){
        isOnPager(1);
    });

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

function onClickPagerControl(e) {
    e.preventDefault();
    var isOn = $('#pager-control .onOff').index($(this));
    isOnPager(!isOn);
}

function isOnPager(isOn) {
    $('#pager-control .onOff').removeClass('active');
    $('#pager-control .onOff').eq(isOn).addClass('active');

    if(isOn) {
        isRunning = true;
    } else {
        isRunning = false;
    }
}

function requestYoutubeList() {
    $.ajax({
        method:'get',
        url: PLAYLIST_URL,
        success:function(result) {
            makePager(result);
            initPager(result);
            $('#youtube-playlist ul li').eq(0).find('img').on('load', function() {
                start();
            });
        }
    });
}

function start() {
    timer = setInterval(function(){
        if(!isRunning) {
            return;
        }
        ytbShow = ytbShow + 1;
        setIndex();
    }, PERIOD);
}

function initPager(result) {
    var length = result.items.length;
    ytbShow = 0;
    ytbNext = 0 < length ? ytbShow + 1 : 0;
    ytbPrev = 0 < length ? length - 1 : 0;

    $('#youtube-playlist ul li').eq(ytbPrev).addClass('prev');
    $('#youtube-playlist ul li').eq(ytbShow).addClass('show');
    $('#youtube-playlist ul li').eq(ytbNext).addClass('next');
}

function makePager(result) {
    console.log(result);
    var length = result.items.length;
    for(var i=length-1; i>=0; i--) {
        var item = YOUTUBE_ITEM.replace(YOUTUBE_THUMBNAIL, result.items[i].snippet.thumbnails.high.url)
                                .replace(YOUTUBE_TITLE, result.items[i].snippet.title);
        $('#youtube-playlist ul').append(item);
    }
}

function onClickPrev(e) {
    isOnPager(0);

    ytbShow = ytbShow - 1;
    if(ytbShow < 0) {
        ytbShow = $('#youtube-playlist ul li').length - 1;
    }
    setIndex();
}

function onClickNext(e) {
    isOnPager(0);

    ytbShow = ytbShow + 1;
    if($('#youtube-playlist ul li').length - 1 < ytbShow) {
        ytbShow = 0;
    }
    setIndex();
}

function setIndex() {
    var length = $('#youtube-playlist ul li').length;
    ytbNext = ytbShow + 1;
    ytbPrev = ytbShow - 1;

    if(ytbShow === length - 1) {
        ytbNext = 0;
    }

    if(ytbShow === length) {
        ytbShow = 0;
        ytbNext = ytbShow + 1;
        ytbPrev = length - 1;
    }

    if(ytbShow === 0) {
        ytbPrev = length - 1;
    }

    $('#youtube-playlist ul li.prev').removeClass('prev');
    $('#youtube-playlist ul li').eq(ytbPrev).addClass('prev');
    $('#youtube-playlist ul li.show').removeClass('show');
    $('#youtube-playlist ul li').eq(ytbShow).addClass('show');
    $('#youtube-playlist ul li.next').removeClass('next');
    $('#youtube-playlist ul li').eq(ytbNext).addClass('next');
}
