package com.sombre.shop.utils.validator;

/**
 * Created by inna on 12.02.17.
 */
public class ObjectConverterValidator {

    public static void nullChecker(Object object) throws Exception {
        if (object == null) throw new NullPointerException();
    }
}
