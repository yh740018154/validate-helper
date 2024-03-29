package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.validation.impl.IntValidator;
import cn.com.datu.validate.core.validation.impl.StringValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorFactory.class);

    public static AbstractObjectValidatorTemplete getValidator(Object obj, ValidateField vf) {
        String className = obj.getClass().getName();
        if (Constant.STRING_CLASS_NAME.equals(className)) {
            return new StringValidator(vf, obj);
        } else if (Constant.INTEGER_CLASS_NAME.equals(className)) {
            return new IntValidator(vf, obj);
        }
        return null;
    }
}