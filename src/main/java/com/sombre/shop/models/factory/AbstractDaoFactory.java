package com.sombre.shop.models.factory;

import com.sombre.shop.models.repositories.adminRepository.AdminRepository;
import com.sombre.shop.models.repositories.blacklistRepository.BlacklistRepository;
import com.sombre.shop.models.repositories.categoriesRepository.CategoriesRepository;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.models.services.adminDaoService.AdminDaoService;
import com.sombre.shop.models.services.blacklistDaoService.BlacklistDaoService;
import com.sombre.shop.models.services.categoriesDaoService.CategoriesDaoService;
import com.sombre.shop.models.services.productsDaoService.ProductsDaoService;
import com.sombre.shop.models.services.subcategoriesDaoService.SubcategoriesDaoService;
import com.sombre.shop.models.services.userDaoService.UserDaoService;

/**
 * Created by inna on 08.02.17.
 */
public abstract class AbstractDaoFactory implements DataSource {

    AbstractDaoFactory() {
    }

    static class DAO_SERVICE {

        static final UserRepository USER_REPOSITORY
                = new UserDaoService(getInstace());

        static final AdminRepository ADMIN_REPOSITORY
                = new AdminDaoService(getInstace());

        static final CategoriesRepository CATEGORIES_REPOSITORY
                = new CategoriesDaoService(getInstace());

        static final SubcategoriesRepository SUBCATEGORIES_REPOSITORY
                = new SubcategoriesDaoService(getInstace());

        static final ProductsRepository PRODUCTS_REPOSITORY
                = new ProductsDaoService(getInstace());

        static final BlacklistRepository BLACKLIST_REPOSITORY
                = new BlacklistDaoService(getInstace());


    }

    public static AbstractDaoFactory getInstace() {
        return PgDataSource.getInstance();
    }

}
