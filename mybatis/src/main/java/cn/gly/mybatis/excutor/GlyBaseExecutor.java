package cn.gly.mybatis.excutor;

import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 带有一级缓存功能的执行器
 */
public abstract class GlyBaseExecutor implements GlyExecutor {
    private Map<String, List> oneLevelCache = new HashMap<String, List>();

    @Override
    public <T> List<T> query(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param) {
        GlySqlSource sqlSource = mappedStatement.getSqlSource();
        GlyBoundSql boundSql = sqlSource.getBoundSql(param);
        String sql = boundSql.getSql();
        // 先查询一级缓存
        List list = this.oneLevelCache.get(sql);
        if (list != null && list.size() > 0) {
            return list;
        }
        // 没有则查询数据库
        list = queryFromDataSource(configuration, mappedStatement, param, boundSql);
        // 将结果放入缓存
        this.oneLevelCache.put(sql, list);
        return list;
    }

    public abstract List queryFromDataSource(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param, GlyBoundSql boundSql);
}
