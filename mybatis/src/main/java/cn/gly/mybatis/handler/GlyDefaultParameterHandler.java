package cn.gly.mybatis.handler;

import cn.gly.mybatis.sqlsource.entity.GlyBoundSql;
import cn.gly.mybatis.sqlsource.entity.GlyParameterMapping;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class GlyDefaultParameterHandler implements GlyParameterHandler {
    @Override
    public void setParameters(Statement statement, Object param, GlyBoundSql boundSql) throws Exception {
        if (statement instanceof PreparedStatement) {
            PreparedStatement preparedStatement = (PreparedStatement) statement;
            if (param instanceof Integer || param instanceof String) {
                preparedStatement.setObject(1, param);
            } else if (param instanceof Map) {
                Map paramMap = (Map) param;
                // TODO 结合#{}的处理逻辑进行改造
                List<GlyParameterMapping> parameterMappings = boundSql.getParameterMappings();
                for (int i = 0; i < parameterMappings.size(); i++) {
                    GlyParameterMapping parameterMapping = parameterMappings.get(i);
                    String name = parameterMapping.getName();
                    // 获取到的参数
                    Object value = paramMap.get(name);
                    // 获取到的value对应的Java类型
                    Class type = parameterMapping.getType();
                    if (type != null) {
                        //TODO
                        // preparedStatement.setInt(i+1, value);
                        // preparedStatement.setString(i+1, value);

                    } else {
                        preparedStatement.setObject(i + 1, value);
                    }
                }
            } else {
                //TODO
            }
        }

    }
}
