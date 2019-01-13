package devy.cave.server.api.resp;

import devy.cave.server.db.model.Channel;

import java.util.List;

public class ApiResponse {

    private ApiStatus apiStatus;
    private List<Channel> channelList;
    private Object contents;

    public ApiResponse(ApiStatus apiStatus, List<Channel> channelList, Object contents) {
        this.apiStatus = apiStatus;
        this.channelList = channelList;
        this.contents = contents;
    }

    public ApiStatus getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(ApiStatus apiStatus) {
        this.apiStatus = apiStatus;
    }

    public List<Channel> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Channel> channelList) {
        this.channelList = channelList;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "apiStatus=" + apiStatus +
                ", channelList=" + channelList +
                ", contents=" + contents +
                '}';
    }
}
