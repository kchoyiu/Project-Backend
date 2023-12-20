package com.fsse2309.project_backend.service;

import com.fsse2309.project_backend.data.cartitem.entity.CartItemEntity;
import com.fsse2309.project_backend.data.transaction.domainObject.TransactionDetailData;
import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2309.project_backend.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface TransactionService {

    TransactionDetailData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionDetailData getTransactionByTid(FirebaseUserData firebaseUserData, int tid);

    boolean processTransaction(FirebaseUserData firebaseUserData, int tid);

    TransactionDetailData finishTransaction(FirebaseUserData firebaseUserData, int tid);
}
