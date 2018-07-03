package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.QueryMap;
import devy.kave.server.db.model.User;
import devy.kave.server.db.model.UserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Object remove(long key) {
        return map().remove(new UserKey(key));
    }

    @Override
    public Object mod(MarshalledTupleKeyEntity entity) {
        User user = (User) entity;
        return map().replace(new UserKey(user.getUserNo()), entity);
    }

    public StoredMap mapByUserId() {
        return manager.map(queryMap.get(User.INDEX_USER_USER_ID));
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(User.DB_USER));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(User.DB_USER));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(User.DB_USER));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(User.DB_USER));
    }

}