package com.fsse2309.project_backend.dto;

import com.fsse2309.project_backend.data.transactionProduct.domainObject.TransactionProductDetailData;

import java.math.BigDecimal;

public class TransactionProductResponseDto {
    private int tpid;
    private ProductResponseDto product;
    private int quantity;
    private BigDecimal subtotal;

    public TransactionProductResponseDto(TransactionProductDetailData data) {
        this.tpid=data.getTpid();
        this.product=new ProductResponseDto(data);
        this.quantity=data.getQuantity();
        setSubtotal();
    }

    public int getTpid() {
        return tpid;
    }

    public void setTpid(int tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public void setSubtotal(){
        this.subtotal=this.product.getPrice().multiply(new BigDecimal(this.quantity));
    }
}
