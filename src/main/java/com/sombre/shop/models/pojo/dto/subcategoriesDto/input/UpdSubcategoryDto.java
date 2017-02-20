package com.sombre.shop.models.pojo.dto.subcategoriesDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
@Data
@AllArgsConstructor
public class UpdSubcategoryDto {
    private UUID subcategoryid;
    private String name;
    private String description;
}
