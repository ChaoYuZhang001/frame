package cn.gly.springframework.factory;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈该接口提供可以对Spring容器进行列表化操作的功能〉
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public interface GlyListableBeanFactory extends GlyBeanFactory {

    /**
     * 根据指定的类型 去获取该类型和子类对应的所有Bean实例
     *
     * @param clazz
     * @return
     */
    List getBeanByType(Class clazz);

    List getBeanByName(String name);
}