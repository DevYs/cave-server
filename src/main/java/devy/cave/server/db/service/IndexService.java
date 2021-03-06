package devy.cave.server.db.service;

import devy.cave.server.db.mapper.*;
import devy.cave.server.db.model.*;
import devy.cave.server.util.Sort;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class IndexService {

    private final Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private WatchingMapper watchingMapper;

    @Autowired
    private DeckMapper deckMapper;

    @Autowired
    private VideoMapper videoMapper;

    public List<Contents> newContents() {
        int size = 12;
        List<Contents> contentsList = new ArrayList<>();

        Iterator iterator = contentsMapper.sortedSet().iterator();
        while(iterator.hasNext()) {
            Contents contents = (Contents) iterator.next();
            contentsList.add(contents);
        }

        // 내림차순으로 정렬
        contentsList.sort(new Sort().descending());

        if(contentsList.size() < 1) {
            return contentsList;
        }

        if(contentsList.size() < size) {
            size = contentsList.size();
        }

        return contentsList.subList(0, size);
    }

    public List<Watching> watchingList(String userId) {
        List<Watching> watchingList = new ArrayList<>();
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Collection<Watching> watchingCollection = watchingMapper.sortedMapByUserNo().duplicates(new UserKey(user.getUserNo()));
        for(Watching watching : watchingCollection) {
            Video video = (Video) videoMapper.map().duplicates(new VideoKey(watching.getVideoNo())).iterator().next();
            String image = video.getImage();

            if(image == null || image.isEmpty()) {
                Document document = null;
                try {
                    document = Jsoup.connect(video.getShareLinkUrl()).get();
                    String dataDestination = document.selectFirst("div[data-destination]").attr("data-destination");
                    document = Jsoup.connect(dataDestination).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                image = document.selectFirst("meta[property='og:image']").attr("content");
                video.setImage(image);
                videoMapper.map().replace(new VideoKey(watching.getVideoNo()), video);
            }

            watching.setVideo(video);
            watchingList.add(watching);
        }
        return watchingList;
    }

    public List<Deck> deckList(String userId) {
        List<Deck> deckList = new ArrayList<>();
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Collection<Deck> deckCollection = deckMapper.sortedMapByUserNo().duplicates(new UserKey(user.getUserNo()));
        for(Deck deck : deckCollection) {
            Contents contents = (Contents) contentsMapper.map().duplicates(new ContentsKey(deck.getContentsNo())).iterator().next();
            deck.setContents(contents);
            deckList.add(deck);
        }
        return deckList;
    }

    public Watching remove(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        WatchingKey watchingKey = new WatchingKey(user.getUserNo(), videoNo);
        return watchingMapper.remove(watchingKey);
    }
}
