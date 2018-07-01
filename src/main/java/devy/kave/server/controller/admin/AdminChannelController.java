package devy.kave.server.controller.admin;

import devy.kave.server.db.model.Channel;
import devy.kave.server.db.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminChannelController {

    private Logger logger = LoggerFactory.getLogger(AdminChannelController.class);

    @Autowired
    private ChannelService channelService;

    @GetMapping("/admin/channel")
    public String channel(Model model) {
        return "admin/channel";
    }

    @GetMapping("/admin/channel/add")
    public String add() {
        return "admin/channel-add";
    }

    @PostMapping("/admin/channel/add")
    public String add(Channel channel) {
        channelService.add(channel);
        return "redirect:/admin/channel";
    }

    @GetMapping("/admin/channel/mod")
    public String mod(long channelNo, Model model) {
        Channel channel = channelService.getChannel(channelNo);
        model.addAttribute("channel", channel);
        return "admin/channel-mod";
    }

    @PostMapping("/admin/channel/mod")
    public String mod(Channel channel) {
        channelService.modify(channel);
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
        channelService.remove(channelNo);
        return "redirect:/admin/channel";
    }

}
