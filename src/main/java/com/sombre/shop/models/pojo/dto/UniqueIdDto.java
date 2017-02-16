package com.sombre.shop.models.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 15.02.17.
 */
@Data
@AllArgsConstructor
public class UniqueIdDto {
    private UUID uniqueid;
}
