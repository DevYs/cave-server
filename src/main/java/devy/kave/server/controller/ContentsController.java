package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Deck;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.DeckService;
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
    private DeckService deckService;

    @GetMapping("/contents")
    public String contents(Principal principal, String contentsNo, @RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        Deck deck = deckService.getDeck(principal.getName(), contentsNo);
        Collection<Video> videoList = deckService.videoList(contentsNo);
        Contents contents = deckService.getContents(contentsNo);

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
        Deck remove = deckService.remove(principal.getName(), contentsNo);
        logger.info("removed " + remove.toString());
        return "redirect:" + httpServletRequest.getHeader("referer");
    }

}
