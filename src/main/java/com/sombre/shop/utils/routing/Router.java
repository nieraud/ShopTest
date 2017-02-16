package com.sombre.shop.utils.routing;

import com.sombre.shop.controllers.categoriesCtrl.CategoriesCtrl;
import com.sombre.shop.controllers.filters.BeforeFilter;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.productsCtrl.ProductsCtrl;
import com.sombre.shop.controllers.subcategoriesCtrl.SubcategoriesCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import lombok.NonNull;

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

            before("/owner/*", BeforeFilter.getOwnerChecker());
            path("/owner", () -> {
                post("/add", AdminsCtrl.getAddAdmin());
                post("/upd", AdminsCtrl.getUpdateAdmin());
                post("/get", AdminsCtrl.getAdmin());
                get("/all", AdminsCtrl.getAllAdmins());
                delete("/del", AdminsCtrl.getDeleteAdmin());
            });

            before("/user/*", BeforeFilter.getCheckRealUser());
            path("/user", () -> {
                post("/upd", UsersCtrl.getUpdateUser());
                delete("/del", UsersCtrl.getDeleteUser());
            });

            before("/admin/*", BeforeFilter.getAdminChecker());
            path("/admin", () -> {
                path("/category", () -> {
                    post("/add", CategoriesCtrl.getAddCategory());
                    post("/upd", CategoriesCtrl.getUpdateCategory());
                    delete("/del", CategoriesCtrl.getDeleteCategory());
                });
                path("/subcategory", () -> {
                    post("/add", SubcategoriesCtrl.getAddSubcategory());
                    post("/upd", SubcategoriesCtrl.getUpdateCSubcategory());
                    delete("/del", SubcategoriesCtrl.getDeleteSubcategory());

                });


               /* post("/add", ProductsCtrl.getAddSubcategory());
                post("/upd", ProductsCtrl.getUpdateCSubcategory());
                delete("/del", ProductsCtrl.getDeleteSubcategory());
                post("/get", ProductsCtrl.getSubcategoryById());
                get("/all",ProductsCtrl.getAllSubcategories());
                */
            });

            post("/get", UsersCtrl.getUserById()); // what about admins ?

            post("/get", CategoriesCtrl.getCategoryById());
            get("/all", CategoriesCtrl.getAllCategories());

            post("/get", SubcategoriesCtrl.getSubcategoryById());
            get("/all", SubcategoriesCtrl.getAllSubcategories());

        });
    }


}
