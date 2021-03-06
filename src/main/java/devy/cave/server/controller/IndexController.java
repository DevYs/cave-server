package devy.cave.server.controller;

import devy.cave.server.api.req.ApiReqLogin;
import devy.cave.server.api.resp.ApiResponse;
import devy.cave.server.api.resp.ApiStatus;
import devy.cave.server.db.model.*;
import devy.cave.server.db.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
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

    @Autowired
    private ApiAuthService apiAuthService;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        List<Contents> newContentList = indexService.newContents();

        List<Watching> watchingList = new ArrayList<>();
        List<Deck> deckList = new ArrayList<>();

        if(principal != null) {
            watchingList = indexService.watchingList(principal.getName());
            deckList = indexService.deckList(principal.getName());
        }

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

    @CrossOrigin
    @PostMapping("/api/user/login")
    @ResponseBody
    public Object apiLogin(ApiReqLogin apiReqLogin) {
        logger.info("userId " + apiReqLogin.getUserId());
        logger.info("password " + apiReqLogin.getPassword());

        User validPasswordAndUser = userService.isValidPasswordAndUser(apiReqLogin.getUserId(), apiReqLogin.getPassword());
        if(validPasswordAndUser != null) {
            ApiAuth apiAuth = apiAuthService.saveAuth(apiReqLogin.getUserId());
            return new ApiResponse(ApiStatus.SUCCESS_LOGIN, null, apiAuth);
        }

        return new ApiResponse(ApiStatus.FAILED_LOGIN, null,null);
    }

}