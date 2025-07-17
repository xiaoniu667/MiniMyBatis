package com.xiaoniu;

import com.xiaoniu.bean.School;
import com.xiaoniu.bean.SchoolMapper;
import com.xiaoniu.builder.SqlSessionFactoryBuilder;
import com.xiaoniu.io.Resource;
import com.xiaoniu.session.SqlSession;
import com.xiaoniu.session.SqlSessionFactory;

import java.io.Reader;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        MapperProxyFactory<UserMapper> factory = new MapperProxyFactory<>(UserMapper.class);
//        HashMap<String, String> sqlSession = new HashMap<>();
//        sqlSession.put("com.xiaoniu.bean.UserMapper.getUserById","模拟执行sql语句");
//        UserMapper userMapper = factory.newInstance(sqlSession);
//        String result = userMapper.getUserById(1);
//        System.out.println(result);

//        // 1. 注册 Mapper
//        MapperRegistry registry = new MapperRegistry();
//        registry.addMappers("com.xiaoniu.bean");
//
//
//        // 2. 从 SqlSession 工厂获取 Session
//        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3. 获取映射器对象
//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//         // 4. 测试验证
//        String res = userDao.queryUserName("10001");
//        System.out.println("测试结果：" + res + "");

//        Reader reader = Resource.getResourceAsReader("orm-config.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        // 3. 获取映射器对象
//        ISchoolDao schoolDao = sqlSession.getMapper(ISchoolDao.class);
//
//        // 4. 测试验证
//        String res = schoolDao.querySchoolName("10001");
//        System.out.println("测试结果：" + res + "");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config-datasource.xml"));


        Reader reader = Resource.getResourceAsReader("orm-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);

        // 4. 测试验证
//        School school = schoolDao.querySchoolName(new School(1),new School("name"));
//        int count = schoolDao.insertSchoolName(new School(1,"小牛3"));
//        int count = schoolDao.deleteSchoolName(new School(1, "小牛4"));
//        sqlSession.commit();
        List<School> schools = schoolMapper.queryList(new School(1));
        schools.forEach(System.out::println);
//        System.out.println("测试结果：" + schools + "");

    }
}
