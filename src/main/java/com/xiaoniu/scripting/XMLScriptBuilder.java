package com.xiaoniu.scripting;

import com.xiaoniu.Configuration;
import com.xiaoniu.builder.xml.BaseBuilder;
import com.xiaoniu.mapping.SqlSource;
import org.dom4j.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLScriptBuilder extends BaseBuilder {

    private final Element context;
    private boolean isDynamic;
    private final Class<?> parameterType;


    public XMLScriptBuilder(Configuration configuration, Element context, Class<?> parameterType) {
        super(configuration);
        this.context = context;
        this.parameterType = parameterType;
    }


    public SqlSource parseScriptNode() {
        List<SqlNode> sqlNodes = parseDynamicTags(context);
        MixedSqlNode mixedSqlNode = new MixedSqlNode(sqlNodes);
        return new RawSqlSource(configuration, mixedSqlNode, parameterType);
    }

    private List<SqlNode> parseDynamicTags(Element context) {
        ArrayList<SqlNode> sqlNodes = new ArrayList<>();
        String data = context.getTextTrim();
        sqlNodes.add(new StaticTextSqlNode(data));
        return sqlNodes;
    }


}
