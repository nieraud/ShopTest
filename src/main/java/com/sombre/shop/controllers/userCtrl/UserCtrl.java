package com.sombre.shop.controllers.userCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.userDaoService.UserDaoService;
import lombok.Getter;
import spark.Route;

/**
 * Created by inna on 11.02.17.
 */
public class UserCtrl {

    @Getter
    static final UserRepository userDaoService = DaoServiceFactory.getUserService();
    static Gson gson = new Gson();

    @Getter
    private static final Route registrationUser = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route updateUser = (request, response) -> {
      return "";
    };

    @Getter
    private static final Route deleteUser = (request, response) -> {
      return "";
    };

    @Getter
    private static final Route userById = (request, response) -> {
      return "";
    };

}