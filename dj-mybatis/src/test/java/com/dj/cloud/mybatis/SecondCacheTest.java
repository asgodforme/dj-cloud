package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.entity.Blog;
import com.dj.cloud.mybatis.entity.Comments;
import com.dj.cloud.mybatis.mapper.BlogMapper;
import org.apache.catalina.User;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SecondCacheTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private static Configuration configuration;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = factory.openSession();
        configuration = factory.getConfiguration();
    }

    @Test
    public void cacheTest1() {
        Cache cache = configuration.getCache("com.dj.cloud.mybatis.mapper.BlogMapper");
        cache.putObject("jiangjie", new Blog());
        System.out.println(cache.getObject("jiangjie"));
    }

    /**
     * 命中条件: 1. 会话提交
     */
    @Test
    public void cacheTest5() {
        // xml中的<Cache/>标签和mapper中的@CacheNamespace注解不是同一个二级缓存。只针对对应作用于的sql有缓存作用
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        sqlSession1.commit();
        System.out.println(blog1);

        SqlSession sqlSession2 = factory.openSession();
        BlogMapper mapper2 = sqlSession2.getMapper(BlogMapper.class);
        Blog blog2 = mapper2.selectBlog(1);
        System.out.println(blog2);

        System.out.println(blog1 == blog2);
    }

    /**
     * 循环依赖子查询
     */
    @Test
    public void test() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        System.out.println(blog1);
    }

    /**
     * 循环依赖子查询
     */
    @Test
    public void test1() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Comments comments = mapper1.selectComments(1);
        System.out.println(comments);
    }

    /**
     * 懒加载
     */
    @Test
    public void lazyTest() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        blog1.getComments();
    }

    /**
     * 关闭会话之后懒加载
     */
    @Test
    public void stopSessionLazyTest() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        sqlSession1.close();
        blog1.getComments();
    }

    /**
     * 调用set后懒加载会移除
     */
    @Test
    public void lazySetTest() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        blog1.setComments(new ArrayList<>());
        System.out.println(blog1.getComments());
    }

    @Test
    public void lazySerializableTest() throws IOException, ClassNotFoundException {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlog(1);
        byte[] bytes = writeObject(blog1);
        Blog blog2 = (Blog) readObject(bytes);
        System.out.println("反序列化完成");
        System.out.println(blog2.getComments());
    }

    public static class ConfigurationFactory {
        public static Configuration getConfiguration() {
            return configuration;
        }
    }

    private static byte[] writeObject(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        return byteArrayOutputStream.toByteArray();
    }

    private static Object readObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayOutputStream);
        return objectInputStream.readObject();
    }

    @Test
    public void nestQueryTest() {
        SqlSession sqlSession1 = factory.openSession();
        BlogMapper mapper1 = sqlSession1.getMapper(BlogMapper.class);
        Blog blog1 = mapper1.selectBlogById(1);
        System.out.println(blog1);
    }



}
