package devy.kave.server.db.mapper;

import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.ChannelKey;
import devy.kave.server.db.model.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper {

    private final Query channel = new Query().setDbName("channel").setKeyClass(ChannelKey.class).setValueBaseClass(Channel.class);

    @Autowired
    private DatabaseAccessObjectManager databaseAccessObjectManager;

    public void insert(Channel channel) {
        databaseAccessObjectManager.set(this.channel).add(channel);
    }

    public StoredValueSet get() {
        return databaseAccessObjectManager.sortedSet(this.channel);
    }
}