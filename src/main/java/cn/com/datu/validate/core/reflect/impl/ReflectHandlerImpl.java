package cn.com.datu.validate.core.reflect.impl;

import cn.com.datu.validate.core.reflect.ReflectHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author yangheng
 * @Classname ReflectHandlerImpl
 * @Description TODO
 * @Date 2019/10/27 18:05
 * @group smart video north
 */
@Component
public class ReflectHandlerImpl implements ReflectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectHandlerImpl.class);

    @Override
    public Method getMethodByMethodName(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        //学习使用lamda表达式获取元素
        return Arrays.stream(methods).filter(x -> x.getName().equals(methodName)).collect(Collectors.toList()).get(0);
    }

    @Override
    public Annotation getAnnotationByMethod(Class clazz, Method method) {
        Annotation[] annotations = method.getAnnotations();
        return Arrays.stream(annotations).filter(x -> x.annotationType().equals(clazz)).collect(Collectors.toList()).get(0);

    }


    @Override
    public Object getFieldByObjectAndFileName(Object tarObject, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String temp[]=fileName.split("\\.");
        Object arg=tarObject;
        for (int i = 0; i <temp.length ; i++) {
            Method method = arg.getClass().getMethod(getGetterMethodByFieldName(temp[i]));
            arg=method.invoke(arg);
            LOGGER.info("第{}次执行[{}]方法后，arg={}",i,method,arg);
        }
        return arg;
    }
    @Override
    public String getGetterMethodByFieldName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

    }
}
