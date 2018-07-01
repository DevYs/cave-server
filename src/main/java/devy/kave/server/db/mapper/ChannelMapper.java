package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.ChannelKey;
import devy.kave.server.db.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper implements Mapper {

    private final Query qChannel
            = new Query().setDbName(Channel.DB_CHANNEL).setKeyClass(ChannelKey.class).setValueBaseClass(Channel.class);

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object remove(long key) {
        return map().remove(new ChannelKey(key));
    }

    @Override
    public Object mod(MarshalledTupleKeyEntity entity) {
        Channel channel = (Channel) entity;
        return map().replace(new ChannelKey(channel.getChannelNo()), channel);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(qChannel);
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(qChannel);
    }

    @Override
    public StoredMap map() {
        return manager.map(qChannel);
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(qChannel);
    }
}