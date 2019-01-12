package devy.cave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.cave.server.db.DatabaseAccessObjectManager;
import devy.cave.server.db.QueryMap;
import devy.cave.server.db.model.ApiAuth;
import devy.cave.server.db.model.ApiAuthKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiAuthMapper implements Mapper {

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
        ApiAuth apiAuth = (ApiAuth) entity;
        return map().replace(new ApiAuthKey(apiAuth.getAuthKey()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(ApiAuth.DB_API_AUTH));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(ApiAuth.DB_API_AUTH));
    }

    public StoredMap mapByUserId() {
        return manager.map(queryMap.get(ApiAuth.INDEX_API_AUTH_USER_ID));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(ApiAuth.DB_API_AUTH));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(ApiAuth.DB_API_AUTH));
    }
}
