package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ClipKey implements MarshalledTupleEntry {

    private String clipContentsNo;

    public ClipKey(String clipContentsNo) {
        this.clipContentsNo = clipContentsNo;
    }

    public ClipKey() {}

    public String getClipContentsNo() {
        return clipContentsNo;
    }

    public void setClipContentsNo(String clipContentsNo) {
        this.clipContentsNo = clipContentsNo;
    }

    @Override
    public String toString() {
        return "ClipKey{" +
                "clipContentsNo='" + clipContentsNo + '\'' +
                '}';
    }

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.clipContentsNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.clipContentsNo = tupleInput.readString();
    }

}
