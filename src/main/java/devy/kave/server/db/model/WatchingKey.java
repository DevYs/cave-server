package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class WatchingKey implements MarshalledTupleEntry {

    private long watchingNo;

    public WatchingKey(long watchingNo) {
        this.watchingNo = watchingNo;
    }

    public final long getWatchingNo() {
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
        tupleOutput.writeLong(this.watchingNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.watchingNo = tupleInput.readLong();
    }
}