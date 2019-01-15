package devy.cave.server.api.user;

import devy.cave.server.api.req.ApiReqSearch;
import devy.cave.server.api.resp.ApiResponse;
import devy.cave.server.api.resp.ApiStatus;
import devy.cave.server.db.model.Channel;
import devy.cave.server.db.model.api.Movie;
import devy.cave.server.db.service.ChannelService;
import devy.cave.server.db.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class SearchRestController {

    private final Logger logger = LoggerFactory.getLogger(SearchRestController.class);

    @Autowired
    private ChannelService channelService;

    @Autowired
    private SearchService searchService;

    @PostMapping("/api/user/search")
    public Object apiSearch(ApiReqSearch apiReqSearch) {
        List<Channel> channels = channelService.channelList().toList();
        List<Movie> movieList = searchService.movieList(apiReqSearch);
        return new ApiResponse(ApiStatus.SUCCESS, channels, movieList);
    }

}
