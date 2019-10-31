package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.common.ResponseMsg;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.AbstractObjectValidator;
import cn.com.datu.validate.core.validation.FieldValidation;
import cn.com.datu.validate.core.validation.ValidatorFactory;
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
            String msg = getResponseMsg(validateField, arg);
            if (null != msg) {
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
        LOGGER.info("开始校验参数{}，校验规则{}", arg, validateField);
        AbstractObjectValidator validator = ValidatorFactory.getValidator(arg, validateField);
        return validator.service();

    }
}
