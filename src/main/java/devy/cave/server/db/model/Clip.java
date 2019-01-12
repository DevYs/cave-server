package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Clip implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_CLIP = "db_clip";

    private transient String clipContentsNo;
    private String movieId;
    private String youtubeVideoId;

    public Clip() {}

    public Clip(String clipContentsNo) {
        this.clipContentsNo = clipContentsNo;
        this.movieId = "";
        this.youtubeVideoId = "";
    }

    public Clip(String movieId, String youtubeVideoId) {
        this.movieId = movieId;
        this.youtubeVideoId = youtubeVideoId;
    }

    public Clip(String clipContentsNo, String movieId, String youtubeVideoId) {
        this.clipContentsNo = clipContentsNo;
        this.movieId = movieId;
        this.youtubeVideoId = youtubeVideoId;
    }

    final void setKey(String clipContentsNo) {
        this.clipContentsNo = clipContentsNo;
    }

    public String getClipContentsNo() {
        return clipContentsNo;
    }

    public void setClipContentsNo(String clipContentsNo) {
        this.clipContentsNo = clipContentsNo;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    @Override
    public String toString() {
        return "Clip{" +
                "clipContentsNo='" + clipContentsNo + '\'' +
                ", movieId='" + movieId + '\'' +
                ", youtubeVideoId='" + youtubeVideoId + '\'' +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.clipContentsNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.clipContentsNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String keyName, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(keyName);
    }

    @Override
    public boolean nullifyForeignKey(String keyName) {
        throw new UnsupportedOperationException(keyName);
    }

}
