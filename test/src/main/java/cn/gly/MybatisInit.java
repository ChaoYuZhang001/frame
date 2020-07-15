package cn.gly;

import cn.gly.mybatis.build.GlySqlSessionBuilder;
import cn.gly.mybatis.factory.GlySqlSessionFactory;
import cn.gly.mybatis.io.Resources;
import org.junit.Before;

import java.io.InputStream;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class MybatisInit {

    private String mybatisConfigPath;

    // 注意：SqlSessionFactory全局只需要创建一次即可
    public GlySqlSessionFactory sqlSessionFactory;

    public void setMybatisConfigPath(String mybatisConfigPath) {
        this.mybatisConfigPath = mybatisConfigPath;
    }

    public void initSqlSessionFactory() {
        InputStream inputStream = Resources.getResourceAsStream(this.mybatisConfigPath);
        // SqlSessionFactory的创建流程
        this.sqlSessionFactory = new GlySqlSessionBuilder().builder(inputStream);
        System.out.println(sqlSessionFactory);
    }
}