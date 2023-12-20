package com.fsse2309.project_backend.data.product.entity;

import com.fsse2309.project_backend.data.product.domainObject.CreateProduct;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid",nullable = false)
    private int pid;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "description")
    private  String description;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "price",nullable = false)
    private BigDecimal price;
    @Column(name = "stock",nullable = false)
    private int stock;


    public ProductEntity(){
    }

    public ProductEntity(CreateProduct createProduct) {
        this.pid = createProduct.getPid();
        this.name = createProduct.getName();
        this.price = createProduct.getPrice();
        this.imageUrl = createProduct.getImageUrl();
        this.stock = createProduct.getStock();
        this.description = createProduct.getDescription();
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
