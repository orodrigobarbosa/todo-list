package toDo_List_Application.to_do_list.domain.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class StatusValidator implements ConstraintValidator<ValidStatus, String> {
    @Override
    public void initialize(ValidStatus constraintAnnotation) {

    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext context) {

        if (status == null) {
            return false;
        }
        try {

            StatusEnum.valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException s) {
            return false;
        }
    }
}
