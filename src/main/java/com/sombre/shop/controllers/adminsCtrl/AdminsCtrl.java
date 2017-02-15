package com.sombre.shop.controllers.adminsCtrl;

import com.google.gson.Gson;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueId;
import com.sombre.shop.models.pojo.dto.admin.input.AddAdmin;
import com.sombre.shop.models.pojo.dto.admin.input.UpdateAdmin;
import com.sombre.shop.models.pojo.dto.admin.output.GetAdmin;
import com.sombre.shop.models.pojo.dto.user.input.UserAuthorization;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.adminRepository.AdminRepository;
import com.sombre.shop.utils.security.UserSecurity;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.List;

/**
 * Created by inna on 15.02.17.
 */
public class AdminsCtrl {

    @Getter
    private static final AdminRepository adminDaoService = DaoServiceFactory.getAdminService();
    private static Gson json = new Gson();

    @Getter
    private static final Route authorizationAdmin = (request, response) -> {
        UserAuthorization admin = json.fromJson(request.body(), UserAuthorization.class);
        ObjectConverterValidator.nullChecker(admin);

        Users userFromDB = UsersCtrl.getUserDaoService().getUserByUserEmail(admin.getUseremail());
        if (userFromDB == null) return "Exception: User with this email is not registered!";

        Admins adminFromDB = adminDaoService.getAdminByUserId(userFromDB.getUniqueId());
        if (adminFromDB == null) return "Exception: You are not admin!";

        if (UserSecurity.checkPassword(admin.getPassword(), userFromDB.getHashpassword())) {

            String accessToken = UsersCtrl.getUserDaoService().getAccessTokenByUserId(userFromDB.getUniqueId());

            if (accessToken == null) {
                accessToken = UserSecurity.generateAccessToken(userFromDB);
                UsersCtrl.getUserDaoService().authorization(accessToken, userFromDB.getUniqueId());
            }
            response.header("AccessToken", accessToken);
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("admin");
            return json.toJson(response.body());
        } else {
            return "Exception: Not correct password!";
        }
    };

    @Getter
    private static final Route addAdmin = (request, response) -> {
        AddAdmin newAdmin = json.fromJson(request.body(), AddAdmin.class);
        ObjectConverterValidator.nullChecker(newAdmin);

        if (adminDaoService.addAdmin(newAdmin)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return json.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route updateAdmin = (request, response) -> {
        UpdateAdmin admin = json.fromJson(request.body(), UpdateAdmin.class);
        ObjectConverterValidator.nullChecker(admin);

        if (adminDaoService.updateAdmin(admin)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return json.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route deleteAdmin = (request, response) -> {
        UniqueId admin = json.fromJson(request.body(), UniqueId.class);
        ObjectConverterValidator.nullChecker(admin);

        if (adminDaoService.deleteAdmin(admin.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return json.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route admin = (request, response) -> {
        UniqueId admin = json.fromJson(request.body(), UniqueId.class);
        ObjectConverterValidator.nullChecker(admin);

        GetAdmin adminFromDb = adminDaoService.getAdminById(admin.getUniqueid());

        if (adminFromDb != null) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return json.toJson(adminFromDb);
        } else throw new NullPointerException();
    };

    @Getter
    private static final Route allAdmins = (request, response) -> {

        List<GetAdmin> adminFromDb = adminDaoService.getAllAdmins();

        if (!adminFromDb.isEmpty()) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return json.toJson(adminFromDb);
        } else throw new NullPointerException();
    };

}
