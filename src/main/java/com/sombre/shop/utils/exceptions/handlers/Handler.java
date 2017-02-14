package com.sombre.shop.utils.exceptions.handlers;

import lombok.Getter;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2oException;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

/**
 * Created by inna on 14.02.17.
 */
public class Handler implements ExceptionHandler {

    @Override
    public void handle(Exception e, Request request, Response response) {

    }

    @Getter
    private static final ExceptionHandler sql2oException = ((e, request, response) -> {

        Sql2oException exception = (Sql2oException) e;
        response.body(exception.getMessage());
        response.body("application/json");

        System.out.println("Sql2oException");

    });
}
