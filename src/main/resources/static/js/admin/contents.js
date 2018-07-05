var page = 1;

var userTemp = "<div class='cont-thumb col-xs-4 col-sm-3 col-md-2'>" +
                    "<a href='/admin/contents/view?contentsNo=${contentsNo}'>" +
                        "<img class='img-responsive' src='${contentsPosterUrl}' alt='${alt}' />" +
                    "</a>" +
                    "<a class='idx-thumb-tl' href='/admin/contents/view?contentsNo=${contentsNo}'>${contentsName}</a>" +
                    "<a class='idx-thumb-tl' href='/admin/contents/view?contentsNo=${contentsNo}'>${releaseDate}</a>" +
                "</div>";

$(document).ready(function() {
    $("#next").click(function() {
        $(this).attr("disabled", "disabled");

        page = page + 1;

        $.ajax({
            url : "/api/admin/contents?pageNo=" + page + "&searchWord=" + $("#searchWord").val() + "&channelNo=" + $("#channelNo").val(),
            success : function(contentsList){
                if(contentsList.length < 1) {
                    $("#next").hide();
                    return;
                }

                for(var i=0; i<contentsList.length; i++) {
                    var u = userTemp
                    .replace("${contentsNo}", contentsList[i].contentsNo)
                    .replace("${contentsNo}", contentsList[i].contentsNo)
                    .replace("${contentsNo}", contentsList[i].contentsNo)
                    .replace("${contentsPosterUrl}", contentsList[i].contentsPosterUrl)
                    .replace("${contentsName}", contentsList[i].contentsName)
                    .replace("${releaseDate}", contentsList[i].releaseDate)
                    .replace("${alt}", contentsList[i].contentsName + " 포스터 이미지");
                    $("#contentsList").append(u);
                }

                $("#next").removeAttr("disabled");
            }
        })
    });
});

