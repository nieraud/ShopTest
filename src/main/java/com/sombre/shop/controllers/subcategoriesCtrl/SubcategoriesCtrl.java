package com.sombre.shop.controllers.subcategoriesCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import lombok.Getter;
import spark.Route;

/**
 * Created by inna on 16.02.17.
 */
public class SubcategoriesCtrl {

    @Getter
    private static final SubcategoriesRepository subcategoriesDaoService = DaoServiceFactory.getSubcategoriesService();
    Gson gson = new Gson();

    @Getter
    private static final Route addSubcategory = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route updateCSubcategory = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route deleteSubcategory = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route subcategoryById = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allSubcategories = (request, response) -> {
        return "";
    };

}
