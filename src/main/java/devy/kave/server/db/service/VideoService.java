package devy.kave.server.db.service;

import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.VideoMapper;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.VideoKey;
import devy.kave.server.google.photo.ShareLink;
import devy.kave.server.google.photo.ShareLinkParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public Video getVideo(String videoNo) throws IOException {
        Video video = (Video) videoMapper.map().duplicates(new VideoKey(videoNo)).iterator().next();
        String shareLinkUrl = video.getShareLinkUrl();

        ShareLink shareLink = ShareLinkParser.parse(shareLinkUrl);
        video.setVideoUrl(shareLink.getOgVideo());
        video.setImage(shareLink.getOgImage());
        return video;
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }
}
