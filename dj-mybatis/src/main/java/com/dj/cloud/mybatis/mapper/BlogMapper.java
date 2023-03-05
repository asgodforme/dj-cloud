package com.dj.cloud.mybatis.mapper;

import com.dj.cloud.mybatis.entity.Blog;
import com.dj.cloud.mybatis.entity.Comments;
import groovy.cli.Option;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@CacheNamespace
public interface BlogMapper {

    Blog selectBlog(int id);

    Comments selectComments(int id);

    @Select({"select * from blog where id = #{1}"})
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Blog selectBlog3(int id);

    @Select({"select * from blog where id = #{id} or comment = #{comment}"})
    Blog selectByIdOrComment(Integer id, String comment);

    @Select("select * from blog where id < 100")
    Blog selectAll();

//    @Update("update blog set comment= #{param2} where id = #{param1}")
    @Update("select * from blog ")
    int setComment(Integer id, String name);

    Blog selectBlogById(Integer id);

    Blog findBlog(Blog blog);
}
