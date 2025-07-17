# **MiniMyBatis**

🚀 *一个轻量级、手写实现的简易版 MyBatis 框架*

## **项目简介**

`MiniMyBatis` 是一个基于 Java 的简易版 ORM（对象关系映射）框架，灵感来源于 MyBatis。它通过反射和动态代理技术，实现了核心的 SQL 映射、结果集转换和事务管理功能，避免了传统 JDBC 的冗余代码，让数据库操作更简洁。

**核心功能**：
✅ 基于接口的 SQL 映射（Mapper 动态代理）
✅ 自动将 ResultSet 转换为 Java 对象
✅ 简易事务管理（支持手动提交/回滚）
✅ 支持参数化 SQL 和动态 SQL 拼接
✅ 轻量级（无第三方依赖，核心代码仅 XX KB）

## **快速开始**

### 1. 添加依赖

将项目源码下载到本地，或直接打包为 JAR 引入你的项目。

### 2. 配置数据源

在  [orm-config.xml](E:\code_new\study_mybatis\src\main\resources\orm-config.xml) 中配置数据库连接：

properties

```
<property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
<property name="url" value="jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8"/>
<property name="username" value="root"/>
<property name="password" value="zyw123456"/>
```

### 3. 定义 Mapper 接口

java

```
public interface SchoolMapper {

    School querySchoolName(@XiaoNiuParam("school") School school, @XiaoNiuParam("school2") School school2);

    int updateSchoolName(School school);

    int insertSchoolName(School school);

    int deleteSchoolName(School school);

    List<School> queryList(@XiaoNiuParam("school") School school);

}
```

### 4. 使用 SqlSession 执行操作

java

```
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
        Reader reader = Resource.getResourceAsReader("orm-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        SchoolMapper schoolMapper = sqlSession.getMapper(SchoolMapper.class);

        // 4. 测试验证
        List<School> schools = schoolMapper.queryList(new School(1));
        schools.forEach(System.out::println);
    }
}

```

## **功能对比**

| 特性            | MyBatis 官方 | MiniMyBatis |
| :-------------- | :----------- | :---------- |
| 动态代理 Mapper | ✔️            | ✔️           |
| XML 配置支持    | ✔️            | ✔️           |
| 缓存机制        | ✔️            | ❌           |
| 事务管理        | ✔️            | ✔️（简易）   |
| 体积            | ~5MB         | ~50KB       |

## **未来计划**

- 实现二级缓存
- 支持批量操作（Batch）

## **参与贡献**

欢迎提交 Issue 或 PR！

------

### **其他可选补充**

1. **代码结构说明**（如果项目分层复杂）：

   text

   ```
   src/
   ├── main/
   │   ├── java/
   │   │   ├── core/       # 核心逻辑（SqlSession、Executor 等）
   │   │   ├── mapping/    # Mapper 接口代理
   │   │   └── util/       # 工具类（反射、数据库连接等）
   ```

2. **License**：

   markdown

   ```
   ## License  
   MIT © [你的名字]
   ```

根据你的实际实现调整功能描述和代码示例，让用户快速理解项目价值和使用方式。
