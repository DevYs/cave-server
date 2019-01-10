package devy.cave.server.search.parser.daum;

import devy.cave.server.search.parser.IParser;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaumVideoParser implements IParser {

    private Logger logger = LoggerFactory.getLogger(DaumVideoParser.class);

    private final int VALUE = 1;
    private final int INDEX_MOVIE_ID = 0;
    private final int INDEX_V_CLIP_ID = 1;

    @Override
    public Object parse(Document document) {
        logger.info(document.toString());
        return document.toString();
    }

    @Override
    public Object parse(String html) {
        throw new UnsupportedOperationException();
    }
}
