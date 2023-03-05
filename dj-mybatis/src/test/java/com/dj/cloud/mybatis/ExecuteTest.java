package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.entity.Blog;
import com.dj.cloud.mybatis.mapper.BlogMapper;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecuteTest {

    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;
    private SqlSessionFactory sqlSessionFactory;

    private static final String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8&useSSL=false";
    private static final String username = "root";
    private static final String password= "root";


    @Before
    public void init() throws IOException, SQLException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        configuration = sqlSessionFactory.getConfiguration();
        connection = DriverManager.getConnection(url, username, password);
        jdbcTransaction = new JdbcTransaction(connection);
    }


    /**
     * 简单执行器
     * @throws SQLException
     */
    @Test
    public void simpleTest() throws SQLException {
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog");
        List<Object> objects = simpleExecutor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        simpleExecutor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        System.out.println(objects);
    }

    /**
     * 重用执行器
     * @throws SQLException
     */
    @Test
    public void reuseTest() throws SQLException {
        ReuseExecutor simpleExecutor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog");
        List<Object> objects = simpleExecutor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        simpleExecutor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
        System.out.println(objects);
    }

    /**
     * 批处理执行器
     * 只针对修改操作
     * 批处理操作必须手动刷新
     * @throws SQLException
     */
    @Test
    public void batchTest() throws SQLException {
        BatchExecutor simpleExecutor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.dj.cloud.mybatis.mapper.BlogMapper.updateContent");
        Map<Object, Object> map = new HashMap<>();
        map.put("content", "哈哈哈哈哈");
        map.put("id", "2");
        simpleExecutor.doUpdate(ms, map);
        simpleExecutor.doUpdate(ms, map);
        simpleExecutor.doFlushStatements(false);
    }

    /**
     * 命中缓存
     * @throws SQLException
     */
    @Test
    public void cacheTest() throws SQLException {
        Executor executor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog");
        List<Object> objects = executor.query(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        executor.query(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        System.out.println(objects);
    }

    @Test
    public void cacheExecutorTest() throws SQLException {
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration, jdbcTransaction);

        Executor cachingExecutor = new CachingExecutor(simpleExecutor);
        MappedStatement ms = configuration.getMappedStatement("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog");
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        cachingExecutor.commit(true); // 1.先走二级缓存，2.再走一级缓存
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
        cachingExecutor.query(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER);
    }

    @Test
    public void paramTest() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(1);
//            System.out.println(blog);
//            List<Object> list = session.selectList("com.dj.cloud.mybatis.mapper.BlogMapper.selectBlog", 1);
//            System.out.println(list);
        }
    }




}
