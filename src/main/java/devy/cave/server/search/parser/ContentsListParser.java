package devy.cave.server.search.parser;

import devy.cave.server.search.model.ListItem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ContentsListParser implements IParser {

    private final String TAG_NAME           = "ellipsis";
    private final String LIST_SELECTOR      = "table.boardList03 tbody tr";

    private final int TITLE_INDEX = 0;
    private final int TITLE_ENG_INDEX = 1;
    private final int YEAR_INDEX = 2;
    private final int NATION_INDEX = 3;
    private final int GENRE_INDEX = 5;
    private final int DIRECTOR_INDEX = 7;

    @Override
    public List<ListItem> parse(Document document) {

        if(document == null) {
            return null;
        }

        List<ListItem> itemList = new ArrayList<>();
        Elements list = document.select(LIST_SELECTOR);
        for(Element item : list) {
            Elements ellipsisList = item.getElementsByClass(TAG_NAME);
            String code     = ellipsisList.get(TITLE_INDEX).selectFirst("a").attr("onclick");
            code = code.split(",")[1].split("\\)")[0].replace("'", "");
            String title    = ellipsisList.get(TITLE_INDEX).attr("title");
            String titleEng = ellipsisList.get(TITLE_ENG_INDEX).attr("title");
            String year     = ellipsisList.get(YEAR_INDEX).attr("title");
            String nation   = ellipsisList.get(NATION_INDEX).attr("title");
            String genre    = ellipsisList.get(GENRE_INDEX).attr("title");
            String director = ellipsisList.get(DIRECTOR_INDEX).attr("title");

            ListItem listItem = new ListItem();
            listItem.setContentsId(code);
            listItem.setTitle(title);
            listItem.setTitleEng(titleEng);
            listItem.setYear(year);
            listItem.setNation(nation);
            listItem.setGenre(genre);
            listItem.setDirector(director);
            itemList.add(listItem);
        }

        return itemList;
    }

    @Override
    public Object parse(String json) {
        throw new UnsupportedOperationException();
    }

}