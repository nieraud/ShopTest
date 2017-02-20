package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 19.02.17.
 */
@Data
@AllArgsConstructor
public class AllBaskets {
    private UUID uniqueid;
    private UUID id_user;
    private UUID id_basket;
}
