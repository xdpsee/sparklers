package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.reply.ReplyStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyStatsMapper {

    int insertOrIgnore(ReplyStats stats);

    ReplyStats selectForUpdate(@Param("postId") long postId);

    int updateCount(@Param("postId") long postId, @Param("version") int version);

}


