package cn.gly.springframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public class ReflectUtils {

    public static Object invoke(Class clazz, String methodName, Object bean) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method declaredMethod = clazz.getDeclaredMethod(methodName);
        return declaredMethod.invoke(bean);
    }
}