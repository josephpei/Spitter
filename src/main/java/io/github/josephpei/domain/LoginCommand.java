package io.github.josephpei.domain;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginCommand {
    @NotNull
    @Size(min=5, max=20)
    private String name;

    @NotNull
    @Size(min=5, max=20)
    private String pass;

    @NotNull
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
