package devy.kave.server.db.service;

import com.sleepycat.collections.StoredSortedValueSet;
import devy.kave.server.db.DatabaseKeyCreator;
import devy.kave.server.db.mapper.ChannelMapper;
import devy.kave.server.db.model.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelService {

    private final Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @Autowired
    private ChannelMapper channelMapper;

    public void add(Channel channel) {
        long key = DatabaseKeyCreator.createKey();
        channel.setChannelNo(key);

        try {
            channelMapper.addChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(long channelNo) {
        try {
            channelMapper.removeChannel(channelNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modify(Channel channel) {
        try {
            channelMapper.modifyChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel(long channelNo) {
        return channelMapper.getChannelByChannelNo(channelNo);
    }
}