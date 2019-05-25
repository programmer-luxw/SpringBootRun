package cn.luxw.app.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import lombok.extern.slf4j.Slf4j;

/**
 * 参数校验
 * @author yinxujun
 * @date 2019/3/22
 */

@Slf4j
public class ValidatorUtil {

    public static <T> void validate(T t) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (!constraintViolations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            int i = 0;
            int size = constraintViolations.size();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage() + (++i == size ? "" : "、"));
            }
            log.warn("==========参数验证出错！==========={}", errors);
            throw new RuntimeException(errors.toString());
        }
    }

    /**
     * bean校验
     *
     * @param isNull 是否空校验，true-是
     * @param t      bean对象
     * @param <T>    泛型对象
     */
    public static <T> void validate(boolean isNull, T t) {
        if (isNull && t == null) {
        	throw new RuntimeException("not null");
        }
        validate(t);
    }
}
