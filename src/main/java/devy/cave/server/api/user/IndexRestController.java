package devy.cave.server.api.user;

import devy.cave.server.api.resp.ApiResponse;
import devy.cave.server.api.resp.ApiStatus;
import devy.cave.server.db.model.Clip;
import devy.cave.server.db.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class IndexRestController {

    private final Logger logger = LoggerFactory.getLogger(IndexRestController.class);

    @Autowired
    private IndexService indexService;

    @PostMapping("/api/user/index")
    public Object apiIndex(@RequestParam("param1") String param1,
                           @RequestParam("param2") Integer param2) {
        Clip clip = new Clip("1234", param1, String.valueOf(param2));
        ApiResponse apiResponse = new ApiResponse(ApiStatus.SUCCESS, clip);
        return apiResponse;
    }

}

