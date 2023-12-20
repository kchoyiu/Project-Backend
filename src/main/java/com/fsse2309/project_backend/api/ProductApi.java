package com.fsse2309.project_backend.api;

import com.fsse2309.project_backend.data.product.domainObject.CreateProduct;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import com.fsse2309.project_backend.dto.ListAllProductResponseDto;
import com.fsse2309.project_backend.dto.ProductRequestDto;
import com.fsse2309.project_backend.dto.ProductResponseDto;
import com.fsse2309.project_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin("http://localhost:5173")
public class ProductApi {

    private ProductService productService;

    @Autowired
    public ProductApi (ProductService productService){
        this.productService=productService;
    }

    @PostMapping()
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto){
        CreateProduct createProduct = new CreateProduct(productRequestDto);
        Product product=productService.createdProduct(createProduct);
        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return productResponseDto;
    }
    @GetMapping()
    public List<ListAllProductResponseDto> getAllProduct(){
        List<Product> productList =  productService.getAllProducts();
        List<ListAllProductResponseDto> dtoList = new ArrayList<>();
        for (Product product: productList){
            ListAllProductResponseDto listAllProductResponseDto= new ListAllProductResponseDto(product);
            dtoList.add(listAllProductResponseDto);
        }
        return dtoList;
    }

    @GetMapping("{id}")
    public ProductEntity getProductByPid(@PathVariable (name = "id")int pid){
        ProductEntity product = productService.getByPid(pid);
        return product;
    }
}
