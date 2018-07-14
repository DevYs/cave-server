package devy.cave.server.db.service;

import devy.cave.server.db.mapper.AdminConfigMapper;
import devy.cave.server.db.model.AdminConfig;
import devy.cave.server.db.model.AdminConfigKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminConfigService {

    private final Logger logger = LoggerFactory.getLogger(AdminConfigService.class);

    @Autowired
    private AdminConfigMapper adminConfigMapper;

    public AdminConfig getAdminConfig(String configName) {
        return (AdminConfig) adminConfigMapper.map().duplicates(new AdminConfigKey(configName)).iterator().next();
    }

    public AdminConfig getPort() {
        AdminConfig port;
        try {
            port = (AdminConfig) adminConfigMapper.map().duplicates(new AdminConfigKey(AdminConfig.port().getConfigName())).iterator().next();
        } catch(Exception e) {
            port = AdminConfig.port().setConfigValue("8080");
        }

        logger.info(port.toString());

        return port;
    }

    public AdminConfig changePort(String p) {
        AdminConfig port = AdminConfig.port().setConfigValue(p);
        AdminConfig changedPort = (AdminConfig) adminConfigMapper.mod(port);
        if(changedPort == null) {
            boolean isAdded = adminConfigMapper.add(port);
            if(isAdded) {
                logger.info("added port " + port.toString());
                return port;
            }
        }

        return changedPort;
    }

}