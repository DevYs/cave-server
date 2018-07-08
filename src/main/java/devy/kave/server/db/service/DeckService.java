package devy.kave.server.db.service;

import devy.kave.server.db.mapper.*;
import devy.kave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class DeckService {

    private final Logger logger = LoggerFactory.getLogger(DeckService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DeckMapper deckMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private VideoMapper videoMapper;

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

    public boolean add(String userId, String contentsNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        Contents contents = (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
        return deckMapper.add(new Deck(user.getUserNo(), contentsNo , contents));
    }

    public Deck remove(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        DeckKey deckKey = new DeckKey(user.getUserNo(), videoNo);
        return deckMapper.remove(deckKey);
    }

    public Collection<Deck> deckList(String userNo) {
        return deckMapper.sortedMapByUserNo().duplicates(new UserKey(userNo));
    }

    public Collection<Video> videoList(String contentsNo) {
        return videoMapper.sortedSetByContentsNo().duplicates(new ContentsKey(contentsNo));
    }

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

}
