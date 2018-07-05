package devy.kave.server.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseKeyCreator {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static final String createKey() {
        return String.valueOf(System.currentTimeMillis());
    }
}