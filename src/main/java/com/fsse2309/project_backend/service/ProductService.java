package com.fsse2309.project_backend.service;

import com.fsse2309.project_backend.data.product.domainObject.CreateProduct;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    Product createdProduct (CreateProduct createProduct);

    List<Product> getAllProducts();

    ProductEntity getByPid(int pid);

    boolean isValidStock(Integer pid, Integer quantity);

    boolean deductStock(int pid, int quantity);
}
