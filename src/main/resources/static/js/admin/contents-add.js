var page = 1;

var template = "<a href='#' id='{code}' class='list-group-item'>" +
                    "<h4 class='list-group-item-heading'>{title} {titleEng} {year}</h4>" +
                    "<p class='list-group-item-text'>{director} {genre} {nation}</p>" +
                "</a>";

$(document).ready(function() {
    $("#searchBtn").on("click", function() {
        disableBtn();
        page = 1;
        requestContentsList();
    });

    $("#next").on("click", function() {
        disableBtn();
        page = page + 1;
        requestContentsList("next");
    });

    $("#prev").on("click", function() {
        disableBtn();
        page = page - 1;
        requestContentsList("prev");
    });

    $("#searchModal").on("hidden.bs.modal", function (e) {
        page = 1;
        $("#searchText").val("");
        $("#contentsList").empty();
        $("#contentsList").append("<p class='text-center'>검색어를 입력하세요.</p>");
        $("#page").empty();
    })

});

function disableBtn() {
    $("#searchBtn").addClass("disabled");
    $("#next").addClass("disabled");
    $("#prev").addClass("disabled");
}

function activeBtn() {
    $("#searchBtn").removeClass("disabled");
    $("#next").removeClass("disabled");
    $("#prev").removeClass("disabled");
}

function requestContentsList(direction) {
    var url = "http://devy.iptime.org:1043/contents/list";
    var searchText = $("#searchText").val();

    $.post(
        url,
        {searchText: searchText, page: page},
        function(data) {

            if(data.length < 1) {
                if(direction == "next") {
                    alert("마지막 페이지 입니다.");
                    page = page - 1;
                } else if(direction == "prev") {
                    alert("첫 페이지입니다.");
                    page = 1;
                } else {
                    alert("검색된 콘텐츠가 없습니다.");
                }

                activeBtn();

                return 0;
            }

            $("#page").empty();
            $("#page").append(page + " page");

            activeBtn();

            return appendItem(data);
        }
    );
}

function appendItem(data) {
    var contentsList = $("#contentsList");
        contentsList.empty();

    for(var i=0; i<data.length; i++) {

        var code = data[i].code;
        var title = data[i].title;
        var titleEng = data[i].titleEng != "" ? "(" + data[i].titleEng + ")" : "";
        var year = data[i].year != "" ? "- " + data[i].year : "";
        var director = data[i].director != "" ? data[i].director + " 감독 | " : "";
        var genre = data[i].genre != "" ? data[i].genre + " | " : "";
        var nation = data[i].nation != "" ? data[i].nation : "";

        var listItem = template
        .replace("{code}", code)
        .replace("{title}", title)
        .replace("{titleEng}", titleEng)
        .replace("{year}", year)
        .replace("{director}", director)
        .replace("{genre}", genre)
        .replace("{nation}", nation);

        contentsList.append(listItem);
    }

    return data.length;
}