package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
@Component
public interface FieldValidation {

    /**
     * 校验参数
     * @param validateFields
     * @param args
     * @return
     */
    boolean validateFiled(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
