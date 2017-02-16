package com.sombre.shop.models.services.productsDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import com.sombre.shop.models.services.AbstractDaoService;

/**
 * Created by inna on 16.02.17.
 */
public class ProductsDaoService extends AbstractDaoService implements ProductsRepository {
    public ProductsDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }
}
