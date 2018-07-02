package devy.kave.server.controller.admin;

import devy.gansme.tuna.subtitle.converter.Source;
import devy.gansme.tuna.subtitle.converter.Subtitle;
import devy.gansme.tuna.subtitle.converter.SubtitleConverter;
import devy.gansme.tuna.subtitle.converter.SubtitleType;
import devy.kave.server.db.model.Video;
import devy.kave.server.db.service.ContentsService;
import devy.kave.server.db.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    public String add(long contentsNo, Model model) {
        model.addAttribute("contents", videoService.getContents(contentsNo));
        return "admin/video-add";
    }

    @PostMapping("/admin/contents/video/add")
    public String add(Video video, @RequestParam("subtitleFile") MultipartFile subtitleFile) {
        if(!subtitleFile.isEmpty()) {
            SubtitleConverter subtitleConverter = new SubtitleConverter();

            try {
                Source source = subtitleConverter.convertToSource(subtitleFile.getBytes(), subtitleFile.getOriginalFilename());
                Subtitle subtitle = subtitleConverter.convertToSubtitle(source, SubtitleType.VTT);
                video.setSubtitle(subtitle.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean isAdd = videoService.add(video);
        if(isAdd) {
            logger.info("added " + video.toString());
        }
        return "redirect:/admin/contents/view?contentsNo=" + video.getContentsNo();
    }

    @GetMapping("/admin/contents/video/mod")
    public String mod(long videoNo, Model model) {
        model.addAttribute("video", videoService.getVideo(videoNo));
        return "admin/video-mod";
    }

    @PostMapping("/admin/contents/video/mod")
    public String mod(Video video, @RequestParam("subtitleFile") MultipartFile subtitleFile) {
        if(!subtitleFile.isEmpty()) {
            SubtitleConverter subtitleConverter = new SubtitleConverter();

            try {
                Source source = subtitleConverter.convertToSource(subtitleFile.getBytes(), subtitleFile.getOriginalFilename());
                Subtitle subtitle = subtitleConverter.convertToSubtitle(source, SubtitleType.VTT);
                video.setSubtitle(subtitle.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Video mod = videoService.mod(video);
        logger.info("modified from " + video.toString());
        logger.info("to " + mod.toString());

        return "redirect:/admin/contents/view?contentsNo=" + mod.getContentsNo();
    }

    @GetMapping("/subtitle")
    public void subtitle(long videoNo, HttpServletResponse response) {
        Video video = videoService.getVideo(videoNo);

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
    public String remove(long videoNo, Model model) {
        model.addAttribute("video", videoService.getVideo(videoNo));
        return "admin/video-remove";
    }

    @PostMapping("/admin/contents/video/remove")
    public String remove(long videoNo) {
        Video remove = videoService.remove(videoNo);
        logger.info("removed " + remove);
        return "redirect:/admin/contents/view?contentsNo=" + remove.getContentsNo();
    }

}