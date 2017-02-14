package com.sombre.shop;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


import java.io.IOException;
import java.text.ParseException;

/**
 * Created by inna on 08.02.17.
 */
public class Main {

    public static void main(String[] args) throws IOException, ParseException {

         StarterFacade.start();



    }
}