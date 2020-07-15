package cn.gly.mybatis.handler;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class GlyPreparedStatementHandler implements GlyStatementHandler {

    private GlyParameterHandler parameterHandler;

    private GlyResultSetHandler resultSetHandler;

    public GlyPreparedStatementHandler(GlyConfiguration configuration) {
        parameterHandler = configuration.newParameterHandler();
        resultSetHandler = configuration.newResultSetHandler();
    }

    @Override
    public Statement prepared(Connection connection, String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    @Override
    public void setParameters(Statement statement, Object param, GlyBoundSql boundSql) throws Exception {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        parameterHandler.setParameters(preparedStatement, param, boundSql);
    }

    @Override
    public void query(Statement statement, GlyMappedStatement mappedStatement, List<Object> results) throws Exception {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        ResultSet rs = preparedStatement.executeQuery();
        resultSetHandler.handleResultSet(rs, mappedStatement, results);
    }
}
