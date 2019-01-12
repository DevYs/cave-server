package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class ApiAuthKey implements MarshalledTupleEntry {

    private transient String authKey;

    public ApiAuthKey() {}

    public ApiAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public String toString() {
        return "ApiAuthKey{" +
                "authKey='" + authKey + '\'' +
                '}';
    }

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.authKey);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.authKey = tupleInput.readString();
    }
}
