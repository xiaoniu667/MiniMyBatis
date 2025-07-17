package com.xiaoniu.session.defaults;

import com.xiaoniu.Configuration;
import com.xiaoniu.Environment;
import com.xiaoniu.executor.Executor;
import com.xiaoniu.executor.SimpleExecutor;
import com.xiaoniu.session.SqlSession;
import com.xiaoniu.session.SqlSessionFactory;
import com.xiaoniu.transaction.Transaction;
import com.xiaoniu.transaction.TransactionFactory;
import com.xiaoniu.transaction.TransactionIsolationLevel;

import java.sql.SQLException;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        //默认不设置自动提交
        return openSessionFromDataSource(configuration, null, false);
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return openSessionFromDataSource(configuration, null, autoCommit);
    }


    private SqlSession openSessionFromDataSource(Configuration configuration, TransactionIsolationLevel level, boolean autoCommit) {
        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
            final Executor executor = new SimpleExecutor(configuration, tx);
            return new DefaultSqlSession(configuration, executor, autoCommit);
        } catch (Exception e) {
            closeTransaction(tx); // may have fetched a connection so lets call close()
            throw new RuntimeException("Error opening session.  Cause: " + e, e);
        }
    }


    private void closeTransaction(Transaction tx) {
        if (tx != null) {
            try {
                tx.close();
            } catch (SQLException ignore) {
                // Intentionally ignore. Prefer previous error.
            }
        }
    }
}
