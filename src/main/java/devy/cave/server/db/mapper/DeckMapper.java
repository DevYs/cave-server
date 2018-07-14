package devy.cave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.cave.server.db.DatabaseAccessObjectManager;
import devy.cave.server.db.QueryMap;
import devy.cave.server.db.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeckMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Deck remove(MarshalledTupleEntry key) {
        return (Deck) map().remove(key);
    }

    @Override
    public Deck mod(MarshalledTupleKeyEntity entity) {
        Deck deck = (Deck) entity;
        return (Deck) map().replace(deck.getDeckKey(), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Deck.DB_DECK));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Deck.DB_DECK));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Deck.DB_DECK));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Deck.DB_DECK));
    }

    public StoredSortedMap sortedMapByUserNo() {
        return manager.sortedMap(queryMap.get(Deck.INDEX_DECK_USER_NO));
    }

}