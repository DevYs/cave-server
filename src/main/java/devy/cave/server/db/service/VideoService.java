package devy.cave.server.db.service;

import devy.cave.server.db.DatabaseKeyCreator;
import devy.cave.server.db.mapper.ContentsMapper;
import devy.cave.server.db.mapper.VideoMapper;
import devy.cave.server.db.model.Contents;
import devy.cave.server.db.model.ContentsKey;
import devy.cave.server.db.model.Video;
import devy.cave.server.db.model.VideoKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    public Video remove(String videoNo) {
        return videoMapper.remove(new VideoKey(videoNo));
    }

    public Video getVideo(String videoNo) {
        return (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }
}
