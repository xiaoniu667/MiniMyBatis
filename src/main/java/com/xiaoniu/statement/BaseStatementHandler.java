package com.xiaoniu.statement;

import com.xiaoniu.Configuration;
import com.xiaoniu.executor.Executor;
import com.xiaoniu.executor.parameter.ParameterHandler;
import com.xiaoniu.executor.resultset.ResultSetHandler;
import com.xiaoniu.mapping.BoundSql;
import com.xiaoniu.mapping.MappedStatement;
import com.xiaoniu.mapping.SqlSource;
import sun.plugin2.main.server.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseStatementHandler implements StatementHandler {
    protected final Configuration configuration;
    protected final Executor executor;
    protected final MappedStatement mappedStatement;
    protected final ParameterHandler parameterHandler;
    protected final Object parameterObject;
    protected SqlSource sqlSource;
    protected ResultSetHandler resultSetHandler;


    public BaseStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameterObject) {
        this.configuration = mappedStatement.getConfiguration();
        this.executor = executor;
        this.mappedStatement = mappedStatement;
        this.sqlSource = mappedStatement.getSqlSource();
        // 参数和结果集
        this.parameterObject = parameterObject;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappedStatement);
        this.parameterHandler = configuration.newParameterHandler(mappedStatement, parameterObject);
    }

    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement;
        try {
            // 实例化 Statement
            statement = instantiateStatement(connection);
            // 参数设置，可以被抽取，提供配置
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
}
