package me.pingcai.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
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

    String message() default "输入的姓名有误";

    int max() default 12;

    int min() default 4;

    String regex() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

class NameValidator implements ConstraintValidator<NameCheck, String> {

    private NameCheck nameCheck;

    @Override
    public void initialize(NameCheck constraintAnnotation) {
        // 初始化操作
        nameCheck = constraintAnnotation;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        boolean result = true;
        if (StringUtils.isNotBlank(nameCheck.regex())){
            result = Pattern.matches(nameCheck.regex(),name);
        }
        return result && name.length() >= nameCheck.min() && name.length() <= nameCheck.max();
    }
}