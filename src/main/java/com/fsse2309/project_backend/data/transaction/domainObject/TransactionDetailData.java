package com.fsse2309.project_backend.data.transaction.domainObject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fsse2309.project_backend.data.transaction.TransactionStatus;
import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2309.project_backend.data.transactionProduct.domainObject.TransactionProductDetailData;
import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2309.project_backend.data.user.domainObject.UserDetailData;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {
    private int tid;
    private UserDetailData buyer;

    private LocalDateTime datetime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductDetailData>transactionProductList=new ArrayList<>();

    public TransactionDetailData(TransactionEntity entity) {
        this.tid=entity.getTid();
        this.buyer=new UserDetailData(entity.getBuyer());
        this.datetime=entity.getDatetime();
        this.total=entity.getTotal();
        this.status=entity.getStatus();
        setTransactionProductList(entity);
    }

    public List<TransactionProductDetailData> getTransactionProductList() {
        return transactionProductList;
    }

    public void setTransactionProductList(List<TransactionProductDetailData> transactionProductList) {
        this.transactionProductList = transactionProductList;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserDetailData getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDetailData buyer) {
        this.buyer = buyer;
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

    public List<TransactionProductDetailData> getTransactionProductDetailDataList() {
        return transactionProductList;
    }

    public void setTransactionProductDetailDataList(List<TransactionProductDetailData> transactionProductDetailDataList) {
        this.transactionProductList = transactionProductDetailDataList;
    }
    public void setTransactionProductList(TransactionEntity transactionEntity){
      for (TransactionProductEntity transactionProductEntity : transactionEntity.getTransactionProductEntityList()){
          this.transactionProductList.add(new TransactionProductDetailData(transactionProductEntity));
      }
    }
}
