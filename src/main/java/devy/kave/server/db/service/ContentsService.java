package devy.kave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ChannelMapper;
import devy.kave.server.db.mapper.ContentsMapper;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.Contents;
import devy.kave.server.db.model.ContentsKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContentsService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    public StoredSortedValueSet<Channel> channelList() {
        return channelMapper.sortedSet();
    }

    public boolean add(Contents contents) {
        contents.setContentsNo(DatabaseKeyCreator.createKey());
        return contentsMapper.add(contents);
    }

    public Contents remove(long contentsNo) {
        return (Contents) contentsMapper.remove(contentsNo);
    }

    public Contents mod(Contents contents) {
        return (Contents) contentsMapper.mod(contents);
    }

    public StoredSortedValueSet<Contents> contentsList() {
        return contentsMapper.sortedSet();
    }

    public Contents getContents(long contentsNo) {
        return (Contents) contentsMapper.map().duplicates(new ContentsKey(contentsNo)).iterator().next();
    }

}