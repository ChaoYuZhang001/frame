package cn.gly.mybatis.util;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/9
 * @since 1.0.0
 */
public class GlyDocumentUtils {

    /**
     * @param inputStream
     * @return
     */
    public static Document getDocument(InputStream inputStream) {
        try {
            SAXReader saxReader = new SAXReader();
            return saxReader.read(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}