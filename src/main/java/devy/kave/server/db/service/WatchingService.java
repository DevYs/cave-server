package devy.kave.server.db.service;

import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.DeckMapper;
import devy.kave.server.db.mapper.UserMapper;
import devy.kave.server.db.mapper.WatchingMapper;
import devy.kave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
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

    public boolean add(String userNo, Video video, Contents watchingContents, String watchingTime) {
        Watching watching = getWatching(userNo, video.getVideoNo());

        if(watching != null) {
            watching.setWatchingTime(watchingTime);
            Watching modifiedWatching = (Watching) watchingMapper.map().replace(watching.getWatchingKey(), watching);
            return modifiedWatching.equals(watchingTime);
        }

        Watching newWatching = new Watching(userNo, video.getVideoNo(), watchingContents, video, "0");
        return watchingMapper.add(newWatching);
    }

    public boolean add(String userNo, Video video, Contents watchingContents) {
        return add(userNo, video, watchingContents, "0");
    }

    public Watching remove(String userNo, String videoNo) {
        WatchingKey watchingKey = new WatchingKey(userNo, videoNo);
        return watchingMapper.remove(watchingKey);
    }

}