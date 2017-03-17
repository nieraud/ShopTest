package com.sombre.shop.models.services;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import org.sql2o.Sql2o;

/**
 * Created by inna on 11.02.17.
 */
public class    AbstractDaoService {
    protected AbstractDaoFactory daoFactory;

    public AbstractDaoService(AbstractDaoFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
}
