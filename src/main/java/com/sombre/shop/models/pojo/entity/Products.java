package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
@Data
@AllArgsConstructor
public class Products {
    private UUID uniqueId;
    private UUID id_subcategory;
    private String name;
    private String photo;
    private String description;
    private int price;
    private boolean instock;
    private DateTime dateadded;

}
