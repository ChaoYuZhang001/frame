package cn.gly.springframework.ioc;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class GlyTypedStringValue {

    // value 属性值
    private String value;

    // value 属性值对应的类型（Bean中属性的类型）
    private Class<?> targetType;

    public GlyTypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }
}