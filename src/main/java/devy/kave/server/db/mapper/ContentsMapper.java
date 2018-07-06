package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.QueryMap;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentsMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object mod(MarshalledTupleKeyEntity entity) {
        Contents contents = (Contents) entity;
        return map().replace(new ContentsKey(contents.getContentsNo()), entity);
    }

    @Override
    public Object remove(MarshalledTupleEntry key) {
        return map().remove(key);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Contents.DB_CONTENTS));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Contents.DB_CONTENTS));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Contents.DB_CONTENTS));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Contents.DB_CONTENTS));
    }

    public StoredSortedMap sortedMapByChannelNo () {
        return manager.sortedMap(queryMap.get(Contents.INDEX_CONTENTS_CHANNEL_NO));
    }
}