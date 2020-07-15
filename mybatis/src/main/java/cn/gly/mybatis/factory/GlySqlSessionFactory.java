package cn.gly.mybatis.factory;


import cn.gly.mybatis.sqlsession.GlySqlSession;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public interface GlySqlSessionFactory {

    GlySqlSession openSession();
}