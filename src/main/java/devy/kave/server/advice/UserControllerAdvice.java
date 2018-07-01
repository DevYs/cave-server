package devy.kave.server.advice;

import devy.kave.server.db.mapper.ChannelMapper;
import devy.kave.server.db.model.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Iterator;

@ControllerAdvice
public class UserControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(UserControllerAdvice.class);

    @Autowired
    private ChannelMapper channelMapper;

    @ModelAttribute("channels")
    public Iterator<Channel> channels() {
        return channelMapper.getChannelListWithSortedSet().iterator();
    }

}