package cn.gly.mybatis.sqlnode;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装带有<if></if>动态标签的SqlNode〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyIfSqlNode implements GlySqlNode {

    private String test;

    private GlySqlNode rootSqlNode;


    public GlyIfSqlNode(String test, GlySqlNode sqlNode) {
        this.test = test;
        this.rootSqlNode = sqlNode;
    }

    @Override
    public void apply(GlyDynamicContext context) {
        //Todo  判断条件成立
        rootSqlNode.apply(context);
    }
}