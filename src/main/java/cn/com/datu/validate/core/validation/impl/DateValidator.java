package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.validation.AbstractObjectValidatorTemplete;
import com.alibaba.fastjson.util.TypeUtils;

import java.util.Date;

/**
 * @author yangheng
 * @Classname DateValidator
 * @Description TODO
 * @Date 2019/10/31 11:38
 * @group smart video north
 */
public class DateValidator extends AbstractObjectValidatorTemplete {

    protected Date date;

    public DateValidator(ValidateField vf, Object obj) {
        super(vf, obj);
    }

    @Override
    protected String validate() {
        return null;
    }

    @Override
    protected void cast() {
        date= TypeUtils.castToDate(object);

    }
}
