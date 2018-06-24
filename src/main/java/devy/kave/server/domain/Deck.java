package devy.kave.server.domain;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Deck implements Serializable, MarshalledTupleKeyEntity {

    private transient long deckNo;
    private long userNo;
    private Contents contents;

    public Deck(long userNo, long deckNo, Contents contents) {
        this.userNo = userNo;
        this.deckNo = deckNo;
        this.contents = contents;
    }

    public final long getDeckNo() {
        return deckNo;
    }

    public final long getUserNo() {
        return userNo;
    }

    public final Contents getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "userNo=" + userNo +
                ", deckNo=" + deckNo +
                ", contents=" + contents +
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

}
