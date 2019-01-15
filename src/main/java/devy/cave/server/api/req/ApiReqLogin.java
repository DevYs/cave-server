package devy.cave.server.api.req;

import javax.validation.constraints.NotNull;

public class ApiReqLogin {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ApiReqLogin{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
