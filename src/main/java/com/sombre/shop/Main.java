package com.sombre.shop;


import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

/**
 * Created by inna on 08.02.17.
 */
public class Main {

    public static void main(String[] args) {

        //staticFiles.location("/public");

        StarterFacade.start();


    }
}