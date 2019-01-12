$(document).ready(function() {
    if(isExistCookie()) {
        console.log("exist");
    } else {
        window.location.href = "login.html";
    }
});