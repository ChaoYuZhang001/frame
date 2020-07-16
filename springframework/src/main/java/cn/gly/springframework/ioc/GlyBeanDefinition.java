package cn.gly.springframework.ioc;


import cn.gly.springframework.util.enums.GlyBeanScope;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class GlyBeanDefinition {

    // bean 标签 id 和 name属性
    private String beanName;

    // bean 标签 class属性
    private String clazzName;

    // bean 标签 class属性对应的Class对象
    private Class<?> clazzType;

    //初始化方法
    private String initMethod;

    //作用域 默认单例
    private String scope;

    private List<GlyPropertyValue> propertyValueList = new ArrayList<>();

    public GlyBeanDefinition(String beanName, String clazzName) {
        this.beanName = beanName;
        this.clazzName = clazzName;
        this.clazzType = resolveClassName(clazzName);
    }

    private Class<?> resolveClassName(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Class<?> getClazzType() {
        return clazzType;
    }

    public void setClazzType(Class<?> clazzType) {
        this.clazzType = clazzType;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<GlyPropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void setPropertyValueList(List<GlyPropertyValue> propertyValueList) {
        this.propertyValueList = propertyValueList;
    }

    public void addPropertyValue(GlyPropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public Boolean isSingleton() {
        return GlyBeanScope.SINGLETON.getValue().equals(this.scope);
    }

    public Boolean isPrototype() {
        return GlyBeanScope.PROTOTYPE.getValue().equals(this.scope);
    }
}