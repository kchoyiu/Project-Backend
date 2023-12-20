package com.fsse2309.project_backend.data.cartitem.entity;

import com.fsse2309.project_backend.data.cartitem.domainObject.CartItemDetailData;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cartitem")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid",nullable = false)
    private int cid;
    @ManyToOne
    @JoinColumn(name = "uid",nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "pid",nullable = false)
    private ProductEntity product;
    @Column(name = "quantity",nullable = false)
    private int quantity;


    public CartItemEntity() {
    }

    public CartItemEntity(ProductEntity product, UserEntity user, int quantity) {
        this.product = product;
        this.user= user;
        this.quantity = quantity;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
