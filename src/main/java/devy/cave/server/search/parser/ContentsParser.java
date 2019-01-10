package devy.cave.server.search.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import devy.cave.server.search.model.Contents;
import devy.cave.server.search.model.Person;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContentsParser implements IParser {

    private final Logger logger = LoggerFactory.getLogger(ContentsParser.class);

    private final String URL_ACTOR = "http://www.kobis.or.kr/kobis/business/mast/mvie/searchMovActorLists.do";
    private final String URL_STAFF = "http://www.kobis.or.kr/kobis/business/mast/mvie/searchMovStaffLists.do";

    private final Type type = new TypeToken<ArrayList<Person>>(){}.getType();
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new GsonBuilder().create();

    @Override
    public Contents parse(Document document) {
        if(document == null) {
            return null;
        }

        Elements basicInfoList = document.select(".basicInfo dl dd");
        String code = checkNull(basicInfoList.get(0).select("ul li").get(0));
        String posterUrl = "http://www.kobis.or.kr" + document.selectFirst(".mvie_thumb").attr("src");
        String title = checkNull(document.selectFirst(".w80pB strong"));
        String titleEng = checkNull(document.selectFirst(".w80pB"));
        String genre = checkNull(basicInfoList.get(2).select("ul li").get(2));
        String runningTime = checkNull(basicInfoList.get(2).select("ul li").get(3));
        String nation = checkNull(basicInfoList.get(2).select("ul li.last-child").get(0));
        String releaseDate;

        if(0 < basicInfoList.get(3).select("ul li").size()) {
            releaseDate = checkNull(basicInfoList.get(3).select("ul li").get(0));
        } else {
            releaseDate = checkNull(basicInfoList.get(4).select("ul li").get(0));
        }

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("movieCd", code);
        param.add("mgmtMore", "N");

        List<Person> people = new ArrayList<>();
        people.addAll(parseActor(document, param));
        people.addAll(parseStaff(document, param));

        // 줄거리 태그가 아예 없을 수 있음.
        boolean hasStory = 0 < document.getElementsByClass("contentBreak").size();
        String story = hasStory ? checkNull(document.getElementsByClass("contentBreak").get(0)) : "";

        // 영문 제목이 있을 경우 괄호 안의 영문 제목만 가져온다.
        // 영문 제목이 없다면 길이가 0인 빈 문자열을 값으로 한다.
        if (-1 < titleEng.indexOf("(")) {
            int start = titleEng.indexOf("(") + 1;
            int end = titleEng.indexOf(")");
            titleEng = titleEng.substring(start, end);
        } else {
            titleEng = "";
        }

        // 개봉일의 연월일에서 연도만 가져온다.
        if(!releaseDate.equals("해당정보 없음")) {
            releaseDate = releaseDate.substring(0, releaseDate.indexOf("-"));
        }

        // '분(minute)'과 '초(second)'로 이루어진 상영시간에서 '분' 부분만 가져온다.
        if(0 < runningTime.indexOf(" ")) {
            runningTime = runningTime.split(" ")[0];
        }

        Contents contents = new Contents();
        contents.setPosterUrl(posterUrl);
        contents.setTitle(title);
        contents.setTitleEng(titleEng);
        contents.setGenre(genre);
        contents.setNation(nation);
        contents.setReleaseDate(releaseDate);
        contents.setRunningTime(runningTime);
        contents.setStory(story);
        contents.setPeople(people);

        return contents;
    }

    /**
     * text를 읽어오기전 Element 객체가 null인지 체크한다.
     * null일 경우 길이가 0인 문자열을 반환하고, null이 아니라면 해당 객체의 텍스트를 반환한다.
     * @param element 텍스트를 읽어올 Element 객체
     * @return String
     */
    private String checkNull(Element element) {
        return element == null ? "" : element.text();
    }

    private List<Person> parseStaff(Document document, MultiValueMap<String, String> param) {
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URL_STAFF, param, String.class);
        return gson.fromJson(stringResponseEntity.getBody(), type);
    }

    private List<Person> parseActor(Document document, MultiValueMap<String, String> param) {
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URL_ACTOR, param, String.class);
        return gson.fromJson(stringResponseEntity.getBody(), type);
    }

    @Override
    public Object parse(String json) {
        throw new UnsupportedOperationException();
    }

}


