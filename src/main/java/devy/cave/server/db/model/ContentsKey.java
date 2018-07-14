package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ContentsKey implements MarshalledTupleEntry {

    private String contentsNo;

    public ContentsKey(String contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final String getContentsNo() {
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
        tupleOutput.writeString(this.contentsNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.contentsNo = tupleInput.readString();
    }
}
