package cn.com.datu.validate.core.aop.annotation;

import cn.com.datu.validate.core.common.Constant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangheng
 * @Classname ValidateField
 * @Description ：for example
 *      *@ValidateGroup(validateFields = {
 *      *         @ValidateField(index = 0, notNull = true,maxLength = 19,fieldName = "id",regexPattern = Constant.IDENTITY_CARD_PATTERN),
 *      *         @ValidateField(index = 0,notNull = true,maxValue = 10,fieldName = "age")
 *      * })
 *      * @ResponseBody
 *      * @RequestMapping("/hi")
 *      * public String validate(TestEntity testEntity) {
 * @Date 2019/10/27 17:40
 * @group smart video north
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateField {

    /**
     * 参数的索引
     * 例如：此处的index为0，参数列表只有一个
     *
     * @return
     */
    int index() default Constant.INT_DEFAULT_VALUE;

    /**
     * 参数的名字，如果参数为基本数据类型或者String,就不用使用这个参数;
     * 如果参数是对象，要验证对象里面某个属性，就需要使用该参数
     *
     * @return
     */
    String fieldName() default Constant.STRING_DEFAULT_VALUE;

    /**
     * 使用正则校验
     *
     * @return
     */
    String regexPattern() default  Constant.STRING_DEFAULT_VALUE;

    /**
     * 不能为空
     *
     * @return
     */
    boolean notNull() default  Constant.BOOLEAN_DEFAULT_VALUE;

    /**
     * 字段支持的最小长度
     * @return
     */
    int minLength() default Constant.INT_DEFAULT_VALUE;

    /**
     * 字段支持的最大长度
     * @return
     */
    int maxLength() default Constant.INT_DEFAULT_VALUE;

    /**
     * 字段支持的最大值
     * @return
     */
    int minValue() default Constant.INT_DEFAULT_VALUE;

    /**
     * 字段支持的最小值
     * @return
     */
    int maxValue() default Constant.INT_DEFAULT_VALUE;
}
