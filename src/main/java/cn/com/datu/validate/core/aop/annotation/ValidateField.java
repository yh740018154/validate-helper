package cn.com.datu.validate.core.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangheng
 * @Classname ValidateField
 * @Description TODO
 * @Date 2019/10/27 17:40
 * @group smart video north
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateField {

    /**
     * 参数的索引
     *
     * @return
     */
    int index() default -1;

    /**
     * 参数的名字，如果参数为基本数据类型或者String,就不用使用这个参数;
     * 如果参数是对象，要验证对象里面某个属性，就需要使用该参数
     *
     * @return
     */
    String fieldName() default "";

    /**
     * 使用正则校验
     *
     * @return
     */
    String regexPattern() default "";

    /**
     * 不能为空
     *
     * @return
     */
    boolean notNull() default false;

    /**
     * 字段支持的最小长度
     * @return
     */
    int minLength() default -1;

    /**
     * 字段支持的最大长度
     * @return
     */
    int maxLength() default -1;

    /**
     * 字段支持的最大值
     * @return
     */
    int minValue() default -1;

    /**
     * 字段支持的最小值
     * @return
     */
    int maxValue() default -1;
}
