var intervalTime = 60 * 1000;
var intervelStartTime = 1 * intervalTime;

$(document).ready(function() {

    var video = document.getElementById("player");
    var videoNo = $("#videoNo").val();
    var watchingTime = $("#watchingTime").val();
    var isParse = $("#isParse").val();

    $("#toStart").on("click", function() {
        $('#modal').modal("hide");
    });

    $("#toWatching").on("click", function() {
        var video = document.getElementById("player");
        video.currentTime = watchingTime;
        $('#modal').modal("hide");
    });

    $("#player").on("ended", function() {
        $.ajax({
            url : "/api/watching/remove?videoNo=" + videoNo
        })
    });

    $("#player").on("canplay", function() {
        video.play();
    });

    if((0 < parseInt(watchingTime)) || (isParse == "false")) {
        $('#modal').modal('show');
    }

    $('.video-poster').each(function(e) {
        var v = $(this).find('img');

        $.ajax({
            url : "/api/video/poster/" + $(this).attr('id'),
            success : function(result){
                v.attr('src', result.videoPosterUrl);
            }
        })
    });

    setTimeout(function() {
        setInterval(addWatching, intervalTime);
    }, intervelStartTime);

});

function addWatching() {
    var video = document.getElementById("player");
    var videoNo = $("#videoNo").val();
    if(!video.ended) {
        $.ajax({
            url : "/api/watching/add?videoNo=" + videoNo + "&watchingTime=" + video.currentTime
        })
    }
}

$(window).on("beforeunload", addWatching);