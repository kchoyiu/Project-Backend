package com.fsse2309.project_backend.repository;

import com.fsse2309.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity,Integer> {
}
