package com.sombre.shop.models.pojo.dto.admin.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 15.02.17.
 */
@Data
@AllArgsConstructor
public class AddAdmin {
    private UUID userId;
    private int degree;
    private String roledescription;
}
