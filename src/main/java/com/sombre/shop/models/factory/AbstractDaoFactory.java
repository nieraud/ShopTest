package com.sombre.shop.models.factory;

import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.userDaoService.UserDaoService;

/**
 * Created by inna on 08.02.17.
 */
public abstract class AbstractDaoFactory implements DataSource {

    AbstractDaoFactory() {
    }

    static class DAO_SERVICE {

        static final UserRepository USER_REPOSITORY = new UserDaoService(getInstace());








    }

    public static AbstractDaoFactory getInstace() {
        return PgDataSource.getInstance();
    }

}
