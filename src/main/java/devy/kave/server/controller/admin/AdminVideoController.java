package devy.kave.server.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminVideoController {

    @GetMapping("/admin/contents/video/add")
    public String add() {
        return "admin/video-add";
    }

    @GetMapping("/admin/contents/video/mod")
    public String mod() {
        return "admin/video-mod";
    }

    @GetMapping("/admin/contents/video/del")
    public String del() {
        return "admin/video-del";
    }

}