package com.fsse2309.project_backend.service.impl;

import com.fsse2309.project_backend.data.transaction.TransactionStatus;
import com.fsse2309.project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2309.project_backend.data.user.domainObject.FirebaseUserData;
import com.fsse2309.project_backend.data.user.entity.UserEntity;
import com.fsse2309.project_backend.exception.InvalidTransactionException;
import com.fsse2309.project_backend.exception.TransactionNotFound;
import com.fsse2309.project_backend.repository.TransactionRepository;
import com.fsse2309.project_backend.service.*;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private UserService userService;
    private TransactionProductService transactionProductService;
    private CartItemService cartItemService;
    private ProductService productService;
    private TransactionRepository transactionRepository;
    public TransactionServiceImpl(CartItemService cartItemService,ProductService productService,UserService userService,TransactionRepository transactionRepository,TransactionProductService transactionProductService){
        this.userService=userService;
        this.transactionRepository=transactionRepository;
        this.transactionProductService=transactionProductService;
        this.productService=productService;
        this.cartItemService=cartItemService;
    }
@Override
    public TransactionDetailData prepareTransaction(FirebaseUserData firebaseUserData){
        TransactionEntity transactionEntity = transactionRepository.save(
                new TransactionEntity(getUserEntity(firebaseUserData))
        );

        List<TransactionProductEntity>transactionProductEntityList = transactionProductService.createEntityList(transactionEntity);
        transactionEntity.setTransactionProductEntityList(transactionProductEntityList);

        transactionEntity.setTotal(getTotal(transactionProductEntityList));
        transactionEntity=transactionRepository.save(transactionEntity);

        return new TransactionDetailData(transactionEntity);
    }
    @Override
    public TransactionDetailData getTransactionByTid(FirebaseUserData firebaseUserData, int tid){
       UserEntity userEntity = getUserEntity(firebaseUserData);

       return new TransactionDetailData(getEntityByTidAndUid(tid,userEntity.getUid()));
    }
    @Override
    public boolean processTransaction(FirebaseUserData firebaseUserData, int tid){
        TransactionEntity transactionEntity=getEntityByTidAndUid(tid,getUserEntity(firebaseUserData).getUid());
        if (transactionEntity.getStatus()!= TransactionStatus.PREPARE){
            throw new InvalidTransactionException();
        }



        transactionEntity.setStatus(TransactionStatus.PROCESSING);
        transactionRepository.save(transactionEntity);

        return true;
    }
    @Override
    public TransactionDetailData finishTransaction (FirebaseUserData firebaseUserData,int tid){
        UserEntity userEntity = getUserEntity(firebaseUserData);
        TransactionEntity transactionEntity=getEntityByTidAndUid(tid,getUserEntity(firebaseUserData).getUid());

        if(transactionEntity.getStatus()!=TransactionStatus.PROCESSING){
            throw new InvalidTransactionException();
        }
        for (TransactionProductEntity transactionProductEntity : transactionEntity.getTransactionProductEntityList()){
            productService.deductStock(transactionProductEntity.getPid(),transactionProductEntity.getQuantity());
        }
        cartItemService.emptyUserCart(userEntity);

        transactionEntity.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transactionEntity);
        return new TransactionDetailData(transactionEntity);
    }
    public UserEntity getUserEntity(FirebaseUserData data){
        return userService.getEntityByFirebaseUserData(data);
    }
    public TransactionEntity getEntityByTid(int tid){
        return transactionRepository.findById(tid).orElseThrow(TransactionNotFound::new);
    }

    public BigDecimal getTotal(List<TransactionProductEntity> transactionProductEntityList){
        BigDecimal total =BigDecimal.ZERO;
        for(TransactionProductEntity transactionProductEntity: transactionProductEntityList){
            BigDecimal price=transactionProductEntity.getPrice();
            Integer quantity = transactionProductEntity.getQuantity();
            total = total.add(price.multiply(BigDecimal.valueOf((quantity))));
        }
        return total;
    }
    public TransactionEntity getEntityByTidAndUid(int tid, int uid){
        return transactionRepository.findByTidAndBuyer_Uid(tid, uid).orElseThrow(TransactionNotFound::new);
    }
}
