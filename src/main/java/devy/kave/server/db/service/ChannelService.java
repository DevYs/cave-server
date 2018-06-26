package devy.kave.server.db.service;

import com.sleepycat.collections.StoredValueSet;
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

    public void insert(Channel channel) {
        channelMapper.insert(channel);
    }

    public StoredValueSet get() {
        return channelMapper.get();
    }

}