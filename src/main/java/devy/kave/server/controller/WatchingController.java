package devy.kave.server.controller;

import devy.kave.server.db.model.User;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.model.Watching;
import devy.kave.server.db.service.UserService;
import devy.kave.server.db.service.VideoService;
import devy.kave.server.db.service.WatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class WatchingController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private WatchingService watchingService;

    @GetMapping("/watching/remove")
    public String remove(Principal principal, String videoNo, HttpServletRequest request, Model model) {
        User user = userService.getUserByUserId(principal.getName());
        Video watchingVideo = videoService.getVideo(videoNo);

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("watchingVideo", watchingVideo);
        model.addAttribute("watchingContents", watchingVideo.getContents());
        model.addAttribute("refer", request.getHeader("referer"));

        return "watching-remove";
    }

    @PostMapping("/watching/remove")
    public String remove(Principal principal, String videoNo) {
        User user = userService.getUserByUserId(principal.getName());
        Watching remove = watchingService.remove(user.getUserNo(), videoNo);
        return "redirect:/index";
    }

}