package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ChannelKey implements MarshalledTupleEntry {

    private long channelNo;

    public ChannelKey(long channelNo) {
        this.channelNo = channelNo;
    }

    public ChannelKey() {}

    public final long getChannelNo() {
        return channelNo;
    }

    @Override
    public String toString() {
        return "ChannelKey{" +
                "channelNo=" + channelNo +
                '}';
    }

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.channelNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.channelNo = tupleInput.readLong();
    }

}
