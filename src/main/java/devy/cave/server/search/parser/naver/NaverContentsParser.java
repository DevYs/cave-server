package devy.cave.server.search.parser.naver;

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

public class NaverContentsParser implements IParser {

    private Logger logger = LoggerFactory.getLogger(NaverContentsParser.class);

    @Override
    public Object parse(Document document) {

        logger.info(document.toString());

        Contents contents = new Contents();
        String posterUrl = document.selectFirst(".poster a img").attr("src");
        String title = document.selectFirst(".mv_info_area .mv_info h3.h_movie a").attr("src");
        String titleEng = document.selectFirst(".mv_info_area .mv_info strong").text().split(",")[0].trim();
        String releaseDate = document.selectFirst(".mv_info_area .mv_info strong").text().split(",")[1].trim();
        String genre = document.select(".mv_info_area .mv_info .info_spec dd").get(0).select("p span").get(0).text();
        String nation = document.select(".mv_info_area .mv_info .info_spec dd").get(0).select("p span").get(1).text();
        String runningTime = document.select(".mv_info_area .mv_info .info_spec dd").get(0).select("p span").get(2).text();
        String age = "";
        String story = document.select(".story_area .con_tx").text();
        float emphGrade = 0.0f;
        String imageUrl = document.select("meta[property='og:image']").attr("content");

        List<Person> people = new ArrayList<>();
        document.select(".mv_info_area .mv_info .info_spec dd").get(1);

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
