package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Channel implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_CHANNEL = "db_channel";

    private transient String channelNo;

    @NotNull
    @Size(min=2, max=10)
    private String channelName;

    public Channel() {}

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public Channel(String channelNo, String channelName) {
        this.channelNo = channelNo;
        this.channelName = channelName;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
        tupleOutput.writeString(this.channelNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.channelNo = tupleInput.readString();
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