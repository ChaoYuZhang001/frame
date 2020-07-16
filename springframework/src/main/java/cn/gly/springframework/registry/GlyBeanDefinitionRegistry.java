package cn.gly.springframework.registry;

import cn.gly.springframework.ioc.GlyBeanDefinition;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装了对BeanDefinition 集合的操作〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public interface GlyBeanDefinitionRegistry extends GlyAliasRegistry {

    /**
     * 注册beanDefinition
     *
     * @param beanName
     * @return
     */
    GlyBeanDefinition getBeanDefinition(String beanName);

    /**
     * 根据名称获取  beanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, GlyBeanDefinition beanDefinition);

    /**
     * 获取所有的beanDefinition
     *
     * @param beanName
     * @return
     */
    List<GlyBeanDefinition> getBeanDefinitions(String beanName);
}