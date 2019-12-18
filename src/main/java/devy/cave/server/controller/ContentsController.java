package devy.cave.server.controller;

import devy.cave.server.db.model.Contents;
import devy.cave.server.db.model.Deck;
import devy.cave.server.db.model.Video;
import devy.cave.server.db.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;

@Controller
public class ContentsController {

    private final Logger logger = LoggerFactory.getLogger(ContentsController.class);

    @Autowired
    private ContentsService contentsService;

    @GetMapping("/contents")
    public String contents(Principal principal, String contentsNo, @RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        Collection<Video> videoList = contentsService.videoList(contentsNo);
        Contents contents = contentsService.getContents(contentsNo);

        Deck deck = null;
        if(principal != null) {
            deck = contentsService.getDeck(principal.getName(), contentsNo);
        }

        boolean isDeck = deck != null;
        model.addAttribute("isDeck", isDeck);
        model.addAttribute("tab", tab);
        model.addAttribute("contents", contents);
        model.addAttribute("videoList", videoList.iterator());
        model.addAttribute("videoSize", videoList.size());

        return "contents";
    }

    @GetMapping("/contents/deck/remove")
    public String remove(Principal principal, String contentsNo, HttpServletRequest httpServletRequest, Model model) {
        Deck remove = contentsService.remove(principal.getName(), contentsNo);
        logger.info("removed " + remove.toString());
        return "redirect:" + httpServletRequest.getHeader("referer");
    }

}
