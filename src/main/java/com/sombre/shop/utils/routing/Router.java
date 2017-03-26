package com.sombre.shop.utils.routing;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.blacklistCtrl.BlacklistCtrl;
import com.sombre.shop.controllers.categoriesCtrl.CategoriesCtrl;
import com.sombre.shop.controllers.filters.BeforeFilter;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.productsCtrl.ProductsCtrl;
import com.sombre.shop.controllers.subcategoriesCtrl.SubcategoriesCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Products;


import static spark.Spark.*;
import static spark.Spark.get;

/**
 * Created by inna on 08.02.17.
 */
public class Router implements Routing {
    public void init() {

        // Configure Spark
        port(4567);
        staticFiles.location("/public");

        //index
        get("/", (req, res) -> {
            res.redirect("html/index.html");
            return null;
        });

        post("/reg", UsersCtrl.getRegistrationUser());

        post("/auth/adm", AdminsCtrl.getAuthorizationAdmin());
        post("/auth", UsersCtrl.getAuthorization());

        get("/logout", UsersCtrl.getLogOut());

        //get users by token
        //  get("/a/token",AdminsCtrl.getAdminByToken());
        // get("/token", UsersCtrl.getUserByToken());

        before("/sec/*", BeforeFilter.getCheckAuthorizationAndBlacklist());
        path("/sec", () -> {

            before("/owner/*", BeforeFilter.getOwnerChecker());
            path("/owner", () -> {
                post("/add", AdminsCtrl.getAddAdmin());
                post("/upd", AdminsCtrl.getUpdateAdmin());
                delete("/del", AdminsCtrl.getDeleteAdmin());
            });//owner

            before("/admin/*", BeforeFilter.getAdminChecker());
            path("/admin", () -> {

                get("/:id", AdminsCtrl.getAdmin());
                get("/all", AdminsCtrl.getAllAdmins());

                path("/user", () -> {
                    get("/:id", UsersCtrl.getUserById());
                    get("/all", UsersCtrl.getAllUsers());
                });
                path("/blacklist", () -> {
                    post("/add", BlacklistCtrl.getAddUserToBlackList());
                    post("/upd", BlacklistCtrl.getUpdateBlacklist());
                    delete("/:id", BlacklistCtrl.getDeleteBlacklist());
                    get("/:id", BlacklistCtrl.getBlacklistById());
                    get("/:userid", BlacklistCtrl.getBlacklistByUserId());
                    get("/", BlacklistCtrl.getAllBlacklist());
                });


                path("/category", () -> {
                    post("/add", CategoriesCtrl.getAddCategory());
                    post("/upd", CategoriesCtrl.getUpdateCategory());
                    delete("/del", CategoriesCtrl.getDeleteCategory());
                });

                path("/subcategory", () -> {
                    post("/add", SubcategoriesCtrl.getAddSubcategory());
                    post("/upd", SubcategoriesCtrl.getUpdateSubcategory());
                    delete("/del", SubcategoriesCtrl.getDeleteSubcategory());
                });

                path("/product", () -> {
                    post("/add", ProductsCtrl.getAddProduct());
                    post("/upd", ProductsCtrl.getUpdateProduct());
                    delete("/del", ProductsCtrl.getDeleteProduct());
                });

            }); //admin

            path("/category", () -> {
                post("/id", CategoriesCtrl.getCategoryById());
                get("/all", CategoriesCtrl.getAllCategories());
            });

            path("/subcategory", () -> {
                post("/id", SubcategoriesCtrl.getSubcategoryById());
                post("/category", SubcategoriesCtrl.getAllSubcategoriesByCategory());
            });

            path("/product", () -> {
                post("/id", ProductsCtrl.getProductById());
                post("/subcategory", ProductsCtrl.getAllProductsBySubcategory());
                post("/category", ProductsCtrl.getAllProductsByCategory());
                get("/all", ProductsCtrl.getAllProducts());
                //get("/:search", Products.getProductsBySearch());
            });

//above
            before("/own/*", BeforeFilter.getCheckRealUser());
            path("/own", () -> {
                post("/upd", UsersCtrl.getUpdateUser());
                delete("/del", UsersCtrl.getDeleteUser());
                post("/id", UsersCtrl.getUserById());

                path("/basket", () -> {

                });
            });

        });//security
    }
}
