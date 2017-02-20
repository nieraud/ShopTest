package com.sombre.shop.controllers.adminsCtrl;

import com.google.gson.Gson;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.adminDto.input.AddAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.input.UpdateAdminDto;
import com.sombre.shop.models.pojo.dto.adminDto.output.GetAdminDto;
import com.sombre.shop.models.pojo.dto.userDto.input.UserAuthorizationDto;
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
    private static Gson gson = new Gson();

    @Getter
    private static final Route authorizationAdmin = (request, response) -> {
        UserAuthorizationDto admin = gson.fromJson(request.body(), UserAuthorizationDto.class);
        ObjectConverterValidator.nullChecker(admin);

        Users userFromDB = UsersCtrl.getUserDaoService().getUserByUserEmail(admin.getUseremail());
        if (userFromDB == null) return "Exception: User with this email is not registered!";

        Admins adminFromDB = adminDaoService.getAdminByUserId(userFromDB.getUniqueid());
        if (adminFromDB == null) return "Exception: You are not admin!";

        if (UserSecurity.checkPassword(admin.getPassword(), userFromDB.getHashpassword())) {

            String accessToken = UsersCtrl.getUserDaoService().getAccessTokenByUserId(userFromDB.getUniqueid());

            if (accessToken == null) {
                accessToken = UserSecurity.generateAccessToken(userFromDB);
                UsersCtrl.getUserDaoService().authorization(accessToken, userFromDB.getUniqueid());
            }
            response.header("AccessToken", accessToken);
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("admin");
            return gson.toJson(response.body());
        } else {
            return "Exception: Not correct password!";
        }
    };

    @Getter
    private static final Route addAdmin = (request, response) -> {
        AddAdminDto newAdmin = gson.fromJson(request.body(), AddAdminDto.class);
        ObjectConverterValidator.nullChecker(newAdmin);

        if (adminDaoService.addAdmin(newAdmin)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route updateAdmin = (request, response) -> {
        UpdateAdminDto admin = gson.fromJson(request.body(), UpdateAdminDto.class);
        ObjectConverterValidator.nullChecker(admin);

        if (adminDaoService.updateAdmin(admin)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route deleteAdmin = (request, response) -> {
        UniqueIdDto admin = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(admin);

        if (adminDaoService.deleteAdmin(admin.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route admin = (request, response) -> {
        UniqueIdDto admin = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(admin);

        GetAdminDto adminFromDb = adminDaoService.getAdminById(admin.getUniqueid());

        if (adminFromDb != null) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(adminFromDb);
        } else throw new NullPointerException();
    };

    @Getter
    private static final Route allAdmins = (request, response) -> {

        List<GetAdminDto> adminFromDb = adminDaoService.getAllAdmins();

        if (!adminFromDb.isEmpty()) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(adminFromDb);
        } else throw new NullPointerException();
    };

}
