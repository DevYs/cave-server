package devy.kave.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayController {

    @GetMapping("/play")
    public String play(Model model) {
        return "play";
    }

}
