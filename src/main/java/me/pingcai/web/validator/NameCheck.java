package me.pingcai.web.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

/**
 * @author huangpingcai
 * @since 2018/9/2 00:45
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface NameCheck {

    String message() default "姓名不合法";

    int max() default 12;

    int min() default 4;

    String regex() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

class NameValidator implements ConstraintValidator<NameCheck, String> {

    private NameCheck nameCheck;

    private Pattern pattern;

    @Override
    public void initialize(NameCheck nameCheck) {
        this.nameCheck = nameCheck;
        if (StringUtils.isNotEmpty(nameCheck.regex())) {
            this.pattern = Pattern.compile(nameCheck.regex());
        }
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return false;
        }
        if (name.length() < nameCheck.min() || name.length() > nameCheck.max()) {
            return false;
        }
        if (pattern != null) {
            return pattern.matcher(name).matches();
        }
        return true;
    }
}