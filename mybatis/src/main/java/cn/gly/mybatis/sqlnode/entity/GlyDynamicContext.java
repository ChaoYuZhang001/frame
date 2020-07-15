
package cn.gly.mybatis.sqlnode.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装所有SqlNode执行过程中需要的参数信息进行统一封装传递，对于sql语句拼接〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyDynamicContext {

    public static final String PARAMETER_OBJECT_KEY = "_parameter";

    private StringBuffer stringBuffer = new StringBuffer();

    private Map<String, Object> bindings = new HashMap<>();

    /**
     * @param param 入参
     */
    public GlyDynamicContext(Object param) {
        bindings.put(PARAMETER_OBJECT_KEY, param);
    }

    /**
     * @return 拼接后sql
     */
    public String getSql() {
        return stringBuffer.toString();
    }

    /**
     * sql文本拼接
     *
     * @param sql
     */
    public void appendSql(String sql) {
        stringBuffer.append(sql);
        stringBuffer.append(" ");
    }

    public Map<String, Object> getBindings() {
        return bindings;
    }

    public void addBindings(String key, Object value) {
        this.bindings.put(key, value);
    }
}