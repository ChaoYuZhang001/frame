package cn.gly.mybatis.sqlsource;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;
import cn.gly.mybatis.sqlsource.entity.GlyParameterMapping;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;
import cn.gly.mybatis.util.GlyGenericTokenParser;
import cn.gly.mybatis.util.GlyParameterMappingTokenHandler;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装带有${}和动态标签的失去了语句信息〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyDynamicSqlSource implements GlySqlSource {

    private GlySqlNode rootSqlNode;

    public GlyDynamicSqlSource(GlySqlNode rootSqlNode) {
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public GlyBoundSql getBoundSql(Object parameterObject) {
        // 1.处理所有的SqlNode，合并成一条SQL语句（该语句${}已经处理，而#{}还未处理）
        GlyDynamicContext context = new GlyDynamicContext(parameterObject);
        rootSqlNode.apply(context);
        // 合并之后的SQL语句
        // select * from user where id = #{id}
        String sqlText = context.getSql();
        System.out.println("#{}未处理的SQL语句：" + sqlText);

        // 2.处理#{}，得到JDBC可以执行的【SQL语句】，以及解析出来的【参数信息集合】

        // 用来处理#{}中的参数
        // 2.1 、将#{}替换为?----字符串处理
        // 2.2 、将#{}里面的参数名称，比如说id，封装成ParameterMapping对象中，并添加到List集合
        GlyParameterMappingTokenHandler tokenHandler = new GlyParameterMappingTokenHandler();

        // 用来解析SQL文本中的#{}或者${}
        GlyGenericTokenParser tokenParser = new GlyGenericTokenParser("#{", "}", tokenHandler);

        // JDBC可以直接执行的SQL语句
        String sql = tokenParser.parse(sqlText);
        System.out.println("#{}处理之后的SQL语句：" + sql);
        // 3.将得到的SQL语句和参数信息集合，封装到StaticSqlSource里面存储
        return new GlyBoundSql(sql, tokenHandler.getParameterMappings());
    }

    private List<GlyParameterMapping> getParameterMappings(Class<?> parameterType, Object parameterObject) {
        return null;
    }
}