package devy.kave.server.controller;

import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentsController {

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private VideoService videoService;

    @GetMapping("/contents")
    public String contents(String contentsNo, @RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        model.addAttribute("tab", tab);
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        model.addAttribute("videoList", contentsService.videoList(contentsNo));
        return "contents";
    }

}
