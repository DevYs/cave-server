package devy.kave.server.db.mapper;

import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.TransactionWorker;
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

    private final Logger logger = LoggerFactory.getLogger(ChannelMapper.class);

    private final Query qChannel
            = new Query().setDbName(Channel.DB_CHANNEL).setKeyClass(ChannelKey.class).setValueBaseClass(Channel.class);

    @Autowired
    private DatabaseAccessObjectManager databaseAccessObjectManager;

    public void addChannel(Channel channel) throws Exception {
        databaseAccessObjectManager.getTransactionRunner().run(new TransactionWorker() {
            @Override
            public void doWork() throws Exception {
                databaseAccessObjectManager.set(qChannel).add(channel);
            }
        });
    }

    public void removeChannel(long channelNo) throws Exception {
        databaseAccessObjectManager.getTransactionRunner().run(new TransactionWorker() {
            @Override
            public void doWork() throws Exception {
                databaseAccessObjectManager.map(qChannel).remove(new ChannelKey(channelNo));
            }
        });
    }

    public void modifyChannel(Channel channel) throws Exception {
        databaseAccessObjectManager.getTransactionRunner().run(new TransactionWorker() {
            @Override
            public void doWork() throws Exception {
                databaseAccessObjectManager.map(qChannel).replace(new ChannelKey(channel.getChannelNo()), channel);
            }
        });
    }

    public StoredSortedValueSet<Channel> getChannelListWithSortedSet() {
        return (StoredSortedValueSet<Channel>) databaseAccessObjectManager.sortedSet(this.qChannel);
    }

    public Channel getChannelByChannelNo(long channelNo) {
        return (Channel) databaseAccessObjectManager.sortedMap(this.qChannel).duplicates(new ChannelKey(channelNo)).iterator().next();
    }

}