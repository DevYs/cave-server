package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Contents implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    private transient long contentsNo;
    private long channelNo;
    private String contentsPosterUrl;
    private String contentsName;
    private String genre;
    private String nation;
    private String releaseDate;
    private String runningTime;
    private String director;
    private String actor;
    private String story;

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

    public final long getChannelNo() {
        return channelNo;
    }

    public final String getContentsPosterUrl() {
        return contentsPosterUrl;
    }

    public final String getContentsName() {
        return contentsName;
    }

    public final String getGenre() {
        return genre;
    }

    public final String getNation() {
        return nation;
    }

    public final String getReleaseDate() {
        return releaseDate;
    }

    public final String getRunningTime() {
        return runningTime;
    }

    public final String getDirector() {
        return director;
    }

    public final String getActor() {
        return actor;
    }

    public final String getStory() {
        return story;
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
        tupleOutput.writeLong(this.channelNo);
        tupleOutput.writeLong(this.contentsNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.channelNo = tupleInput.readLong();
        this.contentsNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

}
