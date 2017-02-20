package com.sombre.shop.models.factory;

import com.sombre.shop.models.repositories.adminRepository.AdminRepository;
import com.sombre.shop.models.repositories.blacklistRepository.BlacklistRepository;
import com.sombre.shop.models.repositories.categoriesRepository.CategoriesRepository;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import org.sql2o.Sql2o;

/**
 * Created by inna on 11.02.17.
 */
public class DaoServiceFactory extends AbstractDaoFactory {
    public Sql2o getDataSource() {
        return AbstractDaoFactory.getInstace().getDataSource();
    }

    public static UserRepository getUserService() {
        return DAO_SERVICE.USER_REPOSITORY;
    }

    public static AdminRepository getAdminService() {
        return DAO_SERVICE.ADMIN_REPOSITORY;
    }

    public static CategoriesRepository getCategoriesService() {
        return DAO_SERVICE.CATEGORIES_REPOSITORY;
    }

    public static SubcategoriesRepository getSubcategoriesService() {
        return DAO_SERVICE.SUBCATEGORIES_REPOSITORY;
    }

    public static ProductsRepository getProductsDaoService() {
        return DAO_SERVICE.PRODUCTS_REPOSITORY;
    }

    public static BlacklistRepository getBlacklistDaoService() {
        return DAO_SERVICE.BLACKLIST_REPOSITORY;
    }

}
