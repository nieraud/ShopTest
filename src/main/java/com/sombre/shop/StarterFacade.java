package com.sombre.shop;

import com.sombre.shop.utils.exceptions.ExceptionService;
import com.sombre.shop.utils.exceptions.ExceptionRouting;
import com.sombre.shop.utils.routing.Router;
import com.sombre.shop.utils.routing.Routing;

/**
 * Created by inna on 08.02.17.
 */
class StarterFacade {

    static void start() {
        initExceptionService();
        initRoutingService();
    }

    private static void initRoutingService() {
        Routing router = new Router();
        router.init();
    }

    private static void initExceptionService() {
        ExceptionRouting r = new ExceptionService();
        r.init();
    }
}
