package cn.gly.mapper;

import cn.gly.MybatisInit;
import cn.gly.entity.User;
import cn.gly.mybatis.sqlsession.GlySqlSession;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class UserMapperImpl implements UserMapper {

    private MybatisInit mybatisInit;

    public void setMybatisInit(MybatisInit mybatisInit) {
        this.mybatisInit = mybatisInit;
    }

    @Override
    public List<User> queryUserByParams(Object param) {
        // 注意：SqlSession每次执行CRUD都需要至少创建一次
        // 根据用户性别和用户名称查询用户信息
        GlySqlSession glySqlSession = mybatisInit.sqlSessionFactory.openSession();
        return glySqlSession.findByParam(getStatementId(), param);
    }

    String getStatementId() {
        return this.getClass().getInterfaces()[0].getName() + "."
                + this.getClass().getMethods()[0].getName();
    }
}