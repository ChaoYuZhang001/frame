package cn.gly.springframework;

import cn.gly.springframework.factory.GlyBeanFactory;
import cn.gly.springframework.ioc.GlyRuntimeBeanReference;
import cn.gly.springframework.ioc.GlyTypedStringValue;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/15
 */
public class GlyBeanDefinitionResoled {

    private GlyBeanFactory beanFactory;

    public GlyBeanDefinitionResoled(GlyBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resoleValue(Object value) {
        if (value instanceof GlyTypedStringValue) {
            GlyTypedStringValue typedStringValue = (GlyTypedStringValue) value;
            String stringValue = typedStringValue.getValue();
            Class<?> targetType = typedStringValue.getTargetType();
            if (targetType == String.class) {
                return stringValue;
            } else if (targetType == Integer.class) {
                return Integer.parseInt(stringValue);
            }//....
        } else if (value instanceof GlyRuntimeBeanReference) {
            GlyRuntimeBeanReference runtimeBeanReference = (GlyRuntimeBeanReference) value;
            String ref = runtimeBeanReference.getRef();
            return beanFactory.getBean(ref);
        }

        return null;
    }
}