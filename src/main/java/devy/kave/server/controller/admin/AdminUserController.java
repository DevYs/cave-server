package devy.kave.server.controller.admin;

import devy.kave.server.db.model.User;
import devy.kave.server.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminUserController {

    private final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String user(Model model) {
        model.addAttribute("userList", userService.userList());
        return "admin/user";
    }

    @GetMapping("/admin/user/add")
    public String add() {
        return "admin/user-add";
    }

    @PostMapping("/admin/user/add")
    public String add(User user) {
        boolean isAdded = userService.add(user);
        if(isAdded) {
            logger.info("Added " + user.toString());
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/mod")
    public String mod(long userNo, Model model) {
        model.addAttribute("user", userService.getUser(userNo));
        return "admin/user-mod";
    }

    @PostMapping("/admin/user/mod")
    public String mod(User user) {
        User mod = userService.mod(user);
        logger.info("modified from " + user.toString());
        logger.info("to " + mod.toString());
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/remove")
    public String remove(long userNo, Model model) {
        model.addAttribute("user", userService.getUser(userNo));
        return "admin/user-remove";
    }

    @PostMapping("/admin/user/remove")
    public String remove(long userNo) {
        User remove = userService.remove(userNo);
        logger.info("removed " + remove.toString());
        return "redirect:/admin/user";
    }

}