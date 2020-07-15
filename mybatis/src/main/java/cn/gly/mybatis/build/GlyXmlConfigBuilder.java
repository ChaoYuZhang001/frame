package cn.gly.mybatis.build;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.io.Resources;
import cn.gly.mybatis.util.GlyDocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlyXmlConfigBuilder {

    private GlyConfiguration configuration;

    public GlyXmlConfigBuilder() {
        this.configuration = new GlyConfiguration();
    }

    public GlyConfiguration parseConfig(Element rootElement) {

        Element environments = rootElement.element("environments");
        parserEnvironments(environments);

        Element mappers = rootElement.element("mappers");
        parserMappers(mappers);
        return configuration;
    }

    /**
     * @param mappersElement <mappers></mappers>
     */
    public void parserMappers(Element mappersElement) {
        List<Element> mapperElementList = mappersElement.elements("mapper");
        mapperElementList.stream().forEach(mapperElement -> {
                    String resourcePath = mapperElement.attributeValue("resource");
                    InputStream resourceAsStream = Resources.getResourceAsStream(resourcePath);
                    Document document = GlyDocumentUtils.getDocument(resourceAsStream);
                    GlyXmlMapperBuilder xmlMapperBuilder = new GlyXmlMapperBuilder(configuration);
                    xmlMapperBuilder.parserMapper(document.getRootElement());
                }
        );
    }

    /**
     * @param environments <environments></environments>
     */
    public void parserEnvironments(Element environments) {
        String defaultValue = environments.attributeValue("default");

        /**
         * <environment></environment>
         */
        List<Element> elementList = environments.elements();

        elementList.stream().forEach(environment -> {
                    if (defaultValue.equals(environment.attributeValue("id"))) {
                        parserDataSource(environment.element("dataSource"));
                    }
                }
        );

    }

    /**
     * @param dataSourceElement <dataSource></dataSource>
     */
    public void parserDataSource(Element dataSourceElement) {
        String type = dataSourceElement.attributeValue("type");

        if ("DBCP".equals(type)) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties properties = parserProperties(dataSourceElement);
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));

            configuration.setDataSource(dataSource);
        } else {
            //Todo
        }
    }

    /**
     * @param dataSourceElement <property></property>
     * @return
     */
    public Properties parserProperties(Element dataSourceElement) {
        Properties properties = new Properties();
        List<Element> propertyElements = dataSourceElement.elements("property");
        propertyElements.stream().forEach(propertyElement ->
                properties.put(propertyElement.attributeValue("name"), propertyElement.attributeValue("value"))
        );
        return properties;
    }
}