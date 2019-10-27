package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.reflect.ReflectHandler;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yangheng
 * @Classname FieldValidator
 * @Description TODO
 * @Date 2019/10/27 19:06
 * @group smart video north
 */
public class FieldValidator {

    private ReflectHandler reflectHandler;

    public FieldValidator() {
        this.reflectHandler = new ReflectHandler();
    }

    public boolean validateFiled(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object arg = null;
        for (ValidateField validateField : validateFields) {
            arg = reflectHandler.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
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
