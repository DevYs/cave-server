package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class UserConfigKey implements MarshalledTupleEntry {

    private long userNo;
    private String configName;

    public UserConfigKey(long userNo, String configName) {
        this.userNo = userNo;
        this.configName = configName;
    }

    public final long getUserNo() {
        return userNo;
    }

    public final String getConfigName() {
        return configName;
    }

    @Override
    public String toString() {
        return "UserConfigKey{" +
                "userNo=" + userNo +
                ", configName='" + configName + '\'' +
                '}';
    }

    public UserConfigKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.userNo);
        tupleOutput.writeString(this.configName);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.userNo = tupleInput.readLong();
        this.configName = tupleInput.readString();
    }
}
