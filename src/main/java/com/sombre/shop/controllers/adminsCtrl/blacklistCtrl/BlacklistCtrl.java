package com.sombre.shop.controllers.adminsCtrl.blacklistCtrl;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.input.AddBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.input.UpdateBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.GetBlacklistDto;
import com.sombre.shop.models.pojo.dto.blacklistDto.output.IntermediateBlacklistDto;
import com.sombre.shop.models.pojo.dto.productsDto.input.UpdateProductDto;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Blacklist;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.repositories.blacklistRepository.BlacklistRepository;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.List;

/**
 * Created by inna on 20.02.17.
 */
public class BlacklistCtrl {

    @Getter
    private static final BlacklistRepository blacklistDaoService = DaoServiceFactory.getBlacklistDaoService();
    private static Gson gson = new Gson();

    @Getter
    private static Route addUserToBlackList = (request, response) -> {
        AddBlacklistDto list = gson.fromJson(request.body(), AddBlacklistDto.class);
        ObjectConverterValidator.nullChecker(list);

        Users user = UsersCtrl.getUserDaoService().getUserByAccessToken(request.headers("Authorization"));
        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(user.getUniqueid());

        if (blacklistDaoService.addUserToBlacklist(list, admin.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());

        } else throw new Exception();
    };


    @Getter
    private static Route updateBlacklist = (request, response) -> {
        UpdateBlacklistDto list = gson.fromJson(request.body(), UpdateBlacklistDto.class);
        ObjectConverterValidator.nullChecker(list);

        if (blacklistDaoService.updateBlackList(list)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };


    @Getter
    private static Route deleteBlacklist = (request, response) -> {
        UniqueIdDto userId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(userId);

        if (blacklistDaoService.deleteBlacklist(userId.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };


    @Getter
    private static Route blacklistById = (request, response) -> {
        UniqueIdDto listId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(listId);

        GetBlacklistDto blacklist = blacklistDaoService.getBlacklistById(listId.getUniqueid());
        ObjectConverterValidator.nullChecker(blacklist);

        response.status(HttpStatus.OK_200);
        response.type("application/json");
        return gson.toJson(blacklist);
    };

/*
    @Getter
    private static Route INFBlacklistByUserId = (request, response) -> {
        UniqueIdDto userId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(userId);

        GetBlacklistDto blacklist = blacklistDaoService.getINFBlacklistByUserId(userId.getUniqueid());
        ObjectConverterValidator.nullChecker(blacklist);

        response.status(HttpStatus.OK_200);
        response.type("application/json");
        return gson.toJson(blacklist);
    };*/

    @Getter
    private static Route allBlacklist = (request, response) -> {

        List<IntermediateBlacklistDto> blacklist = blacklistDaoService.allBlacklist();

        if (!blacklist.isEmpty()) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            return gson.toJson(blacklist);
        } else throw new NullPointerException("Blacklist is empty!");

    };

}
