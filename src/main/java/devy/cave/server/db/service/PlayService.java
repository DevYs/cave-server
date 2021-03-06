package devy.cave.server.db.service;

import devy.cave.server.db.mapper.ContentsMapper;
import devy.cave.server.db.mapper.UserMapper;
import devy.cave.server.db.mapper.VideoMapper;
import devy.cave.server.db.mapper.WatchingMapper;
import devy.cave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public Watching getWatching(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Video video = (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();

        Watching watching;
        try {
            WatchingKey watchingKey = new WatchingKey(user.getUserNo(), videoNo);
            watching = (Watching) watchingMapper.sortedMap().duplicates(watchingKey).iterator().next();
            watching.setVideo(video);
        } catch (NoSuchElementException e) {
            watching = null;
        }

        return watching;
    }

    public Watching addWatching(String userId, String videoNo, String watchingTime) {
        Watching watching = getWatching(userId, videoNo);
        boolean isWatching = watching != null;

        if(isWatching) {
            watching.setWatchingTime(watchingTime);
            Watching modifiedWatching = (Watching) watchingMapper.map().replace(watching.getWatchingKey(), watching);
            return modifiedWatching;
        }

        Video video = (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Contents watchingContents = (Contents) contentsMapper.map().duplicates(new ContentsKey(video.getContentsNo())).iterator().next();
        Watching newWatching = new Watching(user.getUserNo(), video.getVideoNo(), watchingContents.getContentsNo(), watchingContents, video, "0");

        boolean isAdded = watchingMapper.add(newWatching);
        if(isAdded) {
            return newWatching;
        }

        return null;
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

    public Collection<Video> videoList(String contentsNo) {
        Collection<Video> videoList = videoMapper.sortedSetByContentsNo().duplicates(new ContentsKey(contentsNo));
        for(Video video : videoList) {
            String image = video.getImage();
            if(image == null || image.isEmpty()) {
                try {
                    video.parseShareLink();
                    videoMapper.map().replace(new VideoKey(video.getVideoNo()), video);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return videoList;
    }

}
