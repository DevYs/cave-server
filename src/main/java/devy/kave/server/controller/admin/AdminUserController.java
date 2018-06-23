package devy.kave.server.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {

    @GetMapping("/admin/user")
    public String user() {
        return "admin/user";
    }

    @GetMapping("/admin/user/add")
    public String add() {
        return "admin/user-add";
    }

    @GetMapping("/admin/user/mod")
    public String mod() {
        return "admin/user-mod";
    }

    @GetMapping("/admin/user/del")
    public String del() {
        return "admin/user-del";
    }

}