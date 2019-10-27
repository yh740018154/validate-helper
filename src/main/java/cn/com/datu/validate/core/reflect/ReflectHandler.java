package cn.com.datu.validate.core.reflect;

import cn.com.datu.validate.core.aop.annotation.ValidateField;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yangheng
 * @Classname ReflectHandler
 * @Description TODO
 * @Date 2019/10/27 18:05
 * @group smart video north
 */
public class ReflectHandler<T> {

    /**
     * 获取方法名
     *
     * @param clazz
     * @param methodName
     * @return
     */
    public Method getMethodByMethodName(Class clazz, String methodName) {
        Method[] methods = clazz.getDeclaredMethods();
        //学习使用lamda表达式获取元素
        return Arrays.stream(methods).filter(x -> x.getName().equals(methodName)).collect(Collectors.toList()).get(0);
    }

    /**
     * 获取注解对象
     *
     * @param clazz
     * @param method
     * @return
     */
    public Annotation getAnnotationByMethod(Class clazz, Method method) {
        Annotation[] annotations = method.getAnnotations();
        return Arrays.stream(annotations).filter(x -> x.annotationType().equals(clazz)).collect(Collectors.toList()).get(0);

    }


    /**
     * 根据对象和属性名得到对应的值
     *
     * @return
     */
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
