package cn.gly.mybatis.config;


import cn.gly.mybatis.excutor.GlyCachingExecutor;
import cn.gly.mybatis.excutor.GlyExecutor;
import cn.gly.mybatis.excutor.GlySimpleExecutor;
import cn.gly.mybatis.handler.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/2
 * @since 1.0.0
 */
public class GlyConfiguration {

    private DataSource dataSource;

    private boolean useCache = true;

    private Map<String, GlyMappedStatement> mappedStatementMap = new HashMap();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public GlyMappedStatement getMappedStatementById(String statementId) {
        return mappedStatementMap.get(statementId);
    }

    public void addMappedStatement(String statementId, GlyMappedStatement mappedStatement) {
        mappedStatementMap.put(statementId, mappedStatement);
    }

    public GlyExecutor newExecutor(String executorType) {
        executorType = executorType == null ? "simple" : executorType;
        GlyExecutor executor = null;
        if ("simple".equals(executorType)) {
            executor = new GlySimpleExecutor();
        }//else if ()

        // 默认使用二级缓存执行器去执行一遍
        if (useCache) {
            executor = new GlyCachingExecutor(executor);
        }

        return executor;
    }

    public GlyStatementHandler newStatementHandler(String statementType) {
        if ("prepared".equals(statementType)) {
            return new GlyPreparedStatementHandler(this);
        } else if ("simple".equals(statementType)) {
            return new GlySimpleStatementHandler();
        } else if ("callable".equals(statementType)) {
            return new GlyCallableStatementHandler();
        }
        return null;
    }

    public GlyParameterHandler newParameterHandler() {
        return new GlyDefaultParameterHandler();
    }

    public GlyResultSetHandler newResultSetHandler() {
        return new GlyDefaultResultSetHandler();
    }
}