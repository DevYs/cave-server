package devy.kave.server.util;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    public static <E extends MarshalledTupleKeyEntity> List<E> reverse(List<E> list) {
        List<E> temp = new ArrayList<>();
        int startIndex = list.size() - 1;
        int endIndex = 0;
        for(int i=startIndex; i>=endIndex; i--) {
            temp.add(list.get(i));
        }
        return temp;
    }

}
