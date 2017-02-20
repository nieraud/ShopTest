package com.sombre.shop.models.pojo.dto.blacklistDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
@Data
@AllArgsConstructor
public class AddBlacklistDto {
    private UUID userid;
    private String notes;
}
