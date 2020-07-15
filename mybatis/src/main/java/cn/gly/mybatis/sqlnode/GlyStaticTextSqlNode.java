
package cn.gly.mybatis.sqlnode;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装不带有${}的SqlNode〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyStaticTextSqlNode implements GlySqlNode {

    // 封装未解析的sql文本信息
    private String textSql;

    public GlyStaticTextSqlNode(String textSql) {
        this.textSql = textSql;
    }

    @Override
    public void apply(GlyDynamicContext context) {
        //将文本加入context
        context.appendSql(textSql);
    }
}