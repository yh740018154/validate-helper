package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.validation.impl.IntValidator;
import cn.com.datu.validate.core.validation.impl.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorFactory.class);

    public static AbstractObjectValidator getValidator(Object object, ValidateField validateField) {
        String className = object.getClass().getName();
        if (Constant.STRING_CLASS_NAME.equals(className)) {
            return new StringValidator(validateField,object);
        } else if (Constant.INTEGER_CLASS_NAME.equals(className)) {
            return new IntValidator(validateField, object);
        }
        return null;
    }
}