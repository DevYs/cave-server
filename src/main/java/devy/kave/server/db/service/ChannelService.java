package devy.kave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ChannelMapper;
import devy.kave.server.db.model.Channel;
import devy.kave.server.db.model.ChannelKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @Autowired
    private ChannelMapper channelMapper;

    public boolean add(Channel channel) {
        long key = DatabaseKeyCreator.createKey();
        channel.setChannelNo(key);
        return channelMapper.add(channel);
    }

    public Channel remove(long channelNo) {
        return (Channel) channelMapper.remove(channelNo);
    }

    public Channel mod(Channel channel) {
        return (Channel) channelMapper.mod(channel);
    }

    public Channel getChannel(long channelNo) {
        return (Channel) channelMapper.sortedMap().duplicates(new ChannelKey(channelNo)).iterator().next();
    }

    public StoredSortedValueSet<Channel> channelList() {
        return channelMapper.sortedSet();
    }
}