package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.comment.CommentRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentRelationMapper {

    int insertOrIgnore(CommentRelation relation);

    List<CommentRelation> selectByRefId(@Param("refId") long refId, @Param("startNo") int startNo, @Param("count") int count, @Param("sortAsc") boolean sortAsc);

}
