package com.sombre.shop.models.services;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import org.sql2o.Sql2o;

/**
 * Created by inna on 11.02.17.
 */
public class UserAbstractDaoService {
    protected AbstractDaoFactory daoFactory;

    public UserAbstractDaoService(AbstractDaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
}
