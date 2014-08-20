package io.github.josephpei.Utils;


import io.github.josephpei.domain.RegisterCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegistrationValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationValidator.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterCommand registerCommand = (RegisterCommand) o;
        if (!registerCommand.getConfirmation().equals(registerCommand.getPassword()))
            errors.rejectValue("confirmation", "password.confirmation.error");

    }
}
