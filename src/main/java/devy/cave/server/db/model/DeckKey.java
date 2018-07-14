package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class DeckKey implements MarshalledTupleEntry {

    private String userNo;
    private String contentsNo;

    public DeckKey(String userNo, String contentsNo) {
        this.userNo = userNo;
        this.contentsNo = contentsNo;
    }

    public final String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getContentsNo() {
        return contentsNo;
    }

    public void setContentsNo(String contentsNo) {
        this.contentsNo = contentsNo;
    }

    @Override
    public String toString() {
        return "DeckKey{" +
                "userNo='" + userNo + '\'' +
                ", contentsNo='" + contentsNo + '\'' +
                '}';
    }

    public DeckKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.contentsNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.contentsNo = tupleInput.readString();
    }
}
