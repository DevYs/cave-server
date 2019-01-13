package devy.cave.server.api.req;

public class ApiRequest {

    private String authKey;
    private String userId;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "authKey='" + authKey + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
