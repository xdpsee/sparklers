package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.comment.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CommentMapper {

    int insert(Comment comment);

    Comment selectById(@Param("id") long id);

    List<Comment> selectByIds(@Param("ids") Collection<Long> ids);

    List<Comment> selectBySeq(@Param("replyId") long replyId, @Param("refId") long refId, @Param("startNo") int startNo, @Param("count") int count, @Param("sortAsc") boolean asc);

}


