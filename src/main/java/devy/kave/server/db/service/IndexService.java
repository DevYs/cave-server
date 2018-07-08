package devy.kave.server.db.service;

import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.mapper.DeckMapper;
import devy.kave.server.db.mapper.UserMapper;
import devy.kave.server.db.mapper.WatchingMapper;
import devy.kave.server.db.model.*;
import devy.kave.server.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    @Autowired
    private WatchingMapper watchingMapper;

    @Autowired
    private DeckMapper deckMapper;

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

        return contentsList.subList(0, size);
    }

    public Collection<Watching> watchingList(String userId) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        return watchingMapper.sortedMapByUserNo().duplicates(new UserKey(user.getUserNo()));
    }

    public Collection<Deck> deckList(String userId) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        return deckMapper.sortedMapByUserNo().duplicates(new UserKey(user.getUserNo()));
    }

    public Watching remove(String userId, String videoNo) {
        User user = (User) userMapper.mapByUserId().duplicates(userId).iterator().next();
        WatchingKey watchingKey = new WatchingKey(user.getUserNo(), videoNo);
        return watchingMapper.remove(watchingKey);
    }
}
