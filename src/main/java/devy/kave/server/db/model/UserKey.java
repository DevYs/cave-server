package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class UserKey implements MarshalledTupleEntry {

    private long userNo;

    public UserKey(long userNo) {
        this.userNo = userNo;
    }

    public final long getUserNo() {
        return userNo;
    }

    @Override
    public String toString() {
        return "UserKey{" +
                "userNo=" + userNo +
                '}';
    }

    public UserKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.userNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.userNo = tupleInput.readLong();
    }
}
