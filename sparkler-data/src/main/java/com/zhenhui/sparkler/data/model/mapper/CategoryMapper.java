package com.zhenhui.sparkler.data.model.mapper;

import com.zhenhui.sparkler.data.model.core.post.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int insert(Category category);

    List<Category> selectAll();

    Category selectById(@Param("categoryId") long categoryId);

    int update(@Param("categoryId") long categoryId, @Param("title") String title);

    int updateStatus(@Param("categoryId") long categoryId, @Param("status") int status);
}
