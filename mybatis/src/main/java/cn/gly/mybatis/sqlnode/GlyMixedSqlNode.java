package cn.gly.mybatis.sqlnode;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈用于打包同级别的SqlNode〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyMixedSqlNode implements GlySqlNode {

    private List<GlySqlNode> sqlNodeList;

    public GlyMixedSqlNode(List<GlySqlNode> sqlNodeList) {
        this.sqlNodeList = sqlNodeList;
    }

    @Override
    public void apply(GlyDynamicContext context) {
        //依次调用list里每个元素的apply
        for (GlySqlNode sqlNode : sqlNodeList) {
            sqlNode.apply(context);
        }
    }
}