package com.sombre.shop.controllers.subcategoriesCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.input.AddSubcategoryDto;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.output.SubcategoriesByIdDto;
import com.sombre.shop.models.pojo.entity.Categories;
import com.sombre.shop.models.pojo.entity.SubCategories;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import spark.Route;

import java.util.List;

/**
 * Created by inna on 16.02.17.
 */
public class SubcategoriesCtrl {

    @Getter
    private static final SubcategoriesRepository subcategoriesDaoService = DaoServiceFactory.getSubcategoriesService();
    private static Gson gson = new Gson();

    @Getter
    private static final Route addSubcategory = (request, response) -> {
        AddSubcategoryDto subcategory = gson.fromJson(request.body(), AddSubcategoryDto.class);
        ObjectConverterValidator.nullChecker(subcategory);

        if (subcategoriesDaoService.addSubcategory(subcategory)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route updateCSubcategory = (request, response) -> {
        SubCategories subCategory = gson.fromJson(request.body(), SubCategories.class);
        ObjectConverterValidator.nullChecker(subCategory);

        if (subcategoriesDaoService.updateSubcategory(subCategory)) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route deleteSubcategory = (request, response) -> {
        UniqueIdDto subcategoryId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(subcategoryId);

        if (subcategoriesDaoService.deleteSubcategory(subcategoryId.getUniqueid())) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(response.body());
        } else throw new Exception();
    };

    @Getter
    private static final Route subcategoryById = (request, response) -> {
        UniqueIdDto subcategoryId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(subcategoryId);

        SubcategoriesByIdDto subcategory = subcategoriesDaoService.getSubcategoryById(subcategoryId.getUniqueid());

        if (subcategory != null) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(subcategory);
        } else
            throw new NullPointerException();
    };

    @Getter
    private static final Route allSubcategoriesByCategory = (request, response) -> {
        UniqueIdDto categoryId = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(categoryId);

        List<SubcategoriesByIdDto> subcategories =
                subcategoriesDaoService.getAllSubcategoriesByCategoryId(categoryId.getUniqueid());

        if (!subcategories.isEmpty()) {
            response.status(HttpStatus.OK_200);
            response.type("application/json");
            response.body("successfully");
            return gson.toJson(subcategories);
        } else throw new NullPointerException();
    };

}
