package cn.gly.mybatis.util;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlyReflectUtil {

    public static Class<?> resolverType(String resultType) {
        try {
            Class<?> clazz = Class.forName(resultType);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}