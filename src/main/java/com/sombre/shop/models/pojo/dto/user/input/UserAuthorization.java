package com.sombre.shop.models.pojo.dto.user.input;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class UserAuthorization {
    private String useremail;
    private String password;
}
