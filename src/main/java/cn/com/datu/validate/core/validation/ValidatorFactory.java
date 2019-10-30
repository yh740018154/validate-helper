package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.validation.impl.IntegerValidator;
import cn.com.datu.validate.core.validation.impl.StringValidator;
import com.alibaba.fastjson.util.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValidatorFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorFactory.class);

    private static Map<String, AbstractObjectValidator> objectCache = new ConcurrentHashMap<>(0);

    private boolean registeValidator(String name, AbstractObjectValidator objectValidator) {
        if (!objectCache.containsKey(name)) {
            objectCache.put(name, objectValidator);
            return true;
        } else {
            return false;
        }
    }

    private AbstractObjectValidator getValidatorByName(String name) {
        if (objectCache.containsKey(name)) {
            return (AbstractObjectValidator) objectCache.get(name);
        } else {
            AbstractObjectValidator newInstance = null;
            try {
                newInstance = (AbstractObjectValidator) Class.forName(name).newInstance();
            } catch (Exception e) {
                LOGGER.error("类型转化异常，异常信息<{}>", e.getMessage());
            }
            objectCache.put(name, newInstance);
            return newInstance;
        }

    }

    public static AbstractObjectValidator getValidator(Object object, ValidateField validateField) {
        if (Constant.STRING_CLASS_NAME.equals(object.getClass().getName())) {
            String string = TypeUtils.castToString(object);
            return new StringValidator(validateField);
        }else if(Constant.INTEGER_CLASS_NAME.equals(object.getClass().getName())){
            return new IntegerValidator();
        }
        return null;
    }
}