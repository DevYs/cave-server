package devy.kave.server.domain;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class DefaultConfig implements Serializable, MarshalledTupleKeyEntity {

    private transient long configNo;
    private String configName;
    private String configValue;

    public DefaultConfig(long configNo, String configName, String configValue) {
        this.configNo = configNo;
        this.configName = configName;
        this.configValue = configValue;
    }

    public final long getConfigNo() {
        return configNo;
    }

    public final String getConfigName() {
        return configName;
    }

    public final String getConfigValue() {
        return configValue;
    }

    @Override
    public String toString() {
        return "DefaultConfig{" +
                "configNo=" + configNo +
                ", configName='" + configName + '\'' +
                ", configValue='" + configValue + '\'' +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.configNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.configNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

    public class DefaultConfigKey implements MarshalledTupleEntry {

        private long configNo;

        public DefaultConfigKey(long configNo) {
            this.configNo = configNo;
        }

        public final long getConfigNo() {
            return configNo;
        }

        @Override
        public String toString() {
            return "DefaultConfigKey{" +
                    "configNo=" + configNo +
                    '}';
        }

        public DefaultConfigKey() {}

        @Override
        public void marshalEntry(TupleOutput tupleOutput) {
            tupleOutput.writeLong(this.configNo);
        }

        @Override
        public void unmarshalEntry(TupleInput tupleInput) {
            this.configNo = tupleInput.readLong();
        }
    }
}
