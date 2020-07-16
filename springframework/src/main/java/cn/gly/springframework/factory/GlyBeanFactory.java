package cn.gly.springframework.factory;

/**
 * 〈一句话功能简述〉<br>
 * 〈Spring 容器的顶级接口〉
 *
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public interface GlyBeanFactory {

    /**
     * 根据Bean 名称 获取Bean实例
     *
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}