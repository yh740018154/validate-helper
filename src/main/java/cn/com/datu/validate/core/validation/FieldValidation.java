package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.ResponseMsg;
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
    ResponseMsg validateFileds(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
