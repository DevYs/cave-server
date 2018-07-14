package devy.cave.server.controller.config;

import devy.cave.server.controller.config.param.NewPassword;
import devy.cave.server.db.model.User;
import devy.cave.server.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PasswordController {

    private final Logger logger = LoggerFactory.getLogger(PasswordController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/config/password/change")
    public String mod(NewPassword newPassword) {
        return "config/password-change";
    }

    @PostMapping("/config/password/change")
    public String mod(Principal principal, @Valid NewPassword newPassword, BindingResult bindingResult, Model model) {

        boolean isError = false;
        if(newPassword.equals()) {
            isError = true;
            model.addAttribute("errorPassword", "현재 비밀번호와 다른 새로운 비밀번호를 입력하세요.");
        }

        User user = userService.isValidPasswordAndUser(principal.getName(), newPassword.getInputPassword());
        if(user == null) {
            isError = true;
            model.addAttribute("errorPassword", "현재 비밀번호가 유효하지 않습니다.");
        }

        if(bindingResult.hasErrors() || isError) {
            return "config/password-change";
        }

        user.setPassword(newPassword.getNewPassword());
        User modifiedUser = userService.mod(user);
        logger.info("modified " + modifiedUser.toString());

        return "redirect:/index";
    }

}
