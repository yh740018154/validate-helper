package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.ResponseMsg;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
@Component
public interface FieldValidation {

    /**
     * 校验参数
     * @param vf
     * @param args
     * @return
     */
    ResponseMsg validateFileds(ValidateField[] vf, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
