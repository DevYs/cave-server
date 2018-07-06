package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.service.ChannelService;
import devy.kave.server.db.service.ContentsService;
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
import java.util.List;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ChannelService channelService;

    @Autowired
    private ContentsService contentsService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        List<Contents> newContents = contentsService.newContents();
        model.addAttribute("newContentsList", newContents);
        model.addAttribute("newContentsSize", newContents.size());
        return "index";
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