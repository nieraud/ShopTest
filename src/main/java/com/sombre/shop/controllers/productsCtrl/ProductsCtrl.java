package com.sombre.shop.controllers.productsCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import lombok.Getter;
import spark.Route;

/**
 * Created by inna on 16.02.17.
 */
public class ProductsCtrl {

    @Getter
    private static final ProductsRepository productsDaoService = DaoServiceFactory.getProductsDaoService();
    Gson gson = new Gson();

    @Getter
    private static final Route addProduct = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route updateProduct = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route deleteProduct = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route productById = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allProducts = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allProductsBySubcategory = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allProductsByCategory = (request, response) -> {
        return "";
    };
}
