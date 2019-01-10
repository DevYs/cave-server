package devy.cave.server.search.parser.daum;

import devy.cave.server.search.model.Contents;
import devy.cave.server.search.model.Person;
import devy.cave.server.search.parser.IParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DaumContentsParser implements IParser {

    private Logger logger = LoggerFactory.getLogger(DaumContentsParser.class);

    @Override
    public Object parse(Document document) {

        logger.info(document.toString());

        Contents contents = new Contents();
        String posterUrl = document.selectFirst(".img_summary").attr("src");
        String title = document.selectFirst(".tit_movie").text();
        String titleEng = document.selectFirst(".txt_origin").text();
        String releaseDate = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
        String genre = "";
        String nation = "";
        String runningTime = "";
        String age = "";
        String story = document.selectFirst(".desc_movie > p").wholeText();
        float emphGrade = Float.parseFloat(document.selectFirst(".emph_grade").text());
        String imageUrl = document.select("meta[property='og:image']").attr("content");
        List<Person> people = new ArrayList<>();

        Elements children = document.selectFirst(".movie_summary dl.list_main").children();
        for(Element child : children) {
            if(child.tagName().equals("dt")) {
                if(child.text().contains("장르")) {
                    Element element = child.nextElementSibling();
                    genre = element.text();
                    nation = element.nextElementSibling().text();
                }
            }else if(!child.hasClass("txt_main") && !child.hasClass("screen_out") && !child.hasClass("type_ellipsis")) {
                if(child.text().contains("분")) {
                    String str = child.text();
                    runningTime = str.split(",")[0];
                    age = str.split(",")[1];
                    age = age.trim();
//                    runningTime = str.substring(0, str.indexOf("분") + 1);
                }
            }
        }

        if(posterUrl.indexOf("//") == 0) {
            posterUrl = "http:" + posterUrl;
        }

        title = title.substring(0, title.indexOf("(")).trim();

        Elements pList = document.select(".type_ellipsis");
        for(Element p : pList) {
            Person person = new Person();
            if(p.text().contains("(감독)")) {
                person.setPeopleNm(p.text().replace("(감독)", "").trim());
                person.setRepRoleNm("감독");
            } else {
                person.setPeopleNm(p.text().replace("(주연)", "").trim());
                person.setRepRoleNm("주연");
            }
            people.add(person);
        }

        contents.setPosterUrl(posterUrl);
        contents.setTitle(title);
        contents.setTitleEng(titleEng);
        contents.setReleaseDate(releaseDate);
        contents.setGenre(genre);
        contents.setNation(nation);
        contents.setRunningTime(runningTime);
        contents.setStory(story);
        contents.setPeople(people);
        contents.setEmphGrade(emphGrade);
        contents.setImageUrl(imageUrl);
        contents.setAge(age);

        return contents;
    }

    @Override
    public Object parse(String html) {
        throw new UnsupportedOperationException();
    }
}
