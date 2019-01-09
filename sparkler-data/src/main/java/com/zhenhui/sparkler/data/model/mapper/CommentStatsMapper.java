package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.comment.CommentStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentStatsMapper {

    int insertOrIgnore(CommentStats count);

    CommentStats selectForTest(@Param("replyId") long replyId, @Param("refId") long refId);

    int selectCount(@Param("replyId") long replyId, @Param("refId") long refId);

    CommentStats selectForUpdate(@Param("replyId")long replyId, @Param("refId") long refId);

    int updateCount(@Param("replyId") long replyId, @Param("refId") long refId, @Param("version") long version);

}
