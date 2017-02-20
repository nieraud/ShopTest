package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
@Data
@AllArgsConstructor
public class Blacklist {
    private UUID uniqueid;
    private UUID id_user;
    private UUID id_adminadded;
    private String notes;
    private DateTime dateadded;
}
