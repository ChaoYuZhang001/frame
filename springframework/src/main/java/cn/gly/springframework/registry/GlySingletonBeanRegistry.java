package cn.gly.springframework.registry;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装了对Bean 集合的操作〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public interface GlySingletonBeanRegistry {

    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object bean);
}