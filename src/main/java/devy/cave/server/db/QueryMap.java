package devy.cave.server.db;

import devy.cave.server.db.model.*;

import java.util.HashMap;

public class QueryMap extends HashMap<String, Query> {

    public QueryMap() {
        channel();
        contents();
        user();
        video();
        watching();
        deck();
        adminConfig();
    }

    private void channel() {
        put(Channel.DB_CHANNEL, new Query().setDbName(Channel.DB_CHANNEL).setKeyClass(ChannelKey.class).setValueBaseClass(Channel.class));
    }

    private void contents() {
        put(Contents.DB_CONTENTS, new Query().setDbName(Contents.DB_CONTENTS).setKeyClass(ContentsKey.class).setValueBaseClass(Contents.class));
        put(Contents.INDEX_CONTENTS_CHANNEL_NO, new Query()
                .setDbName(Contents.INDEX_CONTENTS_CHANNEL_NO)
                .setKeyClass(ChannelKey.class)
                .setValueBaseClass(Contents.class)
                .setPrimaryDbName(Contents.DB_CONTENTS)
                .setForeignKeyDbName(Channel.DB_CHANNEL)
                .setKeyName(Contents.KEY_CONTENTS_CHANNEL_NO));
    }

    private void video() {
        put(Video.DB_VIDEO, new Query().setDbName(Video.DB_VIDEO).setKeyClass(VideoKey.class).setValueBaseClass(Video.class));
        put(Video.INDEX_VIDEO_CONTENTS_NO, new Query()
                .setDbName(Video.INDEX_VIDEO_CONTENTS_NO)
                .setKeyClass(ContentsKey.class)
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

    private void watching() {
        put(Watching.DB_WATCHING, new Query().setDbName(Watching.DB_WATCHING).setKeyClass(WatchingKey.class).setValueBaseClass(Watching.class));
        put(Watching.INDEX_WATCHING_USER_NO, new Query()
                .setDbName(Watching.INDEX_WATCHING_USER_NO)
                .setKeyClass(UserKey.class)
                .setValueBaseClass(Watching.class)
                .setKeyName(Watching.KEY_WATCHING_USER_NO)
                .setForeignKeyDbName(User.DB_USER)
                .setPrimaryDbName(Watching.DB_WATCHING));
        put(Watching.INDEX_WATCHING_VIDEO_NO, new Query()
                .setDbName(Watching.INDEX_WATCHING_VIDEO_NO)
                .setKeyClass(VideoKey.class)
                .setValueBaseClass(Watching.class)
                .setKeyName(Watching.KEY_WATCHING_VIDEO_NO)
                .setForeignKeyDbName(Video.DB_VIDEO)
                .setPrimaryDbName(Watching.DB_WATCHING));
    }

    private void deck() {
        put(Deck.DB_DECK, new Query().setDbName(Deck.DB_DECK).setKeyClass(DeckKey.class).setValueBaseClass(Deck.class));
        put(Deck.INDEX_DECK_USER_NO, new Query()
                .setDbName(Deck.INDEX_DECK_USER_NO)
                .setKeyClass(UserKey.class)
                .setValueBaseClass(Deck.class)
                .setKeyName(Deck.KEY_DECK_USER_NO)
                .setForeignKeyDbName(User.DB_USER)
                .setPrimaryDbName(Deck.DB_DECK));
        put(Deck.INDEX_DECK_CONTENTS_NO, new Query()
                .setDbName(Deck.INDEX_DECK_CONTENTS_NO)
                .setKeyClass(ContentsKey.class)
                .setValueBaseClass(Deck.class)
                .setKeyName(Deck.KEY_DECK_CONTENTS_NO)
                .setForeignKeyDbName(Contents.DB_CONTENTS)
                .setPrimaryDbName(Deck.DB_DECK));
    }

    private void adminConfig() {
        put(AdminConfig.DB_ADMIN_CONFIG,
                new Query().setDbName(AdminConfig.DB_ADMIN_CONFIG)
                        .setKeyClass(AdminConfigKey.class)
                        .setValueBaseClass(AdminConfig.class));
    }

}