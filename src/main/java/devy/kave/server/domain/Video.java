package devy.kave.server.domain;

import com.sleepycat.bind.tuple.MarshalledTupleEntry;
import com.sleepycat.bind.tuple.MarshalledTupleKeyEntity;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

import java.io.Serializable;

public class Video implements Serializable, MarshalledTupleKeyEntity {

    private transient long videoNo;
    private long contentsNo;
    private String videoPosterUrl;
    private String videoUrl;
    private String videoName;
    private String subtitle;
    private Contents contents;

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

    public final long getContentsNo() {
        return contentsNo;
    }

    public final String getVideoPosterUrl() {
        return videoPosterUrl;
    }

    public final String getVideoUrl() {
        return videoUrl;
    }

    public final String getVideoName() {
        return videoName;
    }

    public final String getSubtitle() {
        return subtitle;
    }

    public final Contents getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoNo=" + videoNo +
                ", contentsNo=" + contentsNo +
                ", videoPosterUrl='" + videoPosterUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoName='" + videoName + '\'' +
                ", subtitle='" + subtitle + '\'' +
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
    public boolean marshalSecondaryKey(String s, TupleOutput tupleOutput) {
        throw new UnsupportedOperationException(s);
    }

    @Override
    public boolean nullifyForeignKey(String s) {
        throw new UnsupportedOperationException(s);
    }

    public class VideoKey implements MarshalledTupleEntry {

        private long videoNo;

        public VideoKey(long videoNo) {
            this.videoNo = videoNo;
        }

        public final long getVideoNo() {
            return videoNo;
        }

        @Override
        public String toString() {
            return "VideoKey{" +
                    "videoNo=" + videoNo +
                    '}';
        }

        public VideoKey() {}

        @Override
        public void marshalEntry(TupleOutput tupleOutput) {
            tupleOutput.writeLong(this.videoNo);
        }

        @Override
        public void unmarshalEntry(TupleInput tupleInput) {
            this.videoNo = tupleInput.readLong();
        }
    }

}
