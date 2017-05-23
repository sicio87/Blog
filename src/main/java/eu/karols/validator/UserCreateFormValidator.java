package eu.karols.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import eu.karols.form.UserCreateForm;
import eu.karols.service.user.UserService;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateLogin(errors, form);
        validateName(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateLogin(Errors errors, UserCreateForm form) {
        if (userService.getUserByLogin(form.getLogin()).isPresent()) {
            errors.reject("login.exists", "User with this login already exists");
        }
    }
    
    private void validateName(Errors errors, UserCreateForm form) {
        if (userService.getUserByName(form.getName()).isPresent()) {
            errors.reject("name.exists", "User with this name already exists");
        }
    }
    
    private void validateEmail(Errors errors, UserCreateForm form) {
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}