$(document).ready(function() {

    $("#toStart").on("click", function() {
        $('#modal').modal("hide");
    });

    $("#toWatching").on("click", function() {
        var video = document.getElementById("player");
        video.currentTime = watchingTime;
        $('#modal').modal("hide");
    });

    if(0 < watchingTime) {
        $('#modal').modal('show');
    }

});

$(window).on("beforeunload", function() {
    var video = document.getElementById("player");

    $.ajax({
        url : "/api/watching/add?videoNo=" + $("#videoNo").val() + "&watchingTime=" + video.currentTime
    })
});

