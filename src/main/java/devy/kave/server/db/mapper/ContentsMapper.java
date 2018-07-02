package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import devy.kave.server.db.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentsMapper implements Mapper {

    private final Query qContents
            = new Query().setDbName(Contents.DB_CONTENTS).setKeyClass(ContentsKey.class).setValueBaseClass(Contents.class);

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
    public Object remove(long key) {
        return map().remove(new ContentsKey(key));
    }

    @Override
    public StoredValueSet set() {
        return manager.set(qContents);
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(qContents);
    }

    @Override
    public StoredMap map() {
        return manager.map(qContents);
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(qContents);
    }
}