$(document).ready(function() {

    var videoNo = $("#videoNo").val();
    var watchingTime = $("#watchingTime").val();

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

    if(0 < parseInt(watchingTime)) {
        console.log();
        $('#modal').modal('show');
    }

});

$(window).on("beforeunload", function() {
    var video = document.getElementById("player");
    var videoNo = $("#videoNo").val();
    if(!video.ended) {
        $.ajax({
            url : "/api/watching/add?videoNo=" + videoNo + "&watchingTime=" + video.currentTime
        })
    }
});

