package devy.cave.server.db;

public class Query {

    private String dbName = "";
    private String primaryDbName = "";
    private String foreignKeyDbName = "";
    private String keyName;
    private Class  keyClass;
    private Class  valueBaseClass;
    private boolean writeAllowed = true;

    public String getDbName() {
        return dbName;
    }

    public Query setDbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public String getPrimaryDbName() {
        return primaryDbName;
    }

    public Query setPrimaryDbName(String primaryDbName) {
        this.primaryDbName = primaryDbName;
        return this;
    }

    public String getForeignKeyDbName() {
        return foreignKeyDbName;
    }

    public Query setForeignKeyDbName(String foreignKeyDbName) {
        this.foreignKeyDbName = foreignKeyDbName;
        return this;
    }

    public String getKeyName() {
        return keyName;
    }

    public Query setKeyName(String keyName) {
        this.keyName = keyName;
        return this;
    }

    public Class getKeyClass() {
        return keyClass;
    }

    public Query setKeyClass(Class keyClass) {
        this.keyClass = keyClass;
        return this;
    }

    public Class getValueBaseClass() {
        return valueBaseClass;
    }

    public Query setValueBaseClass(Class valueBaseClass) {
        this.valueBaseClass = valueBaseClass;
        return this;
    }

    public boolean isWriteAllowed() {
        return writeAllowed;
    }

    public Query setWriteAllowed(boolean writeAllowed) {
        this.writeAllowed = writeAllowed;
        return this;
    }

    @Override
    public String toString() {
        return "Query{" +
                "dbName='" + dbName + '\'' +
                ", primaryDbName='" + primaryDbName + '\'' +
                ", foreignKeyDbName='" + foreignKeyDbName + '\'' +
                ", keyName='" + keyName + '\'' +
                ", keyClass=" + keyClass +
                ", valueBaseClass=" + valueBaseClass +
                ", writeAllowed=" + writeAllowed +
                '}';
    }
}