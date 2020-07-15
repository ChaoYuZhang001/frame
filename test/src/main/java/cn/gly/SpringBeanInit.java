package cn.gly;

import cn.gly.mybatis.build.GlySqlSessionBuilder;
import cn.gly.mybatis.factory.GlySqlSessionFactory;
import cn.gly.mybatis.io.Resources;

import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public class SpringBeanInit {

    private String beanConfigPath;
    // 注意：SqlSessionFactory全局只需要创建一次即可
    public GlySqlSessionFactory sqlSessionFactory;

    public SpringBeanInit() {
        InputStream inputStream = Resources.getResourceAsStream(beanConfigPath);
    }

}