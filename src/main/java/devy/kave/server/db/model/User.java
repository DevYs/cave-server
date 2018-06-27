package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class User implements Serializable, MarshalledTupleKeyEntity {

    private transient long userNo;
    private String userId;
    private String password;
    private String email;
    private boolean isAdmin;

    public User(long userNo, String userId, String password, String email, boolean isAdmin) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public final long getUserNo() {
        return userNo;
    }

    public final String getUserId() {
        return userId;
    }

    public final String getPassword() {
        return password;
    }

    public final String getEmail() {
        return email;
    }

    public final boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.userNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.userNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
