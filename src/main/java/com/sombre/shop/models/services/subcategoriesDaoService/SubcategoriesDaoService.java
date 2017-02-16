package com.sombre.shop.models.services.subcategoriesDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import com.sombre.shop.models.services.AbstractDaoService;

/**
 * Created by inna on 16.02.17.
 */
public class SubcategoriesDaoService extends AbstractDaoService implements SubcategoriesRepository {
    public SubcategoriesDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }
}
