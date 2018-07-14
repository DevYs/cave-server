package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class AdminConfig implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_ADMIN_CONFIG = "db_admin_config";
    public static final String CONFIG_PORT = "port";

    private transient String configName;
    private String configValue;

    public AdminConfig() {}

    public AdminConfig(String configName, String configValue) {
        this.configName = configName;
        this.configValue = configValue;
    }

    final void setKey(String configName) {
        this.configName = configName;
    }

    public final String getConfigName() {
        return configName;
    }

    public AdminConfig setConfigName(String configName) {
        this.configName = configName;
        return this;
    }

    public final String getConfigValue() {
        return configValue;
    }

    public AdminConfig setConfigValue(String configValue) {
        this.configValue = configValue;
        return this;
    }

    @Override
    public String toString() {
        return "AdminConfig{" +
                "configName='" + configName + '\'' +
                ", configValue='" + configValue +
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

    public static AdminConfig port() {
        return new AdminConfig().setConfigName(CONFIG_PORT);
    }

}
