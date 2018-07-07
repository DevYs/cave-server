package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class WatchingKey implements MarshalledTupleEntry {

    private String userNo;
    private String videoNo;

    public WatchingKey(String userNo, String videoNo) {
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

    @Override
    public String toString() {
        return "WatchingKey{" +
                "userNo='" + userNo + '\'' +
                ", videoNo='" + videoNo + '\'' +
                '}';
    }

    public WatchingKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.videoNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.videoNo = tupleInput.readString();
    }
}