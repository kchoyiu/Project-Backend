package com.fsse2309.project_backend.data.product.domainObject;


import com.fsse2309.project_backend.dto.ProductRequestDto;

import java.math.BigDecimal;

public class CreateProduct {
    private int pid;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int stock;

    public CreateProduct(ProductRequestDto dto) {
        this.pid = dto.getPid();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.imageUrl = dto.getImageUrl();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
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
