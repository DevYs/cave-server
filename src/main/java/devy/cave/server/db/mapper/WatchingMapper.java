package devy.cave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.cave.server.db.DatabaseAccessObjectManager;
import devy.cave.server.db.QueryMap;
import devy.cave.server.db.model.Watching;
import devy.cave.server.db.model.WatchingKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WatchingMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Watching remove(MarshalledTupleEntry key) {
        return (Watching) map().remove(key);
    }

    @Override
    public Watching mod(MarshalledTupleKeyEntity entity) {
        Watching watching = (Watching) entity;
        WatchingKey watchingKey = new WatchingKey(watching.getVideoNo(), watching.getUserNo());
        return (Watching) map().replace(watchingKey, entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Watching.DB_WATCHING));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Watching.DB_WATCHING));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Watching.DB_WATCHING));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Watching.DB_WATCHING));
    }

    public StoredSortedMap sortedMapByUserNo() {
        return manager.sortedMap(queryMap.get(Watching.INDEX_WATCHING_USER_NO));
    }
}
