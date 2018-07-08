package devy.kave.server.db.service;

import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.UserMapper;
import devy.kave.server.db.mapper.VideoMapper;
import devy.kave.server.db.mapper.WatchingMapper;
import devy.kave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class PlayService {

    private final Logger logger = LoggerFactory.getLogger(PlayService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private WatchingMapper watchingMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    public Video getVideo(String videoNo) {
        return (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
    }

    public boolean isWatching(String userId, String videoNo) {
        return getWatching(userId, videoNo) != null;
    }

    public Watching getWatching(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();

        Watching watching;
        try {
            WatchingKey watchingKey = new WatchingKey(user.getUserNo(), videoNo);
            watching = (Watching) watchingMapper.sortedMap().duplicates(watchingKey).iterator().next();
        } catch (NoSuchElementException e) {
            watching = null;
        }

        return watching;
    }

    public boolean addWatching(String userId, String videoNo, Contents watchingContents, String watchingTime) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Video video = (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
        Watching watching = getWatching(userId, video.getVideoNo());

        boolean isWatching = watching != null;
        if(isWatching) {
            watching.setWatchingTime(watchingTime);
            Watching modifiedWatching = (Watching) watchingMapper.map().replace(watching.getWatchingKey(), watching);
            return modifiedWatching.equals(watchingTime);
        }

        Watching newWatching = new Watching(user.getUserNo(), video.getVideoNo(), watchingContents, video, "0");
        return watchingMapper.add(newWatching);
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

    public Collection<Video> videoList(String contentsNo) {
        return videoMapper.sortedSetByContentsNo().duplicates(new ContentsKey(contentsNo));
    }

}
