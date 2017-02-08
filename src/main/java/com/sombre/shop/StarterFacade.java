package com.sombre.shop;

import com.sombre.shop.utils.routing.Router;
import com.sombre.shop.utils.routing.Routing;

/**
 * Created by inna on 08.02.17.
 */
public class StarterFacade {

    public static void start() {

        initRoutingService();


    }

    private static void initRoutingService() {

        Routing router = new Router();
        router.init();
    }

    private static void initExceptionService() {

        Routing router = new Router();
        router.init();

    }
}
