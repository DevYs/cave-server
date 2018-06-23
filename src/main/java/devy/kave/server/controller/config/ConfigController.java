package devy.kave.server.controller.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config() {

        return "config/config";
    }

}
