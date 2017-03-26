package com.sombre.shop.controllers.usersCtrl;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.userDto.input.UserAuthorizationDto;
import com.sombre.shop.models.pojo.dto.userDto.input.UserRegistrationDto;
import com.sombre.shop.models.pojo.dto.userDto.input.UserUpdateDto;
import com.sombre.shop.models.pojo.dto.userDto.input.returnPassAndSalt.HashPasswordAndSaltDto;
import com.sombre.shop.models.pojo.dto.userDto.output.UserForAddingToDBDto;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.utils.exceptions.exceptions.UnauthorizedException;
import com.sombre.shop.utils.security.UserSecurity;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.Route;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.halt;

/**
 * Created by inna on 11.02.17.
 */
public class UsersCtrl {

    @Getter
    private static final UserRepository userDaoService = DaoServiceFactory.getUserService();
    private static Gson gson = new Gson();

    @Getter
    private static final Route registrationUser = (request, response) -> {

        UserRegistrationDto user = gson.fromJson(request.body(), UserRegistrationDto.class);
        ObjectConverterValidator.nullChecker(user);

        HashPasswordAndSaltDto security = UserSecurity.generateHashPassword(user.getPassword());

        UserForAddingToDBDto endingUser = new UserForAddingToDBDto(user.getFirstname(),
                user.getLastname(),
                new SimpleDateFormat("yyyy-MM-dd").parse(user.getBirthday()),
                user.getPhonenumber(),
                user.getUseremail(),
                security.getPassword().getBytes(),
                security.getSalt().getBytes());

        if (userDaoService.registration(endingUser)) {

            response.status(HttpStatus.OK_200);
            return response;
        } else throw new InternalError();
    };

    @Getter
    private static final Route authorization = (request, response) -> {

        UserAuthorizationDto user = gson.fromJson(request.body(), UserAuthorizationDto.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDB = userDaoService.getUserByUserEmail(user.getUseremail());
        if (userFromDB == null) return new UnauthorizedException();

        if (UserSecurity.checkPassword(user.getPassword(), userFromDB.getHashpassword())) {

            String accessToken = UsersCtrl.getUserDaoService().getAccessTokenByUserId(userFromDB.getUniqueid());

            if (accessToken == null) {
                accessToken = UserSecurity.generateAccessToken(userFromDB);
                UsersCtrl.getUserDaoService().authorization(accessToken, userFromDB.getUniqueid());
            }

            request.session(true);
            request.session().attribute("AccessToken", accessToken);
            response.header(HttpHeader.AUTHORIZATION.asString(), accessToken);
            response.header("UniqueId", userFromDB.getUniqueid().toString());
            response.status(HttpStatus.OK_200);
            return response;
        } else return new UnauthorizedException();
    };

    @Getter
    private static final Route updateUser = (request, response) -> {
        UserUpdateDto user = gson.fromJson(request.body(), UserUpdateDto.class);
        ObjectConverterValidator.nullChecker(user);

        if (userDaoService.updateUser(user)) {

            response.status(HttpStatus.OK_200);
            return response;
        } else throw new Exception();

    };

    @Getter
    private static Route logOut = (request, response) -> {

        request.session().attribute("AccessToken", null);

        response.header(HttpHeader.AUTHORIZATION.asString(), null);
        response.status(HttpStatus.OK_200);
        return response;
    };

    @Getter
    private static final Route deleteUser = (request, response) -> {
        UniqueIdDto user = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(user);

        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(user.getUniqueid());
        if (admin != null)
            AdminsCtrl.getAdminDaoService().deleteAdmin(admin.getUniqueid());

        if (userDaoService.deleteUser(user.getUniqueid())) {

            response.status(HttpStatus.OK_200);
            return response;

        } else throw new Exception();

    };

    @Getter
    private static final Route userById = (request, response) -> {
        UUID userId = UUID.fromString(request.params("id"));
        ObjectConverterValidator.nullChecker(userId);

        Users userFromDb = userDaoService.getUserById(userId);
        if (userFromDb != null) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return gson.toJson(userFromDb);

        } else throw new UnauthorizedException();

    };

    @Getter
    private static final Route allUsers = (request, response) -> {
        List<Users> users = getUserDaoService().getAllUsers();

        if (!users.isEmpty()) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return gson.toJson(users);
        } else throw new NullPointerException();
    };

    /*@Getter
    private static final Route userByToken = (request, response) -> {

        if (request.headers(HttpHeader.AUTHORIZATION.asString()) == null) throw new UnauthorizedException();

        Users user = userDaoService.getUserByAccessToken(request.headers(HttpHeader.AUTHORIZATION.asString()));
        if (user != null) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body(gson.toJson(user));
            System.out.println(gson.toJson(user));
            return response;
        } else return null;
    };*/


}