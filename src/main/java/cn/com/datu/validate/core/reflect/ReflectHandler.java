package cn.com.datu.validate.core.reflect;

import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public interface ReflectHandler {


    /**
     * 通过方法名，获取方法
     * @param clazz
     * @param methodName
     * @return
     */
    Method getMethodByMethodName(Class clazz, String methodName);

    /**
     * 获取注解对象
     * @param clazz
     * @param method
     * @return
     */
    Annotation getAnnotationByMethod(Class clazz, Method method);

    /**
     * 根据对象和属性名得到对应的值
     *
     * @return
     */
    Object getFieldByObjectAndFileName(Object tarObject, String fileName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    /**
     * 拼接getter方法，获取对应参数的值
     * @param fieldName
     * @return
     */
    String getGetterMethodByFieldName(String fieldName);


}
