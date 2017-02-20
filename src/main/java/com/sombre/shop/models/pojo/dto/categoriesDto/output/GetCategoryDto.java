package com.sombre.shop.models.pojo.dto.categoriesDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
@Data
@AllArgsConstructor
public class GetCategoryDto {
    private UUID uniqueid;
    private String name;
    private String description;
    private DateTime dateadded;
    private UUID id_adminadded;

    private String firstname;
    private String lastname;
}
