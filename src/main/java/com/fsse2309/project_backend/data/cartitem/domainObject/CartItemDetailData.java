package com.fsse2309.project_backend.data.cartitem.domainObject;


import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.product.domainObject.Product;
import com.fsse2309.project_backend.data.user.domainObject.UserDetailData;

public class CartItemDetailData {
    private int cid;
    private UserDetailData user;
    private Product product;
    private int quantity;

    public CartItemDetailData(CartItemEntity entity) {
        this.cid = entity.getCid();
        this.user = new UserDetailData(entity.getUser());
        this.product = new Product(entity.getProduct());
        this.quantity = entity.getQuantity();
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
