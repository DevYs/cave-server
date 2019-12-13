package devy.cave.server.search.service.naver;

import devy.cave.server.search.model.Contents;
import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.parser.daum.DaumContentsParser;
import devy.cave.server.search.parser.naver.NaverContentsParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverContentsService implements IService {

    private final String TAG_MOVIE_ID = "{movieId}";

    @Autowired
    private DocumentParser documentParser;

    public Contents requestContents(String contentsId) {
        String url = url().replace(TAG_MOVIE_ID, contentsId);
        return (Contents) documentParser.parseHtml(url, new NaverContentsParser());
    }

    @Override
    public String url() {
        return "https://movie.naver.com/movie/bi/mi/basic.nhn?code={movieId}";
    }
}
