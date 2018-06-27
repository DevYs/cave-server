package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

public class DeckKey implements MarshalledTupleEntry {

    private long deckNo;

    public DeckKey(long deckNo) {
        this.deckNo = deckNo;
    }

    public final long getDeckNo() {
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
        tupleOutput.writeLong(this.deckNo);
    }

    @Override
    public void unmarshalEntry(TupleInput tupleInput) {
        this.deckNo = tupleInput.readLong();
    }
}
