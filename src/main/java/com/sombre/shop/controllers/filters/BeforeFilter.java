package com.sombre.shop.controllers.filters;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.pojo.dto.UniqueId;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import spark.Filter;

import static spark.Spark.halt;

/**
 * Created by inna on 15.02.17.
 */
public class BeforeFilter {

    static Gson json = new Gson();

    @Getter
    private static final Filter checkAuthorization = (request, response) -> {

        Users userFromDB = UsersCtrl.getUserDaoService().getUserByAccessToken(request.headers("Authorization"));

        if (userFromDB == null) {
            halt(401, "Exception: You are unauthorized user!");
        }
    };

    @Getter
    private static final Filter ownerChecker = (request, response) -> {

        Users owner = UsersCtrl.getUserDaoService().getUserByAccessToken(request.headers("Authorization"));
        ObjectConverterValidator.nullChecker(owner);

        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(owner.getUniqueId());
        if (admin == null || admin.getDegree() > 1) {
            halt(401, "Exception: You are not owner!");
        }
    };

    @Getter
    private static final Filter checkRealUser = (request, response) -> {

        UniqueId user = json.fromJson(request.body(), UniqueId.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDb = UsersCtrl.getUserDaoService().getUserByAccessToken(request.headers("Authorization"));
        ObjectConverterValidator.nullChecker(userFromDb);

        if(user.getUniqueid() != userFromDb.getUniqueId()){
            halt(401, "Exception: You can't use someone else's account!");
        }

    };


}
