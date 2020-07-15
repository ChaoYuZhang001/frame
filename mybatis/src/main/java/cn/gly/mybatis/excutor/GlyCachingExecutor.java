package cn.gly.mybatis.excutor;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.config.GlyMappedStatement;

import java.util.List;

/**
 * 带有二级缓存功能的执行器
 */
public class GlyCachingExecutor implements GlyExecutor{

    private GlyExecutor executor;

    public GlyCachingExecutor(GlyExecutor executor) {
        this.executor = executor;
    }

    @Override
    public <T> List<T> query(GlyConfiguration configuration, GlyMappedStatement mappedStatement, Object param) {
        //查询二级缓存

        // 二级缓存没有，则走一级缓存逻辑
        return executor.query(configuration,mappedStatement,param);
    }
}
