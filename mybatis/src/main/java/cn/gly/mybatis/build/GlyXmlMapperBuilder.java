package cn.gly.mybatis.build;


import cn.gly.mybatis.config.GlyConfiguration;
import org.dom4j.Element;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlyXmlMapperBuilder {

    private GlyConfiguration configuration;

    public GlyXmlMapperBuilder(GlyConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @param mapperRootElement <mapper></mapper>
     */
    public void parserMapper(Element mapperRootElement) {
        String namespace = mapperRootElement.attributeValue("namespace");
        List<Element> selectElementList = mapperRootElement.elements("select");
        selectElementList.stream().forEach(selectElement -> {
            GlyXmlStatementBuilder xmlStatementBuilder = new GlyXmlStatementBuilder(configuration);
            xmlStatementBuilder.parserStatement(selectElement, namespace);
        });
    }

}