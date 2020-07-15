package cn.gly.mybatis.sqlsession;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.excutor.GlyExecutor;

import java.util.List;

public class GlyDefaultSqlSession implements GlySqlSession {
    private GlyConfiguration configuration;

    public GlyDefaultSqlSession(GlyConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> findByParam(String statementId, Object param) {
        GlyMappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        GlyExecutor executor = configuration.newExecutor(null);
        return executor.query(configuration, mappedStatement, param);
    }

    @Override
    public <T> T findOne(String statementId, Object param) {
        List<Object> list = this.findByParam(statementId, param);
        if (list == null) {
            return null;
        } else if (list.size() == 1) {
            return (T) list.get(0);
        } else if (list.size() != 1) {
            //TODO
        }
        return null;
    }
}
