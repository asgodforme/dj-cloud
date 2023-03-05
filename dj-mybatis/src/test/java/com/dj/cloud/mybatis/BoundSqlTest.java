package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.entity.Blog;
import com.dj.cloud.mybatis.mapper.BlogMapper;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoundSqlTest {

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
    public void ognlTest() {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        Blog blog = new Blog();
        boolean b = expressionEvaluator.evaluateBoolean("id != null && comment != null", blog);
        System.out.println(b);
    }

    @Test
    public void ifTest() {
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("title");
        DynamicContext dynamicContext = new DynamicContext(configuration, blog);
        new StaticTextSqlNode("select * from blog where").apply(dynamicContext);
        IfSqlNode ifSqlNode = new IfSqlNode(new StaticTextSqlNode(" and id = #{id}"), "id != null");
        IfSqlNode titleIfSqlNode = new IfSqlNode(new StaticTextSqlNode(" or title = #{title}"), "title != null");
//        ifSqlNode.apply(dynamicContext);
        MixedSqlNode mixedSqlNode = new MixedSqlNode(Arrays.asList(ifSqlNode, titleIfSqlNode));
        WhereSqlNode whereSqlNode = new WhereSqlNode(configuration, mixedSqlNode);
        whereSqlNode.apply(dynamicContext);
        System.out.println(dynamicContext.getSql());
    }


    @Test
    public void whereTest() {
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(1);
        Blog blog1 = mapper.findBlog(blog);
        System.out.println(blog1);
    }

    @Test
    public void foreachTest() {
        Map<Object, Object> parameter = new HashMap<>();
        parameter.put("list", Arrays.asList(1, 2, 3, 4, 5));
        List<Blog> blogs =  sqlSession.selectList("findByIds", parameter);
        System.out.println(blogs.size());
    }
}
