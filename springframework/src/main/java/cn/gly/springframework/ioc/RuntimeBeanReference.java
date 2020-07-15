package cn.gly.springframework.ioc;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class RuntimeBeanReference {

    // ref 属性值
    private String ref;

    public RuntimeBeanReference(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}