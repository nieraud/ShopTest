package com.sombre.shop.controllers.userCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.services.userDaoService.UserDaoService;
import lombok.Getter;

/**
 * Created by inna on 11.02.17.
 */
public class UserCtrl {

    @Getter
    static UserDaoService userDaoService = AbstractDaoFactory.getInstace().getUserService();
    static Gson gson = new Gson();


}
