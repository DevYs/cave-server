package devy.cave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Deck implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_DECK = "db_deck";
    public static final String INDEX_DECK_USER_NO = "index_deck_user_no";
    public static final String INDEX_DECK_CONTENTS_NO = "index_deck_contents_no";
    public static final String KEY_DECK_USER_NO = "key_deck_user_no";
    public static final String KEY_DECK_CONTENTS_NO = "key_deck_contents_no";

    private transient String userNo;
    private transient String contentsNo;
    private Contents contents;

    public Deck(String userNo, String contentsNo, Contents contents) {
        this.userNo = userNo;
        this.contentsNo = contentsNo;
        this.contents = contents;
    }

    final void setKey(String userNo, String contentsNo) {
        this.userNo = userNo;
        this.contentsNo = contentsNo;
    }

    public final String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public final String getContentsNo() {
        return contentsNo;
    }

    public void setContentsNo(String contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public DeckKey getDeckKey() {
        return new DeckKey(this.userNo, this.contentsNo);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "userNo='" + userNo + '\'' +
                ", contentsNo=" + contentsNo +
                ", contents=" + contents +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.userNo);
        tupleOutput.writeString(this.contentsNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.userNo = tupleInput.readString();
        this.contentsNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String keyName, TupleOutput keyOutput) {
        if (keyName.equals(KEY_DECK_USER_NO)) {
            if(this.userNo != null) {
                keyOutput.writeString(this.userNo);
                return true;
            } else {
                return false;
            }
        } else if(keyName.equals(KEY_DECK_CONTENTS_NO)) {
            if(this.contentsNo != null) {
                keyOutput.writeString(this.contentsNo);
                return true;
            } else {
                return false;
            }
        } else {
            throw new UnsupportedOperationException(keyName);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
