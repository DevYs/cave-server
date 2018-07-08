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

    public Contents getContents(String contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

}
