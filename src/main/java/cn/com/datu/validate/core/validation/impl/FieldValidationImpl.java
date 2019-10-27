package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.FieldValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

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


    //此方法需要优化，lamda表达式。且不单单返回true，false，应该携带校验信息返回
    public boolean validateFiled(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        for (ValidateField validateField : validateFields) {
            Object arg = null;
            if (Constant.STRING_DEFAULT_VALUE.equals(validateField.fieldName())) {
                arg = args[validateField.index()];
            } else {
                arg = reflectHandler.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
            }

            //对象校验
            if (validateField.notNull()) {
                if (Constant.OBJECT_DEFAULT_VALUE == arg) {
                    return false;
                } else {
                    if (Constant.OBJECT_DEFAULT_VALUE == arg) {
                        return true;
                    }
                }

                //字符串校验
                if (validateField.maxLength() > 0) {
                    int length = ((String) arg).length();
                    if (((String) arg).length() > validateField.maxLength()) {
                        return false;
                    }
                }


                if (validateField.minLength() > 0) {
                    if (((String) arg).length() < validateField.minLength()) {
                        return false;
                    }
                }

                //校验正则表达式
                if (!Constant.STRING_DEFAULT_VALUE.equals(validateField.regexPattern())) {

                    if (arg instanceof String) {
                        if (!((String) arg).matches(validateField.regexPattern())) {
                            return false;
                        }
                    }
                }
                //数字类型校验
                if (validateField.maxValue() > 0) {
                    if ((Integer) arg > validateField.maxValue()) {
                        return false;
                    }
                }

                if (validateField.minValue() > 0) {
                    if ((Integer) arg < validateField.minValue()) {
                        return false;
                    }
                }
                //....未完待续
            }

        }
        return true;
    }

}
