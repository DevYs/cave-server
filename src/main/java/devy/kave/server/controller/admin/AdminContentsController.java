package devy.kave.server.controller.admin;

import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;

@Controller
public class AdminContentsController {

    private final Logger logger = LoggerFactory.getLogger(AdminContentsController.class);

    @Autowired
    private ContentsService contentsService;

    @GetMapping("/admin/contents")
    public String contents(Model model) {
        model.addAttribute("contentsList", contentsService.contentsList());
        return "admin/contents";
    }

    @PostMapping("/admin/contents")
    public String contents(@RequestParam(name = "channelNo", defaultValue = "0", required = false) long channelNo, String searchWord, Model model) {
        return "admin/contents";
    }

    @GetMapping("/admin/contents/view")
    public String view(@RequestParam(value = "tab", defaultValue = "cont") String tab, long contentsNo, Model model) {
        model.addAttribute("videoList", contentsService.videoList(contentsNo));
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        model.addAttribute("tab", tab);
        return "admin/contents-view";
    }

    @GetMapping("/admin/contents/add")
    public String add(Model model) {
        return "admin/contents-add";
    }

    @PostMapping("/admin/contents/add")
    public String add(Contents contents) {
        boolean isAdded = contentsService.add(contents);
        if(isAdded) {
            logger.info("added " + contents.toString());
        }
        return "redirect:/admin/contents";
    }

    @GetMapping("/admin/contents/mod")
    public String mod(long contentsNo, Model model) {
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        return "admin/contents-mod";
    }

    @PostMapping("/admin/contents/mod")
    public String mod(Contents contents) {
        Contents mod = contentsService.mod(contents);
        logger.info("modified from " + contents.toString());
        logger.info("to " + mod.toString());
        return "redirect:/admin/contents";
    }

    @GetMapping("/admin/contents/remove")
    public String remove(long contentsNo, Model model) {
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        return "admin/contents-remove";
    }

    @PostMapping("/admin/contents/remove")
    public String remove(long contentsNo) {
        Contents remove = contentsService.remove(contentsNo);
        logger.info("removed " + remove);
        return "redirect:/admin/contents";
    }

}