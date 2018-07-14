package devy.cave.server.db;

import com.sleepycat.je.DatabaseConfig;

public class DatabaseConfigBuilder {

    private boolean allowCreate = true;
    private boolean transactional = true;

    public DatabaseConfigBuilder setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
        return this;
    }

    public DatabaseConfigBuilder setTransactional(boolean transactional) {
        this.transactional = transactional;
        return this;
    }

    public DatabaseConfig build() {
        return DatabaseConfig.DEFAULT.setAllowCreate(allowCreate).setTransactional(transactional);
    }

}
