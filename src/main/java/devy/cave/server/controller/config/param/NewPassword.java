package devy.cave.server.controller.config.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewPassword {

    @NotNull
    @Size(min = 4, max = 16)
    private String inputPassword;

    @NotNull
    @Size(min = 4, max = 16)
    private String newPassword;

    public String getInputPassword() {
        return inputPassword;
    }

    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean equals() {
        return this.inputPassword.equals(this.newPassword);
    }

    @Override
    public String toString() {
        return "NewPassword{" +
                "inputPassword='" + inputPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
