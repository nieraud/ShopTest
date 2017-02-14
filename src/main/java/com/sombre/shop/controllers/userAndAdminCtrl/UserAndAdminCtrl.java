package com.sombre.shop.controllers.userAndAdminCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.user.input.UserAuthorization;
import com.sombre.shop.models.pojo.dto.user.input.UserRegistration;
import com.sombre.shop.models.pojo.dto.user.input.returnPassAndSalt.HashPasswordAndSalt;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.utils.security.UserSecurity;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.text.SimpleDateFormat;

/**
 * Created by inna on 11.02.17.
 */
public class UserAndAdminCtrl {

    private static final UserRepository userDaoService = DaoServiceFactory.getUserService();
    private static Gson gson = new Gson();


    // User's part

    @Getter
    private static final Route registrationUser = (request, response) -> {

        UserRegistration user = gson.fromJson(request.body(), UserRegistration.class);
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

        UserAuthorization user = gson.fromJson(request.body(), UserAuthorization.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDB = userDaoService.getUserByUserEmail(user.getUseremail());
        ObjectConverterValidator.nullChecker(userFromDB);

        if (UserSecurity.checkPassword(user.getPassword(), userFromDB.getHashpassword())) {

            String accessToken = userDaoService.getAccessTokenByUserId(userFromDB.getUniqueId());

            if (accessToken == null) {

                accessToken = UserSecurity.generateAccessToken(userFromDB);
                userDaoService.userAuthorization(accessToken, userFromDB.getUniqueId());
            }

            response.header("AccessToken", accessToken);
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return response;
        } else throw new Exception();
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


    // Admin's part

    @Getter
    private static final Route addAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route authorizationAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route updateAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route deleteAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route admin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allAdmins = (request, response) -> {
        return "";
    };
}