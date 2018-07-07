package devy.kave.server.controller.admin;

import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.service.ContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminContentsController {

    private final Logger logger = LoggerFactory.getLogger(AdminContentsController.class);

    @Autowired
    private ContentsService contentsService;

    @GetMapping("/admin/contents")
    public String contents(@RequestParam(defaultValue = "1", required = false) int pageNo, @RequestParam(defaultValue = "", required = false) String searchWord, @RequestParam(defaultValue = "0", required = false) String channelNo, Model model) {
        model.addAttribute("contentsList", contentsService.contentsList(pageNo, searchWord, channelNo));
        model.addAttribute("channelNo", channelNo);
        model.addAttribute("searchWord", searchWord);
        return "admin/contents";
    }

    @RequestMapping(value = "/api/admin/contents", method = RequestMethod.GET)
    @ResponseBody
    public List<Contents> contents(@RequestParam(defaultValue = "1", required = false) int pageNo, @RequestParam(defaultValue = "", required = false) String searchWord, @RequestParam(defaultValue = "0", required = false) String channelNo) {
        logger.info("pageNo : " + pageNo + ", searchWord : " + searchWord + ", channelNo : " + channelNo);
        return contentsService.contentsList(pageNo, searchWord, channelNo);
    }

    @GetMapping("/admin/contents/view")
    public String view(@RequestParam(value = "tab", defaultValue = "cont") String tab, String contentsNo, Model model) {
        model.addAttribute("videoList", contentsService.videoList(contentsNo).iterator());
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        model.addAttribute("tab", tab);
        return "admin/contents-view";
    }

    @GetMapping("/admin/contents/add")
    public String add(Contents contents, Model model) {
        return "admin/contents-add";
    }

    @PostMapping("/admin/contents/add")
    public String add(@Valid Contents contents, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "admin/contents-add";
        }

        boolean isAdded = contentsService.add(contents);
        if(isAdded) {
            logger.info("added " + contents.toString());
        }
        return "redirect:/admin/contents";
    }

    @GetMapping("/admin/contents/mod")
    public String mod(String contentsNo, Model model) {
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        return "admin/contents-mod";
    }

    @PostMapping("/admin/contents/mod")
    public String mod(@Valid Contents contents, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "admin/contents-mod";
        }

        Contents mod = contentsService.mod(contents);
        logger.info("modified from " + contents.toString());
        logger.info("to " + mod.toString());
        return "redirect:/admin/contents";
    }

    @GetMapping("/admin/contents/remove")
    public String remove(String contentsNo, Model model) {
        model.addAttribute("contents", contentsService.getContents(contentsNo));
        return "admin/contents-remove";
    }

    @PostMapping("/admin/contents/remove")
    public String remove(String contentsNo) {
        Contents remove = contentsService.remove(contentsNo);
        logger.info("removed " + remove);
        return "redirect:/admin/contents";
    }

    @GetMapping("/admin/contents/data")
    public String data() {
        String[] a = {"20180707091229904", "20180707091234599", "20180707091241303", "20180707091249074", "20180707091252737"};
        String[] b = {"a", "b", "c", "d", "e", "f", "g", "가", "나", "다", "참", "햄", "피", "라", "치"};

        Contents contents = contentsService.getContents("20180707091441554");
        for(int i=0; i<150; i++) {
            Contents c = new Contents();
            c.setChannelNo(a[i/30]);
            c.setContentsNo(DatabaseKeyCreator.createKey());
            c.setContentsName(b[i/10] + contents.getContentsName() + (i+1));
            c.setStory(contents.getStory());
            c.setActor(contents.getActor());
            c.setContentsPosterUrl(contents.getContentsPosterUrl());
            c.setDirector(contents.getDirector());
            c.setNation(contents.getNation());
            c.setGenre(contents.getGenre());
            c.setReleaseDate(contents.getReleaseDate());
            c.setRunningTime(contents.getRunningTime());
            boolean add = contentsService.add(c);
            if(add) {
                logger.info(c.toString());
            }
        }

        return "redirect:/admin/contents";
    }


}