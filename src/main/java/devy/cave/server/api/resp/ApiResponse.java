package devy.cave.server.api.resp;

public class ApiResponse {

    private ApiStatus apiStatus;
    private Object contents;

    public ApiResponse(ApiStatus apiStatus, Object contents) {
        this.apiStatus = apiStatus;
        this.contents = contents;
    }

    public ApiStatus getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(ApiStatus apiStatus) {
        this.apiStatus = apiStatus;
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
                ", contents=" + contents +
                '}';
    }
}
