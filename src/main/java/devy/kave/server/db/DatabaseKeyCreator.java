package devy.kave.server.db;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseKeyCreator {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static final String createKey() {
        long lt = System.currentTimeMillis();
        String st = format.format(new Date(lt));
        long l = Long.valueOf(st);
        return String.valueOf(l);
    }
}