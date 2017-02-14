package com.sombre.shop.utils.routing;

import com.sombre.shop.controllers.userAndAdminCtrl.UserAndAdminCtrl;
import com.sombre.shop.utils.path.Path;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by inna on 08.02.17.
 */
public class Router implements Routing {
    public void init() {

        // User's part
        post(Path.REGISTRATION, UserAndAdminCtrl.getRegistrationUser());
        post(Path.AUTHORIZATION, UserAndAdminCtrl.getAuthorization());
        post(Path.UPDATE_USER, UserAndAdminCtrl.getUpdateUser());
        get(Path.GET_USER, UserAndAdminCtrl.getUserById());
        post(Path.DELETE_USER, UserAndAdminCtrl.getDeleteUser());

        // Admin's part
        post(Path.ADD_ADMIN, UserAndAdminCtrl.getAddAdmin());
        post(Path.AUTHORIZATION_ADMIN, UserAndAdminCtrl.getAuthorizationAdmin());
        post(Path.UPDATE_ADMIN, UserAndAdminCtrl.getUpdateAdmin());
        get(Path.GET_ADMIN, UserAndAdminCtrl.getAdmin());
        get(Path.GET_ALL_ADMINS, UserAndAdminCtrl.getAllAdmins());
        post(Path.DELETE_ADMIN, UserAndAdminCtrl.getDeleteAdmin());
    }


}
