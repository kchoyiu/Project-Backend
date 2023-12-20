package com.fsse2309.project_backend.service.impl;

import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2309.project_backend.repository.TransactionProductRepository;
import com.fsse2309.project_backend.service.CartItemService;
import com.fsse2309.project_backend.service.TransactionProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private CartItemService cartItemService;
    private TransactionProductRepository transactionProductRepository;
    public TransactionProductServiceImpl (CartItemService cartItemService,TransactionProductRepository transactionProductRepository){
        this.cartItemService=cartItemService;
        this.transactionProductRepository=transactionProductRepository;
    }
    @Override
    public List<TransactionProductEntity> createEntityList(TransactionEntity transactionEntity){
        List<CartItemEntity>cartItemEntityList=cartItemService.getEntityListByUser(transactionEntity.getBuyer());
        List<TransactionProductEntity> transactionProductEntityList = new ArrayList<>();

        for (CartItemEntity cartItemEntity: cartItemEntityList){
            TransactionProductEntity transactionProductEntity=new TransactionProductEntity(transactionEntity,cartItemEntity);
            transactionProductRepository.save(transactionProductEntity);
            transactionProductEntityList.add(transactionProductEntity);
        }
        return transactionProductEntityList;
    }
}
