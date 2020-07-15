package cn.gly.mybatis.sqlsource;

import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;
import cn.gly.mybatis.sqlsource.entity.GlyParameterMapping;
import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装解析出来的SqlSource信息〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyStaticSqlSource implements GlySqlSource {

    private String sql;

    private List<GlyParameterMapping> parameterMappingList;

    public GlyStaticSqlSource(String sql, List<GlyParameterMapping> parameterMappingList) {
        this.sql = sql;
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public GlyBoundSql getBoundSql(Object object) {

        return new GlyBoundSql(sql, parameterMappingList);
    }
}