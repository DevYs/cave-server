package devy.kave.server.db;

import devy.kave.server.db.model.*;

import java.util.HashMap;

public class QueryMap extends HashMap<String, Query> {

    public QueryMap() {
        channel();
        contents();
        user();
        video();
    }

    private void channel() {
        put(Channel.DB_CHANNEL, new Query().setDbName(Channel.DB_CHANNEL).setKeyClass(ChannelKey.class).setValueBaseClass(Channel.class));
    }

    private void contents() {
        put(Contents.DB_CONTENTS, new Query().setDbName(Contents.DB_CONTENTS).setKeyClass(ContentsKey.class).setValueBaseClass(Contents.class));
    }

    private void video() {
        put(Video.DB_VIDEO, new Query().setDbName(Video.DB_VIDEO).setKeyClass(VideoKey.class).setValueBaseClass(Video.class));
        put(Video.INDEX_VIDEO_CONTENTS_NO, new Query()
                .setDbName(Video.INDEX_VIDEO_CONTENTS_NO)
                .setKeyClass(Long.class)
                .setValueBaseClass(Video.class)
                .setPrimaryDbName(Video.DB_VIDEO)
                .setForeignKeyDbName(Contents.DB_CONTENTS)
                .setKeyName(Video.KEY_VIDEO_CONTENTS_NO));
    }

    private void user() {
        put(User.DB_USER, new Query().setDbName(User.DB_USER).setKeyClass(UserKey.class).setValueBaseClass(User.class));
        put(User.INDEX_USER_USER_ID, new Query()
                .setDbName(User.INDEX_USER_USER_ID)
                .setKeyClass(String.class)
                .setValueBaseClass(User.class)
                .setKeyName(User.KEY_USER_USER_ID)
                .setPrimaryDbName(User.DB_USER));
    }

}
