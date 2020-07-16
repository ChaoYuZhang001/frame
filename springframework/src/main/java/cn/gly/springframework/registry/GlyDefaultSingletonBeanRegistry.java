package cn.gly.springframework.registry;

import cn.gly.springframework.util.GlyAssert;

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
public class GlyDefaultSingletonBeanRegistry implements GlySingletonBeanRegistry {

    //单例池---在正常情况下所有单例Bean被容器初始化完成之后缓存在这个Map当中
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>(255);

    @Override
    public Object getSingleton(String beanName) {
        GlyAssert.notNull(beanName, "Bean name must not be null");
        return this.singletonObjects.get(beanName);
    }

    @Override
    public void addSingleton(String beanName, Object singletonObject) {
        //单例模式
        GlyAssert.notNull(beanName, "Bean name must not be null");
        GlyAssert.notNull(singletonObject, "Singleton object must not be null");
        synchronized (this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            addSingleton(beanName, singletonObject);
        }
    }
}