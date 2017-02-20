package com.sombre.shop.models.services.categoriesDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.categoriesDto.input.AddCategoryDto;
import com.sombre.shop.models.pojo.dto.categoriesDto.output.GetCategoryDto;
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
    public boolean addCategory(AddCategoryDto category, UUID adminId) {

        String sql = "INSERT INTO categories VALUES (DEFAULT, :name, :description, :adminId, DEFAULT);";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("adminId", adminId)
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
                    .addParameter("id", category.getUniqueid())
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
    public GetCategoryDto getCategoryById(UUID categoryId) {

        String sql = "SELECT categories.uniqueid, " +
                "categories.name, " +
                "categories.description, " +
                "categories.dateadded, " +
                "categories.id_adminadded, " +
                "users.firstname, " +
                "users.lastname " +
                "FROM categories " +
                "INNER JOIN admins ON categories.id_adminadded = admins.uniqueid " +
                "INNER JOIN users ON admins.id_user = users.uniqueid " +
                "WHERE categories.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            GetCategoryDto category = connection.createQuery(sql, false)
                    .addParameter("id", categoryId)
                    .executeAndFetchFirst(GetCategoryDto.class);

            if (category != null) return category;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<GetCategoryDto> getAllCategories() {

        String sql = "SELECT categories.uniqueid, " +
                "categories.name, " +
                "categories.description, " +
                "categories.dateadded, " +
                "categories.id_adminadded, " +
                "users.firstname, " +
                "users.lastname " +
                "FROM categories " +
                "INNER JOIN admins ON categories.id_adminadded = admins.uniqueid " +
                "INNER JOIN users ON admins.id_user = users.uniqueid";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<GetCategoryDto> categories = connection.createQuery(sql, false)
                    .executeAndFetch(GetCategoryDto.class);

            if (!categories.isEmpty()) return categories;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }
}
