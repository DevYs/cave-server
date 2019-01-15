package devy.cave.server.api.user;

import devy.cave.server.api.req.ApiReqPlay;
import devy.cave.server.api.resp.ApiPlay;
import devy.cave.server.api.resp.ApiResponse;
import devy.cave.server.api.resp.ApiStatus;
import devy.cave.server.db.mapper.VideoMapper;
import devy.cave.server.db.model.Channel;
import devy.cave.server.db.model.Video;
import devy.cave.server.db.model.Watching;
import devy.cave.server.db.service.ChannelService;
import devy.cave.server.db.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
public class PlayRestController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PlayService playService;

    @PostMapping("/api/user/play")
    public Object apiPlay(ApiReqPlay apiReqPlay) {
        List<Channel> channelList = channelService.channelList().toList();

        Watching watching = playService.getWatching(apiReqPlay.getUserId(), apiReqPlay.getVideoNo());

        boolean isWatching = watching != null;
        if(!isWatching) {
            watching = playService.addWatching(apiReqPlay.getUserId(), apiReqPlay.getVideoNo(), "0");
        }

        List<Video> videoList = (List<Video>) playService.videoList(watching.getContentsNo());

        ApiPlay apiPlay = new ApiPlay();
        apiPlay.setWatching(watching);
        apiPlay.setVideoList(videoList);

        return new ApiResponse(ApiStatus.SUCCESS, channelList, apiPlay);
    }

}
