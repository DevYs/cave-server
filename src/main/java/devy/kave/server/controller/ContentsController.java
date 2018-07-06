package devy.kave.server.controller;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class ContentsController {

    @Autowired
    private ContentsService contentsService;

    @Autowired
    private VideoService videoService;

    @GetMapping("/contents")
    public String contents(String contentsNo, @RequestParam(value = "tab", defaultValue = "cont") String tab, Model model) {
        Collection<Video> videoList = contentsService.videoList(contentsNo);
        Contents contents = contentsService.getContents(contentsNo);

        model.addAttribute("tab", tab);
        model.addAttribute("contents", contents);
        model.addAttribute("videoList", videoList.iterator());
        model.addAttribute("videoSize", videoList.size());

        return "contents";
    }

}
