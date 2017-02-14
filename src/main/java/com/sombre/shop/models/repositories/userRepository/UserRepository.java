package com.sombre.shop.models.repositories.userRepository;

import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.pojo.entity.Users;

import java.text.ParseException;
import java.util.UUID;


/**
 * Created by inna on 11.02.17.
 */
public interface UserRepository {
    boolean registration(UserForAddingToDB user) throws ParseException;

    Users getUserByUserEmail(String userEmail);

    boolean userAuthorization(String accessToken, UUID user);

    Users getUserByAccessToken(String token);

    String getAccessTokenByUserId(UUID userId);



}
