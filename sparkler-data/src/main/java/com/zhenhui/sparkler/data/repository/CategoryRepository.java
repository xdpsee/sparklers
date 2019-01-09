package com.zhenhui.sparkler.data.repository;

import com.zhenhui.sparkler.data.model.core.post.Category;

import java.util.List;

public interface CategoryRepository {

    Category createCategory(Category category);

    List<Category> queryAll();

    Category queryById(long categoryId);

    boolean updateCategory(long categoryId, String title);

    boolean updateStatus(long categoryId, int status);


}
