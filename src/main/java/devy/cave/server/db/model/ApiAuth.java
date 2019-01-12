package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class ApiAuth implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_API_AUTH = "db_api_auth";
    public static final String INDEX_API_AUTH_USER_ID = "index_api_auth_user_id";
    public static final String KEY_API_AUTH_USER_ID = "key_api_auth_user_id";

    private transient String authKey;
    private String userId;
    private String expiredDate;

    public ApiAuth() {}

    public ApiAuth(String authKey, String userId, String expiredDate) {
        this.authKey = authKey;
        this.userId = userId;
        this.expiredDate = expiredDate;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "ApiAuth{" +
                "authKey='" + authKey + '\'' +
                ", userId='" + userId + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                '}';
    }

    // --- MarshalledTupleKeyEntity implementation ---
    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.authKey);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.authKey = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String keyName, TupleOutput tupleOutput) {
        if(keyName.equals(KEY_API_AUTH_USER_ID)) {
            if (this.userId != null) {
                tupleOutput.writeString(this.userId);
                return true;
            } else {
                return false;
            }
        } else {
            throw new UnsupportedOperationException(keyName);
        }

    }

    @Override
    public boolean nullifyForeignKey(String keyName) {
        throw new UnsupportedOperationException(keyName);
    }
}
