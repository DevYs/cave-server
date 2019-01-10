package devy.cave.server.search.parser.daum;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaumContentsListParser implements IParser {

    private final Logger logger = LoggerFactory.getLogger(DaumContentsListParser.class);

    private final String[] tagList = {"<b>", "</b>"};

    @Override
    public Object parse(Document document) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object parse(String json) {
        if(json.indexOf("null") == 0) {
            json = json.replaceFirst("null", "");
        }

        for(int i=0; i<tagList.length; i++) {
            json = json.replaceAll(tagList[i], "");
        }

        logger.info(json);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        DaumResult daumResult = null;
        try {
            jsonNode = objectMapper.readTree(json);
            daumResult = objectMapper.treeToValue(jsonNode, DaumResult.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Page p = daumResult.getPage();
        int totalCount = p.getTotalCount() != null ? Integer.valueOf(p.getTotalCount()) : 0;
        int sizePerPage = p.getPageSize() != null ? Integer.valueOf(p.getPageSize()) : 0;
        int lastPage = p.getLast() != null ? Integer.valueOf(p.getLast()) : 0;
        int page = p.getCurrent() != null ? Integer.valueOf(p.getCurrent()) : 0;

        List<ListItem> listItems = new ArrayList<>();

        Result result = new Result();
        result.setTotalCount(daumResult.getCount());
        result.setSizePerPage(sizePerPage);
        result.setTotalCount(totalCount);
        result.setLastPage(lastPage);
        result.setPage(page);

        List<Data> dataList = daumResult.getData();
        for(Data data : dataList) {
            ListItem listItem = new ListItem();
            listItem.setContentsId(data.getMovieId());
            listItem.setGenre(data.getGenres());
            listItem.setTitle(data.getTitleKo());
            listItem.setTitleEng(data.getTitleEn());
            listItem.setNation(data.getCountries());
            listItem.setYear(data.getProdYear());
            listItem.setPosterUrl(data.getPhoto().getFullname());
            List<Casting> castingList = data.getCasting();

            if(castingList != null) {
                StringBuilder actor = new StringBuilder();
                for(int castingIndex=0; castingIndex<castingList.size(); castingIndex++) {
                    String nameKo = castingList.get(castingIndex).getNameKo();

                    if(0 < castingIndex) {
                        actor.append(", " + nameKo);
                    } else {
                        actor.append(nameKo);
                    }
                }
                listItem.setActor(actor.toString());
            }

            listItems.add(listItem);
        }

        result.setItemList(listItems);

        return result;
    }

}