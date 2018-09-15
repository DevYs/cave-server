package devy.cave.server.controller;

import devy.cave.server.db.model.Video;
import devy.cave.server.db.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import subtitle.converter.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
public class SubtitleController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/subtitle")
    public void subtitle(String videoNo, @RequestParam(required = false) String type, HttpServletResponse response) {
        Video video = videoService.getVideo(videoNo);

        if(video.getSubtitle() == null) {
            return;
        }

        String subtitle = video.getSubtitle();
        SubtitleType subtitleType = SubtitleType.VTT;

        if(type != null && !type.isEmpty()) {
            SubtitleFile subtitleFile = new SubtitleFile(SubtitleType.VTT, EncodingType.UTF8);
            Subtitle origin = new Subtitle(subtitleFile, video.getSubtitle());

            VttSubtitleConverter vttSubtitleConverter = new VttSubtitleConverter();
            Source source = vttSubtitleConverter.convertToSource(origin);
            SubtitleConverter converter = new SubtitleConverter();

            if(SubtitleType.SMI.extension().contains(type)) {
                subtitle = converter.convertToSubtitle(source, SubtitleType.SMI).getContent();
                subtitleType = SubtitleType.SMI;
            } else if(SubtitleType.SRT.extension().contains(type)) {
                subtitle = converter.convertToSubtitle(source, SubtitleType.SRT).getContent();
                subtitleType = SubtitleType.SRT;
            }
        }

        try {
            String docName = URLEncoder.encode( video.getVideoNo() + "." + subtitleType.extension(),"UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
            response.setContentType("text/" + subtitleType.extension());
            response.setCharacterEncoding("UTF-8");
            PrintWriter txtPrinter = response.getWriter();

            String snippet = URLDecoder.decode(subtitle.replaceAll("%(?![0-9a-fA-F]{2})", "%25"), "UTF-8");
            txtPrinter.print(subtitle.replaceAll("%(?![0-9a-fA-F]{2})", "%25"));
            response.flushBuffer();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
