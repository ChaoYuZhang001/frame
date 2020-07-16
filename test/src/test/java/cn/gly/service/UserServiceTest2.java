package cn.gly.service;

import cn.gly.entity.User;
import cn.gly.mybatis.io.Resources;
import cn.gly.springframework.factory.GlyBeanFactory;
import cn.gly.springframework.factory.support.GlyDefaultListableBeanFactory;
import cn.gly.springframework.ioc.GlyBeanDefinition;
import cn.gly.springframework.ioc.GlyPropertyValue;
import cn.gly.springframework.ioc.GlyRuntimeBeanReference;
import cn.gly.springframework.ioc.GlyTypedStringValue;
import cn.gly.springframework.util.enums.GlyBeanScope;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class UserServiceTest2 {

//    //存储单例bean 集合
//    private Map<String, Object> singletonMap = new ConcurrentHashMap<>();
//
    //存储 在xml 或注解 解析出的beanDefinition 集合
    private Map<String, GlyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private GlyBeanFactory beanFactory = new GlyDefaultListableBeanFactory();

    @Before
    public void initBeanConfig() {
        InputStream inputStream = Resources.getResourceAsStream("beans.xml");
        Document document = null;
        try {
            document = new SAXReader().read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        registerBeanDefinitions(document.getRootElement());
    }


    @Test
    public void test() {
        UserService userService = (UserService) beanFactory.getBean("userService");
        Map<String, Object> param = new HashMap<>();
        param.put("username", "张三");
        param.put("sex", "男");
        List<User> users = userService.queryUserByParams(param);
        System.out.println(users);
    }


    private void registerBeanDefinitions(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String name = element.getName();
            if ("bean".equals(name)) {
                parseDefaultElement(element);
            } else {
                parseCustomElement(element);
            }
        }
    }

    private void parseDefaultElement(Element beanElement) {
        try {
            if (beanElement == null) {
                return;
            }
            // 获取id属性
            String id = beanElement.attributeValue("id");

            // 获取name属性
            String name = beanElement.attributeValue("name");

            // 获取class属性
            String clazzName = beanElement.attributeValue("class");
            if (clazzName == null || "".equals(clazzName)) {
                return;
            }

            // 获取init-method属性
            String initMethod = beanElement.attributeValue("init-method");
            // 获取scope属性
            String scope = beanElement.attributeValue("scope");
            scope = scope != null && !scope.equals("") ? scope : GlyBeanScope.SINGLETON.getValue();

            // 获取beanName
            String beanName = id == null ? name : id;
            Class<?> clazzType = Class.forName(clazzName);
            beanName = beanName == null ? clazzType.getSimpleName() : beanName;
            // 创建BeanDefinition对象
            // 此次可以使用构建者模式进行优化
            GlyBeanDefinition beanDefinition = new GlyBeanDefinition(beanName, clazzName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);
            // 获取property子标签集合
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }

            // 注册BeanDefinition信息
            this.beanDefinitionMap.put(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyElement(GlyBeanDefinition beanDefinition, Element propertyElement) {
        if (propertyElement == null)
            return;

        // 获取name属性
        String name = propertyElement.attributeValue("name");
        // 获取value属性
        String value = propertyElement.attributeValue("value");
        // 获取ref属性
        String ref = propertyElement.attributeValue("ref");

        // 如果value和ref都有值，则返回
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }

        /**
         * PropertyValue就封装着一个property标签的信息
         */
        GlyPropertyValue pv;

        if (value != null && !value.equals("")) {
            // 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
            GlyTypedStringValue typeStringValue = new GlyTypedStringValue(value);

            Class<?> targetType = getTypeByFieldName(beanDefinition.getClazzName(), name);
            typeStringValue.setTargetType(targetType);

            pv = new GlyPropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(pv);
        } else if (ref != null && !ref.equals("")) {

            GlyRuntimeBeanReference reference = new GlyRuntimeBeanReference(ref);
            pv = new GlyPropertyValue(name, reference);
            beanDefinition.addPropertyValue(pv);
        } else {
            return;
        }
    }

    private Class<?> getTypeByFieldName(String beanClassName, String name) {
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseCustomElement(Element element) {
        // AOP、TX、MVC标签的解析，都是走该流程
    }

//    private Object getBean(String beanName) {
//        //查看单例bean是否已存在
//        Object bean = singletonMap.get(beanName);
//        if (Objects.isNull(bean)) {
//            //先从beanDefinition 集合中 获取 beanDefinition
//            GlyBeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
//            if (Objects.isNull(beanDefinition)) {
//                return null;
//            }
//            if (beanDefinition.isSingleton()) {
//                //单例 放入singletonMap 缓存集合
//                bean = createBean(beanDefinition);
//                singletonMap.put(beanName, bean);
//            } else if (beanDefinition.isPrototype()) {
//                // 多例直接返回
//                bean = createBean(beanDefinition);
//            }
//        }
//        return bean;
//    }

//    /**
//     * 创建Bean
//     *
//     * @param beanDefinition
//     * @return
//     */
//    private Object createBean(GlyBeanDefinition beanDefinition) {
//
//        Object bean = createBeanInstance(beanDefinition);
//
//        populateBean(bean, beanDefinition);
//
//        initializeBean(bean, beanDefinition);
//
//        return bean;
//    }
//
//
//    /**
//     * 创建Bean实例 （new）
//     *
//     * @param beanDefinition
//     * @return
//     */
//    private Object createBeanInstance(GlyBeanDefinition beanDefinition) {
//        // 通过工厂方法创建实例
//
//        // 通过实例工厂创建实例
//
//        // 通过构造器创建实例
//        Object bean = createBeanByConstructor(beanDefinition);
//        return bean;
//    }
//
//    /**
//     * 通过构造器创建实例
//     *
//     * @param beanDefinition
//     * @return
//     */
//    private Object createBeanByConstructor(GlyBeanDefinition beanDefinition) {
//
//        Class<?> clazzType = beanDefinition.getClazzType();
//        Object instance = null;
//        try {
//            //无参构造器
//            Constructor<?> declaredConstructor = clazzType.getDeclaredConstructor();
//            instance = declaredConstructor.newInstance();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }
//
//    /**
//     * bean依赖注入 (set属性值)
//     *
//     * @param bean
//     * @param beanDefinition
//     */
//    private void populateBean(Object bean, GlyBeanDefinition beanDefinition) {
//        List<GlyPropertyValue> propertyValueList = beanDefinition.getPropertyValueList();
//        for (GlyPropertyValue propertyValue : propertyValueList) {
//            //属性名称
//            String name = propertyValue.getName();
//            //属性值需要特殊处理
//            Object value = propertyValue.getValue();
//
//            //将Object的Value转换成指定类型的Value
//            Object valueToUse = resoleValue(value);
//
//            setPropertyValue(bean, name, valueToUse, beanDefinition);
//        }
//    }
//
//    private void setPropertyValue(Object bean, String name, Object value, GlyBeanDefinition beanDefinition) {
//        Class<?> clazzType = beanDefinition.getClazzType();
//        try {
//            Field declaredField = clazzType.getDeclaredField(name);
//            declaredField.setAccessible(true);
//            declaredField.set(bean, value);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Object resoleValue(Object value) {
//        if (value instanceof GlyTypedStringValue) {
//            GlyTypedStringValue typedStringValue = (GlyTypedStringValue) value;
//            String stringValue = typedStringValue.getValue();
//            Class<?> targetType = typedStringValue.getTargetType();
//            if (targetType == String.class) {
//                return stringValue;
//            } else if (targetType == Integer.class) {
//                return Integer.parseInt(stringValue);
//            }//....
//        } else if (value instanceof GlyRuntimeBeanReference) {
//            GlyRuntimeBeanReference runtimeBeanReference = (GlyRuntimeBeanReference) value;
//            String ref = runtimeBeanReference.getRef();
//            return getBean(ref);
//        }
//
//        return null;
//    }
//
//    /**
//     * 初始化Bean
//     *
//     * @param bean
//     * @param beanDefinition
//     */
//    private void initializeBean(Object bean, GlyBeanDefinition beanDefinition) {
//        //Todo 初始化可以通过Aware标记接口 进行特殊的属性注入 比如BeanFactory注入
//
//        // bean 标签如果配置了 init-method 属性 那么属性值就是初始化方法
//        invokeInitMethod(bean, beanDefinition);
//    }
//
//    private void invokeInitMethod(Object bean, GlyBeanDefinition beanDefinition) {
//        String initMethod = beanDefinition.getInitMethod();
//        if ("".equals(initMethod) || null == initMethod) {
//            return;
//        }
//        Class<?> clazzType = beanDefinition.getClazzType();
//        try {
//            Method declaredMethod = clazzType.getDeclaredMethod(initMethod);
//            declaredMethod.invoke(bean);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
}