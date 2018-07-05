package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ChannelKey implements MarshalledTupleEntry {

    private String channelNo;

    public ChannelKey(String channelNo) {
        this.channelNo = channelNo;
    }

    public ChannelKey() {}

    public final String getChannelNo() {
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
        tupleOutput.writeString(this.channelNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.channelNo = tupleInput.readString();
    }

}
