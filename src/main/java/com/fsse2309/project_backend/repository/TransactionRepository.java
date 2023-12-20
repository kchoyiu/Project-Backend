package com.fsse2309.project_backend.repository;



import com.fsse2309.project_backend.data.transaction.entity.TransactionEntity;
import jakarta.transaction.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<TransactionEntity,Integer> {
    Optional<TransactionEntity>findByTidAndBuyer_Uid(int tid, int uid);
}
