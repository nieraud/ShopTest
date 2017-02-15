package com.sombre.shop.controllers.usersCtrl;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueId;
import com.sombre.shop.models.pojo.dto.user.input.UserAuthorization;
import com.sombre.shop.models.pojo.dto.user.input.UserRegistration;
import com.sombre.shop.models.pojo.dto.user.input.UserUpdate;
import com.sombre.shop.models.pojo.dto.user.input.returnPassAndSalt.HashPasswordAndSalt;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.utils.exceptions.exceptions.UnauthorizedException;
import com.sombre.shop.utils.security.UserSecurity;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.text.SimpleDateFormat;

import static spark.Spark.halt;

/**
 * Created by inna on 11.02.17.
 */
public class UsersCtrl {

    @Getter
    private static final UserRepository userDaoService = DaoServiceFactory.getUserService();
    private static Gson json = new Gson();


    @Getter
    private static final Route registrationUser = (request, response) -> {

        UserRegistration user = json.fromJson(request.body(), UserRegistration.class);
        ObjectConverterValidator.nullChecker(user);

        HashPasswordAndSalt security = UserSecurity.generateHashPassword(user.getPassword());

        UserForAddingToDB endingUser = new UserForAddingToDB(user.getFirstname(),
                user.getLastname(),
                new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthday()),
                user.getPhonenumber(),
                user.getUseremail(),
                security.getPassword().getBytes(),
                security.getSalt().getBytes());

        if (userDaoService.registration(endingUser)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return response;
        } else throw new Exception();
    };

    @Getter
    private static final Route authorization = (request, response) -> {

        UserAuthorization user = json.fromJson(request.body(), UserAuthorization.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDB = userDaoService.getUserByUserEmail(user.getUseremail());
        if (userFromDB == null) return "Exception: User with this email is not registered!";

        if (UserSecurity.checkPassword(user.getPassword(), userFromDB.getHashpassword())) {

            String accessToken = userDaoService.getAccessTokenByUserId(userFromDB.getUniqueId());

            if (accessToken == null) {
                accessToken = UserSecurity.generateAccessToken(userFromDB);
                userDaoService.authorization(accessToken, userFromDB.getUniqueId());
            }
            response.header("AccessToken", accessToken);
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("user");
            return json.toJson(response.body());
        } else {
            return "Exception: Not correct password!";
        }
    };


    @Getter
    private static final Route updateUser = (request, response) -> {
        UserUpdate user = json.fromJson(request.body(), UserUpdate.class);
        ObjectConverterValidator.nullChecker(user);

        if (userDaoService.updateUser(user)) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("user updated");
            return json.toJson(response.body());
        } else throw new Exception();


    };

    @Getter
    private static final Route deleteUser = (request, response) -> {
        UniqueId user = json.fromJson(request.body(), UniqueId.class);
        ObjectConverterValidator.nullChecker(user);

        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(user.getUniqueid());
        if (admin != null)
            AdminsCtrl.getAdminDaoService().deleteAdmin(admin.getUniqueId());

        if (userDaoService.deleteUser(user.getUniqueid())) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("user deleted");
            return json.toJson(response.body());

        } else throw new Exception();

    };

    @Getter
    private static final Route userById = (request, response) -> {
        UniqueId user = json.fromJson(request.body(), UniqueId.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDb = userDaoService.getUserById(user.getUniqueid());
        if (userFromDb != null) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return json.toJson(userFromDb);

        } else throw new NullPointerException();

    };

}