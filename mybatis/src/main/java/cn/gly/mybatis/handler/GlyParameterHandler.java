package cn.gly.mybatis.handler;


import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;

import java.sql.Statement;

public interface GlyParameterHandler {

    void setParameters(Statement statement, Object param, GlyBoundSql boundSql) throws Exception;
}
