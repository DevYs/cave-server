package devy.kave.server.controller;

import com.sleepycat.collections.StoredValueSet;
import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.service.ChannelService;
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
import java.util.Iterator;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private ChannelService channelService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model) {

        StoredValueSet channels = channelService.get();

        Iterator iterator = channels.iterator();

        while(iterator.hasNext()) {
            Channel channel = (Channel) iterator.next();
            logger.info(channel.toString());
        }

        model.addAttribute("test", "test");
        model.addAttribute("channels", channels.iterator());

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