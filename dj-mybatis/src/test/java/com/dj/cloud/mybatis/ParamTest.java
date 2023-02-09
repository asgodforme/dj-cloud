package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.mapper.BlogMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParamTest {
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private Configuration configuration;
    private BlogMapper blogMapper;

    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        factory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        sqlSession = factory.openSession();
        configuration = factory.getConfiguration();
        blogMapper = sqlSession.getMapper(BlogMapper.class);
    }

    @After
    public void close() {
        sqlSession.close();
    }


    @Test
    public void singleTest() {
        blogMapper.selectBlog(1);
    }

    @Test
    public void singleTest2() {
        blogMapper.selectByIdOrComment(1, "hello world");
    }

    @Test
    public void test3() {
        List<Object> list = new ArrayList<>();
        ResultHandler handler = new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                if (resultContext.getResultCount() == 1) {
                    resultContext.stop();
                }
                list.add(resultContext.getResultObject());
            }
        };

        sqlSession.select("com.dj.cloud.mybatis.mapper.BlogMapper.selectAll", handler);
        System.out.println(list.size());
    }
}
