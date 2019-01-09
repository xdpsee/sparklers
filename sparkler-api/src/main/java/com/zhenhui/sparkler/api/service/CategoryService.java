package com.zhenhui.sparkler.api.service;

import com.zhenhui.sparkler.api.domain.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> listAll();

    CategoryDto createCategory(String title);

    CategoryDto updateCategory(long id, String title);

    boolean removeCategory(long id);

    boolean recoverCategory(long id);
}




