// isAvailable : cast 가능 여부
window['__onGCastApiAvailable'] = function(isAvailable) {
    if (isAvailable) {
        initializeCastApi();
    }

    var castContext = cast.framework.CastContext.getInstance();
    castContext.addEventListener(cast.framework.CastContextEventType.CAST_STATE_CHANGED, castMedia);
};

/*
    receiverApplicationId: applicationId,
    autoJoinPolicy: chrome.cast.AutoJoinPolicy.ORIGIN_SCOPED
*/
var initializeCastApi = function() {
    cast.framework.CastContext.getInstance().setOptions({
        receiverApplicationId: chrome.cast.media.DEFAULT_MEDIA_RECEIVER_APP_ID
    });
};

var castMedia = function() {
    var castSession = cast.framework.CastContext.getInstance().getCurrentSession();
    var currentMediaURL = $("#player source").attr("src");
    var contentType = "video/mp4";
    var mediaInfo = new chrome.cast.media.MediaInfo(currentMediaURL, contentType);
    var request = new chrome.cast.media.LoadRequest(mediaInfo);

    castSession.loadMedia(request).then(loadedMedia, failedLoadMedia);
}

var loadedMedia = function() {
    // TODO Cast 작업
}

var failedLoadMedia = function(errorCode) {
    console.log('Error code: ' + errorCode);
}
