package com.sombre.shop.models.pojo.dto.userDto.input.returnPassAndSalt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by inna on 14.02.17.
 */
@Data
@AllArgsConstructor
public class HashPasswordAndSaltDto {

    private String password;
    private String salt;
}
