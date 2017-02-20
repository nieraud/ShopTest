package com.sombre.shop.models.pojo.dto.subcategoriesDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 18.02.17.
 */
@Data
@AllArgsConstructor
public class SubcategoriesByIdDto {
    private UUID uniqueid;
    private String name;
    private String description;
    private DateTime dateadded;

    private UUID id_category;
    private String catName;

    private UUID subAdminId;
    private String firstname;
    private String lastlast;
}
