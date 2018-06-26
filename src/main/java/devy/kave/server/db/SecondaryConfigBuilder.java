package devy.kave.server.db;

import com.sleepycat.je.SecondaryConfig;

public class SecondaryConfigBuilder {

    private boolean allowCreate = true;
    private boolean transactional = true;
    private boolean sortedDuplicates = true;

    public SecondaryConfigBuilder setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
        return this;
    }

    public SecondaryConfigBuilder setTransactional(boolean transactional) {
        this.transactional = transactional;
        return this;
    }

    public SecondaryConfigBuilder setSortedDuplicates(boolean sortedDuplicates) {
        this.sortedDuplicates = sortedDuplicates;
        return this;
    }

    public SecondaryConfig build() {
        return (SecondaryConfig) new SecondaryConfig()
                .setAllowCreate(allowCreate)
                .setTransactional(transactional)
                .setSortedDuplicates(sortedDuplicates);
    }

}
