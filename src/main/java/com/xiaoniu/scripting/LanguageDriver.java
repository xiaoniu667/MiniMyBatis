
package com.xiaoniu.scripting;


import com.xiaoniu.Configuration;
import com.xiaoniu.mapping.SqlSource;
import org.dom4j.Element;

public interface LanguageDriver {

  SqlSource createSqlSource(Configuration configuration, Element element, Class<?> parameterType);
}
