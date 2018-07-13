package devy.kave.server.controller.config;

import devy.kave.server.db.model.AdminConfig;
import devy.kave.server.db.service.AdminConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConfigController {

    private final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private AdminConfigService adminConfigService;

    @GetMapping("/config/port")
    public String configPort(Model model) {
        model.addAttribute("port", adminConfigService.getPort());
        return "config/port";
    }

    @PostMapping("/config/port")
    public String configPort(String port, Model model) {

        if(port == null || port.isEmpty()) {
            model.addAttribute("errorPort", "포트 번호를 입력하세요.");
            model.addAttribute("port", adminConfigService.getPort());
            return "config/port";
        }

        int p = Integer.valueOf(port);
        if(p < 1024 || 49151 < p) {
            model.addAttribute("errorPort", "포트 번호는 1024~49151 사이의 숫자만 가능합니다.");
            model.addAttribute("port", adminConfigService.getPort());
            return "config/port";
        }

        AdminConfig adminConfig = adminConfigService.changePort(port);
        logger.info("changed port " + adminConfig.toString());

        return "redirect:/config/port";
    }
}