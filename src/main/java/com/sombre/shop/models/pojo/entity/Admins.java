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
public class Admins {
    private UUID uniqueId;
    private UUID id_user;
    private int degree;
    private String roledescription;
    private DateTime dateadded;
}
