package com.sombre.shop.models.repositories.userRepository;

import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;

/**
 * Created by inna on 11.02.17.
 */
public interface UserRepository {
    boolean registration(UserForAddingToDB user);

}
