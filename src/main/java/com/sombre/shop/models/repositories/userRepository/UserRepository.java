package com.sombre.shop.models.repositories.userRepository;

import com.sombre.shop.models.pojo.dto.user.input.UserUpdate;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.pojo.entity.Users;

import java.text.ParseException;
import java.util.UUID;


/**
 * Created by inna on 11.02.17.
 */
public interface UserRepository {

    boolean registration(UserForAddingToDB user) throws ParseException;
    boolean authorization(String accessToken, UUID user);

    Users getUserByUserEmail(String userEmail);
    Users getUserByAccessToken(String token);
    Users getUserById(UUID userId);

    String getAccessTokenByUserId(UUID userId);

    boolean deleteUser(UUID userId);
    boolean updateUser(UserUpdate user);


}
