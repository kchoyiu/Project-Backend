package com.fsse2309.project_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2309.project_backend.data.product.domainObject.Product;

import java.math.BigDecimal;

public class ListAllProductResponseDto {
    private int pid;
    private String name;
    @JsonProperty("image_url")
    private String imageUrl;
    private BigDecimal price;
    @JsonProperty("has_stock")
    private boolean hasStock;

    public ListAllProductResponseDto(Product product) {
        this.pid = product.getPid();
        this.name = product.getName();
        this.imageUrl = product.getImageUrl();
        this.price = product.getPrice();
        this.hasStock = product.getStock()>0;
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

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }
}
