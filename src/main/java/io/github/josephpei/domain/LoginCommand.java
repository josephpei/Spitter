package io.github.josephpei.domain;


import javax.validation.constraints.Size;

public class LoginCommand {
    @Size(min=5, max=20)
    private String username;

    @Size(min=5, max=20)
    private String password;

//    private String kaptcha;
//
//    public String getKaptcha() {
//        return kaptcha;
//    }
//
//    public void setKaptcha(String kaptcha) {
//        this.kaptcha = kaptcha;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
