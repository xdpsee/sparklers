package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.post.PostStats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostStatsMapper {

    int insert(PostStats stats);

    List<PostStats> selectByCategory(@Param("category") long category, @Param("startNo") long startNo, @Param("count") int count);

    List<PostStats> selectByCategorySection(@Param("category")long category, @Param("section")long section, @Param("startNo")long startNo, @Param("count")int count);

}
