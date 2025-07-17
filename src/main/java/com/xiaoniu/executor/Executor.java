package com.xiaoniu.executor;

import com.xiaoniu.mapping.MappedStatement;
import com.xiaoniu.transaction.Transaction;
import sun.plugin2.main.server.ResultHandler;

import java.sql.SQLException;
import java.util.List;

public interface Executor {
    ResultHandler NO_RESULT_HANDLER = null;

    <E> List<E> query(MappedStatement ms, Object parameter) throws Exception;

    int update(MappedStatement ms, Object parameter) throws SQLException;


    Transaction getTransaction();

    void commit(boolean required) throws SQLException;

    void rollback(boolean required) throws SQLException;

    void close(boolean forceRollback);

    boolean isClosed();
}
