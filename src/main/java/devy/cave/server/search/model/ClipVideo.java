package devy.cave.server.search.model;

public class ClipVideo {

    private String movieId;
    private String videoKey;
    private String clipId;
    private String clipImage;
    private String clipImageAlt;
    private String clipVideoUrl;
    private boolean hasVideo = false;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getVideoKey() {
        return videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getClipId() {
        return clipId;
    }

    public void setClipId(String clipId) {
        this.clipId = clipId;
    }

    public String getClipImage() {
        return clipImage;
    }

    public void setClipImage(String clipImage) {
        this.clipImage = clipImage;
    }

    public String getClipImageAlt() {
        return clipImageAlt;
    }

    public void setClipImageAlt(String clipImageAlt) {
        this.clipImageAlt = clipImageAlt;
    }

    public String getClipVideoUrl() {
        return clipVideoUrl;
    }

    public void setClipVideoUrl(String clipVideoUrl) {
        this.clipVideoUrl = clipVideoUrl;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    @Override
    public String toString() {
        return "ClipVideo{" +
                "movieId='" + movieId + '\'' +
                ", videoKey='" + videoKey + '\'' +
                ", clipId='" + clipId + '\'' +
                ", clipImage='" + clipImage + '\'' +
                ", clipImageAlt='" + clipImageAlt + '\'' +
                ", clipVideoUrl='" + clipVideoUrl + '\'' +
                ", hasVideo=" + hasVideo +
                '}';
    }
}
