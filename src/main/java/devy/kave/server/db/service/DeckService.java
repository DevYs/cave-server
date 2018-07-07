package devy.kave.server.db.service;

import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.DeckMapper;
import devy.kave.server.db.mapper.WatchingMapper;
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
    private DeckMapper deckMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    public Deck getDeck(String userNo, String contentsNo) {
        Deck deck = null;
        try {
            deck = (Deck) deckMapper.map().duplicates(new DeckKey(userNo, contentsNo)).iterator().next();
        } catch (NoSuchElementException e) {
            logger.info("다음에 보기 목록에 없습니다. " + userNo + ", " + contentsNo);
            deck = null;
        }

        return deck;
    }

    public boolean add(String userNo, String contentsNo) {
        Contents contents = (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
        return deckMapper.add(new Deck(userNo, contentsNo , contents));
    }

    public Deck remove(String userNo, String videoNo) {
        DeckKey deckKey = new DeckKey(userNo, videoNo);
        return deckMapper.remove(deckKey);
    }

    public Collection<Deck> deckList(String userNo) {
        return deckMapper.sortedMapByUserNo().duplicates(new UserKey(userNo));
    }

}
