package com.sombre.shop.models.pojo.dto.user.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;

/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class UserRegistration {
    private String firstname;
    private String lastname;
    private String birthday;
    private int phonenumber;
    private String useremail;
    private String password;
}
