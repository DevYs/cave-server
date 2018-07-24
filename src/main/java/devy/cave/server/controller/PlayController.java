package devy.cave.server.controller;

import devy.cave.server.db.model.Video;
import devy.cave.server.db.model.Watching;
import devy.cave.server.db.service.PlayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class PlayController {

    private final Logger logger = LoggerFactory.getLogger(PlayController.class);

    @Autowired
    private PlayService playService;

    @GetMapping("/play")
    public String play(Principal principal, String videoNo, Model model) {

        int parseCount = 0;
        boolean isParse = false;

        Watching watching = playService.getWatching(principal.getName(), videoNo);

        boolean isWatching = watching != null;
        if(!isWatching) {
            watching = playService.addWatching(principal.getName(), videoNo, "0");
        }

        while(!isParse && parseCount < 3) {
            parseCount++;
            isParse = isParse(watching.getVideo());
        }

        Collection<Video> videoList = playService.videoList(watching.getContentsNo());

        model.addAttribute("isParse", isParse);
        model.addAttribute("watching", watching);
        model.addAttribute("isWatching", isWatching);
        model.addAttribute("videoList", videoList);

        return "play";
    }

    private boolean isParse(Video video) {
        try {
            video.parseShareLink();
        } catch (IOException e) {
            logger.info("Failed get video data from gogole photo!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
