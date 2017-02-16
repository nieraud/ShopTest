package com.sombre.shop.models.pojo.dto.categoriesDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by inna on 16.02.17.
 */
@Data
@AllArgsConstructor
public class AddCategoryDto {
    private String name;
    private String description;
}
