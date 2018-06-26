package devy.kave.server.db;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import com.sleepycat.je.Database;

public class DatabaseViewFactory {

    public <K, V extends MarshalledTupleKeyEntity> StoredMap<K, V> map(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return map(factory, db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredMap<K, V> map(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass, boolean writeAllowed) {
        return factory.newMap(db, keyClass, valueBaseClass, writeAllowed);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedMap<K, V> sortedMap(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return sortedMap(factory, db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedMap<K, V> sortedMap(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass, boolean writeAllowed) {
        return factory.newSortedMap(db, keyClass, valueBaseClass, writeAllowed);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredValueSet set(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return set(factory, db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredValueSet set(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass, boolean writeAllowed) {
        return (StoredValueSet) factory.newMap(db, keyClass, valueBaseClass, writeAllowed).values();
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedValueSet sortedSet(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass) {
        return sortedSet(factory, db, keyClass, valueBaseClass, true);
    }

    public <K, V extends MarshalledTupleKeyEntity> StoredSortedValueSet sortedSet(TupleSerialFactory factory, Database db, Class<K> keyClass, Class<V> valueBaseClass, boolean writeAllowed) {
        return (StoredSortedValueSet) factory.newSortedMap(db, keyClass, valueBaseClass, writeAllowed).values();
    }

}