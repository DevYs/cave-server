package devy.cave.server.api.resp;

public class ApiStatus {

    public static final ApiStatus SUCCESS = new ApiStatus(ApiStatusCode.SUCCESS, ApiStatusCode.SUCCESS_MSG);
    public static final ApiStatus SUCCESS_LOGIN = new ApiStatus(ApiStatusCode.SUCCESS, ApiStatusCode.LOGIN_SUCCESS_MSG);
    public static final ApiStatus UNAUTHORIZED = new ApiStatus(ApiStatusCode.UNAUTHORIZED, ApiStatusCode.UNAUTHORIZED_MSG);
    public static final ApiStatus FAILED_LOGIN = new ApiStatus(ApiStatusCode.LOGIN_FAILED, ApiStatusCode.LOGIN_FAILED_MSG);

    protected int statusCode;
    protected String statusMessage;

    public ApiStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "ApiStatus{" +
                "statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
