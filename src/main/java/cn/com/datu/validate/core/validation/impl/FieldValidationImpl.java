package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.FieldValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yangheng
 * @Classname FieldValidationImpl
 * @Description TODO
 * @Date 2019/10/27 19:06
 * @group smart video north
 */
@Component
public class FieldValidationImpl implements FieldValidation {

    @Autowired
    private ReflectHandler reflectHandler;


    public boolean validateFiled(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        for (ValidateField validateField : validateFields) {
            Object arg = null;
            if("".equals(validateField.fieldName())){
                arg=args[validateField.index()];
            }else {
                arg = reflectHandler.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
            }

            if (validateField.notNull()) {
                if (null == arg) {
                    return false;
                } else {
                    if (arg == null) {
                        return true;
                    }
                }
                if (validateField.maxLength() > 0) {
                    if (((String) arg).length() > validateField.maxLength()) {
                        return false;
                    }
                }

                //....还有的方法省略
            }
        }
        return true;
    }
}
