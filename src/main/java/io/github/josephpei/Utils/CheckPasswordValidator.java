package io.github.josephpei.Utils;

import io.github.josephpei.domain.RegisterCommand;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, RegisterCommand> {

    @Override
    public void initialize(CheckPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegisterCommand user, ConstraintValidatorContext context) {
        if(user == null) {
            return true;
        }

        //没有填密码
//        if(!StringUtils.hasText(user.getPassword())) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("{password.null}")
//                    .addPropertyNode("password")
//                    .addConstraintViolation();
//            return false;
//        }
//
//        if(!StringUtils.hasText(user.getConfirmation())) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("{password.confirmation.null}")
//                    .addPropertyNode("confirmation")
//                    .addConstraintViolation();
//            return false;
//        }

        //两次密码不一样
        if (!user.getPassword().trim().equals(user.getConfirmation().trim())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{password.confirmation.error}")
                    .addPropertyNode("confirmation")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
