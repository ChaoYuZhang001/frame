package cn.gly.mybatis.sqlsession;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2020/7/9
 * @since 1.0.0
 */
public interface GlySqlSession {

    <T> List<T> findByParam(String statementId, Object params);

    <T> T findOne(String statementId, Object params);
}