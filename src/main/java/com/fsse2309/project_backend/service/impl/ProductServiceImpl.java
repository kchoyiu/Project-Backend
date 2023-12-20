package com.fsse2309.project_backend.service.impl;

import com.fsse2309.project_backend.data.product.domainObject.CreateProduct;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import com.fsse2309.project_backend.exception.NoStockException;
import com.fsse2309.project_backend.exception.ProductExistedException;
import com.fsse2309.project_backend.exception.ProductNotFoundException;
import com.fsse2309.project_backend.repository.ProductRepository;
import com.fsse2309.project_backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl (ProductRepository productRepository){
        this.productRepository=productRepository;
    }

@Override
    public Product createdProduct(CreateProduct createProduct){
        try{
            if (isProductExist(createProduct.getPid())){
                throw new ProductExistedException();
            }return new Product(
                    productRepository.save(new ProductEntity(createProduct)));
        }catch (ProductExistedException ex){
            logger.warn("Create Product: pid existed. Pid: "+createProduct.getPid());
            throw ex;
        }
    }
@Override
    public List<Product> getAllProducts(){
        List<Product> productList= new ArrayList<>();
        for (ProductEntity entity: productRepository.findAll()){
            Product product = new Product(entity);
            productList.add(product);
        }
        return productList;
    }


@Override
    public ProductEntity getByPid(int pid){
        Optional<ProductEntity>optionalProductEntity = productRepository.findByPid(pid);
        if (optionalProductEntity.isPresent()){
            return optionalProductEntity.get();
        }else {
            logger.warn("Product Not Found. Pid: "+pid);
            throw new ProductNotFoundException();

        }
    }

    public boolean isProductExist(int pid){
        return productRepository.existsByPid(pid);
    }

    @Override
    public boolean isValidStock(Integer pid, Integer quantity){
        ProductEntity productEntity = getByPid(pid);
        return quantity>0 && productEntity.getStock()>=quantity;
    }
    @Override
    public boolean deductStock(int pid, int quantity){
        ProductEntity productEntity = getByPid(pid);
        if (productEntity.getStock() - quantity<0){
            throw new NoStockException();
        }
        productEntity.setStock(productEntity.getStock() - quantity);
        productRepository.save(productEntity);
        return true;
    }
}
