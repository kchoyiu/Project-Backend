package com.fsse2309.project_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.data.transactionProduct.domainObject.TransactionProductDetailData;

import java.math.BigDecimal;

public class ProductResponseDto {
    private int pid;
    private String name;
    private String description;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    private int stock;

    public ProductResponseDto(Product product) {
        this.pid = product.getPid();
        this.name = product.getName();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public ProductResponseDto(TransactionProductDetailData data) {
        this.pid=data.getPid();
        this.name=data.getName();
        this.description=data.getDescription();
        this.imageUrl=data.getImageUrl();
        this.price=data.getPrice();
        this.stock=data.getStock();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
