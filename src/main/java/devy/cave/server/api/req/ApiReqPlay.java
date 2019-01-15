package devy.cave.server.api.req;

public class ApiReqPlay extends ApiRequest {

    private String videoNo;

    public ApiReqPlay(String videoNo) {
        this.videoNo = videoNo;
    }

    public String getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(String videoNo) {
        this.videoNo = videoNo;
    }

    @Override
    public String toString() {
        return "ApiReqPlay{" +
                "videoNo='" + videoNo + '\'' +
                '}';
    }
}
