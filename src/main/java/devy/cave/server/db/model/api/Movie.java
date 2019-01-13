package devy.cave.server.db.model.api;

import devy.cave.server.db.model.Clip;
import devy.cave.server.db.model.Contents;

public class Movie {

    private Contents contents;
    private Clip clip;

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "contents=" + contents +
                ", clip=" + clip +
                '}';
    }
}
