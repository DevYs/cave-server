package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class DeckKey implements MarshalledTupleEntry {

    private String deckNo;

    public DeckKey(String deckNo) {
        this.deckNo = deckNo;
    }

    public final String getDeckNo() {
        return deckNo;
    }

    @Override
    public String toString() {
        return "DeckKey{" +
                "deckNo=" + deckNo +
                '}';
    }

    public DeckKey() {}

    @Override
    public void marshalEntry(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.deckNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.deckNo = tupleInput.readString();
    }
}
