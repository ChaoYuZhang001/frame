package cn.gly.mybatis.sqlnode;

import cn.gly.mybatis.sqlnode.entity.GlyDynamicContext;
import cn.gly.mybatis.sqlnode.interfacs.GlySqlNode;
import cn.gly.mybatis.util.GlyGenericTokenParser;
import cn.gly.mybatis.util.GlyOgnlUtils;
import cn.gly.mybatis.util.GlySimpleTypeRegistry;
import cn.gly.mybatis.util.GlyTokenHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装带有${}的SqlNode〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/3
 * @since 1.0.0
 */
public class GlyTextSqlNode implements GlySqlNode {

    private String textSql;

    public GlyTextSqlNode(String textSql) {
        this.textSql = textSql;
    }

    @Override
    public void apply(GlyDynamicContext context) {
        // 用来处理${}中的参数
        // select * from user where name like '${name}%'


        // 用来解析SQL文本中的#{}或者${}
        GlyGenericTokenParser tokenParser = new GlyGenericTokenParser("${", "}", new BindingTokenHandler(context));

        // JDBC可以直接执行的SQL语句
        String sql = tokenParser.parse(textSql);

        context.appendSql(sql);
    }

    public Boolean isDynamic() {
        return textSql.trim().indexOf("${") > -1;
    }

    class BindingTokenHandler implements GlyTokenHandler {

        private GlyDynamicContext context;

        public BindingTokenHandler(GlyDynamicContext context) {
            this.context = context;
        }

        /**
         * 使用参数值来替换${}
         *
         * @param content 是${}中的内容
         * @return 用来替换${}的值
         */
        @Override
        public String handleToken(String content) {
            // 获取参数值
            Object parameter = context.getBindings().get("_parameter");

            if (parameter == null) {
                return "";
            } else if (GlySimpleTypeRegistry.isSimpleType(parameter.getClass())) {
                return parameter.toString();
            }
            // POJO类型或者Map类型
            Object value = GlyOgnlUtils.getValue(content, parameter);
            return value == null ? "" : value.toString();
        }
    }
}