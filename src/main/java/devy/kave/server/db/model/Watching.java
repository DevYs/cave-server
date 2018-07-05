package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Watching implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    private transient String watchingNo;
    private String userNo;
    private Contents contents;
    private Video video;

    public Watching(String watchingNo, String userNo, Contents contents, Video video) {
        this.watchingNo = watchingNo;
        this.userNo = userNo;
        this.contents = contents;
        this.video = video;
    }

    public final String getWatchingNo() {
        return watchingNo;
    }

    public final String getUserNo() {
        return userNo;
    }

    public final Contents getContents() {
        return contents;
    }

    public final Video getVideo() {
        return video;
    }

    @Override
    public String toString() {
        return "Watching{" +
                "watchingNo=" + watchingNo +
                ", userNo=" + userNo +
                ", contents=" + contents +
                ", video=" + video +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.watchingNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.watchingNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
