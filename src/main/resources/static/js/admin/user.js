var page = 1;

var userTemp = "<div class='col-xs-6 col-sm-4 col-md-3'>" +
               "<ul class='jumbotron'>" +
                   "<li>${userName}</li>" +
                   "<li>${userId}</li>" +
                   "<li>${email}</li>" +
                   "<li class='text-center'>" +
                       "<a href='/admin/user/mod?userNo=${userNo1}' class='btn btn-warning' role='button'>수정</a>" +
                       "<a href='/admin/user/remove?userNo=${userNo2}' class='btn btn-danger' role='button'>삭제</a>" +
                   "</li>" +
               "</ul>" +
           "</div>";

$(document).ready(function() {
    $("#next").click(function() {
        page = page + 1;

        $.ajax({
            url : "/api/admin/user?pageNo=" + page + "&searchWord=" + $("#srchWd").val(),
            success : function(userList){
                if(userList.length < 1) {
                    $("#next").hide();
                    return;
                }

                for(var i=0; i<userList.length; i++) {
                    var u = userTemp
                    .replace("${userNo1}", userList[i].userNo)
                    .replace("${userNo2}", userList[i].userNo)
                    .replace("${userName}", userList[i].userName)
                    .replace("${userId}", userList[i].userId)
                    .replace("${email}", userList[i].email);
                    $("#userList").append(u);
                }
            }
        })
    });
});

