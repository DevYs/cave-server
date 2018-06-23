package devy.kave.server.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminChannelController {

    @GetMapping("/admin/channel")
    public String channel() {
        return "admin/channel";
    }

    @GetMapping("/admin/channel/add")
    public String add() {
        return "admin/channel-add";
    }

    @GetMapping("/admin/channel/mod")
    public String mod() {
        return "admin/channel-mod";
    }

    @GetMapping("/admin/channel/del")
    public String del() {
        return "admin/channel-del";
    }

}
