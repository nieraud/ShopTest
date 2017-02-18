package com.sombre.shop.models.pojo.dto.productsDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
@Data
@AllArgsConstructor
public class ProductsBySubcategoryDto {
    private UUID uniqueid;
    private String name;
    private String photo;
    private String description;
    private int price;
    private boolean instock;
    private DateTime dateadded;

    private UUID subcategoryId;
    private String subcategoryName;
    private String subcategoryDescription;
    private UUID id_category;
}
