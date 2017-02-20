package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 19.02.17.
 */
@Data
@AllArgsConstructor
public class Orders {
    private UUID uniqueid;
    private UUID id_allbaskets;
    private UUID id_admin;
    private boolean confirmation;
    private DateTime date;
}
