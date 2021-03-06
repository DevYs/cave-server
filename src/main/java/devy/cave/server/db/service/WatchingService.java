package devy.cave.server.db.service;

import devy.cave.server.db.mapper.*;
import devy.cave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WatchingService {

    private final Logger logger = LoggerFactory.getLogger(WatchingService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WatchingMapper watchingMapper;

    @Autowired
    private DeckMapper deckMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    public Watching getWatching(String userNo, String videoNo) {
        Watching watching = null;

        try {
            watching = (Watching) watchingMapper.sortedMap().duplicates(new WatchingKey(userNo, videoNo)).iterator().next();
        } catch (NoSuchElementException e) {
            logger.info("감상중인 콘텐츠가 아닙니다. " + userNo + ", " + videoNo);
            watching = null;
        }

        return watching;
    }

    public Watching add(String userId, String videoNo, String watchingTime) {
        Video video = getVideo(videoNo);
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Contents watchingContents = getContents(video.getContentsNo());
        Watching watching = getWatching(user.getUserNo(), video.getVideoNo());

        if(watching != null) {
            watching.setWatchingTime(watchingTime);
            return (Watching) watchingMapper.map().replace(watching.getWatchingKey(), watching);
        }

        Watching newWatching = new Watching(user.getUserNo(), video.getVideoNo(), video.getContentsNo(), watchingContents, video, watchingTime);
        boolean isAdded = watchingMapper.add(newWatching);

        return isAdded ? newWatching : null;
    }

    public Watching remove(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        WatchingKey watchingKey = new WatchingKey(user.getUserNo(), videoNo);
        return watchingMapper.remove(watchingKey);
    }

    public Video getVideo(String videoNo) {
        return (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }
}