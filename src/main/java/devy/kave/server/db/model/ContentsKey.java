package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ContentsKey implements MarshalledTupleEntry {

    private long contentsNo;

    public ContentsKey(long contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final long getContentsNo() {
        return contentsNo;
    }

    @Override
    public String toString() {
        return "ContentsKey{" +
                "contentsNo=" + contentsNo +
                '}';
    }

    public ContentsKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.contentsNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.contentsNo = tupleInput.readLong();
    }
}
