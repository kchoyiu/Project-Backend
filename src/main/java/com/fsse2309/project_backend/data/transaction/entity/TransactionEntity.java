package com.fsse2309.project_backend.data.transaction.entity;

import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.transaction.TransactionStatus;
import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import com.fsse2309.project_backend.service.TransactionService;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid", nullable = false)
    private int tid;
    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity buyer;
    @Column(name = "datetime",nullable = false)
    private LocalDateTime datetime;
    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private TransactionStatus status;
    @Column(name = "total",nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity>transactionProductEntityList=new ArrayList<>();

    public TransactionEntity() {
    }
    public TransactionEntity(UserEntity user){
        this.buyer=user;
        this.datetime=LocalDateTime.now();
        this.status=TransactionStatus.PREPARE;
        this.total=BigDecimal.ZERO;
    }

    public List<TransactionProductEntity> getTransactionProductEntityList() {
        return transactionProductEntityList;
    }

    public void setTransactionProductEntityList(List<TransactionProductEntity> transactionProductEntityList) {
        this.transactionProductEntityList = transactionProductEntityList;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public UserEntity getBuyer() {
        return buyer;
    }

    public void setBuyer(UserEntity buyer) {
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
}






