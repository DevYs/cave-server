package devy.kave.server.db.service;

import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.DeckMapper;
import devy.kave.server.db.mapper.WatchingMapper;
import devy.kave.server.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchingService {

    private final Logger logger = LoggerFactory.getLogger(WatchingService.class);

    @Autowired
    private WatchingMapper watchingMapper;

    @Autowired
    private DeckMapper deckMapper;

    public boolean add(String userNo, Video video, Contents watchingContents) {
        Watching newWatching = new Watching(userNo, video.getVideoNo(), watchingContents, video);

        boolean isAdded = watchingMapper.add(newWatching);
        if(isAdded) {
            boolean isExist = 0 < deckMapper.map().duplicates(newWatching.getDeckKey()).size();
            if(isExist) {
                Deck removedDeck = deckMapper.remove(newWatching.getDeckKey());
                logger.info("removed " + removedDeck);
            }
        }

        return isAdded;
    }

    public Watching remove(String userNo, String videoNo) {
        WatchingKey watchingKey = new WatchingKey(userNo, videoNo);
        return watchingMapper.remove(watchingKey);
    }

}