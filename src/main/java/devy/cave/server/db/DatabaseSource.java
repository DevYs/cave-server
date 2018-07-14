package devy.cave.server.db;

import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.*;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSource {

    private final Environment env = new EnvironmentBuilder().build();
    private final DatabaseConfig dbConfig = new DatabaseConfigBuilder().build();
    private final TupleSerialFactory factory = new TupleSerialFactoryBuilder(env, dbConfig).build();
    private final TransactionRunner txnRunner = new TransactionRunner(env);

    public final Environment getEnv() {
        return env;
    }

    public final DatabaseConfig getDbConfig() {
        return dbConfig;
    }

    public final TupleSerialFactory getFactory() {
        return factory;
    }

    public final TransactionRunner getTxnRunner() {
        return txnRunner;
    }

}