package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.validation.AbstractObjectValidatorTemplete;
import com.alibaba.fastjson.util.TypeUtils;

public class StringValidatorTemplete extends AbstractObjectValidatorTemplete {

    private String string;

    public StringValidatorTemplete(ValidateField vf, Object object) {
        super(vf, object);
    }

    @Override
    protected String validate() {
        if (vf.maxLength() > 0) {
            if (string.length() > vf.maxLength()) {
                return "字符串[" + string + "]长度太长,最大长度是:" + vf.maxLength();
            }
        }
        if (vf.minLength() > 0) {
            if (string.length() < vf.minLength()) {
                return "字符串[" + string + "]长度太短,最短长度是:" + vf.minLength();
            }
        }
        //校验正则表达式
        if (!Constant.STRING_DEFAULT_VALUE.equals(vf.regexPattern())) {
            if (!string.matches(vf.regexPattern())) {
                return "字符串[" + string + "]正则表达式未通过,正则表达式是:" + vf.regexPattern();
            }
        }
        return null;
    }

    @Override
    protected void cast() {
        string = TypeUtils.castToString(object);
    }
}
