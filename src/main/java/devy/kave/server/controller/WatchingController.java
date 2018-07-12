package devy.kave.server.controller;

import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.Watching;
import devy.kave.server.db.service.UserService;
import devy.kave.server.db.service.VideoService;
import devy.kave.server.db.service.WatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WatchingController {

    private final Logger logger = LoggerFactory.getLogger(WatchingController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private WatchingService watchingService;

    @RequestMapping("/api/watching/add")
    @ResponseBody
    public void add(Principal principal, String videoNo, String watchingTime) {
        Video video = watchingService.getVideo(videoNo);
        boolean isAdded = watchingService.add(principal.getName(), video, video.getContentsNo(), watchingTime);
        if(isAdded) {
            logger.info("added Watching " + principal.getName() + ", " + video.toString());
        }
    }

    @GetMapping("/watching/remove")
    public String remove(Principal principal, String videoNo, HttpServletRequest request, Model model) {
        Video watchingVideo = watchingService.getVideo(videoNo);

        model.addAttribute("userName", principal.getName());
        model.addAttribute("watchingVideo", watchingVideo);
        model.addAttribute("watchingContents", watchingVideo.getContents());
        model.addAttribute("refer", request.getHeader("referer"));

        return "watching-remove";
    }

    @PostMapping("/watching/remove")
    public String remove(Principal principal, String videoNo) {
        Watching remove = watchingService.remove(principal.getName(), videoNo);
        return "redirect:/index";
    }

}