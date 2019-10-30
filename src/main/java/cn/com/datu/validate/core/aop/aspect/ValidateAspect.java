package cn.com.datu.validate.core.aop.aspect;

import cn.com.datu.validate.core.aop.annotation.ValidateGroup;
import cn.com.datu.validate.core.common.ResponseMsg;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.FieldValidation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author yangheng
 * @Classname ValidateAspect
 * @Description TODO
 * @Date 2019/10/27 16:49
 * @group smart video north
 */
@Component
@Aspect
public class ValidateAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateAspect.class);

    @Autowired
    private ReflectHandler reflectHandler;

    @Autowired
    private FieldValidation fieldValidation;


    @Around("@annotation(cn.com.datu.validate.core.aop.annotation.ValidateGroup)")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint) {
        //获取方法名
        String methodName = null;
        //获取controller对象
        Object target = null;
        //获取到改注解的方法
        Method method = null;
        //获取到入参列表
        Object[] args = null;
        //参数校验注解类
        ValidateGroup annotation = null;
        //返回的校验信息
        ResponseMsg responseMsg = null;

        try {
            //获取方法名
            methodName = proceedingJoinPoint.getSignature().getName();
            //获取对象
            target = proceedingJoinPoint.getTarget();
            //获取到改注解的方法
            method = reflectHandler.getMethodByMethodName(target.getClass(), methodName);
            //获取到参数
            args = proceedingJoinPoint.getArgs();
            annotation = (ValidateGroup) reflectHandler.getAnnotationByMethod(ValidateGroup.class, method);
            LOGGER.info("准备校验{}方法的参数...,\n 当前类是:{},\n 注解约束条件:{}", methodName, target, annotation);
            //校验：此处代码需要优化
            responseMsg = fieldValidation.validateFileds(annotation.validateFields(), args);
        } catch (Exception e) {
            LOGGER.error("参数校验过程中发生错误，异常信息为：{}", e.getMessage());
        } finally {
            if ("200".equals(responseMsg.getCode())) {
                LOGGER.info("验证通过");
                try {
                    return proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    LOGGER.error("验证通过,方法执行失败，异常信息为：{}", throwable.getMessage());
                }
            } else {
                LOGGER.error("参数校验未通过,异常信息:{}", responseMsg.getMsg());
                Class<?> returnType = method.getReturnType();
                //可能还有其他情况，后续补充
                if (returnType == String.class) {
                    return responseMsg.getMsg();
                } else {
                    return responseMsg;
                }
            }
        }
        return null;
    }

}
