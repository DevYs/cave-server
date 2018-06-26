package devy.kave.server.config;

import devy.kave.server.db.DatabaseAccessObjectManager;
import devy.kave.server.db.DatabaseSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    private Logger logger = LoggerFactory.getLogger(DbConfig.class);

    @Bean
    public DatabaseAccessObjectManager databaseAccessObjectManager() {
        return new DatabaseAccessObjectManager();
    }

}
