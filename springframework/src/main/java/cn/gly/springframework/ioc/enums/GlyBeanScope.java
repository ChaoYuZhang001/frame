package cn.gly.springframework.ioc.enums;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/14
 * @since 1.0.0
 */
public enum GlyBeanScope {

    SINGLETON("singleton"),
    PROTOTYPE("prototype"),;

    private String value;

    GlyBeanScope(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}