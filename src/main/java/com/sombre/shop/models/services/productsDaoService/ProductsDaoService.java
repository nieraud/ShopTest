package com.sombre.shop.models.services.productsDaoService;

import com.sombre.shop.models.factory.AbstractDaoFactory;
import com.sombre.shop.models.pojo.dto.productsDto.input.AddProductDto;
import com.sombre.shop.models.pojo.dto.productsDto.input.UpdateProductDto;
import com.sombre.shop.models.pojo.dto.productsDto.output.ProductByIdDto;
import com.sombre.shop.models.pojo.dto.productsDto.output.ProductsByCategoryDto;
import com.sombre.shop.models.pojo.dto.productsDto.output.ProductsBySubcategoryDto;
import com.sombre.shop.models.pojo.entity.Products;
import com.sombre.shop.models.repositories.productsRepository.ProductsRepository;
import com.sombre.shop.models.services.AbstractDaoService;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public class ProductsDaoService extends AbstractDaoService implements ProductsRepository {
    public ProductsDaoService(AbstractDaoFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public boolean addProduct(AddProductDto product, UUID adminId) {
        String sql = "INSERT INTO products VALUES (DEFAULT, :name, :photo, :description, " +
                " :price, :instock, DEFAULT, :subcategoryId, :adminId);";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", product.getName())
                    .addParameter("photo", product.getPhoto())
                    .addParameter("description", product.getDescription())
                    .addParameter("price", product.getPrice())
                    .addParameter("instock", product.isInstock())
                    .addParameter("subcategoryId", product.getId_subcategory())
                    .addParameter("adminId", adminId)
                    .executeUpdate();
            return true;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean updateProduct(UpdateProductDto product) {

        String sql = "UPDATE products SET (name, photo, description, price, instock, " +
                " id_subcategory) = (:name, :photo, :description, :price,  :instock, " +
                " :subcategoryId) WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("name", product.getName())
                    .addParameter("photo", product.getPhoto())
                    .addParameter("description", product.getDescription())
                    .addParameter("price", product.getPrice())
                    .addParameter("instock", product.isInstock())
                    .addParameter("subcategoryId", product.getId_subcategory())
                    .addParameter("id", product.getUniqueid())
                    .executeUpdate();
            return true;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public boolean deleteProduct(UUID productId) {

        String sql = "DELETE FROM products WHERE uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            connection.createQuery(sql, false)
                    .addParameter("id", productId)
                    .executeUpdate();
            return true;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public ProductByIdDto getProductById(UUID productId) {

        String sql = "SELECT products.uniqueid, " +
                "products.name, " +
                "products.photo, " +
                "products.description, " +
                "products.price, " +
                "products.instock, " +
                "products.dateadded, " +
                "products.id_subcategory, " +
                "subcategories.name AS subName, " +
                "subcategories.id_category, " +
                "categories.name AS catName, " +
                "products.id_admin, " +
                "users.firstname, " +
                "users.lastname " +
                "FROM products " +
                "INNER JOIN subcategories ON products.id_subcategory = subcategories.uniqueid " +
                "INNER JOIN admins ON products.id_admin = admins.uniqueid " +
                "INNER JOIN categories ON subcategories.id_category = categories.uniqueid " +
                "INNER JOIN users ON admins.id_user = users.uniqueid " +
                "WHERE products.uniqueid = :id;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            ProductByIdDto product = connection.createQuery(sql, false)
                    .addParameter("id", productId)
                    .executeAndFetchFirst(ProductByIdDto.class);

            if (product != null) return product;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<ProductsBySubcategoryDto> gelAllProductsBySubcategoryId(UUID subcategoryId) {

        String sql = "SELECT products.uniqueid, " +
                " products.name, " +
                " products.photo, " +
                " products.description, " +
                " products.price, " +
                " products.instock, " +
                " products.dateadded, " +
                " subcategories.uniqueid, " +
                " subcategories.name, " +
                " subcategories.description, " +
                " subcategories.id_category " +
                " FROM products " +
                " INNER JOIN subcategories  " +
                " ON products.id_subcategory = subcategories.uniqueid" +
                " WHERE subcategories.uniqueid = :id; ";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<ProductsBySubcategoryDto> products = connection.createQuery(sql, false)
                    .addParameter("id", subcategoryId)
                    .addColumnMapping("subcategories.uniqueid", "subcategoryId")
                    .addColumnMapping("subcategories.name", "subcategoryName")
                    .addColumnMapping("subcategories.description", "subcategoryDescription")
                    .executeAndFetch(ProductsBySubcategoryDto.class);

            if (!products.isEmpty()) return products;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<ProductsByCategoryDto> gelAllProductsByCategoryId(UUID categoryId) {

        String sql = "SELECT products.uniqueid, " +
                " products.name, " +
                " products.photo, " +
                " products.description, " +
                " products.price, " +
                " products.instock, " +
                " products.dateadded, " +
                " subcategories.uniqueid, " +
                " subcategories.name, " +
                " subcategories.description, " +
                " categories.uniqueid, " +
                " categories.name, " +
                " categories.description " +
                " FROM products " +
                " INNER JOIN subcategories  " +
                " ON products.id_subcategory = subcategories.uniqueid" +
                " INNER JOIN categories " +
                " ON subcategories.id_category = categories.uniqueid " +
                " WHERE categories.uniqueid = :id; ";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<ProductsByCategoryDto> products = connection.createQuery(sql, false)
                    .addParameter("id", categoryId)
                    .addColumnMapping("subcategories.uniqueid", "subcategoryId")
                    .addColumnMapping("subcategories.name", "subcategoryName")
                    .addColumnMapping("subcategories.description", "subcategoryDescription")
                    .addColumnMapping("subcategories.uniqueid", "categoryId")
                    .addColumnMapping("subcategories.name", "categoryName")
                    .addColumnMapping("subcategories.description", "categoryDescription")
                    .executeAndFetch(ProductsByCategoryDto.class);

            if (!products.isEmpty()) return products;
            else return null;

        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }

    @Override
    public List<Products> gelAllProducts() {

        String sql = "SELECT * FROM products;";

        try (Connection connection = daoFactory.getDataSource().open()) {

            List<Products> products = connection.createQuery(sql, false)
                    .executeAndFetch(Products.class);

            if (!products.isEmpty()) return products;
            else return null;
        } catch (Sql2oException e) {
            throw new Sql2oException(e);
        }
    }
}
