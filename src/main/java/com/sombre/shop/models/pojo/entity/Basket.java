package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
@Data
@AllArgsConstructor
public class Basket {
    private UUID uniqueId;
    private UUID id_user;
    private UUID id_product;
    private int count;
    private boolean confirmation;
}
