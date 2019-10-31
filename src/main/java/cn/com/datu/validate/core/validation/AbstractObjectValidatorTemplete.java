package cn.com.datu.validate.core.validation;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;

/***
 * 模板类
 * @param
 */
public abstract class AbstractObjectValidatorTemplete {

    protected ValidateField vf;
    protected Object object;


    protected AbstractObjectValidatorTemplete(ValidateField vf, Object obj) {
        this.vf = vf;
        this.object = object;
    }

    protected String preValidate() {
        if (vf.notNull()) {
            if (Constant.OBJECT_DEFAULT_VALUE == object) {
                return "字段为空";
            } else {
                if (Constant.OBJECT_DEFAULT_VALUE == object) {
                    return null;
                }
            }
        }
        return null;
    }

    protected abstract String validate();

    protected abstract void cast();


    /**
     * 模板方法
     *
     * @return
     */
    public final String service() {
        preValidate();
        cast();
        return validate();
    }
}
