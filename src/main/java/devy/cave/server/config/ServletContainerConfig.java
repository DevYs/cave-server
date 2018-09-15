package devy.cave.server.config;

import devy.cave.server.db.service.AdminConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ServletContainerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    // Session Timeout 10 Hour
    private final long SESSION_TIMEOUT_OF_MINUTE = 10 * 60;

    @Autowired
    private AdminConfigService adminConfigService;

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        Duration between = Duration.ofMinutes(SESSION_TIMEOUT_OF_MINUTE);
        Session session = new Session();
        session.setTimeout(between);
        factory.setSession(session);

        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("vtt","text/vtt");
        mappings.add("smi","text/smi");
        mappings.add("srt","text/srt");
        factory.setMimeMappings(mappings);

        int port = Integer.valueOf(adminConfigService.getPort().getConfigValue());
        factory.setPort(port);

    }
}