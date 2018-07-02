package devy.kave.server.db.mapper;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.StoredSortedValueSet;
import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Query;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.VideoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper implements Mapper {

    private final Query qVideo
            = new Query().setDbName(Video.DB_VIDEO).setKeyClass(VideoKey.class).setValueBaseClass(Video.class);

    private final Query qVideoForContentsNo
            = new Query()
            .setDbName(Video.DB_VIDEO_BY_CONTENTS_NO)
            .setKeyClass(Long.class)
            .setValueBaseClass(Video.class)
            .setPrimaryDbName(Video.DB_VIDEO)
            .setForeignKeyDbName(Contents.DB_CONTENTS)
            .setKeyName(Video.VIDEO_KEY_CONTENTS_NO);

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Override
    public boolean add(MarshalledTupleKeyEntity entity) {
        return set().add(entity);
    }

    @Override
    public Video remove(long key) {
        return (Video) map().remove(new VideoKey(key));
    }

    @Override
    public Video mod(MarshalledTupleKeyEntity entity) {
        Video video = (Video) entity;
        return (Video) map().replace(new VideoKey(video.getVideoNo()), entity);
    }

    @Override
    public StoredValueSet set() {
        return manager.set(qVideo);
    }

    @Override
    public StoredSortedValueSet sortedSet() {
        return manager.sortedSet(qVideo);
    }

    @Override
    public StoredMap map() {
        return manager.map(qVideo);
    }

    @Override
    public StoredSortedMap sortedMap() {
        return manager.sortedMap(qVideo);
    }

    public StoredSortedMap sortedSetByContentsNo() {
        return manager.sortedMap(qVideoForContentsNo);
    }
}