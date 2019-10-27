package cn.com.datu.validate.core.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangheng
 * @Classname ValidateGroup
 * @Description TODO
 * @Date 2019/10/27 16:04
 * @group smart video north
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateGroup {
    ValidateField[] validateFields();
}
