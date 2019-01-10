package devy.cave.server.search.service.daum;

import devy.cave.server.search.model.ClipVideo;
import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.parser.daum.DaumVideoPageParser;
import devy.cave.server.search.parser.daum.DaumVideoParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DaumVideoService implements IService {

    private final Logger logger = Logger.getLogger(DaumVideoService.class.getSimpleName());

    private final String TAG_VIDEO_KEY = "{videoKey}";
    private final String TAG_MOVIE_ID = "{movieId}";

    private final String URL_VIDEO_PAGE_URL = "https://movie.daum.net/moviedb/video?id=" + TAG_MOVIE_ID;
    private final String URL_VIDEO_URL = "https://kakaotv.daum.net/embed/player/cliplink/" + TAG_VIDEO_KEY + "?service=daum_movie&autoplay=true&profile=HIGH&playsinline=true";

    @Autowired
    private DocumentParser documentParser;

    public ClipVideo requestVideoPage(String contentsId) {
        String url = URL_VIDEO_PAGE_URL.replace(TAG_MOVIE_ID, contentsId);
        logger.info("URL_VIDEO_PAGE_URL " + url);
        return (ClipVideo) documentParser.parseHtml(url, new DaumVideoPageParser());
    }

    public ClipVideo requestVideo(String contentsId) {

        ClipVideo clipVideo = requestVideoPage(contentsId);
        String url = URL_VIDEO_URL.replace(TAG_VIDEO_KEY, clipVideo.getVideoKey() + "@my");
        logger.info("URL_VIDEO_URL " + url);

        String clipVideoUrl = (String) documentParser.parseHtml(url, new DaumVideoParser());
        clipVideo.setClipVideoUrl(clipVideoUrl);
        return clipVideo;

    }

    @Override
    public String url() {
        return "https://movie.daum.net/moviedb/video?id={movieId}";
    }
}
