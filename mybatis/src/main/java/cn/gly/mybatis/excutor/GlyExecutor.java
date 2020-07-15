package cn.gly.mybatis.excutor;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;

import java.util.List;

public interface GlyExecutor {

    <T> List<T> query(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param);
}
