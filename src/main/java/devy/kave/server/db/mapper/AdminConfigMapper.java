package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.QueryMap;
import devy.kave.server.db.model.AdminConfig;
import devy.kave.server.db.model.AdminConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminConfigMapper implements Mapper {

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Autowired
    private QueryMap queryMap;

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
        AdminConfig adminConfig = (AdminConfig) entity;
        return map().replace(new AdminConfigKey(adminConfig.getConfigName()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(AdminConfig.DB_ADMIN_CONFIG));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(AdminConfig.DB_ADMIN_CONFIG));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(AdminConfig.DB_ADMIN_CONFIG));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(AdminConfig.DB_ADMIN_CONFIG));
    }
}