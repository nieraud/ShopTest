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
public class Categories {
    private UUID uniqueid;
    private UUID id_adminadded;
    private String name;
    private String description;
    private DateTime dateadded;
}
