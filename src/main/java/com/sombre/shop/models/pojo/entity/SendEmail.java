package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
@Data
@AllArgsConstructor
public class SendEmail {
    private UUID uniqueid;
    private UUID id_user;
    private String message;
    private boolean status;
}
