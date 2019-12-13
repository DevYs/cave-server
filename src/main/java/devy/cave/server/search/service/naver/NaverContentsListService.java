package devy.cave.server.search.service.naver;

import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.parser.naver.NaverContentsListParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

@Service
public class NaverContentsListService implements IService {

    private final int PAGE_SIZE = 10;
    private final String TAG_SIZE = "{size}";
    private final String TAG_SEARCH_TEXT = "{searchText}";
    private final String TAG_PAGE = "{page}";

    @Autowired
    private DocumentParser documentParser;

    public Object requestContentsList(String searchText, int page) {
        searchText = searchText.replace(" ", "%20");
        String url = url()
                .replace(TAG_SEARCH_TEXT, searchText)
                .replace(TAG_PAGE, String.valueOf(page))
                .replace(TAG_SIZE, String.valueOf(PAGE_SIZE));
        return documentParser.parseHtml(url, new NaverContentsListParser());
    }

    @Override
    public String url() {
        return "https://movie.naver.com/movie/search/result.nhn?section=movie&query=" + TAG_SEARCH_TEXT + "&ie=utf8";
    }
}
