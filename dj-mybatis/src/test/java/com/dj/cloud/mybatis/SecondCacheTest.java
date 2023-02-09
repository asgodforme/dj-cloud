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

import java.io.IOException;
import java.util.List;

public class SecondCacheTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private Configuration configuration;

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



}
