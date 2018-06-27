package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class DefaultConfigKey implements MarshalledTupleEntry {

    private String configName;

    public DefaultConfigKey(String configName) {
        this.configName = configName;
    }

    public final String getConfigName() {
        return configName;
    }

    @Override
    public String toString() {
        return "DefaultConfigKey{" +
                "configName='" + configName + '\'' +
                '}';
    }

    public DefaultConfigKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.configName);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.configName = tupleInput.readString();
    }
}
