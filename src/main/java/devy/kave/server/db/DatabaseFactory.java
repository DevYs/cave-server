package devy.kave.server.db;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.*;

public class DatabaseFactory {

    public Database openDatabase(Environment env, DatabaseConfig dbConfig, String dbName) {
        return openDatabase(env, dbConfig, dbName, null);
    }

    public Database openDatabase(Environment env, DatabaseConfig dbConfig, String dbName, Transaction txn) {
        return env.openDatabase(txn, dbName, dbConfig);
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase openSecDatabase(Environment env, TupleSerialFactory factory, Class<V> valueBaseClass, String keyName, String secDbName, Database primaryDatabase) {
        SecondaryConfig secConfig =
                new SecondaryConfigBuilder().build().setKeyCreator(factory.getKeyCreator(valueBaseClass, keyName));
        return openSecDatabase(env, secConfig, secDbName, primaryDatabase, null);
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase openSecDatabaseForeignKeyDb(Environment env, TupleSerialFactory factory, Class<V> valueBaseClass, String keyName, String secDbName, Database foreignKeyDb, Database primaryDatabase) {
        SecondaryConfig secConfig = new SecondaryConfigBuilder().build()
                .setForeignKeyDatabase(foreignKeyDb)
                .setForeignKeyDeleteAction(ForeignKeyDeleteAction.CASCADE)
                .setKeyCreator(factory.getKeyCreator(valueBaseClass, keyName));
        return openSecDatabase(env, secConfig, secDbName, primaryDatabase, null);
    }

    public SecondaryDatabase openSecDatabase(Environment env, SecondaryConfig secConfig, String secDbName, Database primaryDatabase, Transaction txn) {
        return env.openSecondaryDatabase(txn, secDbName, primaryDatabase, secConfig);
    }

}
