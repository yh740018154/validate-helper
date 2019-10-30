package cn.com.datu.validate.core.validation.impl;

import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.Constant;
import cn.com.datu.validate.core.validation.AbstractObjectValidator;
import com.alibaba.fastjson.util.TypeUtils;

public class StringValidator<T> extends AbstractObjectValidator<T> {

    private ValidateField validateField;

    public StringValidator(ValidateField vf) {
        this.validateField=vf;
    }

    @Override
    public String validate(T string) {
        //字符串校验
        if (validateField.maxLength() > 0) {
            int length = ((String) super.t).length();
            if (((String) t).length() > validateField.maxLength()) {
                return "字符串["+t+"]长度太长,最大长度是:"+validateField.maxLength();
            }
        }


        if (validateField.minLength() > 0) {
            if (((String) super.t).length() < validateField.minLength()) {
                return "字符串["+super.t+"]长度太短,最短长度是:"+validateField.minLength();
            }
        }

        //校验正则表达式
        if (!Constant.STRING_DEFAULT_VALUE.equals(validateField.regexPattern())) {

            if (super.t instanceof String) {
                if (!((String) super.t).matches(validateField.regexPattern())) {
                    return "字符串["+super.t+"]正则表达式未通过,正则表达式是:"+validateField.regexPattern();
                }
            }
        }


        return null;
    }

    @Override
    public T cast(T t) {
        return (T)TypeUtils.castToString(t);
    }
}
