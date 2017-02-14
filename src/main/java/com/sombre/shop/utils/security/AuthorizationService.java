package com.sombre.shop.utils.security;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.models.services.UserAbstractDaoService;
import org.eclipse.jetty.server.Authentication;
import org.sql2o.Sql2oException;

/**
 * Created by inna on 12.02.17.
 */
public class AuthorizationService extends UserAbstractDaoService {
    public AuthorizationService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }
/*
    public static Users checkUser(String accessToken){

        try {
            return datasource.findUserByAccessToken(accessToken);
        }
        catch(Sql2oException e){
            throw new Sql2oException(e);
        }
    }*/
}
