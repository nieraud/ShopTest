package com.sombre.shop.models.pojo.dto.productsDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
@Data
@AllArgsConstructor
public class UpdateProductDto {
    private UUID uniqueid;
    private UUID id_subcategory;
    private String name;
    private String photo;
    private String description;
    private int price;
    private boolean instock;
}
