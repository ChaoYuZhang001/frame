package cn.gly.mybatis.handler;

import cn.gly.mybatis.config.GlyMappedStatement;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public interface GlyStatementHandler {

    Statement prepared(Connection connection, String sql) throws Exception;

    void setParameters(Statement statement, Object param, GlyBoundSql boundSql) throws Exception;

    void query(Statement statement, GlyMappedStatement mappedStatement, List<Object> results) throws Exception;
}