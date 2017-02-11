package com.sombre.shop.models.services.userDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.factory.DataSource;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.UserAbstractDaoService;

/**
 * Created by inna on 11.02.17.
 */
public class UserDaoService extends UserAbstractDaoService implements UserRepository{

    public UserDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }


}
