package cn.gly.mybatis.sqlsource.entity;


import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装Jdbc可执行的Sql语句〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyBoundSql {

    // 封装解析之后的sql语句
    private String sql;

    // #{}解析出来的参数集合信息
    private List<GlyParameterMapping> parameterMappings;

    public GlyBoundSql(String sql, List<GlyParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<GlyParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<GlyParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}