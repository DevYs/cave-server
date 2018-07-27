var ROLE_NAME_ACTOR = "배우";
var ROLE_NAME_ACTOR_2 = "주연";
var ROLE_NAME_ACTOR_3 = "조연";
var ROLE_NAME_DIRECTOR = "감독";
var ROLE_NAME_DIRECTOR_2 = "연출";
var PEOPLE_SEPERATOR = ", ";

var URL_CONTENTS = "http://devy.iptime.org:1043/contents";
var URL_CONTENTS_LIST = "http://devy.iptime.org:1043/contents/list";
//var URL_CONTENTS = "http://192.168.43.54:1043/contents";
//var URL_CONTENTS_LIST = "http://192.168.43.54:1043/contents/list";
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
        if($("#searchText").val().length < 1) {
            return;
        }

        disableBtn();
        page = page + 1;
        requestContentsList("next");
    });

    $("#prev").on("click", function() {
        if($("#searchText").val().length < 1) {
            return;
        }

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
    var searchText = $("#searchText").val();

    $.post(
        URL_CONTENTS_LIST,
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

        $("#" + code).on("click", setOnClickEvent);
    }

    return data.length;
}

function setOnClickEvent() {

    disableBtn();

    var code = $(this).attr("id");

    $.post(
        URL_CONTENTS,
        {code: code},
        function(data) {

            activeBtn();

            if(setValues(data)) {
                $("#searchModal").hide();
            }
        }
    );

}

function setValues(data) {

    console.log(data);

    try {
        $("#contentsPosterUrl").val(data.posterUrl);
            $("#contentsName").val(data.title);
            $("#genre").val(data.genre);
            $("#nation").val(data.nation);
            $("#releaseDate").val(data.releaseDate);
            $("#runningTime").val(data.runningTime);
            $("#story").val(data.story);

            var director = "";
            var actor = "";

            for(var i=0; i<data.people.length; i++) {
                if(data.people[i].repRoleNm == ROLE_NAME_ACTOR) {
                    actor = people(actor, data.people[i].peopleNm);
                } else if(data.people[i].repRoleNm == ROLE_NAME_ACTOR_2) {
                    actor = people(actor, data.people[i].peopleNm);
                } else if(data.people[i].repRoleNm == ROLE_NAME_ACTOR_3) {
                    actor = people(actor, data.people[i].peopleNm);
                } else if(data.people[i].repRoleNm == ROLE_NAME_DIRECTOR) {
                    director = people(director, data.people[i].peopleNm);
                } else if(data.people[i].repRoleNm == ROLE_NAME_DIRECTOR_2) {
                    director = people(director, data.people[i].peopleNm);
                }
            }

            // 마지막 공백에 의해 ', ' 길이가 2이므로
            if((director.length - 2) == director.lastIndexOf(PEOPLE_SEPERATOR)) {
                director = director.substring(0, director.length - 2);
            }

            if((actor.length - 2) == actor.lastIndexOf(PEOPLE_SEPERATOR)) {
                actor = actor.substring(0, actor.length - 2);
            }

            $("#director").val(director);
            $("#actor").val(actor);
    } catch(exception) {
        return false;
    }

    return true;
}

function people(people, name) {

    if(people.indexOf(name) < 0) {
        return people + name + PEOPLE_SEPERATOR;
    }

    return people;
}