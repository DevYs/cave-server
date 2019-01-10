var ROLE_NAME_ACTOR = "배우";
var ROLE_NAME_ACTOR_2 = "주연";
var ROLE_NAME_ACTOR_3 = "조연";
var ROLE_NAME_DIRECTOR = "감독";
var ROLE_NAME_DIRECTOR_2 = "연출";
var PEOPLE_SEPERATOR = ", ";

var TAG_CONTENTS_ID = "{contentsId}";
var TAG_TITLE = "{title}";
var TAG_YEAR = "{year}";
var TAG_ACTOR = "{actor}";

//var URL_CONTENTS = "http://devy.iptime.org:1043/contents";
//var URL_CONTENTS_LIST = "http://devy.iptime.org:1043/contents/list";
//var URL_CONTENTS = "http://192.168.43.54:1043/contents";
//var URL_CONTENTS_LIST = "http://192.168.43.54:1043/contents/list";
var URL_CONTENTS = "/api/contents";
var URL_CONTENTS_LIST = "/api/contents/list";

var page = 1;

var template = "<a href='#' id='" + TAG_CONTENTS_ID + "' class='list-group-item search-item'>" +
                    "<h4 class='list-group-item-heading'>" + TAG_TITLE + " " + TAG_YEAR + "</h4>" +
                    "<p class='list-group-item-text'>" + TAG_ACTOR + "</p>" +
                "</a>";

$(document).ready(function() {

    $("#searchContentsForm").on("submit", function() {
        disableBtn();
        page = 1;
        requestContentsList();
        return false;
    });

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

    $.ajaxSetup({
        headers: { 'X-XSRF-TOKEN': $('input[name="_csrf"]').val() }
    });

    $.post(
        URL_CONTENTS_LIST,
        {searchText: searchText, page: page},
        function(data) {

            if(data.totalCount < 1) {
                alert("검색된 콘텐츠가 없습니다.");
            }

            if(page < 1) {
                alert("첫 페이지입니다.");
                page = 1;
            }

            if(data.lastPage < page) {
                alert("마지막 페이지 입니다.");
                page = page - 1;
            }

            if(data.itemList.length < 1) {
                return 0;
            }

            activeBtn();

            $("#page").empty();
            $("#page").append(data.page + " page");

            return appendItem(data.itemList);
        }
    );
}

function appendItem(data) {

    console.log(data);

    var contentsList = $("#contentsList");
        contentsList.empty();

    for(var i=0; i<data.length; i++) {

        var contentsId = data[i].contentsId;
        var title = data[i].title;
        var year = data[i].year != "" ? "- " + data[i].year : "";
        var actor = data[i].actor != null ? data[i].actor : "";

        var listItem = template
        .replace(TAG_CONTENTS_ID, contentsId)
        .replace(TAG_TITLE, title)
        .replace(TAG_ACTOR, actor)
        .replace(TAG_YEAR, year);

        contentsList.append(listItem);

        $("#" + contentsId).on("click", setOnClickEvent);
    }

    return data.length;
}

function setOnClickEvent() {

    disableBtn();

    var contentsId = $(this).attr("id");

    $.post(
        URL_CONTENTS,
        {contentsId: contentsId},
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