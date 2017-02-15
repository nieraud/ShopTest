package com.sombre.shop.models.pojo.dto.user.input;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by inna on 15.02.17.
 */
@Data
@AllArgsConstructor
public class UserUpdate {
    private UUID uniqueid;
    private String firstname;
    private String lastname;
    private String birthday;
    private long phonenumber;

}
