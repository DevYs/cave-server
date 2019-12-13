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

        Contents contents = new Contents();
        String posterUrl = document.selectFirst("meta[property=og:image]") != null ? document.selectFirst("meta[property=og:image]").attr("content") : "";
        if(posterUrl.indexOf("//") == 0) {
            posterUrl = "http:" + posterUrl;
        }

        String title = document.selectFirst(".mv_info_area .mv_info h3.h_movie a").text();
        String titleEng = "";

        String releaseDate = "";
        String[] split = document.selectFirst(".mv_info_area .mv_info strong").attr("title").split(",");
        releaseDate = split[split.length - 1].trim();

        String genre = "";
        String nation = "";
        String runningTime = "";
        Elements mvInfoArea = document.select(".mv_info_area .mv_info .info_spec dd p span");
        for(Element mvInfo : mvInfoArea) {

            Elements tagA = mvInfo.select("a");
            if(0 < tagA.size()) {
                if(-1 < tagA.get(0).attr("href").indexOf("genre")) {
                    genre = genre.length() == 0 ? mvInfo.select("a").text() : mvInfo.select("a").text() + ", ";
                }else if(-1 < tagA.get(0).attr("href").indexOf("nation")) {
                    nation = nation.length() == 0 ? mvInfo.select("a").text() : mvInfo.select("a").text() + ", ";
                }
            } else {
                if(mvInfo.text() != null && -1 < mvInfo.text().indexOf("분")) {
                    runningTime = mvInfo.text();
                }
            }

        }

        String age = "";
        String story = document.select(".story_area .con_tx").text();
        float emphGrade = 0.0f;
        String imageUrl = document.select("meta[property='og:image']").attr("content");

        List<Person> people = new ArrayList<>();
        getDirectors(document, people);
        getActors(document, people);

        logger.info(people.toString());

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

    private void getActors(Document document, List<Person> people) {
        if(document.select(".step3").size() == 0) {
            return;
        }

        Element element = document.select(".step3").next("dd").get(0);
        Elements actors = element.select("a");
        logger.info(actors.toString());
        for(Element actor : actors) {
            if(-1 < actor.text().indexOf("더보기")) {
                continue;
            }

            actor.select("span").remove();
            Person person = new Person();
            person.setPeopleNm(actor.text());
            person.setRepRoleNm("출연");
            people.add(person);
        }
    }

    private void getDirectors(Document document, List<Person> people) {
        if(document.select(".step2").size() == 0) {
            return;
        }

        Element element = document.select(".step2").next("dd").get(0);
        Elements directors = element.select("a");
        for(Element director : directors) {
            director.select("span").remove();
            Person person = new Person();
            person.setPeopleNm(director.text());
            person.setRepRoleNm("감독");
            people.add(person);
        }
    }
}
