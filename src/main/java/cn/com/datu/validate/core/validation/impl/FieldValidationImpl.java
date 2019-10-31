package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.common.ResponseMsg;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.AbstractObjectValidatorTemplete;
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
    public ResponseMsg validateFileds(ValidateField[] vfs, Object[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ResponseMsg responseMsg = new ResponseMsg("200", "success");
        StringBuffer stringBuffer = new StringBuffer("");
        for (ValidateField vf : vfs) {
            Object arg = null;
            if (Constant.STRING_DEFAULT_VALUE.equals(vf.fieldName())) {
                arg = args[vf.index()];
            } else {
                LOGGER.info("fieldName是{}", vf.fieldName());
                arg = reflectHandler.getFieldByObjectAndFileName(args[vf.index()], vf.fieldName());
            }
            String msg = getResponseMsg(vf, arg);
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

    private String getResponseMsg(ValidateField vf, Object arg) {
        LOGGER.info("开始校验参数{}，校验规则{}", arg, vf);
        AbstractObjectValidatorTemplete validator = ValidatorFactory.getValidator(arg, vf);
        return validator.service();

    }
}
