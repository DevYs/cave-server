package devy.cave.server.db;

import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import java.io.File;

public class EnvironmentBuilder {

    public static String defaultDbDir = "./bdb";
    private boolean allowCreate = true;
    private boolean transactional = true;

    public EnvironmentBuilder setDbDir(String dbDir) {
        this.defaultDbDir = dbDir;
        return this;
    }

    public EnvironmentBuilder setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
        return this;
    }

    public EnvironmentBuilder setTransactional(boolean transactional) {
        this.transactional = transactional;
        return this;
    }

    public Environment build() {
        File file = new File(defaultDbDir);
        EnvironmentConfig environmentConfig
                = EnvironmentConfig.DEFAULT.setAllowCreate(allowCreate).setTransactional(transactional);
        return new Environment(file, environmentConfig);
    }
}
