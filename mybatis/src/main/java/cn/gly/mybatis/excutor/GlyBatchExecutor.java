package cn.gly.mybatis.excutor;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import java.util.List;

/**
 * 带有一级缓存功能的执行器
 */
public class GlyBatchExecutor extends GlyBaseExecutor{

    @Override
    public List queryFromDataSource(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param, GlyBoundSql boundSql) {
        return null;
    }
}
