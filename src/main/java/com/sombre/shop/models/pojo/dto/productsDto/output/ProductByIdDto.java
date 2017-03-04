package com.sombre.shop.models.pojo.dto.productsDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 21.02.17.
 */
@Data
@AllArgsConstructor
public class ProductByIdDto {
    private UUID uniqueid;
    private String name;
    private String photo;
    private String description;
    private int price;
    private boolean instock;
    private DateTime dateadded;
    private UUID id_subcategory;
    private String subName;
    private UUID id_category;
    private String catName;
    private UUID id_admin;
    private String firstname;
    private String lastname;
}
