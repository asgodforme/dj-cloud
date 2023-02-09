package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.entity.Blog;
import com.dj.cloud.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.util.List;

public class FirstCacheTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = factory.openSession();
    }

    /**
     1. 同一个会话
     2.sql语句，参数相同
     3.相同的statementID
     4.RowBounds相同
     */
    @Test
    public void test1() {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
//        Blog blog1 = factory.openSession().getMapper(BlogMapper.class).selectBlog3(1);
        List<Object> list = sqlSession.selectList("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog", 1, RowBounds.DEFAULT);
        System.out.println(blog == list.get(0));
    }

    @Test
    public void test2() {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlog(1);
//        sqlSession.clearCache();
//        mapper.setComment(1, "llllllll");
        Blog blog1 = mapper.selectBlog(1);
        System.out.println(blog == blog1);
    }

    @Test
    public void testBySpring() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        // spring：  maaper 动态代理 -> SqlSessionTemplate -> SqlSessionInterceptor动态代理 -> SqlSessionFactory
        BlogMapper blogMapper = context.getBean(BlogMapper.class);
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) context.getBean("transactionManager");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        // 每次都会构造一个新的会话发起调用,所以一级缓存失效 ，解决方法：开启事务，将两次查询放到一个事务中
        Blog blog = blogMapper.selectBlog(1);
        Blog blog1 = blogMapper.selectBlog(1);
        System.out.println(blog == blog1);
    }

}
