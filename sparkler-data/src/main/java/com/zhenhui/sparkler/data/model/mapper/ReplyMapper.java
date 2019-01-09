package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.reply.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    int insert(Reply reply);

    Reply selectById(@Param("id") long replyId);

    List<Reply> selectBySeq(@Param("postId") Long postId, @Param("startNo") int startNo, @Param("count") int count, @Param("sortAsc") boolean asc);

}


