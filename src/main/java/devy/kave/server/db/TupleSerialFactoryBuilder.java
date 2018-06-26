package devy.kave.server.db;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.collections.TupleSerialFactory;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Environment;
import com.sleepycat.je.Transaction;

public class TupleSerialFactoryBuilder {

    private Environment env;
    private DatabaseConfig dbConfig;
    private String classCatalog = "class_catalog";
    private Transaction txn = null;

    public TupleSerialFactoryBuilder() {
        this.env = null;
        this.dbConfig = null;
    }

    public TupleSerialFactoryBuilder(Environment env, DatabaseConfig dbConfig) {
        this.env = env;
        this.dbConfig = dbConfig;
    }

    public TupleSerialFactoryBuilder setEnv(Environment env) {
        this.env = env;
        return this;
    }

    public TupleSerialFactoryBuilder setDbConfig(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
        return this;
    }

    public TupleSerialFactoryBuilder setClassCatalog(String classCatalog) {
        this.classCatalog = classCatalog;
        return this;
    }

    public TupleSerialFactoryBuilder setTxn(Transaction txn) {
        this.txn = txn;
        return this;
    }

    public TupleSerialFactory build() {
        Database catalogDb = env.openDatabase(txn, classCatalog, dbConfig);
        StoredClassCatalog storedClassCatalog = new StoredClassCatalog(catalogDb);
        return new TupleSerialFactory(storedClassCatalog);
    }

}
