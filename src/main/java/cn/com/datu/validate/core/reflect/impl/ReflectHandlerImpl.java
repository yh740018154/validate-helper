package cn.com.datu.validate.core.reflect.impl;

import cn.com.datu.validate.core.reflect.ReflectHandler;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author yangheng
 * @Classname ReflectHandlerImpl
 * @Description TODO
 * @Date 2019/10/27 18:05
 * @group smart video north
 */
@Component
public class ReflectHandlerImpl<T> implements ReflectHandler {

    public Method getMethodByMethodName(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        //学习使用lamda表达式获取元素
        return Arrays.stream(methods).filter(x -> x.getName().equals(methodName)).collect(Collectors.toList()).get(0);
    }


    public Annotation getAnnotationByMethod(Class clazz, Method method) {
        Annotation[] annotations = method.getAnnotations();
        return Arrays.stream(annotations).filter(x -> x.annotationType().equals(clazz)).collect(Collectors.toList()).get(0);

    }



    public Object getFieldByObjectAndFileName(Object tarObject, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String temp[]=fileName.split("\\.");
        Object arg=tarObject;
        for (int i = 0; i <temp.length ; i++) {
            Method method = arg.getClass().getMethod(getGetterMethodByFieldName(temp[i]));
            arg=method.invoke(arg);
        }

        return arg;
    }

    public String getGetterMethodByFieldName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

    }
}
