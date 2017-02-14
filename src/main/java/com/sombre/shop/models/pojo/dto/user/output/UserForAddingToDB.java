package com.sombre.shop.models.pojo.dto.user.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;


/**
 * Created by inna on 12.02.17.
 */
@Data
@AllArgsConstructor
public class UserForAddingToDB {
    private String firstname;
    private String lastname;
    private Date birthday;
    private long phonenumber;
    private String useremail;
    private byte[] hashpassword;
    private byte[] encryptionsalt;
}
