package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoriesService {
    void AddCategory(CategoryDTO categoryDTO);

    void DeleteCategoryById(long id);

    void StartOrStop(Integer status, long id);

    PageResult PageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void UpdateCategory(CategoryDTO categoryDTO);

    List<Category> GetCategoriesByType(Integer type);
}
