package devy.cave.server.config;

import com.sleepycat.collections.StoredMap;
import devy.cave.server.db.DatabaseAccessObjectManager;
import devy.cave.server.db.DatabaseKeyCreator;
import devy.cave.server.db.QueryMap;
import devy.cave.server.db.Query;
import devy.cave.server.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.Iterator;

@Configuration
public class BerkeleyDbConfig {

    private final Logger logger = LoggerFactory.getLogger(BerkeleyDbConfig.class);

    @Autowired
    private DatabaseAccessObjectManager manager;

    @Bean
    public QueryMap queryMap() {

        QueryMap queryMap = new QueryMap();

        Iterator<Query> iterator = queryMap.values().iterator();
        while(iterator.hasNext()) {
            manager.getDatabase(iterator.next());
        }

        StoredMap map = manager.map(queryMap.get(User.INDEX_USER_USER_ID));
        if(map.size() < 1) {
            User user = new User(
                    DatabaseKeyCreator.createKey(),
                    "admin",
                    PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("0000"),
                    "",
                    "최고관리자",
                    true);

            boolean isAdded = manager.set(queryMap.get(User.DB_USER)).add(user);
            if(isAdded) {
                logger.info("Created Administrator User");
            }
        }

        return queryMap;
    }

}