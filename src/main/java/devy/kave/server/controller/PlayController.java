package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class PlayController {

    private final Logger logger = LoggerFactory.getLogger(PlayController.class);

    @Autowired
    private PlayService playService;

    @GetMapping("/play")
    public String play(Principal principal, String videoNo, Model model) {
        Video video = playService.getVideo(videoNo);
        boolean isWatching = playService.isWatching(principal.getName(), videoNo);
        Contents contents = playService.getContents(video.getContentsNo());
        Collection<Video> videoList = playService.videoList(video.getContentsNo());

        boolean isAdded = playService.addWatching(principal.getName(), videoNo, contents, "0");
        if(isAdded) {
            logger.info("'" + principal.getName() + "'님 " + contents.getContentsName() + " " + video.getVideoName() + " 시작");
        }

        model.addAttribute("isWatching", isWatching);
        model.addAttribute("watchingVideo", video);
        model.addAttribute("contents", contents);
        model.addAttribute("videoList", videoList);

        return "play";
    }

}
