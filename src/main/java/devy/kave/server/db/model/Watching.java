package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Watching implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_WATCHING = "db_watching";
    public static final String INDEX_WATCHING_USER_NO = "index_watching_user_no";
    public static final String INDEX_WATCHING_VIDEO_NO = "index_watching_video_no";

    public static final String KEY_WATCHING_USER_NO = "key_watching_user_no";
    public static final String KEY_WATCHING_VIDEO_NO = "key_watching_video_no";

    private transient String userNo;
    private transient String videoNo;
    private Contents contents;
    private Video video;

    public Watching(String userNo, String videoNo, Contents contents, Video video) {
        this.userNo = userNo;
        this.videoNo = videoNo;
        this.contents = contents;
        this.video = video;
    }

    final void setKey(String userNo, String videoNo) {
        this.userNo = userNo;
        this.videoNo = videoNo;
    }

    public final String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public final String getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(String videoNo) {
        this.videoNo = videoNo;
    }

    public final Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public final Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public WatchingKey getWatchingKey() {
        return new WatchingKey(this.userNo, this.videoNo);
    }

    public DeckKey getDeckKey() {
        return new DeckKey(this.userNo, this.videoNo);
    }

    @Override
    public String toString() {
        return "Watching{" +
                "userNo=" + userNo +
                ", videoNo=" + videoNo +
                ", contents=" + contents +
                ", video=" + video +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.videoNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.videoNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        if(s.equals(KEY_WATCHING_USER_NO)) {
            if(this.userNo != null) {
                tupleOutput.writeString(this.userNo);
                return true;
            } else {
                return false;
            }
        } else if(s.equals(KEY_WATCHING_VIDEO_NO)) {
            if(this.videoNo != null) {
                tupleOutput.writeString(this.videoNo);
                return true;
            } else {
                return false;
            }
        } else {
            throw new UnsupportedOperationException(s);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
