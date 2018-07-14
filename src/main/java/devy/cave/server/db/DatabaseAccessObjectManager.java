package devy.cave.server.db;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.collections.*;
import com.sleepycat.je.Database;
import com.sleepycat.je.SecondaryDatabase;
import devy.cave.server.db.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DatabaseAccessObjectManager {

    @Autowired
    private DatabaseSource databaseSource;
    private DatabaseFactory databaseFactory;
    private DatabaseViewFactory databaseViewFactory;
    private Map<String, Database> databaseMap;

    public DatabaseAccessObjectManager() {
        this.databaseFactory = new DatabaseFactory();
        this.databaseViewFactory = new DatabaseViewFactory();
        this.databaseMap = new HashMap();
    }

    public Database getDatabase(Query query) {
        if(!query.getPrimaryDbName().isEmpty()) {
            return getSecondaryDatabase(query);
        }

        return getDatabase(query.getDbName());
    }

    public Database getDatabase(String dbName) {
        if(!this.databaseMap.containsKey(dbName)) {
            Database database = this.databaseFactory.openDatabase(databaseSource.getEnv(), databaseSource.getDbConfig(), dbName);
            this.databaseMap.put(dbName, database);
        }
        return this.databaseMap.get(dbName);
    }

    public SecondaryDatabase getSecondaryDatabase(Query query) {
        if (query.getForeignKeyDbName().isEmpty()) {
            return getSecondaryDatabase(query.getValueBaseClass(), query.getKeyName(), query.getDbName(), query.getPrimaryDbName());
        }
        return getSecondaryDatabaseForeignKey(query.getForeignKeyDbName(), query.getValueBaseClass(), query.getKeyName(), query.getDbName(), query.getPrimaryDbName());
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase getSecondaryDatabase(Class<V> valueBaseClass,
                                                                                       String keyName,
                                                                                       String secDbName,
                                                                                       String primaryDbName) {
        if (!this.databaseMap.containsKey(secDbName)) {
            Database primaryDatabase = getDatabase(primaryDbName);
            SecondaryDatabase secondaryDatabase
                    = this.databaseFactory.openSecDatabase(this.databaseSource.getEnv(), this.databaseSource.getFactory(), valueBaseClass, keyName, secDbName, primaryDatabase);
            this.databaseMap.put(secDbName, secondaryDatabase);
        }
        return (SecondaryDatabase) this.databaseMap.get(secDbName);
    }

    public <V extends MarshalledTupleKeyEntity> SecondaryDatabase getSecondaryDatabaseForeignKey(String foreignKeyDbName,
                                                                                                 Class<V> valueBaseClass,
                                                                                                 String keyName,
                                                                                                 String secDbName,
                                                                                                 String primaryDbName) {
        if (!this.databaseMap.containsKey(secDbName)) {
            Database primaryDatabase = getDatabase(primaryDbName);
            Database foreignKeyDatabase = getDatabase(foreignKeyDbName);
            SecondaryDatabase secondaryDatabase
                    = this.databaseFactory.openSecDatabaseForeignKeyDb(this.databaseSource.getEnv(), this.databaseSource.getFactory(), valueBaseClass, keyName, secDbName, foreignKeyDatabase, primaryDatabase);
            this.databaseMap.put(secDbName, secondaryDatabase);
        }

        return (SecondaryDatabase) this.databaseMap.get(secDbName);
    }

    public StoredMap map(Query query) {
        return this.databaseViewFactory.map(this.databaseSource.getFactory(), getDatabase(query), query.getKeyClass(), query.getValueBaseClass(), query.isWriteAllowed());
    }

    public StoredSortedMap sortedMap(Query query) {
        return this.databaseViewFactory.sortedMap(this.databaseSource.getFactory(), getDatabase(query), query.getKeyClass(), query.getValueBaseClass(), query.isWriteAllowed());
    }

    public StoredValueSet set(Query query) {
        return this.databaseViewFactory.set(this.databaseSource.getFactory(), getDatabase(query), query.getKeyClass(), query.getValueBaseClass(), query.isWriteAllowed());
    }

    public StoredSortedValueSet sortedSet(Query query) {
        return this.databaseViewFactory.sortedSet(this.databaseSource.getFactory(), getDatabase(query), query.getKeyClass(), query.getValueBaseClass(), query.isWriteAllowed());
    }

}