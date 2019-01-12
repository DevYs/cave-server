package devy.cave.server.api.user;

import devy.cave.server.db.model.Clip;
import devy.cave.server.db.service.ApiAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class IndexRestController {

    private final Logger logger = LoggerFactory.getLogger(IndexRestController.class);

    @Autowired
    private ApiAuthService apiAuthService;

    @PostMapping("/api/user/index")
    public Object apiIndex(HttpServletRequest request, @RequestParam("param1") String param1, @RequestParam("param2") Integer param2) {
        String apiKey = request.getHeader("apiKey");

        logger.info("apiKey " + apiKey);

        logger.info("param1 " + param1);
        logger.info("param2 " + param2);
        Clip clip = new Clip("1234", param1, String.valueOf(param2));
        return clip;
    }

}

