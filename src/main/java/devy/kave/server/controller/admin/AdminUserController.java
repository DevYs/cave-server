package devy.kave.server.controller.admin;

import devy.kave.server.db.model.User;
import devy.kave.server.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminUserController {

    private final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String user(@RequestParam(defaultValue = "") String searchWord, Model model) {
        model.addAttribute("userList", userService.userList(1, searchWord));
        model.addAttribute("searchWord", searchWord);
        return "admin/user";
    }

    @RequestMapping(value = "/api/admin/user", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List user(@RequestParam(defaultValue = "1") int pageNo, String searchWord) {
        return userService.userList(pageNo, searchWord);
    }

    @GetMapping("/admin/user/add")
    public String add(User user) {
        return "admin/user-add";
    }

    @PostMapping("/admin/user/add")
    public String add(@Valid User user, BindingResult bindingResult, Model model) {

        UserDetails userDetails = userService.loadUserByUsername(user.getUserId());
        boolean isNewUser = userDetails.getUsername().equals("anonymous");
        if(bindingResult.hasErrors() || !isNewUser) {
            if(!isNewUser) {
                model.addAttribute("existUser", "이미 사용중인 아이디입니다.");
            }
            return "admin/user-add";
        }

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
    public String mod(@Valid User user, BindingResult bindingResult, String newPassword, Model model) {

        if(30 < newPassword.length()) {
            model.addAttribute("errorNewPassword", "반드시 최대값 30이하의 크기이어야 합니다.");
            return "admin/user-mod";
        }

        if(bindingResult.hasErrors()) {
            return "admin/user-mod";
        }

        User mod = userService.mod(user.setNewPassword(newPassword));
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