package devy.cave.server.search.controller;

import devy.cave.server.search.model.ClipVideo;
import devy.cave.server.search.model.Contents;
import devy.cave.server.search.service.daum.DaumContentsService;
import devy.cave.server.search.service.daum.DaumVideoService;
import devy.cave.server.search.service.naver.NaverContentsListService;
import devy.cave.server.search.service.naver.NaverContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Controller
public class MovieSearchController {

    private final Logger logger = LoggerFactory.getLogger(MovieSearchController.class);

    @Autowired
    private NaverContentsListService contentsListService;

    @Autowired
    private NaverContentsService contentsService;

    @Autowired
    private DaumVideoService videoService;

//    @PostMapping("/api/contents/list")
    @RequestMapping(value = "/api/contents/list", method = RequestMethod.POST)
    @ResponseBody
    public Object contentsList(@RequestParam(name = "searchText") String searchText, @RequestParam(name ="page", defaultValue = "1", required = false) int page) {
        logger.info("search text : " + searchText + ", page : " + page);
        return contentsListService.requestContentsList(searchText, page);
    }

//    @PostMapping("/api/contents")
    @RequestMapping(value = "/api/contents", method = RequestMethod.POST)
    @ResponseBody
    public Object contents(String contentsId) {
        logger.info("contentsId : " + contentsId);
        Contents contents = contentsService.requestContents(contentsId);
//        ClipVideo clipVideo = videoService.requestVideoPage(contentsId);
//        contents.setClipVideo(clipVideo);
        return contents;
    }

//    @PostMapping("/api/video")
    @RequestMapping(value = "/api/video", method = RequestMethod.POST)
    @ResponseBody
    public Object video(String contentsId) {
        logger.info("contentsId : " + contentsId);

        ClipVideo clipVideo = videoService.requestVideoPage(contentsId);

        logger.info("clipVideo " + clipVideo.toString());
//        ClipVideo clipVideo = videoService.requestVideo(contentsId);


        return clipVideo;
    }

}
