package devy.kave.server.controller.admin;

import devy.kave.server.db.model.User;
import devy.kave.server.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ShutdownController {

    private final Logger logger = LoggerFactory.getLogger(ShutdownController.class);

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private ExitCodeGenerator exitCodeGenerator;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/shutdown")
    public String shutdown() {
        return "admin/shutdown";
    }

    @PostMapping("/admin/shutdown")
    public String shutdown(Principal principal, String password, Model model) {
        User user = userService.isValidPasswordAndUser(principal.getName(), password);

        if(user == null) {
            model.addAttribute("errorPassword", "비밀번호가 유효하지 않습니다.");
            return "admin/shutdown";
        }

        int exit = SpringApplication.exit(applicationContext, exitCodeGenerator);
        logger.info("Shutdown ... exit code " + exit);

        return null;
    }

}
