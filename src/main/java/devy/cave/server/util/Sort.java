package devy.cave.server.util;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class Sort {

    private static final Logger logger = LoggerFactory.getLogger(Sort.class);

    public SortDescending descending() {
        return new SortDescending();
    }

    public SortAscending ascending() {
        return new SortAscending();
    }

    private class SortDescending implements Comparator<MarshalledTupleKeyEntity> {
        @Override
        public int compare(MarshalledTupleKeyEntity o1, MarshalledTupleKeyEntity o2) {
            Long o1Key = toLong(o1);
            Long o2Key = toLong(o2);
            return o1Key < o2Key ? 1 : -1;
        }
    }

    private class SortAscending implements Comparator<MarshalledTupleKeyEntity> {
        @Override
        public int compare(MarshalledTupleKeyEntity o1, MarshalledTupleKeyEntity o2) {
            Long o1Key = toLong(o1);
            Long o2Key = toLong(o2);
            return o1Key > o2Key ? 1 : -1;
        }
    }

    private Long toLong(MarshalledTupleKeyEntity o) {
        TupleOutput keyOutput = new TupleOutput();
        o.marshalPrimaryKey(keyOutput);
        return Long.valueOf(keyOutput.toString().substring(0, 17));
    }

}
