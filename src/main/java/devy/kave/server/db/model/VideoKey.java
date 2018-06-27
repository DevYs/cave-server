package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class VideoKey implements MarshalledTupleEntry {

    private long videoNo;

    public VideoKey(long videoNo) {
        this.videoNo = videoNo;
    }

    public final long getVideoNo() {
        return videoNo;
    }

    @Override
    public String toString() {
        return "VideoKey{" +
                "videoNo=" + videoNo +
                '}';
    }

    public VideoKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.videoNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.videoNo = tupleInput.readLong();
    }
}
