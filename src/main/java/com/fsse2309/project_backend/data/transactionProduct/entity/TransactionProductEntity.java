package com.fsse2309.project_backend.data.transactionProduct.entity;

import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.product.entity.ProductEntity;
import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tpid;
    @ManyToOne
    @JoinColumn(name = "tid",nullable = false)
    private TransactionEntity transaction;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "quantity",nullable = false)
    private int quantity;

    public TransactionProductEntity() {
    }
    public TransactionProductEntity(TransactionEntity transaction, CartItemEntity cartItemEntity){
        this.transaction=transaction;
        this.pid=cartItemEntity.getProduct().getPid();
        this.name=cartItemEntity.getProduct().getName();
        this.description=cartItemEntity.getProduct().getDescription();
        this.imageUrl=cartItemEntity.getProduct().getImageUrl();
        this.price=cartItemEntity.getProduct().getPrice();
        this.stock=cartItemEntity.getProduct().getStock();
        this.quantity=cartItemEntity.getQuantity();
    }

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
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
