$(document).ready(function() {
    $(window).on("beforeunload", function() {
        $.ajax({
            url : "/api/watching/add?videoNo=" + $("#videoNo").val() + "&watchingTime=0"
        })
    });
});

