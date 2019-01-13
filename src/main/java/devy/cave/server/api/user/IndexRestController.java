package devy.cave.server.api.user;

import devy.cave.server.api.req.ApiRequest;
import devy.cave.server.api.resp.ApiResponse;
import devy.cave.server.api.resp.ApiStatus;
import devy.cave.server.api.resp.IndexContents;
import devy.cave.server.db.model.*;
import devy.cave.server.db.model.api.Movie;
import devy.cave.server.db.service.ChannelService;
import devy.cave.server.db.service.ClipService;
import devy.cave.server.db.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class IndexRestController {

    private final Logger logger = LoggerFactory.getLogger(IndexRestController.class);

    @Autowired
    private ChannelService channelService;

    @Autowired
    private IndexService indexService;

    @Autowired
    private ClipService clipService;

    @PostMapping("/api/user/index")
    public Object apiIndex(ApiRequest apiRequest) {

        List<Channel> channels = channelService.channelList().toList();

        List<Movie> newMovieList = new ArrayList<>();
        List<Contents> newContentsList = indexService.newContents();
        for(Contents contents : newContentsList) {
            Clip clip = clipService.getClip(contents.getContentsNo());

            Movie movie = new Movie();
            movie.setContents(contents);
            movie.setClip(clip);
            newMovieList.add(movie);
        }

        List<Watching> watchings = indexService.watchingList(apiRequest.getUserId());
        List<Deck> decks = indexService.deckList(apiRequest.getUserId());

        IndexContents indexContents = new IndexContents();
        indexContents.setNewMovieList(newMovieList);
        indexContents.setWatchingList(watchings);
        indexContents.setDeckList(decks);

        ApiResponse apiResponse = new ApiResponse(ApiStatus.SUCCESS, channels, indexContents);
        return apiResponse;
    }

}

