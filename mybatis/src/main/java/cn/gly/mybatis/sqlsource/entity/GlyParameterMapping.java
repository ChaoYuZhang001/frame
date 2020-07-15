
package cn.gly.mybatis.sqlsource.entity;

/**
 * 〈一句话功能简述〉<br>
 * 〈记录参数名称，参数类型〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyParameterMapping {

    private String name;

    private Class type;

    public GlyParameterMapping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}