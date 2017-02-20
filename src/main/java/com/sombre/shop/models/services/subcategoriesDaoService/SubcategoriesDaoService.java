package com.sombre.shop.models.services.subcategoriesDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.input.AddSubcategoryDto;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.input.UpdSubcategoryDto;
import com.sombre.shop.models.pojo.dto.subcategoriesDto.output.SubcategoriesByIdDto;
import com.sombre.shop.models.pojo.entity.SubCategories;
import com.sombre.shop.models.repositories.subcategoriesRepository.SubcategoriesRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public class SubcategoriesDaoService extends AbstractDaoService implements SubcategoriesRepository {
    public SubcategoriesDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public boolean addSubcategory(AddSubcategoryDto subCategory, UUID adminId) {

        String sql = "INSERT INTO subcategories " +
                "VALUES(DEFAULT, :name, :description, :categoryId, :adminId, " +
                "DEFAULT );";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", subCategory.getName())
                    .addParameter("description", subCategory.getDescription())
                    .addParameter("categoryId", subCategory.getCategoryid())
                    .addParameter("adminId", adminId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateSubcategory(UpdSubcategoryDto subCategory) {

        String sql = "UPDATE subcategories SET (name, description) " +
                "= (:name, :description) WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", subCategory.getName())
                    .addParameter("description", subCategory.getDescription())
                    .addParameter("id", subCategory.getSubcategoryid())
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteSubcategory(UUID subCategoryId) {

        String sql = "DELETE FROM subcategories WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("id", subCategoryId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public SubcategoriesByIdDto getSubcategoryById(UUID subCategoryId) {

        String sql = "SELECT subcategories.uniqueid, " +
                "subcategories.name, " +
                "subcategories.description, " +
                "subcategories.dateadded, " +
                "subcategories.id_category, " +
                "categories.name AS catname, " +
                "admins.uniqueid AS adminid, " +
                "users.firstname, " +
                "users.lastname " +
                "FROM subcategories " +
                "INNER JOIN categories ON subcategories.id_category = categories.uniqueid " +
                "INNER JOIN admins ON subcategories.id_adminadded = admins.uniqueid " +
                "INNER JOIN users ON admins.id_user = users.uniqueid " +
                "WHERE subcategories.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            SubcategoriesByIdDto subcategory = connection.createQuery(sql, false)
                    .addParameter("id", subCategoryId)
                    .executeAndFetchFirst(SubcategoriesByIdDto.class);

            if (subcategory != null) return subcategory;
            else return null;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<SubcategoriesByIdDto> getAllSubcategoriesByCategoryId(UUID categoryId) {

        String sql = "SELECT subcategories.uniqueid, " +
                "subcategories.name, " +
                "subcategories.description, " +
                "subcategories.dateadded, " +
                "subcategories.id_category, " +
                "categories.name AS catName, " +
                "admins.uniqueid AS subAdminId, " +
                "users.firstname, " +
                "users.lastname " +
                "FROM subcategories " +
                "INNER JOIN categories ON subcategories.id_category = categories.uniqueid " +
                "INNER JOIN admins ON subcategories.id_adminadded = admins.uniqueid " +
                "INNER JOIN users ON admins.id_user = users.uniqueid " +
                "WHERE categories.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<SubcategoriesByIdDto> subCategories = connection.createQuery(sql, false)
                    .addParameter("id", categoryId)
                    .executeAndFetch(SubcategoriesByIdDto.class);

            if (!subCategories.isEmpty()) return subCategories;
            else return null;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }


}
