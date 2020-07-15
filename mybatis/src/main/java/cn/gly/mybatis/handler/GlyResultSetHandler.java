package cn.gly.mybatis.handler;

import cn.gly.mybatis.config.GlyMappedStatement;

import java.sql.ResultSet;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/9
 * @since 1.0.0
 */
public interface GlyResultSetHandler {

    void handleResultSet(ResultSet rs, GlyMappedStatement mappedStatement, List<Object> results) throws Exception;
}