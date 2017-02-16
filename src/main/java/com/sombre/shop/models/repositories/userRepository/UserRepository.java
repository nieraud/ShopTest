package com.sombre.shop.models.repositories.userRepository;

import com.sombre.shop.models.pojo.dto.userDto.input.UserUpdateDto;
import com.sombre.shop.models.pojo.dto.userDto.output.UserForAddingToDBDto;
import com.sombre.shop.models.pojo.entity.Users;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;


/**
 * Created by inna on 11.02.17.
 */
public interface UserRepository {

    boolean registration(UserForAddingToDBDto user) throws ParseException;

    boolean authorization(String accessToken, UUID user);

    Users getUserByUserEmail(String userEmail);

    Users getUserByAccessToken(String token);

    Users getUserById(UUID userId);

    String getAccessTokenByUserId(UUID userId);

    boolean deleteUser(UUID userId);

    boolean updateUser(UserUpdateDto user);

    List<Users> getAllUsers();


}
