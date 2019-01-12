package devy.cave.server.api.resp;

public class StatusLogin extends ApiStatus {

    private String apiKey;

    public StatusLogin(int statusCode, String statusMessage, String apiKey) {
        super(statusCode, statusMessage);
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "StatusLogin{" +
                "apiKey='" + apiKey + '\'' +
                ", statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
