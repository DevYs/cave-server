package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;

public interface Mapper {

    boolean add(MarshalledTupleKeyEntity entity);

    Object remove(MarshalledTupleEntry key);

    Object mod(MarshalledTupleKeyEntity entity);

    StoredValueSet set();

    StoredSortedValueSet sortedSet();

    StoredMap map();

    StoredSortedMap sortedMap();

}