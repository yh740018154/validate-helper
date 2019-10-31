package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.validation.AbstractObjectValidatorTemplete;
import com.alibaba.fastjson.util.TypeUtils;

/**
 * @author yangheng
 * @Classname IntValidator
 * @Description TODO
 * @Date 2019/10/31 8:29
 * @group smart video north
 */
public class IntValidator extends AbstractObjectValidatorTemplete {

    private int intValue;

    public IntValidator(ValidateField vf, Object object) {
        super(vf, object);
    }

    @Override
    protected String validate() {
        if (vf.maxValue() > 0) {
            if (intValue > vf.maxValue()) {
                return "数字[" + intValue + "]超过最大值,最大值是:" + vf.maxValue();
            }
        }
        if (vf.minValue() > 0) {
            if (intValue < vf.minValue()) {
                return "数字[" + intValue + "]小于最小值,最小值是:" + vf.minValue();
            }
        }
        return null;
    }

    @Override
    protected void cast() {
        intValue = TypeUtils.castToInt(super.object);
    }
}
