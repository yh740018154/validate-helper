package cn.com.datu.validate.core.aop.aspect;

import cn.com.datu.validate.core.aop.annotation.ValidateGroup;
import cn.com.datu.validate.core.reflect.ReflectHandler;
import cn.com.datu.validate.core.validation.FieldValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
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

    private ReflectHandler<ValidateAspect> reflectHandler;

    private FieldValidator fieldValidator;

    public ValidateAspect() {
        this.reflectHandler = new ReflectHandler<ValidateAspect>();
        this.fieldValidator = new FieldValidator();
    }

    @Around("@annotation(cn.com.datu.validate.core.aop.annotation.ValidateGroup)")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint) {
        //获取方法名
        String methodName = null;
        Object target = null;
        Method method = null;
        Object[] args = null;
        ValidateGroup annotation = null;
        boolean validateFileds = false;


        try {
            methodName = proceedingJoinPoint.getSignature().getName();
            //获取对象
            target = proceedingJoinPoint.getTarget();
            //获取到改注解的方法
            method = reflectHandler.getMethodByMethodName(target.getClass(), methodName);
            //获取到参数
            args = proceedingJoinPoint.getArgs();
            annotation = (ValidateGroup) reflectHandler.getAnnotationByMethod(ValidateGroup.class, method);
            validateFileds = fieldValidator.validateFiled(annotation.validateFields(), args);
        } catch (NoSuchMethodException e) {
                validateFileds=false;
        } catch (IllegalAccessException e) {
            validateFileds=false;
        } catch (InvocationTargetException e) {
            validateFileds=false;
        } finally {
            if (validateFileds) {
                LOGGER.info("验证通过");
                try {
                    return proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    LOGGER.error("验证通过,方法执行失败");
                }
            }else {
                LOGGER.warn("参数校验未通过");
                Class<?> returnType = method.getReturnType();
                //可能还有其他情况，后续补充
                if(returnType==String.class){
                    return "参数校验失败";
                }else {
                    return null;
                }
            }
        }
        return null;
    }

}
