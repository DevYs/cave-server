package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class PlayController {

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private VideoService videoService;

    @GetMapping("/play")
    public String play(String videoNo, Model model) {
        Video video = videoService.getVideo(videoNo);
        model.addAttribute("watchingVideo", video);
        model.addAttribute("contents", contentsService.getContents(video.getContentsNo()));
        model.addAttribute("videoList", contentsService.videoList(video.getContentsNo()));
        return "play";
    }

}
