package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class DefaultConfig implements Serializable, MarshalledTupleKeyEntity {

    private transient String configName;
    private String configValue;
    private long modDateTime;

    public DefaultConfig(String configName, String configValue, long modDateTime) {
        this.configName = configName;
        this.configValue = configValue;
        this.modDateTime = modDateTime;
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
        return "DefaultConfig{" +
                "configName='" + configName + '\'' +
                ", configValue='" + configValue + '\'' +
                ", modDateTime=" + modDateTime +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.configName);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
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
