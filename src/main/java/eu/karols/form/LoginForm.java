package eu.karols.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    @Size(min=2, max=30, message = "Login size should be in the range [2...30]")
    private String login;

    @NotNull
    @Size(min=1, max=50)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}