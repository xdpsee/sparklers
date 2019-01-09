package com.zhenhui.sparkler.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zhenhui.sparkler.api.domain.CategoryDto;
import com.zhenhui.sparkler.api.service.CategoryService;
import com.zhenhui.sparkler.data.model.core.post.Category;
import com.zhenhui.sparkler.data.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service(version = "1.0.0")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> listAll() {
        return categoryRepository.queryAll().stream()
                .map(e -> {
                    CategoryDto category = new CategoryDto();
                    BeanUtils.copyProperties(e, category);
                    return category;
                }).collect(toList());
    }

    @Override
    public CategoryDto createCategory(String title) {

        Category category = new Category();
        category.setTitle(title);
        category.setStatus(1);

        category = categoryRepository.createCategory(category);

        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

    @Override
    public CategoryDto updateCategory(long id, String title) {

        if (categoryRepository.updateCategory(id, title)) {
            Category category = categoryRepository.queryById(id);
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(category, dto);
            return dto;
        }

        return null;
    }

    @Override
    public boolean removeCategory(long id) {
        return categoryRepository.updateStatus(id, 0);
    }

    @Override
    public boolean recoverCategory(long id) {
        return categoryRepository.updateStatus(id, 1);
    }
}


