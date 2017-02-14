package com.sombre.shop.models.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by inna on 11.02.17.
 */
@Data
@AllArgsConstructor
public class Users {
    private UUID uniqueId;
    private String firstname;
    private String lastname;
    private Date birthday;
    private int phonenumber;
    private DateTime dateReg;
    private String useremail;
    private String hashpassword;
    private String encryptionsalt;
    private String accesstoken;

}
