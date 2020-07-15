package cn.gly.mybatis.sqlsource;


import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;
import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;
import cn.gly.mybatis.util.GlyGenericTokenParser;
import cn.gly.mybatis.util.GlyParameterMappingTokenHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈
 * 封装不带有${}和动态标签的失去了语句信息'
 * #{} 只需要解析一次
 * 只能在构造方法中解析，不能每次都调用getBoundSql
 * 〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyRawSqlSource implements GlySqlSource {

    private GlySqlSource sqlSource;

    public GlyRawSqlSource(GlySqlNode sqlNode) {
        // 解析#{}
        // 1.处理所有的SqlNode，合并成一条SQL语句（该语句#{}还未处理）
        GlyDynamicContext context = new GlyDynamicContext(null);
        sqlNode.apply(context);

        // 合并之后的SQL语句
        // select * from user where id = #{id}
        String sqlText = context.getSql();
        // 2.处理#{}，得到JDBC可以执行的【SQL语句】，以及解析出来的【参数信息集合】


        // 用来处理#{}中的参数
        // 2.1 、将#{}替换为?----字符串处理
        // 2.2 、将#{}里面的参数名称，比如说id，封装成ParameterMapping对象中，并添加到List集合
        GlyParameterMappingTokenHandler tokenHandler = new GlyParameterMappingTokenHandler();

        // 用来解析SQL文本中的#{}或者${}
        GlyGenericTokenParser tokenParser = new GlyGenericTokenParser("#{", "}", tokenHandler);

        // JDBC可以直接执行的SQL语句
        String sql = tokenParser.parse(sqlText);

        // 3.将得到的SQL语句和参数信息集合，封装到StaticSqlSource里面存储
        sqlSource = new GlyStaticSqlSource(sql, tokenHandler.getParameterMappings());
    }

    @Override
    public GlyBoundSql getBoundSql(Object object) {
        return sqlSource.getBoundSql(object);
    }
}