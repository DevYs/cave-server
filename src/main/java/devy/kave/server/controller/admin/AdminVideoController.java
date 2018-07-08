package devy.kave.server.controller.admin;

import devy.gansme.tuna.subtitle.converter.*;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
public class AdminVideoController {

    private final Logger logger = LoggerFactory.getLogger(AdminVideoController.class);

    @Autowired
    private VideoService videoService;

    @GetMapping("/admin/contents/video/add")
    public String add(Video video, Model model) {
        model.addAttribute("contents", videoService.getContents(video.getContentsNo()));
        return "admin/video-add";
    }

    @PostMapping("/admin/contents/video/add")
    public String add(@Valid Video video, BindingResult bindingResult, @RequestParam("subtitleFile") MultipartFile subtitleFile, Model model) {

        boolean errProcSubtitle = false;
        if(!subtitleFile.isEmpty()) {
            String s = procSubtitle(subtitleFile);
            errProcSubtitle = (s == null);
            if(!errProcSubtitle) {
                video.setSubtitle(s);
            }
        }

        if(bindingResult.hasErrors() || errProcSubtitle) {
            if(errProcSubtitle) {
                model.addAttribute("errProcSubtitle", "자막 처리중 오류가 발생하였습니다.");
            }

            model.addAttribute("contents", videoService.getContents(video.getContentsNo()));
            return "admin/video-add";
        }

        boolean isAdd = videoService.add(video);
        if(isAdd) {
            logger.info("added " + video.toString());
        }
        return "redirect:/admin/contents/view?contentsNo=" + video.getContentsNo();
    }

    @GetMapping("/admin/contents/video/mod")
    public String mod(Video video, Model model) {
        Video storedVideo = videoService.getVideo(video.getVideoNo());
        model.addAttribute("video", storedVideo);
        model.addAttribute("storedVideo", storedVideo);
        return "admin/video-mod";
    }

    @PostMapping("/admin/contents/video/mod")
    public String mod(@Valid Video video, BindingResult bindingResult, @RequestParam("subtitleFile") MultipartFile subtitleFile, Model model) {

        Video storedVideo = videoService.getVideo(video.getVideoNo());

        boolean errProcSubtitle = false;
        if(!subtitleFile.isEmpty()) {
            String s = procSubtitle(subtitleFile);
            errProcSubtitle = (s == null);
            if(!errProcSubtitle) {
                video.setSubtitle(s);
            }
        } else {
            video.setSubtitle(storedVideo.getSubtitle());
        }

        if(bindingResult.hasErrors() || errProcSubtitle) {

            logger.info("has error true");

            if(errProcSubtitle) {
                model.addAttribute("errProcSubtitle", "자막 처리중 오류가 발생하였습니다.");
            }

            model.addAttribute("storedVideo", storedVideo);
            return "admin/video-mod";
        }

        Video mod = videoService.mod(video);
        logger.info("modified from " + video.toString());
        logger.info("to " + mod.toString());

        return "redirect:/admin/contents/view?contentsNo=" + mod.getContentsNo();
    }

    @GetMapping("/subtitle")
    public void subtitle(String videoNo, HttpServletResponse response) {
        Video video = videoService.getVideo(videoNo);

        if(video.getSubtitle() == null) {
            return;
        }

        try {
            String docName = URLEncoder.encode( video.getVideoNo()+ ".vtt","UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
            response.setContentType("text/vtt");
            response.setCharacterEncoding("UTF-8");
            PrintWriter txtPrinter = response.getWriter();

            String snippet = URLDecoder.decode(video.getSubtitle().replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8");
            txtPrinter.print(video.getSubtitle().replaceAll("%(?![0-9a-fA-F]{2})", "%25"));
            response.flushBuffer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/admin/contents/video/remove")
    public String remove(String videoNo, Model model) {
        model.addAttribute("video", videoService.getVideo(videoNo));
        return "admin/video-remove";
    }

    @PostMapping("/admin/contents/video/remove")
    public String remove(String videoNo) {
        Video remove = videoService.remove(videoNo);
        logger.info("removed " + remove);
        return "redirect:/admin/contents/view?contentsNo=" + remove.getContentsNo();
    }

    private String procSubtitle(MultipartFile subtitleFile) {
        SubtitleConverter subtitleConverter = new SubtitleConverter();

        try {
            Source source = subtitleConverter.convertToSource(subtitleFile.getBytes(), subtitleFile.getOriginalFilename());
            if(source.getLines().size() < 1) {
                return null;
            }

            Subtitle subtitle = subtitleConverter.convertToSubtitle(source, SubtitleType.VTT);
            if(subtitle.getSubtitleFile().getEncodingType() == EncodingType.NONE) {
                return null;
            } else if(subtitle.getSubtitleFile().getSubtitleType() == SubtitleType.NONE) {
                return null;
            } else {
                return subtitle.getContent();
            }
        } catch (IOException e) {
            return null;
        }
    }

}