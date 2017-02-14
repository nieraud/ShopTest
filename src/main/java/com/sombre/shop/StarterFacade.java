package com.sombre.shop;

import com.sombre.shop.utils.exceptions.ExceptionService;
import com.sombre.shop.utils.exceptions.ExcetionRouting;
import com.sombre.shop.utils.routing.Router;
import com.sombre.shop.utils.routing.Routing;

/**
 * Created by inna on 08.02.17.
 */
public class StarterFacade {

    public static void start() {

        initExceptionService();
        initRoutingService();


    }

    private static void initRoutingService() {

        Routing router = new Router();
        router.init();
    }

    private static void initExceptionService() {

        ExcetionRouting r = new ExceptionService();
        r.init();

    }
}
