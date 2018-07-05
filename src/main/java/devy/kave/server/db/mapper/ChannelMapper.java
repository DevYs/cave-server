package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.QueryMap;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.ChannelKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object remove(String key) {
        return map().remove(new ChannelKey(key));
    }

    @Override
    public Object mod(MarshalledTupleKeyEntity entity) {
        Channel channel = (Channel) entity;
        return map().replace(new ChannelKey(channel.getChannelNo()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Channel.DB_CHANNEL));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Channel.DB_CHANNEL));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Channel.DB_CHANNEL));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Channel.DB_CHANNEL));
    }
}