package devy.cave.server.search.parser;

import org.jsoup.nodes.Document;

public interface IParser {

    Object parse(Document document);

    Object parse(String json);

}
