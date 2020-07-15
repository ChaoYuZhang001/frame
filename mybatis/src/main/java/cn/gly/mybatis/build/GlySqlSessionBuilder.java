package cn.gly.mybatis.build;


import cn.gly.mybatis.config.GlyConfiguration;
import cn.gly.mybatis.factory.GlyDefaultSqlSessionFactory;
import cn.gly.mybatis.factory.GlySqlSessionFactory;
import cn.gly.mybatis.util.GlyDocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * 〈一句话功能简述〉<br>
 * 〈生产SqlSessionFactory〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlySqlSessionBuilder {

    public GlySqlSessionFactory builder(InputStream inputStream) {

        Document document = GlyDocumentUtils.getDocument(inputStream);

        GlyXmlConfigBuilder configBuilder = new GlyXmlConfigBuilder();

        //根据InputStream 流信息获取流对象
        GlyConfiguration configuration = configBuilder.parseConfig(document.getRootElement());
        return build(configuration);
    }

    public GlySqlSessionFactory builder(Reader reader) {
        //根据Reader 流信息获取流对象

        return null;
    }

    public GlySqlSessionFactory build(GlyConfiguration configuration) {

        return new GlyDefaultSqlSessionFactory(configuration);
    }
}