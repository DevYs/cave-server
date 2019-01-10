package devy.cave.server.search.service.daum;

import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.parser.daum.DaumContentsListParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DaumContentsListService implements IService {

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
        return documentParser.parseJson(url, new DaumContentsListParser());
    }

    @Override
    public String url() {
        return "https://movie.daum.net/data/movie/search/v2/movie.json?size={size}&searchText={searchText}&start={page}";
    }
}
