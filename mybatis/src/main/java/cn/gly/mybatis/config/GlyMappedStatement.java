package cn.gly.mybatis.config;


import cn.gly.mybatis.sqlsource.interfacs.GlySqlSource;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/2
 * @since 1.0.0
 */
public class GlyMappedStatement {

    private String statementId;

    private Class resultClass;

    private String resultType;

    private String statementType;

    private GlySqlSource sqlSource;

    public GlyMappedStatement(String statementId, Class resultClass, String statementType, GlySqlSource sqlSource) {
        this.statementId = statementId;
        this.resultClass = resultClass;
        this.statementType = statementType;
        this.sqlSource = sqlSource;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public Class getResultClass() {
        return resultClass;
    }

    public void setResultClass(Class resultClass) {
        this.resultClass = resultClass;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public GlySqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(GlySqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }
}