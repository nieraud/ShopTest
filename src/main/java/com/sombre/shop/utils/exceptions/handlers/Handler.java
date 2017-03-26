package com.sombre.shop.utils.exceptions.handlers;

import com.sombre.shop.utils.exceptions.exceptions.NotAdminException;
import com.sombre.shop.utils.exceptions.exceptions.UnauthorizedException;
import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2oException;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Route;

import java.text.ParseException;

/**
 * Created by inna on 14.02.17.
 */
public class Handler {

    @Getter
    private static final Route notFound = (request, response) -> {
        response.type("application/json");
        response.status(404);
        System.out.println("{\"message\":\"Custom 404\"}");
        return "{\"message\":\"Custom 404\"}";
    };

    @Getter
    private static final Route internalServerError = (request, response) -> {
        response.type("application/json");
        response.status(500);
        System.out.println("{\"message\":\"Custom 500 handling\"}");
        return "{\"message\":\"Custom 500 handling\"}";
    };


    @Getter
    private static final ExceptionHandler sql2oException = ((e, request, response) -> {
        response.body(e.getLocalizedMessage());
        response.type("application/json");
        response.status(500);
    });

    @Getter
    private static final ExceptionHandler parseException = ((e, request, response) -> {
        response.body(e.getLocalizedMessage());
        response.type("application/json");
        response.status(500);
    });

    @Getter
    private static final ExceptionHandler unauthorizedException = ((e, request, response) -> {
        response.body(e.getLocalizedMessage());
        response.type("application/json");
        response.status(401);
    });

    @Getter
    private static final ExceptionHandler notAdminException = ((e, request, response) -> {
        response.body(e.getLocalizedMessage());
        response.type("application/json");
        response.status(60);
    });

    @Getter
    private static final ExceptionHandler exception = (e, request, response) -> {
        response.body(e.getLocalizedMessage());
        response.type("application/json");
        response.status(500);
    };


}
