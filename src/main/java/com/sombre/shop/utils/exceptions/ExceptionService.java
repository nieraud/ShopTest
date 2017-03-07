package com.sombre.shop.utils.exceptions;

import com.sombre.shop.utils.exceptions.handlers.Handler;
import org.sql2o.Sql2oException;

import java.text.ParseException;

import static spark.Spark.exception;
import static spark.Spark.internalServerError;
import static spark.Spark.notFound;

/**
 * Created by inna on 08.02.17.
 */
public class ExceptionService implements ExceptionRouting {
    public void init() {

        internalServerError(Handler.getInternalServerError());

        notFound(Handler.getNotFound());


        exception(Sql2oException.class, Handler.getSql2oException());

        exception(ParseException.class, Handler.getParseException());


    }
}
