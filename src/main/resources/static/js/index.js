var PLAYLIST_ID = 'PLRHrA8PrvZBqKwpJaZm0wfkbZiO1l0Yf6';
var PART = 'snippet';
var MAX_RESUSLTS = 12;
var KEY = 'AIzaSyASag-hnXY1mqkhNHJ3NXgkLkOlhaw362A';
var PLAYLIST_URL = 'https://www.googleapis.com/youtube/v3/playlistItems?playlistId=' + PLAYLIST_ID + '&part=' + PART + '&maxResults=' + MAX_RESUSLTS + '&key=' + KEY;
var YOUTUBE_ID = '{youtube_id}';
var YOUTUBE_IFRAME = '<li><iframe src="https://www.youtube.com/embed/' + YOUTUBE_ID + '" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></li>';

var INDICATOR_ITEM_NO = '{indicatorItemNo}';
var INDICATOR_ITEM = '<a href="#">' + INDICATOR_ITEM_NO + '</a>';

var timer = 0;
var START_DELAY = 1000 * 5;
var PERIOD = 1000 * 3;
var ytbShow = 0;
var ytbPrev = 0;
var ytbNext = 0;

$(document).ready(function() {
    requestYoutubeList();

    $('#youtube-playlist').on('mouseenter', function() {
        isOnPager(0);
    });
    $('#pager-control a').on('click', onClickPagerControl);

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
    var isOn = $('#pager-control a').index($(this));
    isOnPager(!isOn);
}

var isRunning = true;

function isOnPager(isOn) {
    $('#pager-control a').removeClass('active');
    $('#pager-control a').eq(isOn).addClass('active');

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
            makeIndicator(result);
            initPager(result);
            setTimeout(function() {
                start();
            }, START_DELAY);
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
        console.log(ytbPrev, ytbShow, ytbNext);
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
    var length = result.items.length;
    for(var i=0; i<length; i++) {
        var item = YOUTUBE_IFRAME.replace(YOUTUBE_ID, result.items[i].snippet.resourceId.videoId);
        $('#youtube-playlist ul').append(item);
    }
}

function makeIndicator(result) {
    var length = result.items.length;
    for(var i=1; i<=length; i++) {
        var indicatorItem = INDICATOR_ITEM.replace(INDICATOR_ITEM_NO, i);
        $('#pager').append(indicatorItem);
    }
    $('#pager a').eq(0).addClass('selected');

    $('#pager a').on('click', function(e) {
        e.preventDefault();
        ytbShow = $('#pager a').index($(this));
        setIndex();
    });
}

function setIndex() {
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
    $('#pager a').removeClass('selected').eq(ytbShow).addClass('selected');
}
