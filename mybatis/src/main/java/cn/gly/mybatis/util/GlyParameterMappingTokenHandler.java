
package cn.gly.mybatis.util;


import cn.gly.mybatis.sqlsource.entity.GlyParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理#{}中的参数的
 *
 * @author 灭霸詹
 */

public class GlyParameterMappingTokenHandler implements GlyTokenHandler {
    private List<GlyParameterMapping> parameterMappings = new ArrayList<>();

    // content是参数名称
    // content 就是#{}中的内容
    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private GlyParameterMapping buildParameterMapping(String content) {
        GlyParameterMapping parameterMapping = new GlyParameterMapping(content);
        return parameterMapping;
    }

    public List<GlyParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<GlyParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

}
