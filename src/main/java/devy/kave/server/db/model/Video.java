package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Video implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_VIDEO = "db_video";
    public static final String INDEX_VIDEO_CONTENTS_NO = "index_video_by_contents_no";

    public static final String KEY_VIDEO_CONTENTS_NO = "key_video_contents_no";

    private transient long videoNo;
    private long contentsNo;
    private String videoPosterUrl;
    private String videoUrl;
    private String videoName;
    private String subtitle;
    private Contents contents;

    public Video() {}

    public Video(long videoNo, long contentsNo, String videoPosterUrl, String videoUrl, String videoName, String subtitle) {
        this.videoNo = videoNo;
        this.contentsNo = contentsNo;
        this.videoPosterUrl = videoPosterUrl;
        this.videoUrl = videoUrl;
        this.videoName = videoName;
        this.subtitle = subtitle;
    }

    public Video(long videoNo, long contentsNo, String videoPosterUrl, String videoUrl, String videoName, String subtitle, Contents contents) {
        this.videoNo = videoNo;
        this.contentsNo = contentsNo;
        this.videoPosterUrl = videoPosterUrl;
        this.videoUrl = videoUrl;
        this.videoName = videoName;
        this.subtitle = subtitle;
        this.contents = contents;
    }

    public final long getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(long videoNo) {
        this.videoNo = videoNo;
    }

    public final long getContentsNo() {
        return contentsNo;
    }

    public void setContentsNo(long contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final String getVideoPosterUrl() {
        return videoPosterUrl;
    }

    public void setVideoPosterUrl(String videoPosterUrl) {
        this.videoPosterUrl = videoPosterUrl;
    }

    public final String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public final String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public final String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public final Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoNo=" + videoNo +
                ", contentsNo=" + contentsNo +
                ", videoPosterUrl='" + videoPosterUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoName='" + videoName + '\'' +
//                ", subtitle='" + subtitle + '\'' +
                ", contents=" + contents +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeLong(this.videoNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.videoNo = tupleInput.readLong();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput keyOutput) {
        if(s.equals(KEY_VIDEO_CONTENTS_NO)) {
            keyOutput.writeLong(this.contentsNo);
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
