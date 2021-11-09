package com.dj.cloud.mybatis.mapper;

import com.dj.cloud.mybatis.entity.Blog;
import org.apache.ibatis.annotations.Update;

public interface BlogMapper {

    Blog selectBlog(int id);

    @Update("update blog set comment = #{content} where id = #{id}")
    void updateContent(String content, Integer id);
}
