package com.sombre.shop.models.repositories.subcategoriesRepository;

import com.sombre.shop.models.pojo.dto.subcategoriesDto.input.AddSubcategoryDto;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.output.SubcategoriesByIdDto;
import com.sombre.shop.models.pojo.entity.SubCategories;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public interface SubcategoriesRepository {

    boolean addSubcategory(AddSubcategoryDto subCategory);

    boolean updateSubcategory(SubCategories subCategory);

    boolean deleteSubcategory(UUID subCategoryId);

    SubcategoriesByIdDto getSubcategoryById(UUID subCategoryId);

    List<SubcategoriesByIdDto> getAllSubcategoriesByCategoryId(UUID categoryId);
}
