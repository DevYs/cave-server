package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class UserConfigKey implements MarshalledTupleEntry {

    private String userNo;
    private String configName;

    public UserConfigKey(String userNo, String configName) {
        this.userNo = userNo;
        this.configName = configName;
    }

    public final String getUserNo() {
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
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.configName);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.configName = tupleInput.readString();
    }
}
