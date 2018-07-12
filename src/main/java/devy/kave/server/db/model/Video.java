package devy.kave.server.db.model;

import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Video implements Serializable, MarshalledTupleKeyEntity {

    private static final long serialVersionUID = 1L;

    public static final String DB_VIDEO = "db_video";
    public static final String INDEX_VIDEO_CONTENTS_NO = "index_video_by_contents_no";

    public static final String KEY_VIDEO_CONTENTS_NO = "key_video_contents_no";

    private transient String videoNo;
    private String contentsNo;

    @Size(max = 2000)
    private String videoPosterUrl;

    @NotEmpty
    private String shareLinkUrl;

    @NotEmpty
    private String videoName;

    @Null
    private String videoUrl;

    private String image;

    private String subtitle;

    private Contents contents;

    public Video() {}

    public Video(String videoNo, String contentsNo, String videoPosterUrl, String shareLinkUrl, String videoName, String subtitle) {
        this.videoNo = videoNo;
        this.contentsNo = contentsNo;
        this.videoPosterUrl = videoPosterUrl;
        this.shareLinkUrl = shareLinkUrl;
        this.videoName = videoName;
        this.subtitle = subtitle;
    }

    public Video(String videoNo, String contentsNo, String videoPosterUrl, String shareLinkUrl, String videoName, String subtitle, Contents contents) {
        this.videoNo = videoNo;
        this.contentsNo = contentsNo;
        this.videoPosterUrl = videoPosterUrl;
        this.shareLinkUrl = shareLinkUrl;
        this.videoName = videoName;
        this.subtitle = subtitle;
        this.contents = contents;
    }

    final void setKey(String videoNo) {
        this.videoNo = videoNo;
    }

    public final String getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(String videoNo) {
        this.videoNo = videoNo;
    }

    public final String getContentsNo() {
        return contentsNo;
    }

    public void setContentsNo(String contentsNo) {
        this.contentsNo = contentsNo;
    }

    public final String getVideoPosterUrl() {
        return videoPosterUrl;
    }

    public void setVideoPosterUrl(String videoPosterUrl) {
        this.videoPosterUrl = videoPosterUrl;
    }

    public String getShareLinkUrl() {
        return shareLinkUrl;
    }

    public void setShareLinkUrl(String shareLinkUrl) {
        this.shareLinkUrl = shareLinkUrl;
    }

    public final String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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
                ", shareLinkUrl='" + shareLinkUrl + '\'' +
                ", videoName='" + videoName + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", image='" + image + '\'' +
//                ", subtitle='" + subtitle + '\'' +
                ", contents=" + contents +
                '}';
    }

    @Override
    public void marshalPrimaryKey(TupleOutput tupleOutput) {
        tupleOutput.writeString(this.videoNo);
    }

    @Override
    public void unmarshalPrimaryKey(TupleInput tupleInput) {
        this.videoNo = tupleInput.readString();
    }

    @Override
    public boolean marshalSecondaryKey(String s, TupleOutput keyOutput) {
        if(s.equals(KEY_VIDEO_CONTENTS_NO)) {
            keyOutput.writeString(this.contentsNo);
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
