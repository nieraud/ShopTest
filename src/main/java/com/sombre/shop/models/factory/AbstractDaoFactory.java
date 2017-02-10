package com.sombre.shop.models.factory;

/**
 * Created by inna on 08.02.17.
 */
public abstract class AbstractDaoFactory implements DataSource {

    //private UserService userService = null;

    public static AbstractDaoFactory getInstace(){
        return PgDataSource.getInstance();
    }

   /* public UserService getUserService(){
        if (userService == null) {
            synchronized ((PgDataSource.class)) {
                if (userService == null) {
                    userService = new UserService(this);
                }
            }
        }
        return userService;
    }*/
}
