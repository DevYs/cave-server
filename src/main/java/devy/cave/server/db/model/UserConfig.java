package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class UserConfig implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    private transient String userNo;
    private transient String configName;
    private String configValue;
    private long modDateTime;

    public UserConfig(String userNo, String configName, String configValue, long modDateTime) {
        this.userNo = userNo;
        this.configName = configName;
        this.configValue = configValue;
        this.modDateTime = modDateTime;
    }

    final void setKey(String userNo, String configName) {
        this.userNo = userNo;
        this.configName = configName;
    }

    public final String getUserNo() {
        return userNo;
    }

    public final String getConfigName() {
        return configName;
    }

    public final String getConfigValue() {
        return configValue;
    }

    public long getModDateTime() {
        return modDateTime;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "userNo=" + userNo +
                ", configName='" + configName + '\'' +
                ", configValue='" + configValue + '\'' +
                ", modDateTime=" + modDateTime +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.configName);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.configName = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
