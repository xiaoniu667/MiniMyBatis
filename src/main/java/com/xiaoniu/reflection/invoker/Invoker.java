package com.xiaoniu.reflection.invoker;

public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
