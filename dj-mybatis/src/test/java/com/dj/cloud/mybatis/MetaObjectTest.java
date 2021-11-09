package com.dj.cloud.mybatis;

import com.dj.cloud.mybatis.entity.Blog;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

public class MetaObjectTest {

    @Test
    public void test() {
        Object blog = new Blog();
        Configuration configuration = new Configuration();
        MetaObject metaObject = configuration.newMetaObject(blog);
        metaObject.setValue("comment", "dddddd");
        System.out.println(metaObject.getValue("comment"));
    }
}
