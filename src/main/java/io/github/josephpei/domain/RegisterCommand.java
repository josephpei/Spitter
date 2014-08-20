package io.github.josephpei.domain;


import io.github.josephpei.Utils.CheckPassword;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//@ScriptAssert(script = "_this.confirmation==_this.password", lang = "javascript", alias = "_this",
//message="Password don't confirm")

//@CheckPassword
public class RegisterCommand {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public RegisterCommand() {

    }

    @Size(min=5, max=20, message="Username must be between 5 and 20 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces")
    private String username;

    private String fullname;

    @Size(min=6, max=20, message="Password must be between 6 and 20 characters long.")
    private String password;

    @Size(min=6, max=20, message="Password must be between 6 and 20 characters long.")
    private String confirmation;

    @Email
    private String email;
}
