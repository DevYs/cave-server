package devy.cave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.cave.server.db.DatabaseKeyCreator;
import devy.cave.server.db.mapper.*;
import devy.cave.server.db.model.*;
import devy.cave.server.util.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentsService {

    private final Logger logger = LoggerFactory.getLogger(ContentsService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private DeckMapper deckMapper;

    public StoredSortedValueSet<Channel> channelList() {
        return channelMapper.sortedSet();
    }

    public List<Contents> contentsList(int pageNo, String searchWord, String channelNo) {
        int defaulatPagePerSize = 12;
        return contentsList(pageNo, searchWord, channelNo, defaulatPagePerSize);
    }

    public List<Contents> contentsList(int pageNo, String searchWord, String channelNo, int pagePerSize) {
        List<Contents> contentsList = new ArrayList<>();

        Iterator iterator = null;
        if(!channelNo.equals("0")) {
            iterator = contentsMapper.sortedMapByChannelNo().duplicates(new ChannelKey(channelNo)).iterator();
        } else {
            iterator = contentsMapper.sortedSet().iterator();
        }

        while(iterator.hasNext()) {
            Contents contents = (Contents) iterator.next();
            int indexOf = contents.getContentsName().indexOf(searchWord) +
                    contents.getDirector().indexOf(searchWord) +
                    contents.getActor().indexOf(searchWord) +
                    contents.getNation().indexOf(searchWord) +
                    contents.getGenre().indexOf(searchWord) +
                    contents.getReleaseDate().indexOf(searchWord);
            if(-6 < indexOf) {
                contentsList.add(contents);
            }
        }

        // 내림차순으로 정렬
        contentsList.sort(new Sort().descending());

        int s = ((pageNo - 1) * pagePerSize) + 1;
        int e = s + (pagePerSize - 1);

        if(contentsList.size() < s) {
            return new ArrayList<>();
        }

        if(contentsList.size() < e) {
            e = contentsList.size();
        }

        return contentsList.subList(s - 1, e);
    }

    public boolean add(Contents contents) {
        contents.setContentsNo(DatabaseKeyCreator.createKey());
        return contentsMapper.add(contents);
    }

    public Contents remove(String contentsNo) {
        return (Contents) contentsMapper.remove(new ContentsKey(contentsNo));
    }

    public Contents mod(Contents contents) {
        return (Contents) contentsMapper.mod(contents);
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

    public Collection<Video> videoList(String contentsNo) {
        return videoMapper.sortedSetByContentsNo().duplicates(new ContentsKey(contentsNo));
    }

    public Deck getDeck(String userId, String contentsNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();

        Deck deck = null;
        try {
            deck = (Deck) deckMapper.map().duplicates(new DeckKey(user.getUserNo(), contentsNo)).iterator().next();
        } catch (NoSuchElementException e) {
            logger.info("다음에 보기 목록에 없습니다. " + user.getUserNo() + ", " + contentsNo);
            deck = null;
        }

        return deck;
    }

    public Deck remove(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        DeckKey deckKey = new DeckKey(user.getUserNo(), videoNo);
        return deckMapper.remove(deckKey);
    }
}