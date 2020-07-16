package cn.gly.springframework.factory.support;

import cn.gly.springframework.factory.GlyBeanFactory;
import cn.gly.springframework.factory.config.GlyConfigurableBeanFactory;
import cn.gly.springframework.ioc.GlyBeanDefinition;
import cn.gly.springframework.registry.GlyDefaultSingletonBeanRegistry;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public abstract class GlyAbstractBeanFactory extends GlyDefaultSingletonBeanRegistry implements GlyBeanFactory, GlyConfigurableBeanFactory {

    @Override
    public Object getBean(String beanName) {
        //查看单例bean是否已存在
        Object bean = getSingleton(beanName);
        if (Objects.isNull(bean)) {
            //先从beanDefinition 集合中 获取 beanDefinition
            GlyBeanDefinition beanDefinition = getBeanDefinition(beanName);
            if (Objects.isNull(beanDefinition)) {
                return null;
            }
            if (beanDefinition.isSingleton()) {
                //单例 放入singletonMap 缓存集合
                bean = doCreateBean(beanDefinition);
                addSingleton(beanName, bean);
            } else if (beanDefinition.isPrototype()) {
                // 多例直接返回
                bean = doCreateBean(beanDefinition);
            }
        }
        return bean;
    }

    /**
     * 创建Bean实例
     *
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(GlyBeanDefinition beanDefinition);

    /**
     * 获取BeanDefinition
     * (抽象模板设计)
     *
     * @param beanName
     * @return
     */
    protected abstract GlyBeanDefinition getBeanDefinition(String beanName);
}