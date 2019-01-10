package devy.cave.server.search.parser.daum;

import devy.cave.server.search.model.ClipVideo;
import devy.cave.server.search.parser.IParser;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaumVideoPageParser implements IParser {

    private Logger logger = LoggerFactory.getLogger(DaumVideoPageParser.class);

    private final int VALUE = 1;
    private final int INDEX_MOVIE_ID = 0;
    private final int INDEX_V_CLIP_ID = 1;

    @Override
    public Object parse(Document document) {

        logger.info(document.toString());

        logger.info(document.select("meta[name='plink']")
                .attr("content")
                .replace("http://movie.daum.net/moviedb/clipVideo?", ""));

        String[] pLink = document.select("meta[name='plink']")
                .attr("content")
                .replace("http://movie.daum.net/moviedb/clipVideo?", "")
                .split("&");
        String movieId = pLink[INDEX_MOVIE_ID].split("=")[VALUE];
        String vClipId = pLink[INDEX_V_CLIP_ID].split("=")[VALUE];
        String clipImage = document.select("meta[property='og:image']").attr("content");
        String clipImageAlt = document.select("meta[property='og:title']").attr("content");
        String videoKey = clipImage
                .replace("http://t1.daumcdn.net/tvpot/thumb/", "")
                .replace("/thumb.png", "")
                .split("\\?")[0];

        ClipVideo clipVideo = new ClipVideo();
        clipVideo.setMovieId(movieId);
        clipVideo.setClipId(vClipId);
        clipVideo.setClipImage(clipImage);
        clipVideo.setClipImageAlt(clipImageAlt);
        clipVideo.setVideoKey(videoKey);
        clipVideo.setHasVideo(!videoKey.contains("http"));

        return clipVideo;
    }

    @Override
    public Object parse(String html) {
        throw new UnsupportedOperationException();
    }
}
