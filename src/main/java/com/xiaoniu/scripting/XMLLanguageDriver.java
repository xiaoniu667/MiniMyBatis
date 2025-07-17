
package com.xiaoniu.scripting;

import com.xiaoniu.Configuration;
import com.xiaoniu.mapping.SqlSource;
import org.dom4j.Element;

public class XMLLanguageDriver implements LanguageDriver {


  @Override
  public SqlSource createSqlSource(Configuration configuration, Element element, Class<?> parameterType) {
    //用xml脚本构建器解析
    return new XMLScriptBuilder(configuration, element, parameterType).parseScriptNode();
  }
}
