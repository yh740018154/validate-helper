package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.common.ResponseMsg;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.FieldValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FieldValidationImpl.class);

    @Autowired
    private ReflectHandler reflectHandler;


    //此方法需要优化，lamda表达式。且不单单返回true，false，应该携带校验信息返回
    @Override
    public ResponseMsg validateFileds(ValidateField[] validateFields, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        ResponseMsg responseMsg = new ResponseMsg("200", "success");
        StringBuffer stringBuffer = new StringBuffer("");
        for (ValidateField validateField : validateFields) {
            Object arg = null;
            if (Constant.STRING_DEFAULT_VALUE.equals(validateField.fieldName())) {
                arg = args[validateField.index()];
            } else {
                LOGGER.info("fieldName是{}", validateField.fieldName());
                arg = reflectHandler.getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
            }
            String msg= getResponseMsg(validateField, arg);
            if (null!= msg) {
                LOGGER.warn("存在参数不符合规范{}", msg);
                responseMsg.setCode("500");
                stringBuffer.append(msg + ";");
            }
        }

        if ("500".equals(responseMsg.getCode())) {
            responseMsg.setMsg(stringBuffer.toString());
        }
        return responseMsg;
    }

    private String getResponseMsg(ValidateField validateField, Object arg) {

        LOGGER.info("开始校验参数{}，校验规则{}", arg,validateField);
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
                    return "字符串["+arg+"]长度太长,最大长度是:"+validateField.maxLength();
                }
            }


            if (validateField.minLength() > 0) {
                if (((String) arg).length() < validateField.minLength()) {
                    return "字符串["+arg+"]长度太短,最短长度是:"+validateField.minLength();
                }
            }

            //校验正则表达式
            if (!Constant.STRING_DEFAULT_VALUE.equals(validateField.regexPattern())) {

                if (arg instanceof String) {
                    if (!((String) arg).matches(validateField.regexPattern())) {
                        return "字符串["+arg+"]正则表达式未通过,正则表达式是:"+validateField.regexPattern();
                    }
                }
            }

            //数字类型校验
            if (validateField.maxValue() > 0) {
                if ((Integer) arg > validateField.maxValue()) {
                    return "数字["+arg+"]超过最大值,最大值是:"+validateField.maxValue();
                }
            }

            if (validateField.minValue() > 0) {
                if ((Integer) arg < validateField.minValue()) {
                    return  "数字["+arg+"]小于最小值,最小值是:"+validateField.minValue();
                }
            }
            //....未完待续
        }
        return null;
    }

}
