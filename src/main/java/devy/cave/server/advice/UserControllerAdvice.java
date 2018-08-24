package devy.cave.server.advice;

import devy.cave.server.db.model.Channel;
import devy.cave.server.db.service.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Iterator;

@ControllerAdvice(basePackages = "devy.cave.server.controller")
public class UserControllerAdvice {

    @Autowired
    private ChannelService channelService;

    @ModelAttribute("channels")
    public Iterator<Channel> channels() {
        return channelService.channelList().iterator();
    }

}