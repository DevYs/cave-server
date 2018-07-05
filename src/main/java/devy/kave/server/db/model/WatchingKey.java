package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class WatchingKey implements MarshalledTupleEntry {

    private String watchingNo;

    public WatchingKey(String watchingNo) {
        this.watchingNo = watchingNo;
    }

    public final String getWatchingNo() {
        return watchingNo;
    }

    @Override
    public String toString() {
        return "WatchingNo{" +
                "watchingNo=" + watchingNo +
                '}';
    }

    public WatchingKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.watchingNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.watchingNo = tupleInput.readString();
    }
}