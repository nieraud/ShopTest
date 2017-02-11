package com.sombre.shop.models.factory;

import com.sombre.shop.models.repositories.userRepository.UserRepository;
import org.sql2o.Sql2o;

/**
 * Created by inna on 11.02.17.
 */
public class DaoServiceFactory extends AbstractDaoFactory {
    public Sql2o getDataSource() {
        return AbstractDaoFactory.getInstace().getDataSource();
    }

    public static UserRepository getUserService(){
        return DAO_SERVICE.USER_REPOSITORY;
    }
}
