package devy.kave.server.db.service;

import devy.kave.server.db.mapper.DeckMapper;
import devy.kave.server.db.mapper.WatchingMapper;
import devy.kave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DeckService {

    private final Logger logger = LoggerFactory.getLogger(DeckService.class);

    @Autowired
    private DeckMapper deckMapper;

    @Autowired
    private WatchingMapper watchingMapper;

    public boolean add(String userNo, Video video, Contents deckContents) {
        Deck newDeck = new Deck(userNo, video.getVideoNo(), video, deckContents);

        boolean isAdded = deckMapper.add(newDeck);
        if(isAdded) {
            boolean isExist = 0 < watchingMapper.map().duplicates(newDeck.getWatchingKey()).size();
            if(isExist) {
                Watching removedWatcing = watchingMapper.remove(newDeck.getWatchingKey());
                logger.info("removed " + removedWatcing);
            }
        }

        return isAdded;
    }

    public Deck remove(String userNo, String videoNo) {
        DeckKey deckKey = new DeckKey(userNo, videoNo);
        return deckMapper.remove(deckKey);
    }

}
