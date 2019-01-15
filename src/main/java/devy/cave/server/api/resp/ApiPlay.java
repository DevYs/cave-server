package devy.cave.server.api.resp;

import devy.cave.server.db.model.Video;
import devy.cave.server.db.model.Watching;

import java.util.List;

public class ApiPlay {

    private Watching watching;
    private List<Video> videoList;

    public Watching getWatching() {
        return watching;
    }

    public void setWatching(Watching watching) {
        this.watching = watching;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    @Override
    public String toString() {
        return "ApiPlay{" +
                "watching=" + watching +
                ", videoList=" + videoList +
                '}';
    }
}
