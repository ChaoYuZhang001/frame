package cn.gly.mybatis.build;

import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;
import cn.gly.mybatis.util.GlyReflectUtil;
import org.dom4j.Element;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlyXmlStatementBuilder {

    private GlyConfiguration configuration;

    public GlyXmlStatementBuilder(GlyConfiguration configuration) {
        this.configuration = configuration;
    }


    /**
     * @param selectElement <select></select>
     * @param namespace
     */
    public void parserStatement(Element selectElement, String namespace) {
        String statementId = selectElement.attributeValue("id");
        if (statementId == null || statementId == "") {
            return;
        }
        statementId = namespace + "." + statementId;

        String resultType = selectElement.attributeValue("resultType");
        Class<?> resultClass = GlyReflectUtil.resolverType(resultType);

        String statementType = selectElement.attributeValue("statementType");
        statementType = statementType == null || statementType == "" ? "prepared" : statementType;

        // sql Source 封装
        GlySqlSource sqlSource = createSqlSource(selectElement);

        GlyMappedStatement mappedStatement = new GlyMappedStatement(statementId, resultClass, statementType, sqlSource);
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private GlySqlSource createSqlSource(Element selectElement) {
        //TODO 其他子标签的解析处理
        GlyXmlScriptBuilder xmlScriptBuilder = new GlyXmlScriptBuilder();
        GlySqlSource sqlSource = xmlScriptBuilder.parserScriptSqlNode(selectElement);
        return sqlSource;
    }

}