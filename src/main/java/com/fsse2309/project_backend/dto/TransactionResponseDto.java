package com.fsse2309.project_backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2309.project_backend.data.cartitem.domainObject.CartItemDetailData;
import com.fsse2309.project_backend.data.transaction.TransactionStatus;
import com.fsse2309.project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2309.project_backend.data.transactionProduct.domainObject.TransactionProductDetailData;
import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2309.project_backend.data.user.domainObject.UserDetailData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseDto {
    private int tid;
    @JsonProperty("buyer_uid")
    private int buyerUid;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datetime;
    private TransactionStatus status;
    private BigDecimal total;
    @JsonProperty("item")
    private List<TransactionProductResponseDto>transactionProductList=new ArrayList<>();

    public TransactionResponseDto(TransactionDetailData data) {
        this.tid=data.getTid();
        this.buyerUid=data.getBuyer().getUid();
        this.datetime=data.getDatetime();
        this.total=data.getTotal();
        this.status=data.getStatus();
        setTransactionProductList(data);
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(int buyerUid) {
        this.buyerUid = buyerUid;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductResponseDto> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProductResponseDto> transactionProductList) {
        this.transactionProductList = transactionProductList;
    }
    public void setTransactionProductList(TransactionDetailData data) {
        for (TransactionProductDetailData transactionProductDetailData : data.getTransactionProductDetailDataList()){
            this.transactionProductList.add(new TransactionProductResponseDto(transactionProductDetailData));
        }
    }
}
