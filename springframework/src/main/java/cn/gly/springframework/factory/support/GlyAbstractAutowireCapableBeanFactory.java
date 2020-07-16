package cn.gly.springframework.factory.support;

import cn.gly.springframework.GlyBeanDefinitionResoled;
import cn.gly.springframework.factory.GlyAutowireCapableBeanFactory;
import cn.gly.springframework.ioc.GlyBeanDefinition;
import cn.gly.springframework.ioc.GlyPropertyValue;
import cn.gly.springframework.util.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public abstract class GlyAbstractAutowireCapableBeanFactory extends GlyAbstractBeanFactory implements GlyAutowireCapableBeanFactory {

    @Override
    protected Object doCreateBean(GlyBeanDefinition beanDefinition) {
        Object bean = createBeanInstance(beanDefinition);

        populateBean(bean, beanDefinition);

        initializeBean(bean, beanDefinition);

        return bean;
    }

    /**
     * 创建Bean实例 （new）
     *
     * @param beanDefinition
     * @return
     */
    private Object createBeanInstance(GlyBeanDefinition beanDefinition) {
        // 通过工厂方法创建实例

        // 通过实例工厂创建实例

        // 通过构造器创建实例
        Object bean = createBeanByConstructor(beanDefinition);
        return bean;
    }

    /**
     * 通过构造器创建实例
     *
     * @param beanDefinition
     * @return
     */
    private Object createBeanByConstructor(GlyBeanDefinition beanDefinition) {

        Class<?> clazzType = beanDefinition.getClazzType();
        Object instance = null;
        try {
            //无参构造器
            Constructor<?> declaredConstructor = clazzType.getDeclaredConstructor();
            instance = declaredConstructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * bean依赖注入 (set属性值)
     *
     * @param bean
     * @param beanDefinition
     */
    private void populateBean(Object bean, GlyBeanDefinition beanDefinition) {
        List<GlyPropertyValue> propertyValueList = beanDefinition.getPropertyValueList();
        for (GlyPropertyValue propertyValue : propertyValueList) {
            //属性名称
            String name = propertyValue.getName();
            //属性值需要特殊处理
            Object value = propertyValue.getValue();
            GlyBeanDefinitionResoled beanDefinitionResole = new GlyBeanDefinitionResoled(this);
            //将Object的Value转换成指定类型的Value
            Object valueToUse = beanDefinitionResole.resoleValue(value);

            setPropertyValue(bean, name, valueToUse, beanDefinition);
        }
    }

    private void setPropertyValue(Object bean, String name, Object value, GlyBeanDefinition beanDefinition) {
        Class<?> clazzType = beanDefinition.getClazzType();
        try {
            Field declaredField = clazzType.getDeclaredField(name);
            declaredField.setAccessible(true);
            declaredField.set(bean, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Bean
     *
     * @param bean
     * @param beanDefinition
     */
    private void initializeBean(Object bean, GlyBeanDefinition beanDefinition) {
        //Todo 初始化可以通过Aware标记接口 进行特殊的属性注入 比如BeanFactory注入

        // bean 标签如果配置了 init-method 属性 那么属性值就是初始化方法
        invokeInitMethod(bean, beanDefinition);
    }

    private void invokeInitMethod(Object bean, GlyBeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if ("".equals(initMethod) || null == initMethod) {
            return;
        }
        try {
            ReflectUtils.invoke(beanDefinition.getClazzType(), beanDefinition.getInitMethod(), bean);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}