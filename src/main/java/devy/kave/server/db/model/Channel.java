package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Channel implements Serializable, MarshalledTupleKeyEntity {

    private transient long channelNo;
    private String channelName;

    public Channel(long channelNo, String channelName) {
        this.channelNo = channelNo;
        this.channelName = channelName;
    }

    public final long getChannelNo() {
        return channelNo;
    }

    public final String getChannelName() {
        return channelName;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelNo=" + channelNo +
                ", channelName='" + channelName + '\'' +
                '}';
    }

    // --- MarshalledTupleKeyEntity implementation ---
    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.channelNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.channelNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String keyName, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(keyName);
    }

    @Override
    public boolean nullifyForeignKey(String keyName) {
        throw new UnsupportedOperationException(keyName);
    }

}