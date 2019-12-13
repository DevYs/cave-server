package devy.cave.server.search.parser.naver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import devy.cave.server.search.model.ListItem;
import devy.cave.server.search.model.Result;
import devy.cave.server.search.model.daum.Casting;
import devy.cave.server.search.model.daum.Data;
import devy.cave.server.search.model.daum.DaumResult;
import devy.cave.server.search.model.daum.Page;
import devy.cave.server.search.parser.IParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class NaverContentsListParser implements IParser {

    private final Logger logger = LoggerFactory.getLogger(NaverContentsListParser.class);

    private final String[] tagList = {"<b>", "</b>"};

    @Override
    public Object parse(Document document) {

        Result result = new Result();
        List<ListItem> listItems = new ArrayList<>();

        Elements searchListItems = document.select(".search_list_1 li");
        for(Element searchListItem : searchListItems) {
            Elements aList = searchListItem.select(".etc a");
            String contentsId = searchListItem.select("li .result_thumb a").get(0).attr("href")
                    .replace("https://movie.naver.com/movie/bi/mi/basic.nhn?code=", "")
                    .replace("/movie/bi/mi/basic.nhn?code=", "");
            String posterUrl = searchListItem.select("li .result_thumb img").get(0).attr("src");
            String title = searchListItem.select("li dl dt a").text().replace("<strong>", "").replace("</strong>", "");

            ListItem listItem = new ListItem();
            listItem.setContentsId(contentsId);
            listItem.setPosterUrl(posterUrl);
            listItem.setTitle(title);

            for(Element a : aList) {
                boolean hasYear = -1 < a.attr("href").indexOf("year");
                boolean hasDirector = -1 < a.attr("href").indexOf("code");

                if(hasYear) {
                    listItem.setYear(a.text());
                }

                if(hasDirector) {
                    listItem.setActor(a.text());
                }
            }

            listItems.add(listItem);
        }

        result.setTotalCount(1);
        result.setItemList(listItems);
        result.setLastPage(1);
        result.setPage(1);
        result.setSizePerPage(10);

        logger.info(result.toString());

        return result;
    }

    @Override
    public Object parse(String json) {
        throw new UnsupportedOperationException();
    }

}