package com.sombre.shop.models.repositories.productsRepository;

import com.sombre.shop.models.pojo.dto.productsDto.input.AddProductDto;
import com.sombre.shop.models.pojo.dto.productsDto.input.UpdateProductDto;
import com.sombre.shop.models.pojo.dto.productsDto.output.ProductsByCategoryDto;
import com.sombre.shop.models.pojo.dto.productsDto.output.ProductsBySubcategoryDto;
import com.sombre.shop.models.pojo.entity.Products;

import java.util.List;
import java.util.UUID;

/**
 * Created by inna on 16.02.17.
 */
public interface ProductsRepository {

    boolean addProduct(AddProductDto product);

    boolean updateProduct (UpdateProductDto product);

    boolean deleteProduct(UUID productId);

    Products getProductById(UUID productId);

    List<ProductsBySubcategoryDto> gelAllProductsBySubcategoryId(UUID subcategoryId);

    List<ProductsByCategoryDto> gelAllProductsByCategoryId(UUID categoryId);

    List<Products> gelAllProducts();

}
