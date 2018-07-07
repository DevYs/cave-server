package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import javax.validation.constraints.*;
import java.io.Serializable;

public class User implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_USER = "db_user";
    public static final String INDEX_USER_USER_ID = "index_user_user_id";
    public static final String KEY_USER_USER_ID = "key_user_user_id";

    private transient String userNo;

    @NotNull
    @Size(min = 4, max = 16)
    private String userId;

    @NotNull
    @Size(min = 4, max = 16)
    private String password;

    @Size(max = 30)
    private String email;

    @NotNull
    @Size(min = 2, max = 20)
    private String userName;

    private boolean isAdmin = false;

    public User() {}

    public User(String userId, String password, String email, String userName, boolean isAdmin) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    public User(String userNo, String userId, String password, String email, String userName, boolean isAdmin) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    final void setKey(String userNo) {
        this.userNo = userNo;
    }

    public final String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public final String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public final String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public final String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public final String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public final boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public static User emptyUser() {
        return new User("anonymous", "", null, null ,false);
    }

    public User setNewPassword(String newPassword) {
        this.password = newPassword;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput keyOutput) {
        if(s.equals(KEY_USER_USER_ID)) {
            if (this.userId != null) {
                keyOutput.writeString(this.userId);
                return true;
            } else {
                return false;
            }
        } else {
            throw new UnsupportedOperationException(s);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
