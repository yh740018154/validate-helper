package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.common.ResponseMsg;
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


    //此方法需要优化，lamda表达式。且不单单返回true，false，应该携带校验信息返回
    @Override
    public ResponseMsg validateFileds(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        ResponseMsg responseMsg = new ResponseMsg("200", "success");
        for (ValidateField validateField : validateFields) {
            Object arg = null;
            if (Constant.STRING_DEFAULT_VALUE.equals(validateField.fieldName())) {
                arg = args[validateField.index()];
            } else {
                arg = reflectHandler.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
            }
            String msg= getResponseMsg(validateField, arg);
            if (null!= msg) {
                responseMsg.setCode("500");
                responseMsg.setMsg(msg);
            }
        }
        return responseMsg;
    }

    private String getResponseMsg(ValidateField validateField, Object arg) {
        //对象校验
        if (validateField.notNull()) {
            if (Constant.OBJECT_DEFAULT_VALUE == arg) {
                return "字段为空";
            } else {
                if (Constant.OBJECT_DEFAULT_VALUE == arg) {
                    return null;
                }
            }

            //字符串校验
            if (validateField.maxLength() > 0) {
                int length = ((String) arg).length();
                if (((String) arg).length() > validateField.maxLength()) {
                    return "字符串长度太长";
                }
            }


            if (validateField.minLength() > 0) {
                if (((String) arg).length() < validateField.minLength()) {
                    return "字符串长度太短";
                }
            }

            //校验正则表达式
            if (!Constant.STRING_DEFAULT_VALUE.equals(validateField.regexPattern())) {

                if (arg instanceof String) {
                    if (!((String) arg).matches(validateField.regexPattern())) {
                        return "字符串正则表达式未通过";
                    }
                }
            }
            //数字类型校验
            if (validateField.maxValue() > 0) {
                if ((Integer) arg > validateField.maxValue()) {
                    return "数字超过最大值";
                }
            }

            if (validateField.minValue() > 0) {
                if ((Integer) arg < validateField.minValue()) {
                    return  "数字小于最小值";
                }
            }
            //....未完待续
        }
        return null;
    }

}
