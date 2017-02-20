package com.sombre.shop.controllers.productsCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.dto.productsDto.input.AddProductDto;
import com.sombre.shop.models.pojo.dto.productsDto.input.NumberOfProductsOnPage;
import com.sombre.shop.models.pojo.dto.productsDto.input.UpdateProductDto;
import com.sombre.shop.models.pojo.entity.Products;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inna on 16.02.17.
 */
public class ProductsCtrl {

    @Getter
    private static final ProductsRepository productsDaoService = DaoServiceFactory.getProductsDaoService();
    private static Gson gson = new Gson();

    @Getter
    private static final Route addProduct = (request, response) -> {
        AddProductDto product = gson.fromJson(request.body(), AddProductDto.class);
        ObjectConverterValidator.nullChecker(product);

        if (productsDaoService.addProduct(product)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route updateProduct = (request, response) -> {
        UpdateProductDto product = gson.fromJson(request.body(), UpdateProductDto.class);
        ObjectConverterValidator.nullChecker(product);

        if (productsDaoService.updateProduct(product)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route deleteProduct = (request, response) -> {
        UniqueIdDto productId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(productId);

        if (productsDaoService.deleteProduct(productId.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route productById = (request, response) -> {
        UniqueIdDto productId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(productId);

        Products product = productsDaoService.getProductById(productId.getUniqueid());
        if (product != null) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(product);
        } else throw new NullPointerException();
    };

    @Getter
    private static final Route allProducts = (request, response) -> {
        /*NumberOfProductsOnPage num = gson.fromJson(request.body(), NumberOfProductsOnPage.class);
        ObjectConverterValidator.nullChecker(num);

        List<Products> products = productsDaoService.getAllProducts(num.getNumberOfProducts());

        if (!products.isEmpty()) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(products);
        } else throw new NullPointerException();*/
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
