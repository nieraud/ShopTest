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
    private UUID uniqueid;
    private String firstname;
    private String lastname;
    private Date birthday;
    private long phonenumber;
    private DateTime datereg;
    private String useremail;
    private byte[] hashpassword;
    private byte[] encryptionsalt;
    private byte[] accesstoken;
}
