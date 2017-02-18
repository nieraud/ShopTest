package com.sombre.shop.models.pojo.dto.subcategoriesDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 18.02.17.
 */
@Data
@AllArgsConstructor
public class SubcategoriesByIdDto {
    private UUID uniqueid;
    private String subname;
    private String subdescription;
    private UUID id_category;
    private String name;
    private String description;
}
