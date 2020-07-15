package cn.gly.mybatis.build;

import cn.gly.mybatis.sqlnode.GlyIfSqlNode;
import cn.gly.mybatis.sqlnode.GlyMixedSqlNode;
import cn.gly.mybatis.sqlnode.GlyStaticTextSqlNode;
import cn.gly.mybatis.sqlnode.GlyTextSqlNode;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;
import cn.gly.mybatis.sqlsource.GlyDynamicSqlSource;
import cn.gly.mybatis.sqlsource.GlyRawSqlSource;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
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
public class GlyXmlScriptBuilder {

    private boolean isDynamic;

    /**
     * @param selectElement
     * @return
     */
    public GlySqlSource parserScriptSqlNode(Element selectElement) {
        GlySqlNode mixedSqlNode = parserDynamicTags(selectElement);
        GlySqlSource sqlSource;
        if (isDynamic) {
            sqlSource = new GlyDynamicSqlSource(mixedSqlNode);
        } else {
            sqlSource = new GlyRawSqlSource(mixedSqlNode);
        }
        return sqlSource;
    }

    public GlySqlNode parserDynamicTags(Element selectElement) {
        List<GlySqlNode> sqlNodeList = new ArrayList<>();
        int nodeCount = selectElement.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = selectElement.node(i);
            if (node instanceof Text) {
                String text = node.getText();
                if (text == null) {
                    continue;
                }
                if (text.trim().equals("")) {
                    continue;
                }
                GlyTextSqlNode textSqlNode = new GlyTextSqlNode(text.trim());
                if (textSqlNode.isDynamic()) {
                    sqlNodeList.add(textSqlNode);
                    isDynamic = true;
                } else {
                    sqlNodeList.add(new GlyStaticTextSqlNode(text.trim()));
                }

            } else if (node instanceof Element) {
                Element element = (Element) node;
                String name = element.getName();
                if ("if".equals(name)) {
                    String test = element.attributeValue("test");
                    GlySqlNode sqlNode = parserDynamicTags(element);
                    GlyIfSqlNode ifSqlNode = new GlyIfSqlNode(test, sqlNode);

                    sqlNodeList.add(ifSqlNode);
                }
            }
        }
        return new GlyMixedSqlNode(sqlNodeList);
    }
}