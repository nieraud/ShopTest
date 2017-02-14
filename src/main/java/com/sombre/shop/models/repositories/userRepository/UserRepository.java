package com.sombre.shop.models.repositories.userRepository;

import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by inna on 11.02.17.
 */
public interface UserRepository {
    boolean registration(UserForAddingToDB user) throws ParseException;


}
