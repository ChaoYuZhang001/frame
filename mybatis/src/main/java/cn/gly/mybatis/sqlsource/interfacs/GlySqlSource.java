package cn.gly.mybatis.sqlsource.interfacs;


import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/3
 * @since 1.0.0
 */
public interface GlySqlSource {

    /**
     * 入参，主要解析${}
     *
     * @param object
     * @return
     */
    GlyBoundSql getBoundSql(Object object);
}