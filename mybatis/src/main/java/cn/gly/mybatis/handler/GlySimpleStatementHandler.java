package cn.gly.mybatis.handler;


import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class GlySimpleStatementHandler implements GlyStatementHandler{

    @Override
    public Statement prepared(Connection connection, String sql) throws Exception {
        return null;
    }

    @Override
    public void setParameters(Statement statement, Object param, GlyBoundSql boundSql) throws Exception {

    }

    @Override
    public void query(Statement statement, GlyMappedStatement mappedStatement, List<Object> results) throws Exception {

    }
}
