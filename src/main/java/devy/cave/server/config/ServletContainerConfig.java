package devy.cave.server.config;

import devy.cave.server.db.service.AdminConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServletContainerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    private final Logger logger = LoggerFactory.getLogger(ServletContainerConfig.class);

    @Autowired
    private AdminConfigService adminConfigService;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        int port = Integer.valueOf(adminConfigService.getPort().getConfigValue());
        factory.setPort(port);
    }
}