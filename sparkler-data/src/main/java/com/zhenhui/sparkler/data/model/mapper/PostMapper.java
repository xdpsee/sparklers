package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.post.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    int insert(Post post);

    Post selectById(@Param("id") long id);

    List<Post> selectByIds(@Param("ids") List<Long> ids);
}
