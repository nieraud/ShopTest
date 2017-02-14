package com.sombre.shop.models.pojo.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class NewAdmin {
    private UUID userId;
    private int degree;
    private String roledescription;
}
