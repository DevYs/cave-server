package devy.cave.server.search.service.kobis;

import devy.cave.server.search.model.Contents;
import devy.cave.server.search.parser.ContentsParser;
import devy.cave.server.search.parser.DocumentParser;
import devy.cave.server.search.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class KobisContentsService implements IService {

    @Autowired
    private DocumentParser documentParser;

    public Contents requestContents(String contentsId) {
        ContentsParser contentsParser = new ContentsParser();
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("type", "");
        param.add("code", contentsId);
        param.add("sType", "");
        param.add("titleYN", "Y");
        param.add("etcParam", "");
        param.add("isOuterReq", "true");

        return (Contents) documentParser.parse(url(), param, contentsParser);
    }


    @Override
    public String url() {
        return "http://www.kobis.or.kr/kobis/business/mast/mvie/searchMovieDtl.do";
    }
}
