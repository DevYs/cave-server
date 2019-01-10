package devy.cave.server.search.service.kobis;

import devy.cave.server.search.model.ListItem;
import devy.cave.server.search.parser.ContentsListParser;
import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
public class KobisContentsListService implements IService {

    private final String TAG_SEARCH_TEXT = "{searchText}";
    private final String TAG_PAGE = "{page}";

    @Autowired
    private DocumentParser documentParser;

    public List<ListItem> requestContentsList(String searchText, int page) {
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("sMovName", searchText);
        param.add("curPage", String.valueOf(page));
        param.add("useYn", "Y");
        param.add("sMultiChk", "YYY");

        return (List<ListItem>) documentParser.parse(url(), param, new ContentsListParser());
    }

    @Override
    public String url() {
        return "http://www.kobis.or.kr/kobis/business/mast/mvie/searchMovieList.do";
    }
}