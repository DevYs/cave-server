package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Deck implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_DECK = "db_deck";
    public static final String SEC_DB_DECK_BY_USER_NO = "deck_by_user_no";
    public static final String KEY_USER_NO = "key_user_no";

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
        tupleOutput.writeLong(this.deckNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.deckNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String keyName, TupleOutput keyOutput) {
        if (keyName.equals(KEY_USER_NO)) {
            keyOutput.writeLong(this.userNo);
            return true;
        } else {
            throw new UnsupportedOperationException(keyName);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
