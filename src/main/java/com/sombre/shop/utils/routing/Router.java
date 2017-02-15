package com.sombre.shop.utils.routing;

import com.sombre.shop.controllers.filters.BeforeFilter;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;

import static spark.Spark.*;

/**
 * Created by inna on 08.02.17.
 */
public class Router implements Routing {
    public void init() {

        post("/admins/auth", AdminsCtrl.getAuthorizationAdmin());

        post("/reg", UsersCtrl.getRegistrationUser());
        post("/auth", UsersCtrl.getAuthorization());

        before("/sec/*", BeforeFilter.getCheckAuthorization());
        path("/sec", () -> {
            before("/admin/*", BeforeFilter.getOwnerChecker());
            path("/admin", () -> {
                post("/add", AdminsCtrl.getAddAdmin());
                post("/upd", AdminsCtrl.getUpdateAdmin());
                post("/get", AdminsCtrl.getAdmin());
                get("/all", AdminsCtrl.getAllAdmins());
                post("/del", AdminsCtrl.getDeleteAdmin());
            });

            before("/user/*", BeforeFilter.getCheckRealUser());
            path("/user", () -> {
                post("/upd", UsersCtrl.getUpdateUser());
                post("/get", UsersCtrl.getUserById());
                post("/del", UsersCtrl.getDeleteUser());
            });
        });
    }


}
