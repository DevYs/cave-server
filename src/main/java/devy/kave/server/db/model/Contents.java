package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Contents implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_CONTENTS = "db_contents";
    public static final String INDEX_CONTENTS_CHANNEL_NO = "index_contents_channel_no";
    public static final String KEY_CONTENTS_CHANNEL_NO = "key_contents_channel_no";

    private transient long contentsNo;

    private long channelNo;

    @Size(max = 2000)
    private String contentsPosterUrl;

    @NotNull
    @Size(min = 1, max = 50)
    private String contentsName;

    @Size(max = 20)
    private String genre;

    @Size(max = 50)
    private String nation;

    @Size(max = 20)
    private String releaseDate;

    @Size(max = 20)
    private String runningTime;

    @Size(max = 50)
    private String director;

    @Size(max = 100)
    private String actor;

    private String story;

    public Contents() {}

    public Contents(long channelNo, String contentsPosterUrl, String contentsName, String genre, String nation, String releaseDate, String runningTime, String director, String actor, String story) {
        this.channelNo = channelNo;
        this.contentsPosterUrl = contentsPosterUrl;
        this.contentsName = contentsName;
        this.genre = genre;
        this.nation = nation;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.director = director;
        this.actor = actor;
        this.story = story;
    }

    public Contents(long contentsNo, long channelNo, String contentsPosterUrl, String contentsName, String genre, String nation, String releaseDate, String runningTime, String director, String actor, String story) {
        this.contentsNo = contentsNo;
        this.channelNo = channelNo;
        this.contentsPosterUrl = contentsPosterUrl;
        this.contentsName = contentsName;
        this.genre = genre;
        this.nation = nation;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.director = director;
        this.actor = actor;
        this.story = story;
    }

    public final long getContentsNo() {
        return contentsNo;
    }

    public void setContentsNo(long contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final long getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(long channelNo) {
        this.channelNo = channelNo;
    }

    public final String getContentsPosterUrl() {
        return contentsPosterUrl;
    }

    public void setContentsPosterUrl(String contentsPosterUrl) {
        this.contentsPosterUrl = contentsPosterUrl;
    }

    public final String getContentsName() {
        return contentsName;
    }

    public void setContentsName(String contentsName) {
        this.contentsName = contentsName;
    }

    public final String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public final String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public final String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public final String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public final String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public final String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public final String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "Contents{" +
                "channelNo=" + channelNo +
                ", contentsNo=" + contentsNo +
                ", contentsPosterUrl='" + contentsPosterUrl + '\'' +
                ", contentsName='" + contentsName + '\'' +
                ", genre='" + genre + '\'' +
                ", nation='" + nation + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", story='" + story + '\'' +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.contentsNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.contentsNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        if(s.equals(Contents.KEY_CONTENTS_CHANNEL_NO)) {
            tupleOutput.writeLong(this.channelNo);
            return true;
        } else {
            throw new UnsupportedOperationException(s);
        }
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
