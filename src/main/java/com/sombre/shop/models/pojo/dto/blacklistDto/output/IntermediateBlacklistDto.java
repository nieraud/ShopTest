package com.sombre.shop.models.pojo.dto.blacklistDto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by inna on 20.02.17.
 */
@Data
@AllArgsConstructor
public class IntermediateBlacklistDto {
    private UUID uniqueid;
    private String notes;
    private DateTime dateadded;
    private UUID id_user;
    private String firstname;
    private String lastname;
    private UUID id_adminadded;
}

