package com.zhenhui.sparkler.data.repository.impl;

import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.model.mapper.CategoryMapper;
import com.zhenhui.sparkler.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category createCategory(Category category) {

        categoryMapper.insert(category);

        return category;
    }

    @Override
    public List<Category> queryAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category queryById(long categoryId) {
        return categoryMapper.selectById(categoryId);
    }

    @Override
    public boolean updateCategory(long categoryId, String title) {
        return categoryMapper.update(categoryId, title) > 0;
    }

    @Override
    public boolean updateStatus(long categoryId, int status) {
        return categoryMapper.updateStatus(categoryId, status) > 0;
    }
}


