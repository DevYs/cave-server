package devy.kave.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("test", "test");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}