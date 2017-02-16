package com.sombre.shop.controllers.categoriesCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.entity.Categories;
import com.sombre.shop.models.repositories.categoriesRepository.CategoriesRepository;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.List;

/**
 * Created by inna on 16.02.17.
 */
public class CategoriesCtrl {

    @Getter
    private static final CategoriesRepository categoriesDaoService = DaoServiceFactory.getCategoriesService();
    private static Gson gson = new Gson();


    @Getter
    private static final Route addCategory = (request, response) -> {
        AddCategoryDto category = gson.fromJson(request.body(), AddCategoryDto.class);
        ObjectConverterValidator.nullChecker(category);

        if (categoriesDaoService.addCategory(category)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route updateCategory = (request, response) -> {
        Categories category = gson.fromJson(request.body(), Categories.class);
        ObjectConverterValidator.nullChecker(category);

        if (categoriesDaoService.updateCategory(category)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route deleteCategory = (request, response) -> {
        UniqueIdDto categoryId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(categoryId);

        if (categoriesDaoService.deleteCategory(categoryId.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route categoryById = (request, response) -> {
        UniqueIdDto categoryId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(categoryId);

        Categories category = categoriesDaoService.getCategoryById(categoryId.getUniqueid());

        if (category != null) {

            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");

            return gson.toJson(category);
        } else throw new NullPointerException();
    };

    @Getter
    private static final Route allCategories = (request, response) -> {
        List<Categories> categoriesList = categoriesDaoService.getAllCategories();

        if (!categoriesList.isEmpty()) return gson.toJson(categoriesList);
        else throw new NullPointerException();
    };

}
