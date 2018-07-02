package devy.kave.server.db.service;

import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.VideoMapper;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.VideoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VideoService {

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private VideoMapper videoMapper;

    public boolean add(Video video) {
        Contents contents = (Contents) contentsMapper.map().duplicates(new ContentsKey(video.getContentsNo())).iterator().next();
        video.setVideoNo(DatabaseKeyCreator.createKey());
        video.setContents(contents);
        return videoMapper.add(video);
    }

    public Video mod(Video video) {
        Contents contents = (Contents) contentsMapper.map().duplicates(new ContentsKey(video.getContentsNo())).iterator().next();
        video.setContents(contents);
        return videoMapper.mod(video);
    }

    public Video remove(long videoNo) {
        return videoMapper.remove(videoNo);
    }

    public Video getVideo(long videoNo) {
        return (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
    }

    public Contents getContents(long contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }
}
