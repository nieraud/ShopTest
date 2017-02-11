package com.sombre.shop.models.services.adminService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.repositories.adminRepository.AdminRepository;
import com.sombre.shop.models.services.UserAbstractDaoService;

/**
 * Created by inna on 11.02.17.
 */
public class AdminDaoService extends UserAbstractDaoService implements AdminRepository {
    public AdminDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }
}
