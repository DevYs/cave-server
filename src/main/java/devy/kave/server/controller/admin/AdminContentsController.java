package devy.kave.server.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminContentsController {

    @GetMapping("/admin/contents")
    public String contents() {
        return "admin/contents";
    }

    @GetMapping("/admin/contents/view")
    public String view(@RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        model.addAttribute("tab", tab);
        return "admin/contents-view";
    }

    @GetMapping("/admin/contents/add")
    public String add() {
        return "admin/contents-add";
    }

    @GetMapping("/admin/contents/mod")
    public String mod() {
        return "admin/contents-mod";
    }

    @GetMapping("/admin/contents/del")
    public String del() {
        return "admin/contents-del";
    }

}
