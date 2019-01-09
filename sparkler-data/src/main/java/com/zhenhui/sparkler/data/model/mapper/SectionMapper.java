package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.post.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectionMapper {

    int insert(Section section);

    List<Section> selectAll(@Param("categoryId") long categoryId);

    Section selectById(@Param("sectionId") long sectionId);

    Integer update(@Param("sectionId") long sectionId
            , @Param("title") String title
            , @Param("description") String description);

    Integer updateStatus(@Param("sectionId") long sectionId, @Param("status") int status);

}
