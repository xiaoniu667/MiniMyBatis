
package com.xiaoniu.executor.result;

/**
 * 结果的内容
 * @param <T>
 */
public interface ResultContext<T> {

  T getResultObject();

  int getResultCount();

  boolean isStopped();

  void stop();

}
