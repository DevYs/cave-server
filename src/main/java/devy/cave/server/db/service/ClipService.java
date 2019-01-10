package devy.cave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.cave.server.db.mapper.ClipMapper;
import devy.cave.server.db.model.Clip;
import devy.cave.server.db.model.ClipKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClipService {

    private final Logger logger = LoggerFactory.getLogger(ClipService.class);

    @Autowired
    private ClipMapper clipMapper;

    public boolean add(Clip clip) {
        return clipMapper.add(clip);
    }

    public Clip remove(String clipContentsNo) {
        return (Clip) clipMapper.remove(new ClipKey(clipContentsNo));
    }

    public Clip mod(Clip clip) {
        Clip storedClip = getClip(clip.getClipContentsNo());

        logger.info(clip.toString());
        logger.info(storedClip.toString());

        if(!storedClip.isAvailable()) {
            add(clip);
            return clip;
        }

        return (Clip) clipMapper.mod(clip);
    }

    public Clip getClip(String clipContentsNo) {

        Clip clip = new Clip(clipContentsNo);
        try {
            clip = (Clip) clipMapper.sortedMap().duplicates(new ClipKey(clipContentsNo)).iterator().next();
        } catch(NoSuchElementException e) {
            logger.info("등록된 클립정보가 없습니다. clipContentsNo " + clipContentsNo);
            return clip;
        }

        return clip;
    }

    public StoredSortedValueSet<Clip> clipList() {
        return clipMapper.sortedSet();
    }
}