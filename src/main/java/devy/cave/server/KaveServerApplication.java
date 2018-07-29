package devy.cave.server;

import devy.cave.server.db.EnvironmentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class KaveServerApplication {

    private static Logger logger = LoggerFactory.getLogger(KaveServerApplication.class);

	public static void main(String[] args) {
        File file = new File(EnvironmentBuilder.defaultDbDir);
        if(!file.exists()) {
            file.mkdir();
            logger.info("Created bdb directory");
        } else {
            logger.info("Already Created bdb directory");
        }

        SpringApplication.run(KaveServerApplication.class, args);

    }

}