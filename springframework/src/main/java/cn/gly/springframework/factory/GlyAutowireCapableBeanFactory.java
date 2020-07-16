package cn.gly.springframework.factory;

/**
 * 〈一句话功能简述〉<br>
 * 〈提供对BeanFactory 中bean进行装配的功能〉
 *
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public interface GlyAutowireCapableBeanFactory extends GlyBeanFactory {

    void createBean();
}