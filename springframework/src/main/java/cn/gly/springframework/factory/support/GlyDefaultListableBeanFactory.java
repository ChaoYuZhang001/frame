package cn.gly.springframework.factory.support;

import cn.gly.springframework.factory.GlyAutowireCapableBeanFactory;
import cn.gly.springframework.factory.config.GlyConfigurableListableBeanFactory;
import cn.gly.springframework.ioc.GlyBeanDefinition;
import cn.gly.springframework.registry.GlyBeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public class GlyDefaultListableBeanFactory extends GlyAbstractAutowireCapableBeanFactory implements GlyConfigurableListableBeanFactory, GlyAutowireCapableBeanFactory, GlyBeanDefinitionRegistry {

    private Map<String, GlyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(255);

    @Override
    public GlyBeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, GlyBeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public List<GlyBeanDefinition> getBeanDefinitions(String beanName) {
        List<GlyBeanDefinition> beanDefinitionList = new ArrayList<>();
        this.beanDefinitionMap.values().forEach(beanDefinition -> beanDefinitionList.add(beanDefinition));
        return beanDefinitionList;
    }

    @Override
    public void createBean() {

    }

    @Override
    public List getBeanByType(Class clazz) {
        return null;
    }

    @Override
    public List getBeanByName(String name) {
        return null;
    }

    @Override
    public void addSingleton(String beanName, Object singletonObject) {
        super.addSingleton(beanName, singletonObject);
    }
}