package com.sombre.shop.models.repositories.categoriesRepository;

import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.dto.categoriesDto.output.GetCategoryDto;
import com.sombre.shop.models.pojo.entity.Categories;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public interface CategoriesRepository {

    boolean addCategory(AddCategoryDto category, UUID adminId);

    boolean updateCategory(Categories category);

    boolean deleteCategory(UUID categoryId);

    GetCategoryDto getCategoryById(UUID categoryId);

    List<GetCategoryDto> getAllCategories();
}
