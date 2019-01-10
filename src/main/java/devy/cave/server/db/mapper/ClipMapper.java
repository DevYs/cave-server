package devy.cave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.cave.server.db.DatabaseAccessObjectManager;
import devy.cave.server.db.QueryMap;
import devy.cave.server.db.model.Clip;
import devy.cave.server.db.model.ClipKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClipMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object remove(MarshalledTupleEntry key) {
        return map().remove(key);
    }

    @Override
    public Object mod(MarshalledTupleKeyEntity entity) {
        Clip clip = (Clip) entity;
        return map().replace(new ClipKey(clip.getClipContentsNo()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Clip.DB_CLIP));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Clip.DB_CLIP));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Clip.DB_CLIP));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Clip.DB_CLIP));
    }
}