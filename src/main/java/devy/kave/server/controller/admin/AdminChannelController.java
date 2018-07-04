package devy.kave.server.controller.admin;

import devy.kave.server.db.model.Channel;
import devy.kave.server.db.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminChannelController {

    private Logger logger = LoggerFactory.getLogger(AdminChannelController.class);

    @Autowired
    private ChannelService channelService;

    @GetMapping("/admin/channel")
    public String channel() {
        return "admin/channel";
    }

    @GetMapping("/admin/channel/add")
    public String add(Channel channel) {
        return "admin/channel-add";
    }

    @PostMapping("/admin/channel/add")
    public String add(@Valid Channel channel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/channel-add";
        }

        boolean add = channelService.add(channel);
        if(add) {
            logger.info("added " + channel.toString());
        }
        return "redirect:/admin/channel";
    }

    @GetMapping("/admin/channel/mod")
    public String mod(long channelNo, Model model) {
        model.addAttribute("channel", channelService.getChannel(channelNo));
        return "admin/channel-mod";
    }

    @PostMapping("/admin/channel/mod")
    public String mod(@Valid Channel channel) {
        Channel mod = channelService.mod(channel);
        logger.info("modified from " + channel.toString());
        logger.info("to " + mod.toString());
        return "redirect:/admin/channel";
    }

    @GetMapping("/admin/channel/del")
    public String del(long channelNo, Model model) {
        Channel channel = channelService.getChannel(channelNo);
        model.addAttribute("channel", channel);
        return "admin/channel-remove";
    }

    @PostMapping("/admin/channel/del")
    public String del(long channelNo) {
        Channel remove = channelService.remove(channelNo);
        logger.info("removed " + remove.toString());
        return "redirect:/admin/channel";
    }

}
