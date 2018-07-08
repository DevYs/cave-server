package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Deck;
import devy.kave.server.db.model.User;
import devy.kave.server.db.model.Watching;
import devy.kave.server.db.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private DeckService deckService;

    @Autowired
    private WatchingService watchingService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        List<Contents> newContentList = indexService.newContents();
        Collection<Watching> watchingList = indexService.watchingList(principal.getName());
        Collection<Deck> deckList = indexService.deckList(principal.getName());

        model.addAttribute("deckList", deckList);
        model.addAttribute("deckSize", deckList.size());
        model.addAttribute("watchingList", watchingList.iterator());
        model.addAttribute("watchingSize", watchingList.size());
        model.addAttribute("newContentsList", newContentList.iterator());
        model.addAttribute("newContentsSize", newContentList.size());

        return "index";
    }

    @GetMapping("/remove/watching")
    public String removeWatching(Principal principal, String videoNo) {
        Watching remove = indexService.remove(principal.getName(), videoNo);
        if(remove != null) {
            logger.info("removed " + remove.toString());
        } else {
            logger.info("not removed Watching " + principal.getName() + ", " + videoNo);
        }
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){

        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "redirect:/login";
    }

}