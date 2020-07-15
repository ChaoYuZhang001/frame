package cn.gly.mybatis.excutor;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.handler.GlyStatementHandler;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 带有一级缓存功能的执行器
 */
public class GlySimpleExecutor extends GlyBaseExecutor {

    @Override
    public List queryFromDataSource(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param, GlyBoundSql boundSql) {

        List<Object> results = new ArrayList<Object>();

        Connection connection = null;
        Statement statement = null;

        try {
            // 1.获取连接
            connection = getConnection(configuration);

            // 2.获取SQL语句(SqlSource和SqlNode的执行过程)
            String sql = boundSql.getSql();

            GlyStatementHandler statementHandler = configuration.newStatementHandler(mappedStatement.getStatementType());
            statement = statementHandler.prepared(connection, sql);
            // 3.创建Statement对象
            // statement = createStatement(connection,sql,mappedStatement);

            // 4.设置参数
            statementHandler.setParameters(statement, param, boundSql);

            // 5.执行Statement
            statementHandler.query(statement, mappedStatement, results);

            // 6.处理ResultSet
            // handleResultSet(rs,results,mappedStatement);
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * 获取连接的代码
     *
     * @return
     */
    private Connection getConnection(GlyConfiguration configuration) {
        // 优化连接处理
        try {
            DataSource dataSource = configuration.getDataSource();
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建Statement对象
     *
     * @param connection
     * @param sql
     * @param mappedStatement
     * @return
     * @throws Exception
     */
    private Statement createStatement(Connection connection, String sql, GlyMappedStatement mappedStatement) throws Exception {
        String statementType = mappedStatement.getStatementType();
        if (statementType.equals("prepared")) {
            return connection.prepareStatement(sql);
        } else if (statementType.equals("statement")) {
            return connection.createStatement();
        } else if (statementType.equals("callable")) {
            //TODO
        } else {
            //TODO
        }
        return null;
    }

}
