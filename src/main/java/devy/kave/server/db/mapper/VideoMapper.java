package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.QueryMap;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.VideoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper implements Mapper {

    @Autowired
    private QueryMap queryMap;

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Video remove(MarshalledTupleEntry key) {
        return (Video) map().remove(key);
    }

    @Override
    public Video mod(MarshalledTupleKeyEntity entity) {
        Video video = (Video) entity;
        return (Video) map().replace(new VideoKey(video.getVideoNo()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(queryMap.get(Video.DB_VIDEO));
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(queryMap.get(Video.DB_VIDEO));
    }

    @Override
    public StoredMap map() {
        return manager.map(queryMap.get(Video.DB_VIDEO));
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(queryMap.get(Video.DB_VIDEO));
    }

    public StoredSortedMap sortedSetByContentsNo() {
        return manager.sortedMap(queryMap.get(Video.INDEX_VIDEO_CONTENTS_NO));
    }
}