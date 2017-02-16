package com.sombre.shop.models.services.categoriesDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.entity.Categories;
import com.sombre.shop.models.repositories.categoriesRepository.CategoriesRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public class CategoriesDaoService extends AbstractDaoService implements CategoriesRepository {
    public CategoriesDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }


    @Override
    public boolean addCategory(AddCategoryDto category) {

        String sql = "INSERT INTO categories VALUES (DEFAULT, :name, :description);";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", category.getName())
                    .addParameter("description", category.getDescription())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateCategory(Categories category) {

        String sql = "UPDATE categories SET (name, description) " +
                " = (:name, :description) WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", category.getName())
                    .addParameter("description", category.getDescription())
                    .addParameter("id", category.getUniqueId())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            e.printStackTrace();
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {

        String sql = "DELETE FROM categories WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("id", categoryId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public Categories getCategoryById(UUID categoryId) {

        String sql = "SELECT * FROM categories WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            Categories category = connection.createQuery(sql, false)
                    .addParameter("id", categoryId)
                    .executeAndFetchFirst(Categories.class);

            if (category != null) return category;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<Categories> getAllCategories() {

        String sql = "SELECT * FROM categories;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<Categories> categories = connection.createQuery(sql, false)
                    .executeAndFetch(Categories.class);

            if (!categories.isEmpty()) return categories;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }
}
