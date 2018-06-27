package devy.kave.server.config;

import devy.kave.server.db.DatabaseAccessObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BerkeleyDbConfig {

    private Logger logger = LoggerFactory.getLogger(BerkeleyDbConfig.class);

    @Bean
    public DatabaseAccessObjectManager databaseAccessObjectManager() {
        return new DatabaseAccessObjectManager();
    }

}
