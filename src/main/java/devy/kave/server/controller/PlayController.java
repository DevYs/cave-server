package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.User;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.Watching;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.UserService;
import devy.kave.server.db.service.VideoService;
import devy.kave.server.db.service.WatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PlayController {

    private final Logger logger = LoggerFactory.getLogger(PlayController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private WatchingService watchingService;

    @GetMapping("/play")
    public String play(Principal principal, String videoNo, Model model) {
        logger.info(principal.getName());
        User user = userService.getUserByUserId(principal.getName());
        Video video = videoService.getVideo(videoNo);
        Watching watching = watchingService.getWatching(user.getUserNo(), videoNo);
        Contents contents = contentsService.getContents(video.getContentsNo());

        boolean isWatching = watching != null;
        if(!isWatching) {
            boolean isAdded = watchingService.add(user.getUserNo(), video, contents);
            if(isAdded) {
                logger.info("'" + user.getUserId() + "'님 " + contents.getContentsName() + " " + video.getVideoName() + " 시작");
            }
        }

        model.addAttribute("isWatching", isWatching);
        model.addAttribute("watchingVideo", video);
        model.addAttribute("contents", contents);
        model.addAttribute("videoList", contentsService.videoList(video.getContentsNo()));

        return "play";
    }

}
