package cn.gly.mybatis.sqlnode.interfacs;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/3
 * @since 1.0.0
 */
public interface GlySqlNode {

    void apply(GlyDynamicContext context);
}