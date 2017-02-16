package com.sombre.shop.models.pojo.dto.userDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class UserForAddingToDBDto {
    private String firstname;
    private String lastname;
    private Date birthday;
    private long phonenumber;
    private String useremail;
    private byte[] hashpassword;
    private byte[] encryptionsalt;
}
