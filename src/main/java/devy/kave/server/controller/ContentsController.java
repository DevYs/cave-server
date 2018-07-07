package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Deck;
import devy.kave.server.db.model.User;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.DeckService;
import devy.kave.server.db.service.UserService;
import devy.kave.server.db.service.VideoService;
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
    private UserService userService;

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private DeckService deckService;

    @GetMapping("/contents")
    public String contents(Principal principal, String contentsNo, @RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        User user = userService.getUserByUserId(principal.getName());
        Deck deck = deckService.getDeck(user.getUserNo(), contentsNo);
        Collection<Video> videoList = contentsService.videoList(contentsNo);
        Contents contents = contentsService.getContents(contentsNo);

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
        User user = userService.getUserByUserId(principal.getName());
        Deck remove = deckService.remove(user.getUserNo(), contentsNo);
        logger.info("removed " + remove.toString());
        return "redirect:" + httpServletRequest.getHeader("referer");
    }

}
