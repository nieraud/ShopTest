package com.sombre.shop.models.pojo.dto.admin.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Date;
import java.util.UUID;

/**
 * Created by inna on 15.02.17.
 */
@Data
@AllArgsConstructor
public class GetAdmin {
    private UUID uniqueid;
    private UUID id_user;
    private int degree;
    private String roledescription;

    private String firstname;
    private String lastname;
    private Date birthday;
    private long phonenumber;
    private DateTime datereg;
    private String useremail;
}
