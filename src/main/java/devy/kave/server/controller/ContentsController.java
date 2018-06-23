package devy.kave.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentsController {

    @GetMapping("/contents")
    public String contents(@RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {

        model.addAttribute("tab", tab);

        return "contents";
    }

}
