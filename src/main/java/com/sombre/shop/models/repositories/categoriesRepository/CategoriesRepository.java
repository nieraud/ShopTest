package com.sombre.shop.models.repositories.categoriesRepository;

import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.entity.Categories;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public interface CategoriesRepository {

    boolean addCategory(AddCategoryDto category);

    boolean updateCategory(Categories category);

    boolean deleteCategory(UUID categoryId);

    Categories getCategoryById(UUID categoryId);

    List<Categories> getAllCategories();
}
