package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Deck implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_DECK = "db_deck";
    public static final String INDEX_DECK_USER_NO = "index_deck_user_no";
    public static final String INDEX_DECK_VIDEO_NO = "index_deck_video_no";
    public static final String KEY_DECK_USER_NO = "key_deck_user_no";
    public static final String KEY_DECK_VIDEO_NO = "key_deck_video_no";

    private transient String userNo;
    private transient String videoNo;
    private Video video;
    private Contents contents;

    public Deck(String userNo, String videoNo, Video video, Contents contents) {
        this.userNo = userNo;
        this.videoNo = videoNo;
        this.video = video;
        this.contents = contents;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(String videoNo) {
        this.videoNo = videoNo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public DeckKey getDeckKey() {
        return new DeckKey(this.userNo, this.videoNo);
    }

    public WatchingKey getWatchingKey() {
        return new WatchingKey(this.userNo, this.videoNo);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "userNo='" + userNo + '\'' +
                ", videoNo='" + videoNo + '\'' +
                ", video=" + video +
                ", contents=" + contents +
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
    public boolean marshalSecondaryKey(String keyName, TupleOutput keyOutput) {
        if (keyName.equals(KEY_DECK_USER_NO)) {
            if(this.userNo != null) {
                keyOutput.writeString(this.userNo);
                return true;
            } else {
                return false;
            }
        } else if(keyName.equals(KEY_DECK_VIDEO_NO)) {
            if(this.videoNo != null) {
                keyOutput.writeString(this.videoNo);
                return true;
            } else {
                return false;
            }
        } else {
            throw new UnsupportedOperationException(keyName);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
