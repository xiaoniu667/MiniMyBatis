package com.xiaoniu.binding;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xiaoniu.Configuration;
import com.xiaoniu.annotation.XiaoNiuParam;
import com.xiaoniu.executor.parameter.ParamNameResolver;
import com.xiaoniu.mapping.MappedStatement;
import com.xiaoniu.mapping.SqlCommandType;
import com.xiaoniu.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;


public class MapperMethod {

    private SqlCommand command;
    private final ParamNameResolver paramNameResolver;
    private final boolean returnsMany;
    private final Class<?> returnType;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(mapperInterface, method, configuration);
        this.returnType = method.getReturnType();
        returnsMany = configuration.getObjectFactory().isCollection(this.returnType) || this.returnType.isArray();
        this.paramNameResolver = new ParamNameResolver(configuration, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) throws Exception {
        Object result = null;
        switch (command.type) {
            case INSERT:
                Object param = convertArgsToSqlCommandParam(args);
                result = sqlSession.insert(command.getName(), param);
                break;
            case DELETE:
                 param = convertArgsToSqlCommandParam(args);
                result = sqlSession.delete(command.getName(), param);
                break;
            case UPDATE:
                param = convertArgsToSqlCommandParam(args);
                result = sqlSession.update(command.getName(), param);
                break;
            case SELECT:
                param = convertArgsToSqlCommandParam(args);
                if (returnsMany) {
                    result = sqlSession.selectList(command.getName(), param);
                } else {
                    result = sqlSession.selectOne(command.getName(), param);
                }
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }


    private Object convertArgsToSqlCommandParam(Object[] args) {
        return paramNameResolver.getNamedParams(args);
    }


    //将参数转为一个map集合 一一对应上去
    //例如：select id, name from school where name = #{name} and first_name = #{name}
    //难道我按照顺序arg0 arg1吗  肯定不对 因为实际上只有一个参数 所以得建立一个map
    //    //但是目前这样做 如果参数是一个对象呢？ 也可以 反正存储的是object
//    private Object convertArgsToSqlCommandParam(Object[] args) {
//        HashMap<String, Object> paramValueMapping = new HashMap<>();
//        Parameter[] parameters = method.getParameters();
//
//        for (int i = 0; i < parameters.length; i++) {
//            Parameter parameter = parameters[i];
//            if (parameter.isAnnotationPresent(Param.class)) {
//                String name = parameter.getAnnotation(Param.class).value();
//                paramValueMapping.put(name, args[i]);
//            }
//            String name = parameter.getName();
//            paramValueMapping.put(name, args[i]);
//        }
//        return paramValueMapping;
//    }


    /**
     * 封装为sql指令
     */
    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Class<?> mapperInterface, Method method, Configuration configuration) {
            final String methodName = method.getName();
            final Class<?> declaringClass = method.getDeclaringClass();
            MappedStatement ms = resolveMappedStatement(mapperInterface, methodName, declaringClass,
                    configuration);
            name = ms.getId(); //com.xiaoniu.ISchoolDao.querySchoolName
            type = ms.getSqlCommandType();
            if (type == SqlCommandType.UNKNOWN) {
                throw new RuntimeException("Unknown execution method for: " + name);
            }
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }

        private MappedStatement resolveMappedStatement(Class<?> mapperInterface, String methodName,
                                                       Class<?> declaringClass, Configuration configuration) {
            String statementId = mapperInterface.getName() + "." + methodName;//com.xiaoniu.ISchoolDao.querySchoolName
            if (configuration.hasStatement(statementId)) {
                return configuration.getMappedStatement(statementId); //获取对应的MappedStatement
            } else if (mapperInterface.equals(declaringClass)) {
                return null;
            }
            for (Class<?> superInterface : mapperInterface.getInterfaces()) {
                if (declaringClass.isAssignableFrom(superInterface)) {
                    MappedStatement ms = resolveMappedStatement(superInterface, methodName,
                            declaringClass, configuration);
                    if (ms != null) {
                        return ms;
                    }
                }
            }
            return null;
        }
    }
}
