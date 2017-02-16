package com.sombre.shop.models.pojo.dto.userDto.input;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class UserRegistrationDto {
    private String firstname;
    private String lastname;
    private String birthday;
    private long phonenumber;
    private String useremail;
    private String password;
}
